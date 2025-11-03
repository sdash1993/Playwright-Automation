package com.dpworld.automation.client.login;

import com.dpworld.automation.client.BaseClient;
import com.dpworld.automation.pages.login.LoginPage;
import com.dpworld.automation.utils.LogUtils;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.util.List;

/**
 * @author Shuvendu
 */
public class LoginClient extends BaseClient {

    private LoginPage loginPage;

    public LoginClient(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void loginFunctionality(String username, String password) {
        loginPage
                .goTo()
                .enterUsername(username)
                .clickOnNextBtn().click();
        loginPage.getpasswordTextBoxLocator().clear();
        loginPage.enterPassword(password)
                .clickOnSignInBtn().click();
        //verifyUserIsEnteredPassword();
        //loginPage.clickONStaySignInBtn().click();
        loginPage.navigateBack();
        loginPage.clickExplore();
        switchWindowIfPresent();
        verifyUserIsLoggedIn();
        loginPage.attachScreenshot("user is logged in");
        LogUtils.info("User is performing login Action");
    }

    public void logOutFromCurrentUsr() {
        loginPage.getUserDisplayName().click();
        loginPage.getLogOutBtn().click();
        loginPage.getExploreUsBtn().waitFor();
        Assert.assertTrue(loginPage.getExploreUsBtn().isVisible());
        LogUtils.success("Current User is logged out successfully.");
    }

    public void loginIfUserIsNotLoggedIn(String username, String password) {
        if (loginPage.isExploreUsBtnVisible()) {
            loginPage.clickExplore();
            loginPage.waitForPageLoad();
            verifyUserIsLoggedIn();
            LogUtils.success("New User is able to log in successfully.");
            loginPage.attachScreenshot("user is logged in");
            LogUtils.info("User is performing login Action");
        } else if (loginPage.isSignInPageTitleVisible()) {
            loginFunctionality(username, password);
            loginPage.waitForPageLoad();
        }
    }

    public void verifyUserIsLoggedIn() {
        Assert.assertTrue(loginPage.isUserProfileIconVisible());
        LogUtils.success("User is logged in successfully.");
    }

    public void switchWindowIfPresent(){
        List<Page> pages = loginPage.windowSwitch();
        for (Page page:pages){
            String url = page.url();
            if (url.contains("staging-argus.dpworld.com/#id_token=")){
                page.close();
            }
        }
    }

    public void verifyUserIsEnteredPassword() {
        Assert.assertTrue(loginPage.isStaySignInBtnVisible());
        LogUtils.success("Stay sign in btn visible successfully.");
    }

}
