package austin.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import austin.pageObjects.functional.LoginPage;
import austin.utilities.Capture;


public class InvestigatorLogin extends BaseClass
{
	@Test
	public void login() throws IOException
	{
		try
		{
			LoginPage l= new LoginPage(driver);
			l.enterInvestUsername(investigatorUname);
			l.enterPassword(password);
			l.clickLoginButton();
			Thread.sleep(3000);
			if(driver.getCurrentUrl().equals("http://austin-web-react.s3-website.ap-south-1.amazonaws.com/pidashboard"))
			{
				Assert.assertTrue(true);
				logger.info("Principal investigator login done successfully");
			}
			else
			{
				handleLoginFailure();
			}
			
		}
		catch(Exception e)
		{
			System.out.println("On error occured while Investigator login " + e.getMessage());
			Capture.CaptureScreenshot(driver, "error.png");
			Assert.fail("Login test faile due to exception "+e.getMessage());
			
		}
		
	}

	private void handleLoginFailure() throws IOException 
	{
		logger.info("Login failed");
		Capture.CaptureScreenshot(driver, "img.png");
		Assert.assertTrue(false);
		
	}
	
}
