package io.github.vikindor.configs;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:${device}.properties"})
public interface BrowserstackConfig extends Config {

    @Key("platformName")
    String platformName();

    @Key("automationName")
    String automationName();

    @Key("deviceName")
    String deviceName();

    @Key("osVersion")
    String osVersion();

    @Key("app")
    String app();

    @Key("projectName")
    String projectName();

    @Key("buildName")
    String buildName();

    @Key("sessionName")
    String sessionName();

    @Key("userName")
    String userName();

    @Key("accessKey")
    String accessKey();
}
