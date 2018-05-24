package testcases;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import manager.IOSDriverManager;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utills.ScreenshotUtils;
import utills.TestcaseListener;
import utills.WaitUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

@Listeners({TestcaseListener.class })
public class BaseTestCase {

    public String screenshot_dir = "test-output/";
    WaitUtils waitUtils = new WaitUtils();

    @BeforeSuite
    public void beforeSuite() throws IOException {
        FileUtils.cleanDirectory(new File(screenshot_dir));
    }


    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(final ITestContext context, Method m) {
        Thread.currentThread().setName(m.getName() + "_" + Thread.currentThread().getId());
        System.out.println("Thread:'" + Thread.currentThread().getName() + "' is executing");
        IOSDriverManager.getThreadLocalDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(final ITestResult result) {
        String fileName = result.getTestClass().getName() + "_" + result.getName();
        System.out.println("Test Case: [" + fileName + "] executed..!");

        quitWebDriver();
    }

    @AfterClass
    public void afterClass() {

    }

    @AfterSuite
    public void afterSuite() {

    }

    protected void waitTillAppIsLaunched() {
        waitUtils.staticWait(5000);
    }

    protected IOSDriver<IOSElement> getDriver() {
        return IOSDriverManager.getThreadLocalDriver();
    }

    private void quitWebDriver() {
        try {
            this.getDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        IOSDriverManager.setThreadLocalWebDriver(null);

    }

}