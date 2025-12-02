package io.github.vikindor.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.vikindor.drivers.BrowserstackDriver;
import io.github.vikindor.helpers.Attach;
import io.github.vikindor.screens.MainScreen;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    MainScreen mainScreen;

    @BeforeAll
    static void setupConfig() {
        Configuration.browser     = BrowserstackDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout     = 30000;
    }

    @BeforeEach
    void setUpTest(TestInfo testInfo) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String name = testInfo.getDisplayName();
        String method = testInfo.getTestMethod().map(m -> m.getName()).orElse(null);
        if (method != null && (name == null || name.isEmpty())) {
            name = method;
        }
        if (name != null && !name.isEmpty()) {
            System.setProperty("bstack.sessionName", name);
        }

        open();

        mainScreen = new MainScreen();
    }

    @AfterEach
    void addAttachments(TestInfo testInfo) {
        String methodName = testInfo.getTestMethod().map(Method::getName).get();
        String sessionID = Selenide.sessionId().toString();

        System.out.println(sessionID);

        Attach.screenshotAs("Screenshot_" + methodName);
        Attach.pageSource();

        closeWebDriver();

        Attach.addVideo(sessionID);
    }
}
