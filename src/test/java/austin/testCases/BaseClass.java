package austin.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import austin.utilities.*;
public class BaseClass {
    protected static final Logger logger = LogManager.getLogger(BaseClass.class);
    
    private static ReadConfig readConfig = new ReadConfig();
    
    protected static String baseURL = readConfig.getUrl().orElseThrow(() -> new RuntimeException("Base URL not found"));
    protected static String adminURL = readConfig.getAdminUrl().orElseThrow(() -> new RuntimeException("Base URL not found"));
    protected static String initiatorUname = readConfig.getInitiatorUname().orElseThrow(() -> new RuntimeException("Username not found"));
    protected static String investigatorUname = readConfig.getInvestigatorUname().orElseThrow(() -> new RuntimeException("Username not found"));
    protected static String grcUname = readConfig.getGrcUname().orElseThrow(() -> new RuntimeException("Username not found"));
    protected static String researchUname = readConfig.getResearchUname().orElseThrow(() -> new RuntimeException("Username not found"));
    protected static String adminUname = readConfig.getAdminUname().orElseThrow(() -> new RuntimeException("Username not found"));
    protected static String password = readConfig.getPassword().orElseThrow(() -> new RuntimeException("Password not found"));
    private static String browser = readConfig.getBrowser().orElseThrow(() -> new RuntimeException("Browser not found"));
    
    public static WebDriver driver;

    @BeforeClass
    public void setup() 
    {
        logger.info("Starting setup...");

        try 
        {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    logger.error("Selected browser is not supported: " + browser);
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            driver.manage().window().maximize();
            driver.get(baseURL);
            logger.info("Navigated to base URL: " + baseURL);
        } 
        catch (Exception e) 
        {
            logger.error("Error during setup: " + e.getMessage(), e);
            throw e;
        }
    }

    @AfterClass
    public void teardown() 
    {
        if (driver != null) 
        {
            driver.quit();
            logger.info("Browser closed.");
        }
    }

    public void captureScreen(String testName) throws IOException 
    {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + testName + ".png");
        FileUtils.copyFile(source, target);
        logger.info("Screenshot taken: " + target.getAbsolutePath());
    }
}
