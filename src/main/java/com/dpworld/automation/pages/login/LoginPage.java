package com.dpworld.automation.pages.login;

import com.dpworld.automation.pages.BasePage;
import com.dpworld.automation.utils.LogUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.util.List;

import static com.dpworld.automation.config.ConfigurationManager.configuration;

/**
 * This class captures the relevant UI components and functionalities of the login page.
 *
 * @author Shuvendu
 */
public class LoginPage extends BasePage {
    private static final String clickOnExploreUsTodayBtn = "(//img[@class='css-1mtygnw-loginButton'])[1]";
    private static final String userProfileIcon = "//button[@aria-label='user-settings']/span[1]";
    private static final String logOutBtn = "//button[normalize-space()='Logout']";
    private static final String emailIdText = "[name='loginfmt']";
    private static final String nextBtn = "//input[@type='submit']";
    private static final String passwordText = "[type='password']";
    private static final String clickOnSignInBtn = "//input[@value='Sign in']";

    private static final String getSignInPageTitle = "//div[text()='Sign in']";
    private static final String staySignInBtnIcon = "//*[text()='Stay signed in?']";
    private static final String clickOnStaySignInBtn = "//input[@type='submit']";

    public LoginPage goTo() {
        LogUtils.info("Navigating to Base URL: " + configuration().baseUrl());
        try {
            page.navigate(configuration().baseUrl());
            page.waitForLoadState(LoadState.NETWORKIDLE);
        } catch (Exception e) {
            LogUtils.error("Navigation to Base URL timed out, trying again...");
            page.reload();
            page.navigate(configuration().baseUrl(), new Page.NavigateOptions().setTimeout(100000));
        }
        return this;
    }

    public LoginPage enterUsername(String username) {
        LogUtils.info("Enter the UserName: " + username);
        page.type(emailIdText, username);
        return this;
    }

    public Locator clickOnNextBtn() {
        LogUtils.info("Getting Next Btn");
        return page.locator(nextBtn);

    }

    public Locator getpasswordTextBoxLocator() {
        LogUtils.info("clearing the password");
        return page.locator(passwordText);
    }

    public Locator clickOnSignInBtn() {
        LogUtils.info("Getting Next Btn");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return page.locator(clickOnSignInBtn);
    }

    public void clickExplore() {
        LogUtils.info("Click on Explore us Today");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.click(clickOnExploreUsTodayBtn);
    }

    public List<Page> windowSwitch(){
        LogUtils.info("get number of window");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForLoadState(LoadState.LOAD);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return page.context().pages();
    }

    public Locator getUserDisplayName() {
        LogUtils.info("Getting User display Name");
        return page.locator(userProfileIcon);
    }

    public Locator getLogOutBtn() {
        LogUtils.info("Getting Logout Btn");
        return page.locator(logOutBtn);
    }

    public Locator getExploreUsBtn() {
        LogUtils.info("Getting User Name Field");
        return page.locator(clickOnExploreUsTodayBtn);
    }

    public LoginPage enterPassword(String password) {
        LogUtils.info("Enter the Password: " + password);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.type(passwordText, password);
        return this;
    }

    public  Locator clickONStaySignInBtn(){
        LogUtils.info("Getting User Name Field");
        return page.locator(clickOnStaySignInBtn);
    }

    public void navigateBack(){
        LogUtils.info("Back to previous page");
        page.goBack();
    }
    public Boolean isExploreUsBtnVisible() {
        LogUtils.info("Check if explore us btn is visible");
        page.navigate(configuration().baseUrl());
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return page.locator(clickOnExploreUsTodayBtn).isVisible();
    }

    public Boolean isSignInPageTitleVisible() {
        LogUtils.info("Check if signIn btn is visible");
        page.navigate(configuration().baseUrl());
        page.waitForLoadState(LoadState.NETWORKIDLE);
        return page.locator(getSignInPageTitle).isVisible();
    }

    public Boolean isUserProfileIconVisible() {
        LogUtils.info("Check if User Profile Icon is visible in logout page");
        page.navigate(configuration().baseUrl());
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return page.locator(userProfileIcon).isVisible();
    }

    public Boolean isStaySignInBtnVisible() {
        LogUtils.info("Check if User Profile Icon is visible in logout page");
        //page.navigate(configuration().baseUrl());
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForLoadState(LoadState.LOAD);
        return page.locator(staySignInBtnIcon).isVisible();
    }

}
