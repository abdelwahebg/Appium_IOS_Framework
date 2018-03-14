package pageobject;

import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleAppPO extends BasePO {

    @iOSFindBy(accessibility = "TextField1")
    IOSElement textfield1;

    public void typeInTextField1(int value) {
        textfield1.sendKeys(value + "");
    }

    @iOSFindBy(accessibility = "TextField2")
    IOSElement textfield2;

    public void typeInTextField2(int value) {
        textfield2.sendKeys(value + "");
    }

    @iOSFindBy(accessibility = "ComputeSumButton")
    IOSElement computeButton;

    public void tapOnComputeButton() {
        computeButton.click();
        waitUtils.waitForElementToBeVisible(answerTextView, getDriver());
    }

    @iOSFindBy(accessibility = "Answer")
    IOSElement answerTextView;

    public String getAnswerTextViewText() {
        return answerTextView.getText();
    }


    @iOSFindBy(xpath = "//XCUIElementTypePickerWheel")
    List<IOSElement> dateSelector;

    public void selectDate(String text) {
        System.out.println(dateSelector);

        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("order", "next");
        params.put("offset", 0.15);
        params.put("element", ((RemoteWebElement) dateSelector.get(0)).getId());
        js.executeScript("mobile: selectPickerWheelValue", params);


        //dateSelector.get(0).setValue("19 Mar, Thu");
       // dateSelector.get(0).sendKeys("Thu Mar 15");
        dateSelector.get(1).sendKeys("9");
        dateSelector.get(2).sendKeys("19");
        dateSelector.get(3).sendKeys("AM");

        System.out.println(dateSelector.get(0).getAttribute("value"));
        System.out.println(dateSelector.get(1).getAttribute("value"));
        System.out.println(dateSelector.get(2).getAttribute("value"));
        System.out.println(dateSelector.get(3).getAttribute("value"));


        //getDriver().findElementByXPath("").
    }

}
