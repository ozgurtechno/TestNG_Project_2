package CuCumberProject.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import testCase.ElementsPage;

public class _02_LoginSteps {

    ElementsPage ep = new ElementsPage();


    @And("Click on login")
    public void clickOnLogin() {
        ep.clickFunction(ep.getLoginButton());
    }

    @And("Enter email")
    public void enterEmail() {
        ep.sendKeysFunction(ep.getLoginEmail(), "omar.tarek@gmail.com");
    }

    @And("Enter password")
    public void enterPassword() {
        ep.sendKeysFunction(ep.getLoginPassword(), "omar0922");
    }

    @When("Click on Login Button")
    public void clickOnLoginButton() {
        ep.clickFunction(ep.getLog_inButton());
    }

    @Then("User should login successfully")
    public void userShouldLoginSuccessfully() {
        Assert.assertTrue(ep.getMyAccButton().isEnabled());
    }
}
