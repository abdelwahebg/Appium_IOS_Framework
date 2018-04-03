package utills;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * TestNG test case listener
 *
 * @author prat3ik
 *
 */

public class TestcaseListener extends TestListenerAdapter {

    private ScreenshotUtils screenShot;

    public TestcaseListener() {
        screenShot = new ScreenshotUtils();
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        screenShot.takeScreenShot(tr);
    }
}
