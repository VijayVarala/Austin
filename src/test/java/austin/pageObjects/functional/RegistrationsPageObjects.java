package austin.pageObjects.functional;

import java.time.Duration;
import java.util.*;
import org.mortbay.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import austin.testCases.BaseClass;

public class RegistrationsPageObjects extends BaseClass
{
	private static WebDriver driver;
	private static WebDriverWait wait;

	@FindBy(id="role")
	static
	WebElement role;

	@FindBy(id="name")
	WebElement name;

	@FindBy(id="phoneNumber")
	WebElement phonenumber;

	@FindBy(id="email")
	WebElement mail;

	@FindBy(id="affiliatedOrganization")
	WebElement affiliatedorganization;

	@FindBy(id="researchExperience")
	WebElement researchExperience;

	@FindBy(id="organization")
	WebElement organization;

	@FindBy(id="professionalTitle")
	WebElement professionalTitle;

	@FindBy(id="committerole")
	WebElement committerole;

	public RegistrationsPageObjects(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
public static void main(String[] args) throws InterruptedException {
	driver= new ChromeDriver();
	driver.get(baseURL);
		LoginPage l= new LoginPage(driver);
		l.clickSignupButton();
		Thread.sleep(3000);
		WebElement ele=driver.findElement(By.id("role"));
		Select select = new Select(ele);
		waitUntilVisible(ele);
		select.selectByVisibleText("Initiator");
		WebElement element=driver.findElement(By.linkText("Initiator"));
		if(element.equals("Initiator")) 
		{
			System.out.println("Yessss");
		}
	
			
				
		select.selectByVisibleText("Principal Investigator");
		select.selectByVisibleText("Governance Committee");
		select.selectByVisibleText("Research Team");
		verifyInputFieldsForRole("Initiator");
	
	
	}

	private static void verifyInputFieldsForRole(String string) 
	{
		// TODO Auto-generated method stub

	}

	private static void waitUntilVisible(WebElement element) 
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Wait until an element is clickable
	private void waitUntilClickable(WebElement element) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
