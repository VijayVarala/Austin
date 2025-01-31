package austin.pageObjects.functional;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "email")
    WebElement username;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@class='signup-btn-sign-page']")
    WebElement signupButton;

    // Constructor
    public LoginPage(WebDriver driver) 
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Enter initiator username
    public void enterInitUsername(String initiatorUname) 
    {
        waitUntilVisible(username);
        username.sendKeys(initiatorUname);
    }
    
    // Enter investigator username
    public void enterInvestUsername(String investigatorUname) 
    {
        waitUntilVisible(username);
        username.sendKeys(investigatorUname);
    }
    
    // Enter Grc username    
    public void enterGrcUsername(String grcUname) 
    {
        waitUntilVisible(username);
        username.sendKeys(grcUname);
    }
    
    // Enter research username   
    public void enterResearchUsername(String researchUname) 
    {
        waitUntilVisible(username);
        username.sendKeys(researchUname);
    }
    
    // Enter admin username   
    public void enterAdminUsername(String adminUname) 
    {
        waitUntilVisible(username);
        username.sendKeys(adminUname);
    }
    
    // Enter password
    public void enterPassword(String password) 
    {
        waitUntilVisible(passwordField);
        passwordField.sendKeys(password);
    }

    // Click the login button
    public void clickLoginButton() 
    {
        waitUntilClickable(loginButton);
        loginButton.click();
    }

    // Click the signup button (if needed for testing)
    public void clickSignupButton() 
    {
        waitUntilClickable(signupButton);
        signupButton.click();
    }

    // Wait until an element is visible
    private void waitUntilVisible(WebElement element) 
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait until an element is clickable
    private void waitUntilClickable(WebElement element) 
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
