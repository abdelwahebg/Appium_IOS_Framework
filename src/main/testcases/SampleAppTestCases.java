package testcases;

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

}