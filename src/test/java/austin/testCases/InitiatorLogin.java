package austin.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import austin.pageObjects.functional.LoginPage;
import austin.utilities.Capture;

public class InitiatorLogin extends BaseClass
{

	//@Test(groups = "smoke")  
	@Test
	public void login() throws InterruptedException, IOException 
	{
//		driver.get(baseURL);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LoginPage lp = new LoginPage(driver);

		try {
			lp.enterInitUsername(initiatorUname);
			lp.enterPassword(password);
			lp.clickLoginButton();
			Thread.sleep(3000);
			String currenturl=driver.getCurrentUrl();
			System.out.println(currenturl);

			// Wait for a moment for redirection

			// Verify login by checking the current URL
			if (driver.getCurrentUrl().equals("http://austin-web-react.s3-website.ap-south-1.amazonaws.com/dashboard")) 
			{
				Assert.assertTrue(true);
				logger.info("Login done...");

				//System.out.println("Login done successfully");
			} 
			else 
			{
				handleLoginFailure();
			}
		} 
		catch (Exception e) 
		{
			System.out.println("An error occurred during login: " + e.getMessage());
			Capture.CaptureScreenshot(driver, "error.png");
			Assert.fail("Login test failed due to an exception: " + e.getMessage());
		}
	}

	private static void handleLoginFailure() throws IOException 
	{
		System.out.println("Login failed");
		Capture.CaptureScreenshot(driver, "InitiatorLoginimg.png");
		Assert.assertTrue(false);
	}

}