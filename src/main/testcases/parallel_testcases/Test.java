package testcases.parallel_testcases;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Test {

    protected AppiumDriverLocalService service;
    IOSDriver<IOSElement> driver;
    WebDriverWait wait;
    public final String url = "https://www.24hourfitness.com/";


    @Parameters({"wda", "deviceName", "port"})
    @BeforeTest
    public void beforeTest(String wda, String deviceName, String port) {
        service = new AppiumServiceBuilder().usingPort(Integer.valueOf(port)).build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium service node not started");
        }

        File file = new File("src/main/resources/RepuEmp_.ipa");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, wda);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        caps.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 50000);
        caps.setCapability(MobileCapabilityType.APP,file.getAbsolutePath());
        driver = new IOSDriver<IOSElement>(service.getUrl(), caps);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null)
            service.stop();
    }
}
