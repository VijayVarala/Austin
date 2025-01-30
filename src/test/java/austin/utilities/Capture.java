package austin.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Capture 
{
	public static void CaptureScreenshot(WebDriver driver, String filename) throws IOException 
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(temp,new File("./Screenshots/"+filename+""));
		
	}

}
