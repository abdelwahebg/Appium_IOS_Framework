package testcases;

import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.SampleAppPO;

public class SampleAppTestCases extends BaseTestCase {

    @Test
    public void verifyUserCanSendMessage() {
        final int value1 = 10;
        final int value2 = 5;

        SampleAppPO po = new SampleAppPO();
        po.typeInTextField1(value1);
        po.typeInTextField2(value2);
        po.tapOnComputeButton();

        Assert.assertEquals(po.getAnswerTextViewText(), (value1 + value2) + "", "Compute Functionality is not working or Answer is not generated yet..! ");
    }

    /**
     * This test case is to select Date and Time Picker values
     * We are not able to select value for date using send keys you need to use JavaScriptExecutor(https://github.com/appium/appium/issues/10144)
     *
     * @implNote : Please change 'ios.app.name = dateSelector.app' in configuration.properties file
     *
     */
    @Test
    public void datePicker(){
        waitUtils.staticWait(5000);

        SampleAppPO po = new SampleAppPO();
        po.selectDate("Sun Apr 1");
        waitUtils.staticWait(10000);

    }

}