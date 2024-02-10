package CuCumberProject.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import testCase.ElementsPage;
import utility.BaseDriver;

public class _01_RegisterSteps extends BaseDriver {

    ElementsPage ep = new ElementsPage();


    @Given("Navigate to NonCommerce WebSite")
    public void navigateToNonCommerceWebSite() {
        driver.get("https://demo.nopcommerce.com/");
    }

    @And("Click on register")
    public void clickOnRegister() {
        ep.clickFunction(ep.getRegister());
    }

    @And("Click on gender male")
    public void clickOnGenderMale() {
        ep.clickFunction(ep.getGender());
    }

    @And("Click on first name")
    public void clickOnFirstName() {
        ep.sendKeysFunction(ep.getFirstname(), "Omar");
    }

    @And("Click on Last name")
    public void clickOnLastName() {
        ep.sendKeysFunction(ep.getLastname(), "Tarek");
    }

    @And("Click on select date")
    public void clickOnSelectDate() {
        ep.selectElement(ep.getMonth(), "22");
    }

    @And("Click on select month")
    public void clickOnSelectMonth() {
        ep.selectElement(ep.getYear(), "09");
    }

    @And("Click on select year")
    public void clickOnSelectYear() {
        ep.selectElement(ep.getYear(), "2002");
    }

    @And("Click on email")
    public void clickOnEmail() {
        ep.sendKeysFunction(ep.getEmail(), "omar.tarek@gmail.com");
    }

    @And("Click company name")
    public void clickCompanyName() {
        ep.sendKeysFunction(ep.getCompany(), "Techno Market");
    }

    @And("Click on password")
    public void clickOnPassword() {
        ep.sendKeysFunction(ep.getPassword(), "omar0922");
    }

    @And("Click on confirm password")
    public void clickOnConfirmPassword() {
        ep.sendKeysFunction(ep.getConfirmPassword(), "omar0922");
    }

    @When("Click on Register button")
    public void clickOnRegisterButton() {
        ep.clickFunction(ep.getRegister());
    }

    @Then("Success message should be displayed")
    public void successMessageShouldBeDisplayed() {
        try {
            Assert.assertTrue(ep.getRegMessage().getText().contains("registration completed"));
            ep.clickFunction(ep.getContine());
        } catch (Exception e) {
            Assert.assertTrue(ep.getRegErrorMessage().getText().contains("The specified email already exists"));
        }
    }


}