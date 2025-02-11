package austin.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import austin.pageObjects.functional.RegistrationsPageObjects;

public class InitiatorRegistration extends BaseClass {
    private RegistrationsPageObjects registrationPage;

    @BeforeClass
    public void setupPage() {
        registrationPage = new RegistrationsPageObjects(driver);
    }

    @Test
    public void InitiatorRegistration() throws InterruptedException{
    	registrationPage.clickSignUp();
        registrationPage.selectRole("Initiator");
        registrationPage.fillCommonFields("John Doe", "9876543210", "johndoe@example.com","Study");
        registrationPage.selectAffiliatedOrganization(1);
        registrationPage.selectResearchExperience(3);
        registrationPage.submitForm();
        Thread.sleep(5000);
        registrationPage.enterUserName("Initiator");

        Assert.assertTrue(registrationPage.isRegistrationSuccessful(), "Initiator registration failed!");
    }
}
