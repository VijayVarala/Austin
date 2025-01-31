package austin.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import austin.pageObjects.functional.LoginPage;
import austin.utilities.Capture;

public class GrcLogin extends BaseClass
{
	@Test
	public void grcLogin() throws InterruptedException, IOException 
	{
		try 
		{
			LoginPage l=new LoginPage(driver);
			l.enterGrcUsername(grcUname);
			l.enterPassword(password);
			l.clickLoginButton();
			Thread.sleep(3000);
			if(driver.getCurrentUrl().equals("http://austin-web-react.s3-website.ap-south-1.amazonaws.com/gov-project"))
			{
				Assert.assertTrue(true);
				logger.info("Login done successfully");
			}
			else
			{
				handleLoginFailure();
			}

		}
		catch(Exception e)
		{

			logger.info("Login failed due to an exception");
			Assert.fail("Login failed due to an exception " + e.getMessage());
			Capture.CaptureScreenshot(driver, "error.png");
		}


	}
	private void handleLoginFailure() throws IOException 
	{
		logger.info("Login unsuccessful");
		Capture.CaptureScreenshot(driver, "img.png");
		Assert.assertTrue(false);
	}
}