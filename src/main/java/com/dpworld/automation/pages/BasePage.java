package com.dpworld.automation.pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.dpworld.automation.reporting.ExtentReport;
import com.dpworld.automation.retry.RetryUtil;
import com.dpworld.automation.utils.CommonUtils;
import com.dpworld.automation.utils.LogUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.dpworld.automation.config.ConfigurationManager.configuration;

/**
 * @author Aniket
 */
public class BasePage {
    protected Page page;
    private static final String highlightedItem = "//li[contains(@class, 'result-selectable select2-highlighted')]";
    private static final String firstValueFromActiveDropdown = "[id='select2-drop'] >ul >li";
    private static final String dropdownLocator = "//label[@title='%s']/../div/a";
    private static final String ddsSearchField = "(//label[contains(@for,'search') and contains(text(),'%s')]/following-sibling::input)[last()]";


    public Locator getFirstValueFromActiveDropdown() {
        LogUtils.info("Getting Dropdown First Value Locator");
        return page.locator(firstValueFromActiveDropdown);
    }

    public Page getPageInstance(){
        return page;
    }

    public Locator getHighlightedItem() {
        LogUtils.info("Getting Highlight Item Locator");
        return page.locator(highlightedItem);
    }

    public void setAndConfigurePage(Page page) {
        this.page = page;
        page.setDefaultTimeout(configuration().timeout());
    }

    public void captureScreenshot(String fileName) {
        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(
                                Paths.get(configuration().baseScreenshotPath()
                                        + "/"
                                        + fileName
                                        + ".png"))
                        .setFullPage(true));
    }

    public void attachScreenshot(String description) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String timeStamp = dateFormat.format(date);
        String screenshotPath = System.getProperty("user.dir") + "/" + configuration().baseScreenshotPath() + "/" + timeStamp + ".png";

        // Create the directory if it does not exist
        File directory = new File(System.getProperty("user.dir") + "/" + configuration().baseScreenshotPath());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Path path = Paths.get(screenshotPath);
            Files.write(path, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Attach the screenshot to the Extent report with the specified description
        ExtentReport.getTest().info(description, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public void selectValueFromDropdown(Locator locator, String value) {
        if (CommonUtils.ifStringNotNullOrEmpty(value)) {
            if (CommonUtils.ifStringNotNullOrEmpty(value)) {
                locator.click();
                locator.type("");
                locator.type(value);
                page.waitForSelector(highlightedItem);
                page.click(firstValueFromActiveDropdown);
                page.waitForLoadState(LoadState.NETWORKIDLE);
                LogUtils.info("Selected " + value + " from dropdown");
            }
        }
    }

    public void pressKeyboardKeys(String keyName) {
        LogUtils.info("Pressing Key : " + keyName);
        page.keyboard().press(keyName);
    }

    public void selectValueFromDropdown2(String dropDownName, String value) {
        if (CommonUtils.ifStringNotNullOrEmpty(value)) {
            RetryUtil.retryAction(() -> {
                page.locator(String.format(dropdownLocator, dropDownName)).first().click();
                page.locator(String.format(ddsSearchField, dropDownName)).first().fill(value);
                page.waitForSelector(highlightedItem);
                page.click(firstValueFromActiveDropdown);
                page.waitForLoadState(LoadState.DOMCONTENTLOADED);
                LogUtils.info("Selected " + value + " from dropdown : " + dropDownName);
                Assert.assertTrue(page.locator(String.format(dropdownLocator, dropDownName)).first().textContent().toLowerCase().contains(value.toLowerCase()));

            }).withMessage("Retrying..!!").pollingEvery(1000).withRetryCount(3).perform();
        }
    }

    public void mouseMove(int xCoordinate, int yCoordinate) {
        page.mouse().move(xCoordinate, yCoordinate);
    }

    public void waitForPageLoad() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void enterValueInLocator(String locator, String value) {
        if (CommonUtils.ifStringNotNullOrEmpty(value)) {
            page.locator(locator).first().type(value);
        }
    }

    /**
     * This method will refresh the page
     */
    public void reloadPage() {
        LogUtils.info("Reloading the page.");
        page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    /**
     * This method will set the window size
     * @param width
     * @param height
     */
    public void setBrowserWidowSize(int width, int height){
        page.setViewportSize(width,height);
    }
}