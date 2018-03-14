package pageobject;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import utills.AssertionUtils;

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


}
