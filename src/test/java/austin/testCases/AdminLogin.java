package austin.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import austin.pageObjects.functional.LoginPage;
import austin.utilities.Capture;

public class AdminLogin extends BaseClass {
	@Test
	public void adminLogin() throws InterruptedException, IOException {
		try {
			driver.get(adminURL);
			LoginPage l = new LoginPage(driver);
			l.enterAdminUsername(adminUname);
			l.enterPassword(password);
			l.clickLoginButton();
			Thread.sleep(10000);
			if (driver.getCurrentUrl()
					.equals("http://austin-web-react.s3-website.ap-south-1.amazonaws.com/admin-dashboard")) {
				logger.info("Admin logged in successfully");
				Assert.assertTrue(true);
			} else {
				handleLoginFailure();
			}
		} catch (Exception e) {
			logger.info("on error occured during login " + e.getMessage());
			Capture.CaptureScreenshot(driver, "error.png");
			Assert.fail("login failed due to an exception " + e.getMessage());
		}
	}

	private void handleLoginFailure() throws IOException {
		logger.info("Login failed");
		Capture.CaptureScreenshot(driver, "img.png");
		Assert.assertTrue(false);
	}
	

}
