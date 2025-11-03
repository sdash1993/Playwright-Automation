package com.dpworld.automation.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

/**
 * Mapping interface for the global parameters contained within config.properties file.
 *
 * @author Aniket
 */
@LoadPolicy(Config.LoadType.MERGE)
@Sources({"system:properties",
        "file:configs/config.${env}.properties",
        "file:configs/config.properties"})
public interface Configuration extends Config {
    /**
     * @return a string containing the browser name
     */
    String browser();

    /**
     * @return a string containing the base url of the AUT
     */
    @Key("base.url")
    String baseUrl();

    @Key("npm.base.url")
    String npmBaseUrl();

    /**
     * @return a boolean containing the choice whether the browser will run in headless mode
     */
    Boolean headless();

    /**
     * @return an integer containing the slow motion value
     */
    @Key("slow.motion")
    int slowMotion();

    /**
     * @return an integer containing the timeout value
     */
    @Key("timeout")
    int timeout();

    /**
     * @return a string containing the base path to store all the test data
     */
    @Key("base.test.data.path")
    String baseTestDataPath();

    /**
     * @return a string containing the base path to store all the test reports
     */
    @Key("base.report.path")
    String baseReportPath();

    /**
     * @return a string containing the base path to store all the failed test screenshots
     */
    @Key("base.screenshot.path")
    String baseScreenshotPath();

    @Key("admin.username")
    String adminUsername();

    @Key("admin.password")
    String adminPassword();

    @Key("hipl.tenantId")
    String hiplTenantId();
}
