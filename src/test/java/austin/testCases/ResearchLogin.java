package austin.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import austin.pageObjects.functional.LoginPage;
import austin.utilities.Capture;

public class ResearchLogin extends BaseClass
{
	@Test
	public void researchLogin() throws InterruptedException, IOException
	{
		try
		{
		LoginPage l = new LoginPage(driver);
		l.enterResearchUsername(researchUname);
		l.enterPassword(password);
		l.clickLoginButton();
		Thread.sleep(3000);
		if(driver.getCurrentUrl().equals("http://austin-web-react.s3-website.ap-south-1.amazonaws.com/research-dashboard"))
		{
			logger.info("Login done successfully");
			Assert.assertTrue(true);
		}
		else
		{
			handleLoginFailure();
		}
		}
		catch(Exception e)
		{
			logger.info("On error occured while login" + e.getMessage());
			Capture.CaptureScreenshot(driver, "error.png");
			Assert.fail("Login failed due to an exception " + e.getMessage());
		}
	}

	private void handleLoginFailure() throws IOException 
	{
		logger.info("Research login failed");
		Capture.CaptureScreenshot(driver, "img.png");
		Assert.assertTrue(false);
	}

}
