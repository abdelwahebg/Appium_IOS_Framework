package utills;

import io.appium.java_client.android.AndroidElement;

/**
 * Assertion Library
 *
 * @author prat3ik
 *
 */

public class AssertionUtils {

    public static WaitUtils waitUtils = new WaitUtils();

    /**
     * This will check whether element is displayed on UI or not
     *
     * @param element
     * @return
     */
    public static boolean isElementDisplayed(AndroidElement element) {
        waitUtils.staticWait(3000);
        boolean isPresent = false;
        try {
            element.isDisplayed();
            isPresent = true;
        } catch (Exception e) {
            isPresent = false;
        }

        return isPresent && element.isDisplayed();
    }
}
