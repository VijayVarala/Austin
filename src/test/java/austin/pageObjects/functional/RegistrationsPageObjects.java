package austin.pageObjects.functional;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationsPageObjects {
    private WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(xpath = "//div[@class='signup-btn-sign-page']")
    private WebElement signupButton;
    
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "role")
    private WebElement roleDropdown;
    
    @FindBy(id="affiliatedOrganization")
    private WebElement affiliatedOrganization;

    @FindBy(id = "phoneNumber")
    private WebElement phoneField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "organization")
    private WebElement organizationField;

    @FindBy(id = "professionalTitle") // Only for Governance Committee
    private WebElement professionalTitle;

    @FindBy(id = "committerole") // Only for Governance Committee
    private WebElement committeeRole;

    @FindBy(id = "researchExperience") // Only for Other Roles
    private WebElement researchExperience;

    @FindBy(xpath = "//button[@class='submit-btn']")
    private WebElement submitButton;


    @FindBy(id = "successMessage")
    private WebElement successMessage;

    public RegistrationsPageObjects(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickSignUp() {
        waitUntilClickable(signupButton);
        signupButton.click();
    }

    public void selectRole(String role) {
        waitUntilVisible(roleDropdown);
        Select select = new Select(roleDropdown);
        select.selectByVisibleText(role);
    }

    public void fillCommonFields(String name,String phone, String email, String organization) {
    	 nameField.sendKeys(name);
         phoneField.sendKeys(phone);
         emailField.sendKeys(email);
         organizationField.sendKeys(organization);
    }

    public void fillGovernanceCommitteeFields(String profTitle) {
        waitUntilVisible(professionalTitle);
        professionalTitle.sendKeys(profTitle);
       
    }
    public void selectAffiliatedOrganization(int affiOrganization) {
        wait.until(ExpectedConditions.visibilityOf(affiliatedOrganization));
        Select select = new Select(affiliatedOrganization);
        select.selectByIndex(affiOrganization);
    }
    
    public void selectResearchExperience(int experience) {
        wait.until(ExpectedConditions.visibilityOf(researchExperience));
        Select select = new Select(researchExperience);
        select.selectByIndex(experience);
    }


    public void selectCommitteeRole(int CommitteeRole) {
        waitUntilVisible(committeeRole);
        Select select = new Select(committeeRole);
        select.selectByIndex(CommitteeRole);
    }

    public void fillOtherRolesFields(int experience) {
        waitUntilVisible(researchExperience);
        Select select = new Select(researchExperience);
        select.selectByIndex(experience);
    }

    public void submitForm() {
        waitUntilClickable(submitButton);
        submitButton.click();
    }
    
    
    public void enterUserName(String username) {
        waitUntilVisible(emailField);
        emailField.sendKeys(username);
       
    }
    

    public boolean isRegistrationSuccessful() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
    }

    public boolean verifyFieldsForRole(String role) {
        waitUntilVisible(roleDropdown);

        boolean isPhoneVisible = phoneField.isDisplayed();
        boolean isEmailVisible = emailField.isDisplayed();
        boolean isOrgVisible = organizationField.isDisplayed();

        if (role.equals("Governance Committee")) {
            boolean isProfTitleVisible = professionalTitle.isDisplayed();
            boolean isCommitteeRoleVisible = committeeRole.isDisplayed();
            return isPhoneVisible && isEmailVisible && isOrgVisible && isProfTitleVisible && isCommitteeRoleVisible;
        } else {
            boolean isResearchExpVisible = researchExperience.isDisplayed();
            return isPhoneVisible && isEmailVisible && isOrgVisible && isResearchExpVisible;
        }
    }

    private void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
