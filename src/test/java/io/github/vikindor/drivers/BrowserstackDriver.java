package io.github.vikindor.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.AppiumDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import io.github.vikindor.configs.BrowserstackConfig;

import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        BrowserstackConfig cfg = ConfigFactory.create(BrowserstackConfig.class);

        MutableCapabilities caps = new MutableCapabilities();

        // W3C required
        caps.setCapability("platformName", cfg.platformName());

        // Appium capabilities (must be prefixed)
        caps.setCapability("appium:automationName", cfg.automationName());
        caps.setCapability("appium:deviceName", cfg.deviceName());
        caps.setCapability("appium:platformVersion", cfg.osVersion());
        caps.setCapability("appium:app", cfg.app());

        // BrowserStack options
        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", cfg.userName());
        bstackOptions.setCapability("accessKey", cfg.accessKey());
        bstackOptions.setCapability("projectName", cfg.projectName());
        bstackOptions.setCapability("buildName", cfg.buildName());

        String sessionName = System.getProperty("bstack.sessionName");
        if (sessionName == null || sessionName.isEmpty()) {
            sessionName = cfg.sessionName();
        }
        bstackOptions.setCapability("sessionName", sessionName);

        // duplicate device info for BrowserStack
        bstackOptions.setCapability("deviceName", cfg.deviceName());
        bstackOptions.setCapability("osVersion", cfg.osVersion());

        caps.setCapability("bstack:options", bstackOptions);

        try {
            URL url = new URL("https://hub-cloud.browserstack.com/wd/hub");
            return new AppiumDriver(url, caps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
