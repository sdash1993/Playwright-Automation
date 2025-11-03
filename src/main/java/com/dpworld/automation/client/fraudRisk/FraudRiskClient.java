package com.dpworld.automation.client.fraudRisk;

import com.dpworld.automation.client.BaseClient;
import com.dpworld.automation.pages.fraudRisk.FraudRiskPage;
import com.dpworld.automation.utils.CommonUtils;
import com.dpworld.automation.utils.LogUtils;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.microsoft.playwright.Download;
import lombok.SneakyThrows;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class FraudRiskClient extends BaseClient {

    private FraudRiskPage fraudRiskPage;
    LinkedHashMap<String, LinkedHashMap<String, String>> frsMonthlyIncidentTableDataMap;
    LinkedHashMap<String, LinkedHashMap<String, String>> frsIncidentClosedTableDataMap;
    LinkedHashMap<String, LinkedHashMap<String, String>> frsIncidentReceivedTableDataMap;
    LinkedList<LinkedHashMap<String, String>> frsMonthlyIncidentTableDataList;
    LinkedList<LinkedHashMap<String, String>> incidentClosedTableDataList;
    LinkedList<LinkedHashMap<String, String>> incidentReceivedTableDataList;
    LinkedHashMap<String, LinkedHashMap<String, String>> csvMap;
    LinkedList<LinkedHashMap<String, String>> csvList;

    public FraudRiskClient(FraudRiskPage fraudRiskPage) {
        this.fraudRiskPage = fraudRiskPage;
    }

    public void navigateToFraudRisk() {
        fraudRiskPage.moveToFraudRiskBtn();
        fraudRiskPage.goToFraudRiskPage().click();
        fraudRiskPage.reloadPage();
        verifyFraudRiskServiceTitle();
        fraudRiskPage.attachScreenshot("Fraud risk page navigated");
    }

    public void clickOnDownLoadAndSave() {
        Download download = fraudRiskPage.saveDownloadedFile();
        download.saveAs(Paths.get("./Downloads/FraudMonthlyIncident.csv"));
    }

    public void verifyFraudRiskServiceTitle() {
        Assert.assertTrue(fraudRiskPage.isFraudRiskPageTitleVisible());
        LogUtils.success("User is navigated fraud risk page successfully.");
    }

    public void sortHeaderForForMonthlyIncident(String headerName, int index, String headerArrowName) {
        fraudRiskPage.getLocatorForMonthlyIncidentHeader(headerName, index).click();
        verifyFraudRiskSortingBtn(headerArrowName);
    }

    public void verifyFraudRiskSortingBtn(String headerName) {
        Assert.assertTrue(fraudRiskPage.isFraudRiskSortingButtonVisible(headerName));
        LogUtils.success("Sorting btn is visible successfully.");
    }

    public void clickOnZoomBtn() {
        fraudRiskPage.getMonthlyIncidentZoomBtn().click();
    }

    public void verifyZoomOut() {
        Assert.assertTrue(fraudRiskPage.isZoomOutSuccessFul());
    }

    public void verifyZoomIn() {
        Assert.assertFalse(fraudRiskPage.isZoomOutSuccessFul());
    }

    public void clickOnDownLoadBtn(String path) {
        clickOnDownLoadImageAndSave(path);
        LogUtils.success("Download and save img successfully.");
    }

    public void clickOnDownLoadImageAndSave(String path) {
        Download download = fraudRiskPage.saveDownloadedImage();
        download.saveAs(Paths.get(path));
    }

    public void clickOnIncidentClosedZoomBtn() {
        fraudRiskPage.getIncidentClosedZoomBtn().click();
    }

    public void verifyIncidentClosedZoomOut() {
        Assert.assertTrue(fraudRiskPage.isIncidentClosedZoomOutSuccessFul());
    }

    public void verifyIncidentClosedZoomIn() {
        Assert.assertFalse(fraudRiskPage.isIncidentClosedZoomOutSuccessFul());
    }

    public void clickOnIncidentReceivedZoomBtn() {
        fraudRiskPage.getIncidentReceivedZoomBtn().click();
    }

    public void verifyIncidentReceivedZoomOut() {
        Assert.assertTrue(fraudRiskPage.isIncidentReceivedZoomOutSuccessFul());
    }

    public void verifyIncidentReceivedZoomIn() {
        Assert.assertFalse(fraudRiskPage.isIncidentReceivedZoomOutSuccessFul());
    }


    public void clickOnExportAndSaveForFRSIncidentClose(String path) {
        Download download = fraudRiskPage.saveDownloadedFileForFRSIncidentClose();
        download.saveAs(Paths.get(path));
    }

    public void clickOnDownLoadImgBtn(String path) {
        clickOnIncidentClosedDownLoadImageAndSave(path);
        LogUtils.success("Download and save img successfully.");
    }

    public void clickOnIncidentClosedDownLoadImageAndSave(String path) {
        Download download = fraudRiskPage.saveIncidentClosedDownloadedImage();
        download.saveAs(Paths.get(path));
    }

    public void clickOnDownLoadIncidentReceiveImgBtn(String path) {
        clickOnIncidentReceivedDownLoadImageAndSave(path);
        LogUtils.success("Download and save img successfully.");
    }

    public void clickOnIncidentReceivedDownLoadImageAndSave(String path) {
        Download download = fraudRiskPage.saveIncidentReceivedDownloadedImage();
        download.saveAs(Paths.get(path));
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getAllFRSMonthlyIncidentTableData() {
        frsMonthlyIncidentTableDataMap = fraudRiskPage.getFRSMonthlyIncidentTableData();
        return frsMonthlyIncidentTableDataMap;
    }


    public LinkedHashMap<String, LinkedHashMap<String, String>> getAllIncidentClosedTableData() {
        frsIncidentClosedTableDataMap = fraudRiskPage.getIncidentClosedTableData();
        return frsIncidentClosedTableDataMap;
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getAllIncidentReceivedTableData() {
        frsIncidentReceivedTableDataMap = fraudRiskPage.getIncidentReceiveTableData();
        return frsIncidentReceivedTableDataMap;
    }

    public LinkedList<LinkedHashMap<String, String>> getFRSMonthlyIncidentTableDataList() {
        frsMonthlyIncidentTableDataList = fraudRiskPage.getFRSMonthlyIncidentTableDataList();
        return frsMonthlyIncidentTableDataList;
    }

    public LinkedList<LinkedHashMap<String, String>> getIncidentClosedTableDataList() {
        incidentClosedTableDataList = fraudRiskPage.getIncidentClosedTableDataList();
        return incidentClosedTableDataList;
    }

    public LinkedList<LinkedHashMap<String, String>> getIncidentReceivedTableDataList() {
        incidentReceivedTableDataList = fraudRiskPage.getIncidentReceivedTableDataList();
        return incidentReceivedTableDataList;
    }


    @SneakyThrows
    public LinkedHashMap<String, LinkedHashMap<String, String>> getCSVDataFromDownloadFile(String path) {
        csvMap = CommonUtils.csvRead(path);
        return csvMap;
    }

    public void verifyWebTableDataWithCsvData() {
        for (String Str : frsMonthlyIncidentTableDataMap.keySet()) {
            Assert.assertTrue(csvMap.containsKey(Str));
            Assert.assertEquals(csvMap.get(Str), frsMonthlyIncidentTableDataMap.get(Str));
        }
    }

    public void verifyWebTableDataWithIncidentCloseCsvData() {
        for (String Str : frsIncidentClosedTableDataMap.keySet()) {
            Assert.assertTrue(csvMap.containsKey(Str));
            Assert.assertEquals(csvMap.get(Str), frsIncidentClosedTableDataMap.get(Str));
        }
    }

    public void verifyWebTableDataWithIncidentReceivedCsvData() {
        for (String Str : frsIncidentReceivedTableDataMap.keySet()) {
            Assert.assertTrue(csvMap.containsKey(Str));
            Assert.assertEquals(csvMap.get(Str), frsIncidentReceivedTableDataMap.get(Str));
        }
    }

    @SneakyThrows
    public LinkedList<LinkedHashMap<String, String>> getCSVDataFromFRSMonthlyIncidentDownloadFileList(String path) {
        csvList = CommonUtils.csvReadList(path);
        return csvList;
    }

    public void verifyWebTableDataWithCsvDataList() {
        for (int i = 0; i < frsMonthlyIncidentTableDataList.size(); i++) {
            Assert.assertEquals(frsMonthlyIncidentTableDataList.get(i), csvList.get(i));
            Assert.assertEquals(frsMonthlyIncidentTableDataList.size(), csvList.size());
        }
    }

    public void verifyWebTableDataWithCsvDataIncidentCloseList() {
        for (int i = 0; i < incidentClosedTableDataList.size(); i++) {
            Assert.assertEquals(incidentClosedTableDataList.get(i), csvList.get(i));
            Assert.assertEquals(incidentClosedTableDataList.size(), csvList.size());
        }
    }

    public void verifyWebTableDataWithCsvDataIncidentReceiveList() {
        for (int i = 0; i < incidentReceivedTableDataList.size(); i++) {
            Assert.assertEquals(incidentReceivedTableDataList.get(i), csvList.get(i));
            Assert.assertEquals(incidentReceivedTableDataList.size(), csvList.size());
        }
    }

    public void clickOnExportAndSaveForFRSIncidentReceived(String path) {
        Download download = fraudRiskPage.saveDownloadedFileForFRSIncidentReceived();
        download.saveAs(Paths.get(path));
    }
}