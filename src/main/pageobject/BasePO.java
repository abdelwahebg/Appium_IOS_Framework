package pageobject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import manager.IOSDriverManager;
import org.openqa.selenium.support.PageFactory;
import utills.WaitUtils;

import java.util.concurrent.TimeUnit;

public class BasePO {

    WaitUtils waitUtils;

    public BasePO() {
        waitUtils = new WaitUtils();
        loadProperties();
        initElements();
    }

    private void initElements() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver(), 5, TimeUnit.SECONDS), this);
    }

    private void loadProperties() {

    }

    protected IOSDriver<IOSElement> getDriver() {
        return IOSDriverManager.getThreadLocalDriver();
    }

}
