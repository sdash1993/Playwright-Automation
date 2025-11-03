package com.dpworld.automation.e2e.fraduRiskService;

import com.dpworld.automation.data.fraudRisk.FraudRiskData;
import com.dpworld.automation.e2e.BaseE2ETest;
import com.dpworld.automation.utils.CommonUtils;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.dpworld.automation.utils.JsonDataProviderUtils.processJson;

@Attributes(attributes = {@Attribute(key = "Module", value = "FraudRisk")})
public class FraudRiskE2ETest extends BaseE2ETest {
    private static final String FRAUD_RISK_DATA = "fraudRisk/fraudRisk.json";

    @DataProvider(name = "fraudRiskData")
    public static Object[][] getData(Method testMethod) throws JsonProcessingException {
        String testCaseId = testMethod.getAnnotation(Test.class).testName();
        return processJson(
                FraudRiskData.class, FRAUD_RISK_DATA, testCaseId);
    }

    @Test(
            testName = "FraudRisk-1",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 1)
    @TestCaseId("FraudRisk-1")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void fraudMonthlyIncidentWithOutSortingTest(FraudRiskData fraudRiskData) {
        loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.clickOnDownLoadAndSave();
        fraudRiskClient.getAllFRSMonthlyIncidentTableData();
        fraudRiskClient.getCSVDataFromDownloadFile("./Downloads/FraudMonthlyIncident.csv");
        fraudRiskClient.verifyWebTableDataWithCsvData();
    }

    @Test(
            testName = "FraudRisk-2",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 2)
    @TestCaseId("FraudRisk-2")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void fraudMonthlyIncidentSortingTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Monthly Incident by Region/Division", 1, "Division / Region");
        fraudRiskClient.clickOnDownLoadAndSave();
        fraudRiskClient.getFRSMonthlyIncidentTableDataList();
        fraudRiskClient.getCSVDataFromFRSMonthlyIncidentDownloadFileList("./Downloads/FraudMonthlyIncident.csv");
        fraudRiskClient.verifyWebTableDataWithCsvDataList();
    }

    @Test(
            testName = "FraudRisk-3",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 3)
    @TestCaseId("FraudRisk-3")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void fraudMonthlyIncidentHeaderSortingTest(FraudRiskData fraudRiskData) {
       // loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Monthly Incident by Region/Division", 1, "Division / Region");
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Monthly Incident by Region/Division", 2, "New Fraud Cases");
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Monthly Incident by Region/Division", 3, "Closed Fraud Cases");
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Monthly Incident by Region/Division", 4, "New Non-Fraud Cases");
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Monthly Incident by Region/Division", 5, "Closed Non-Fraud Cases");
    }

    @Test(
            testName = "FraudRisk-4",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 4)
    @TestCaseId("FraudRisk-4")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void fraudMonthlyIncidentZoomInAndOutTest(FraudRiskData fraudRiskData) {
       // loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.clickOnZoomBtn();
        //fraudRiskClient.verifyZoomIn();
        fraudRiskClient.clickOnZoomBtn();
        fraudRiskClient.verifyZoomOut();
    }

    @Test(
            testName = "FraudRisk-5",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 5)
    @TestCaseId("FraudRisk-5")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void fraudMonthlyIncidentDownLoadReportTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        CommonUtils.deleteAlreadyExistingFile("./Downloads/FraudMonthlyIncidentImg.png");
        fraudRiskClient.clickOnDownLoadBtn("./Downloads/FraudMonthlyIncidentImg.png");
        CommonUtils.ValidateFileContainsDownloadFiles("./Downloads/", "FraudMonthlyIncidentImg.png");

    }


    @Test(
            testName = "FraudRisk-6",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 6)
    @TestCaseId("FraudRisk-6")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentClosedWithOutSortingTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        CommonUtils.deleteAlreadyExistingFile("./Downloads/IncidentClosed.csv");
        fraudRiskClient.clickOnExportAndSaveForFRSIncidentClose("./Downloads/IncidentClosed.csv");
        CommonUtils.ValidateFileContainsDownloadFiles("./Downloads/", "IncidentClosed.csv");
        fraudRiskClient.getAllIncidentClosedTableData();
        fraudRiskClient.getCSVDataFromDownloadFile("./Downloads/IncidentClosed.csv");
        fraudRiskClient.verifyWebTableDataWithIncidentCloseCsvData();
    }

    @Test(
            testName = "FraudRisk-7",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 7)
    @TestCaseId("FraudRisk-7")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentSortingTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Incidents Closed", 1, "Closed Type");
        CommonUtils.deleteAlreadyExistingFile("./Downloads/IncidentClosedSorted.csv");
        fraudRiskClient.clickOnExportAndSaveForFRSIncidentClose("./Downloads/IncidentClosedSorted.csv");
        fraudRiskClient.getIncidentClosedTableDataList();
        fraudRiskClient.getCSVDataFromFRSMonthlyIncidentDownloadFileList("./Downloads/IncidentClosedSorted.csv");
        fraudRiskClient.verifyWebTableDataWithCsvDataIncidentCloseList();
    }

    @Test(
            testName = "FraudRisk-8",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 8)
    @TestCaseId("FraudRisk-8")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentClosedHeaderSortingTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Incidents Closed", 1, "Closed Type");
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Incidents Closed", 2, "No. of Cases");
    }

    @Test(
            testName = "FraudRisk-9",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 9)
    @TestCaseId("FraudRisk-9")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentClosedZoomInAndOutTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.clickOnIncidentClosedZoomBtn();
        //fraudRiskClient.verifyZoomIn();
        fraudRiskClient.clickOnIncidentClosedZoomBtn();
        fraudRiskClient.verifyIncidentClosedZoomOut();
    }


    @Test(
            testName = "FraudRisk-10",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 10)
    @TestCaseId("FraudRisk-10")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentClosedDownLoadReportTest(FraudRiskData fraudRiskData) {
       // loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        CommonUtils.deleteAlreadyExistingFile("./Downloads/FrsIncidentClosedImg.png");
        fraudRiskClient.clickOnDownLoadImgBtn("./Downloads/FrsIncidentClosedImg.png");
        CommonUtils.ValidateFileContainsDownloadFiles("./Downloads/", "FrsIncidentClosedImg.png");
    }

    @Test(
            testName = "FraudRisk-11",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 11)
    @TestCaseId("FraudRisk-11")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentReceivedWithOutSortingTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        CommonUtils.deleteAlreadyExistingFile("./Downloads/IncidentReceived.csv");
        fraudRiskClient.clickOnExportAndSaveForFRSIncidentReceived("./Downloads/IncidentReceived.csv");
        CommonUtils.ValidateFileContainsDownloadFiles("./Downloads/", "IncidentReceived.csv");
        fraudRiskClient.getAllIncidentReceivedTableData();
        fraudRiskClient.getCSVDataFromDownloadFile("./Downloads/IncidentReceived.csv");
        fraudRiskClient.verifyWebTableDataWithIncidentReceivedCsvData();
    }

    @Test(
            testName = "FraudRisk-12",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 12)
    @TestCaseId("FraudRisk-12")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentReceivedSortingTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Incidents Received", 1, "Incident Category");
        fraudRiskClient.sortHeaderForForMonthlyIncident("FRS - Incidents Received", 2, "No. of Cases");
        CommonUtils.deleteAlreadyExistingFile("./Downloads/IncidentReceivedSorted.csv");
        fraudRiskClient.clickOnExportAndSaveForFRSIncidentReceived("./Downloads/IncidentReceivedSorted.csv");
        fraudRiskClient.getIncidentReceivedTableDataList();
        fraudRiskClient.getCSVDataFromFRSMonthlyIncidentDownloadFileList("./Downloads/IncidentReceivedSorted.csv");
        fraudRiskClient.verifyWebTableDataWithCsvDataIncidentReceiveList();
    }

    @Test(
            testName = "FraudRisk-13",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 13)
    @TestCaseId("FraudRisk-13")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentReceivedZoomInAndOutTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        fraudRiskClient.clickOnIncidentReceivedZoomBtn();
        //fraudRiskClient.verifyIncidentReceivedZoomIn();
        fraudRiskClient.clickOnIncidentReceivedZoomBtn();
        fraudRiskClient.verifyIncidentReceivedZoomOut();
    }

    @Test(
            testName = "FraudRisk-14",
            dataProvider = "fraudRiskData",
            groups = {"first", "regression"}, priority = 14)
    @TestCaseId("FraudRisk-14")
    @Attributes(attributes = {@Attribute(key = "Author", value = "Shuvendu")})
    public void frsIncidentReceivedDownLoadReportTest(FraudRiskData fraudRiskData) {
        //loginClient.loginIfUserIsNotLoggedIn(fraudRiskData.getUserName(), fraudRiskData.getPassword());
        fraudRiskClient.navigateToFraudRisk();
        CommonUtils.deleteAlreadyExistingFile("./Downloads/FrsIncidentReceivedImg.png");
        fraudRiskClient.clickOnDownLoadIncidentReceiveImgBtn("./Downloads/FrsIncidentReceivedImg.png");
        CommonUtils.ValidateFileContainsDownloadFiles("./Downloads/", "FrsIncidentReceivedImg.png");
    }

}
