package CuCumberProject.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import testCase.ElementsPage;
import utility.BaseDriver;

public class _04_TabMenuSteps extends BaseDriver {
    ElementsPage ep = new ElementsPage();

    @Given("Navigate to NonCommerce WebSite")
    public void navigateToNonCommerceWebSite() {
        driver.get("https://demo.nopcommerce.com/");
    }

    @When("I hover on the {string} tab")
    public void iHoverOnTheTab(String computers) {
        Assert.assertTrue(ep.getHeaderMenu().isEnabled());
        ep.hoverFunction(ep.getComputers());
    }

    @Then("I should see the following submenus:")
    public void iShouldSeeTheFollowingSubmenus() {



        Assert.assertTrue(driver.getCurrentUrl().contains("/desktops"));
    }

    @When("I hover on the {string} menu")
    public void iHoverOnTheMenu(String electronics) {
        ep.hoverFunction(ep.getComputers());
    }

    @When("I hover on {string} button")
    public void iClickOnButton(String arg0) {

    }

    @When("I Click on {string} menu")
    public void iClickOnMenu(String arg0) {
    }

    @Then("I should be on The Digital Downloads page")
    public void iShouldBeOnTheDigitalDownloadsPage() {
    }

    @When("I Click on {string} page")
    public void iClickOnPage(String arg0) {
    }

    @Then("I should be on the Books page")
    public void iShouldBeOnTheBooksPage() {
    }

    @When("I Click on {string} word")
    public void iClickOnWord(String arg0) {
    }

    @Then("I should  be on The Jewellery page")
    public void iShouldBeOnTheJewelleryPage() {
    }
}
