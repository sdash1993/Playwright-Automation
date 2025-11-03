package com.dpworld.automation.e2e.login;

import com.dpworld.automation.data.login.LoginData;
import com.dpworld.automation.e2e.BaseE2ETest;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.dpworld.automation.utils.JsonDataProviderUtils.processJson;


/**
 * @author Shuvendu
 */
@Attributes(attributes = {
        @Attribute(key = "Author", value = "Shuvendu"),
        @Attribute(key = "Module", value = "Login")
})
public class LoginE2ETest extends BaseE2ETest {
    private static final String LOGIN_FILE = "login/login.json";

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData(Method testMethod) throws JsonProcessingException {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();
        return processJson(
                LoginData.class, LOGIN_FILE, testCaseId);
    }

    @Test(
            testName = "Login-1",
            dataProvider = "loginData",
            priority = 0,
            groups = {"regression"})
    @TestCaseId("Login-1")
    public void testCorrectUserNameAndPassword(LoginData loginDto) {
        loginClient.loginFunctionality(loginDto.getUserName(), loginDto.getPassword());
        loginClient.logOutFromCurrentUsr();

    }


}
