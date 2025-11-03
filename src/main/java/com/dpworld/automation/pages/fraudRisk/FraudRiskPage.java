package com.dpworld.automation.pages.fraudRisk;

import com.dpworld.automation.pages.BasePage;
import com.dpworld.automation.utils.LogUtils;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class FraudRiskPage extends BasePage {

    private static final String getFraudRiskBtn = "//div[text()='Fraud Risk Services']";
    private static final String getHomeLogo = "//div[@id='homeLogo']";
    private static final String getFraudRiskTitle = "//span[text()=' Fraud Risk Services']";
    private static final String fraudRiskTableColumnHeaders = "(//div[@id='FRS - Monthly Incident by Region/Division'])//thead//th";
    private static final String fraudRiskTableRowData = "(//div[@id='FRS - Monthly Incident by Region/Division']//tbody//tr)";
    private static final String fraudRiskTableRowCellsData = "(//div[@id='FRS - Monthly Incident by Region/Division']//tbody//tr)[%d]//td[%d]";
    private static final String getExportToCSVBtnForMonthlyIncident = "(//div[@id='FRS - Monthly Incident by Region/Division'])/div[2]/div/div/div[text()='Export to CSV']";
    private static final String fraudRiskTableColumnHeadersValue = "(//div[@id='%s'])//thead//th[%d]";
    private static final String getFraudRiskSortingBtn = "//th[text()='%s']/span";
    private static final String getZoomInOutBtn = "(//div[@id='FRS - Monthly Incident by Region/Division'])/div[1]/div[1]/div[1]//following-sibling::div/div/button[4]/span/img";
    private static final String getDownLoadBtn = "(//div[@id='FRS - Monthly Incident by Region/Division'])/div[1]/div[1]/div[1]//following-sibling::div/div/button[3]/span/img";
    private static final String getExportToCSVBtnForIncidentClosed = "(//div[@id='FRS - Incidents Closed'])/div[2]/div/div/div[text()='Export to CSV']";

    private static final String incidentClosedTableColumnHeaders = "(//div[@id='FRS - Incidents Closed'])//thead//th";
    private static final String incidentClosedTableRowData = "(//div[@id='FRS - Incidents Closed']//tbody//tr)";
    private static final String incidentClosedTableRowCellsData = "(//div[@id='FRS - Incidents Closed']//tbody//tr)[%d]//td[%d]";
    private static final String getZoomInOutIncidentClosedBtn = "(//div[@id='FRS - Incidents Closed'])/div[1]/div[1]/div[1]//following-sibling::div/div/button[4]/span/img";
    private static final String getIncidentClosedDownLoadBtn = "(//div[@id='FRS - Incidents Closed'])/div[1]/div[1]/div[1]//following-sibling::div/div/button[3]/span/img";

    private static final String getExportToCSVBtnForIncidentReceived = "(//div[@id='FRS - Incidents Received'])/div[2]/div/div/div[text()='Export to CSV']";
    private static final String incidentReceivedTableColumnHeaders = "(//div[@id='FRS - Incidents Received'])//thead//th";
    private static final String incidentReceivedTableRowData = "(//div[@id='FRS - Incidents Received']//tbody//tr)";
    private static final String incidentReceivedTableRowCellsData = "(//div[@id='FRS - Incidents Received']//tbody//tr)[%d]//td[%d]";
    private static final String getZoomInOutIncidentReceivedBtn = "(//div[@id='FRS - Incidents Received'])/div[1]/div[1]/div[1]//following-sibling::div/div/button[4]/span/img";
    private static final String getIncidentReceivedDownLoadBtn = "(//div[@id='FRS - Incidents Received'])/div[1]/div[1]/div[1]//following-sibling::div/div/button[3]/span/img";


    public void moveToFraudRiskBtn() {
        LogUtils.info("move to Fraud Btn");
        page.locator(getHomeLogo).hover();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public Locator goToFraudRiskPage() {
        LogUtils.info("Go to Fraud Btn");
        return page.locator(getFraudRiskBtn);
    }

    public Locator getExportToCSVButtonForMonthlyIncident() {
        LogUtils.info("Get Export to CSV button for Monthly Incident table");
        return page.locator(getExportToCSVBtnForMonthlyIncident);
    }

    public Download saveDownloadedFile() {
        return page.waitForDownload(() ->
        {
            page.click(getExportToCSVBtnForMonthlyIncident);
        });
    }

    public Boolean isFraudRiskPageTitleVisible() {
        LogUtils.info("Check if fraud risk service title is visible");
        return page.locator(getFraudRiskTitle).isVisible();
    }

    public Boolean isFraudRiskSortingButtonVisible(String headerName) {
        LogUtils.info("Check if fraud risk service sorting Btn is visible");
        return page.locator(String.format(getFraudRiskSortingBtn, headerName)).isVisible();

    }

    public Locator getLocatorForMonthlyIncidentHeader(String headerName, int index) {
        LogUtils.info("Getting locators for header sortinh");
        return page.locator(String.format(fraudRiskTableColumnHeadersValue, headerName, index));
    }

    public Locator getMonthlyIncidentZoomBtn() {
        LogUtils.info("Getting locators for zoom btn");
        return page.locator(getZoomInOutBtn);
    }

    public Boolean isZoomOutSuccessFul() {
        LogUtils.info("Check zoom in and out");
        return page.locator(getFraudRiskTitle).isVisible();

    }

    public Download saveDownloadedImage() {
        return page.waitForDownload(() ->
        {
            page.click(getDownLoadBtn);
        });
    }

    public Download saveDownloadedFileForFRSIncidentReceived() {
        return page.waitForDownload(() ->
        {
            page.click(getExportToCSVBtnForIncidentReceived);
        });
    }

    public Locator getIncidentClosedZoomBtn() {
        LogUtils.info("Getting locators for zoom btn");
        return page.locator(getZoomInOutIncidentClosedBtn);
    }

    public Boolean isIncidentClosedZoomOutSuccessFul() {
        LogUtils.info("Check zoom in and out");
        return page.locator(getFraudRiskTitle).isVisible();

    }

    public Download saveIncidentClosedDownloadedImage() {
        return page.waitForDownload(() ->
        {
            page.click(getIncidentClosedDownLoadBtn);
        });
    }

    public Download saveDownloadedFileForFRSIncidentClose() {
        return page.waitForDownload(() ->
        {
            page.click(getExportToCSVBtnForIncidentClosed);
        });
    }

    public Locator getIncidentReceivedZoomBtn() {
        LogUtils.info("Getting locators for zoom btn");
        return page.locator(getZoomInOutIncidentReceivedBtn);
    }

    public Boolean isIncidentReceivedZoomOutSuccessFul() {
        LogUtils.info("Check zoom in and out");
        return page.locator(getFraudRiskTitle).isVisible();

    }

    public Download saveIncidentReceivedDownloadedImage() {
        return page.waitForDownload(() ->
        {
            page.click(getIncidentReceivedDownLoadBtn);
        });
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getFRSMonthlyIncidentTableData() {
        return getTableData(fraudRiskTableRowData, fraudRiskTableColumnHeaders, fraudRiskTableRowCellsData);
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getIncidentClosedTableData() {
        return getTableData(incidentClosedTableRowData, incidentClosedTableColumnHeaders, incidentClosedTableRowCellsData);
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getIncidentReceiveTableData() {
        return getTableData(incidentReceivedTableRowData, incidentReceivedTableColumnHeaders, incidentReceivedTableRowCellsData);
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getTableData(String fraudRiskTableRowData, String fraudRiskTableColumnHeaders, String fraudRiskTableRowCellsData) {
        List<Locator> tableList = page.locator(fraudRiskTableRowData).all();
        LinkedHashMap<String, LinkedHashMap<String, String>> tableMap = new LinkedHashMap<>();
        List<Locator> tableHeader = page.locator(fraudRiskTableColumnHeaders).all();
        for (int td = 0; td < tableList.size(); td++) {
            LinkedHashMap<String, String> innerMap = new LinkedHashMap<>();
            for (int head = 1; head < tableHeader.size(); head++) {
                String data = page.locator(String.format(fraudRiskTableRowCellsData, td + 1, head + 1)).innerText().trim();
                String header = tableHeader.get(head).innerText().trim();
                innerMap.put(header, data);
            }
            String primaryKey = page.locator(String.format(fraudRiskTableRowCellsData, td + 1, 1)).innerText();
            tableMap.put(primaryKey, innerMap);
            // LogUtils.info("Capture Inner data from FRS - Monthly Incident by Region/Division web page:- "+innerMap);
        }
        LogUtils.info("Capture table data from FRS - Monthly Incident by Region/Division web page:- " + tableMap);
        return tableMap;
    }


    public LinkedList<LinkedHashMap<String, String>> getFRSMonthlyIncidentTableDataList() {
        return getTableDataList(fraudRiskTableRowData, fraudRiskTableColumnHeaders, fraudRiskTableRowCellsData);
    }

    public LinkedList<LinkedHashMap<String, String>> getIncidentClosedTableDataList() {
        return getTableDataList(incidentClosedTableRowData, incidentClosedTableColumnHeaders, incidentClosedTableRowCellsData);
    }

    public LinkedList<LinkedHashMap<String, String>>  getIncidentReceivedTableDataList() {
        return getTableDataList(incidentReceivedTableRowData, incidentReceivedTableColumnHeaders, incidentReceivedTableRowCellsData);
    }


    public LinkedList<LinkedHashMap<String, String>> getTableDataList(String fraudRiskTableRowData, String fraudRiskTableColumnHeaders, String fraudRiskTableRowCellsData) {
        LinkedList<LinkedHashMap<String, String>> lst = new LinkedList<>();
        List<Locator> tableList = page.locator(fraudRiskTableRowData).all();
        List<Locator> tableHeader = page.locator(fraudRiskTableColumnHeaders).all();
        for (int td = 0; td < tableList.size(); td++) {
            for (int head = 0; head < tableHeader.size(); head++) {
                LinkedHashMap<String, String> innerMap = new LinkedHashMap<>();
                String data = page.locator(String.format(fraudRiskTableRowCellsData, td + 1, head + 1)).innerText().trim();
                String header = tableHeader.get(head).innerText().trim();
                innerMap.put(header, data);
                lst.add(innerMap);
            }
        }
        LogUtils.info("Capture table data from FRS - Monthly Incident by Region/Division web page:- " + lst);
        return lst;
    }


}
