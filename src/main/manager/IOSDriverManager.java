package manager;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import utills.PropertyUtils;
import utills.WaitUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IOSDriverManager {
    public static ThreadLocal<IOSDriver<IOSElement>> webDriver = new ThreadLocal<IOSDriver<IOSElement>>();
    protected WaitUtils waitUtils = new WaitUtils();
    public AppiumDriverLocalService service;

    public final static String APPIUM_SERVER_URL = PropertyUtils.getProperty("appium.server.url");
    public final static String PLATFORM_NAME = PropertyUtils.getProperty("ios.platform");
    public final static String PLATFORM_VERSION = PropertyUtils.getProperty("ios.platform.version");
    public final static String APP_NAME = PropertyUtils.getProperty("ios.app.name");
    public final static String APP_RELATIVE_PATH = PropertyUtils.getProperty("ios.app.location") + APP_NAME;
    public final static String APP_PATH = getAbsolutePath(APP_RELATIVE_PATH);
    public final static String DEVICE_NAME = PropertyUtils.getProperty("ios.device.name");
    public final static String APP_FULL_RESET = PropertyUtils.getProperty("ios.app.full.reset");
    public final static int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);
    public final static String WDA_LOCAL_PORT = "5230";

    public static DesiredCapabilities getIOSCaps() {
        DesiredCapabilities caps = DesiredCapabilities.iphone();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        caps.setCapability(MobileCapabilityType.APP, APP_PATH);
      //  caps.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, WDA_LOCAL_PORT);

      //  caps.setCapability("noReset", true);
      //  caps.setCapability("fullReset", true);



        caps.setCapability(MobileCapabilityType.FULL_RESET, APP_FULL_RESET);
        return caps;
    }

    public static IOSDriver<IOSElement> getThreadLocalDriver() {
        IOSDriver<IOSElement> driver = webDriver.get();
        if (driver == null) {
            createThreadLocalWebDriver();
            driver = webDriver.get();
        }
        return driver;
    }

    public static void createThreadLocalWebDriver() {
        IOSDriver<IOSElement> driver = null;
        try {
            driver = new IOSDriver<IOSElement>(new URL(APPIUM_SERVER_URL), getIOSCaps());
        } catch (Exception e) {
            try {
                driver = new IOSDriver<IOSElement>(new URL(APPIUM_SERVER_URL), getIOSCaps());
            } catch (MalformedURLException e1) {
                System.out.println("Android Driver is not created..!, Please check capabilitis or make sure Appium Server is running.");
            }
            return;
        }
        setTimeOuts(driver);
        webDriver.set(driver);
    }

    private static String getAbsolutePath(String appRelativePath) {
        File file = new File(appRelativePath);
        return file.getAbsolutePath();
    }

    private static void setTimeOuts(IOSDriver<IOSElement> driver) {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static void setThreadLocalWebDriver(final IOSDriver driver) {
        webDriver.set(driver);
    }
}
