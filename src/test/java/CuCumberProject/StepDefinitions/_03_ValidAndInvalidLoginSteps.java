package CuCumberProject.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import testCase.ElementsPage;
import utility.BaseDriver;

import static testCase.Parent.waiting;

public class _03_ValidAndInvalidLoginSteps extends BaseDriver {

    ElementsPage ep = new ElementsPage();


    @Given("I navigate to the login page of the website")
    public void iNavigateToTheLoginPageOfTheWebsite() {
        ep.clickFunction(ep.getLoginButton());
        waiting(1);
    }

    @When("I enter {string} and {string}")
    public void iEnterAnd(String email, String password) {
        ep.sendKeysFunction(ep.getLoginEmail(), email);
        ep.sendKeysFunction(ep.getLoginPassword(), password);
        waiting(1);
    }

    @Then("I should {string} be logged in")
    public void iShouldBeLoggedIn(String expectedOutcome) {
        ep.clickFunction(ep.getLog_inButton());
        waiting(1);
        if (expectedOutcome.equals("be logged in")) {
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        } else {
            Assert.assertTrue(ep.getLoginErrorMsg().isDisplayed());
        }
        driver.quit();
    }

}

