package com.dpworld.automation.e2e;

import com.dpworld.automation.browser.BrowserManager;
import com.dpworld.automation.client.login.LoginClient;
import com.dpworld.automation.client.fraudRisk.FraudRiskClient;
import com.dpworld.automation.listeners.TestListener;
import com.dpworld.automation.pages.BasePage;
import com.dpworld.automation.pages.BasePageFactory;
import com.dpworld.automation.pages.login.LoginPage;
import com.dpworld.automation.pages.fraudRisk.FraudRiskPage;
import com.microsoft.playwright.*;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;

@Listeners(TestListener.class)
public class BaseE2ETest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    public LoginPage loginPage;
    public LoginClient loginClient;

    public FraudRiskPage fraudRiskPage;
    public FraudRiskClient fraudRiskClient;


    protected <T extends BasePage> T createInstance(Class<T> basePage) {
        return BasePageFactory.createInstance(page, basePage);
    }

    public void initialization() {
        loginPage = createInstance(LoginPage.class);
        loginClient = new LoginClient(loginPage);
        fraudRiskPage = createInstance(FraudRiskPage.class);
        fraudRiskClient = new FraudRiskClient(fraudRiskPage);

    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        browser = BrowserManager.browser();
        playwright = BrowserManager.getPlaywright();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width, height));
        page = browserContext.newPage();
        initialization();
    }

    @BeforeMethod(alwaysRun = true)
    public void createBrowserContextAndPage() {
        if (browserContext == null || page == null || browser == null) {
            browser = BrowserManager.browser();
            browserContext = browser.newContext();
            page = browserContext.newPage();
            initialization();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void captureScreenshotOnFailureAndCloseBrowserContext(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        if (ITestResult.FAILURE == result.getStatus()) {
            loginPage.captureScreenshot(method.getMethodName());
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        browserContext.close();
        BrowserManager.removeBrowser();
        BrowserManager.removePlaywright();
    }

    public void clearCookies() {
        browserContext.clearCookies();
    }
}
