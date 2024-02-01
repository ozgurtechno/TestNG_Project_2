package testCase;

import org.openqa.selenium.Platform;
import utility.BaseDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static testCase.Parent.*;

public class NopCommerce extends BaseDriver {

    @Test(priority = 1, groups = {"Smoke Test"})
    public void US_501() { // The user successfully registers on the Nopcommerce website and receives a verification message

        ElementsPage ep = new ElementsPage();

        ep.clickFunction(ep.getRegister());
        ep.clickFunction(ep.getGender());
        ep.sendKeysFunction(ep.getFirstname(), "Adam");
        ep.sendKeysFunction(ep.getLastname(), "Smith");
        ep.selectElement(ep.getDay(), "24");
        ep.selectElement(ep.getMonth(), "3");
        ep.selectElement(ep.getYear(), "1933");
        ep.sendKeysFunction(ep.getEmail(), "a.smith@gmail.com");
        ep.sendKeysFunction(ep.getCompany(), "Techno Market");
        ep.sendKeysFunction(ep.getPassword(), "Qwerty12");
        ep.sendKeysFunction(ep.getConfirmPassword(), "Qwerty12");
        ep.clickFunction(ep.getButton());
        try {
            Assert.assertTrue(ep.getRegMessage().getText().contains("registration completed"));
            ep.clickFunction(ep.getContine());
        } catch (Exception e) {
            Assert.assertTrue(ep.getRegErrorMessage().getText().contains("The specified email already exists"));
        }
    }

    @Test(priority = 2, groups = {"Smoke Test"})
    public void US_502() { // The user successfully logs in to the Nopcommerce website.

        ElementsPage ep = new ElementsPage();

        ep.clickFunction(ep.getLoginButton());
        ep.sendKeysFunction(ep.getLoginEmail(), "a.smith@gmail.com");
        ep.sendKeysFunction(ep.getLoginPassword(), "Qwerty12");
        ep.clickFunction(ep.getLog_inButton());
        Assert.assertTrue(ep.getMyAccButton().isEnabled());
        ep.clickFunction(ep.getLogout());
    }

    @Test(priority = 3, groups = {"Smoke Test"}, dataProviderClass = NopCommerce.class, dataProvider = "userData")
    public void US_503(String username, String password) { // The user logs in by testing invalid and valid email and password combinations.

        ElementsPage ep = new ElementsPage();

        ep.clickFunction(ep.getLoginButton());
        waiting(1);
        ep.sendKeysFunction(ep.getLoginEmail(), username);
        waiting(1);
        ep.sendKeysFunction(ep.getLoginPassword(), password);
        waiting(1);
        ep.clickFunction(ep.getLog_inButton());
        waiting(1);
        if (username.equals("a.smith@gmail.com") && password.equals("Qwerty12")) {
            Assert.assertTrue(ep.getMyAccButton().isEnabled());
            ep.clickFunction(ep.getLogout());
        } else {
            ep.verifyContainsText(ep.getLoginErrorMsg(), "Login was unsuccessful. Please correct the errors and try again.");
        }
    }

    @DataProvider
    public Object[][] userData() {
        Object[][] data =
                {
                        {"A.smith@gmail.com", "qwerty12"},
                        {"a.simth@gmail.com", ""},
                        {"xyz@gmail.com", "Qwerty12"},
                        {"a.smith@gmail.com", "Qwerty12"}
                };
        return data;
    }

    @Test(priority = 4, groups = {"Smoke Test"})
    public void US_504() { // The user checks that the platform's tab menu functions correctly and verifies that submenus are functional.

        ElementsPage ep = new ElementsPage();

        Assert.assertTrue(ep.getHeaderMenu().isEnabled());
        ep.hoverFunction(ep.getComputers());
        ep.clickFunction(ep.getDesktop());
        Assert.assertTrue(driver.getCurrentUrl().contains("/desktops"));
        ep.hoverFunction(ep.getComputers());
        ep.clickFunction(ep.getNotebooks());
        Assert.assertTrue(driver.getCurrentUrl().contains("/notebooks"));
        ep.hoverFunction(ep.getComputers());
        ep.clickFunction(ep.getSoftware());
        Assert.assertTrue(driver.getCurrentUrl().contains("/software"));
        ep.hoverFunction(ep.getElectronics());
        ep.clickFunction(ep.getCameraAndPhoto());
        Assert.assertTrue(driver.getCurrentUrl().contains("/camera-photo"));
        ep.hoverFunction(ep.getElectronics());
        ep.clickFunction(ep.getCellPhone());
        Assert.assertTrue(driver.getCurrentUrl().contains("/cell-phones"));
        ep.hoverFunction(ep.getElectronics());
        ep.clickFunction(ep.getOthers());
        Assert.assertTrue(driver.getCurrentUrl().contains("/others"));
        ep.hoverFunction(ep.getApparel());
        ep.clickFunction(ep.getShoes());
        Assert.assertTrue(driver.getCurrentUrl().contains("/shoes"));
        ep.hoverFunction(ep.getApparel());
        ep.clickFunction(ep.getClothing());
        Assert.assertTrue(driver.getCurrentUrl().contains("/clothing"));
        ep.hoverFunction(ep.getApparel());
        ep.clickFunction(ep.getAccessories());
        Assert.assertTrue(driver.getCurrentUrl().contains("/accessories"));
        ep.clickFunction(ep.getComputers());
        Assert.assertTrue(driver.getCurrentUrl().contains("/computers"));
        ep.clickFunction(ep.getElectronics());
        Assert.assertTrue(driver.getCurrentUrl().contains("/electronics"));
        ep.clickFunction(ep.getApparel());
        Assert.assertTrue(driver.getCurrentUrl().contains("/apparel"));
        ep.clickFunction(ep.getDigitalDownloads());
        Assert.assertTrue(driver.getCurrentUrl().contains("/digital-downloads"));
        ep.clickFunction(ep.getBooks());
        Assert.assertTrue(driver.getCurrentUrl().contains("/books"));
        ep.clickFunction(ep.getJewelry());
        Assert.assertTrue(driver.getCurrentUrl().contains("/jewelry"));
        ep.clickFunction(ep.getGiftCards());
        Assert.assertTrue(driver.getCurrentUrl().contains("/gift-cards"));
    }


    @Parameters("elements")
    @Test(priority = 5, groups = {"Smoke Test"})
    public void US_505(String elements) { // The user lists the products in the platform's tab menu, performs a product search, and verifies whether the product found in the search results is included in the listed items.
        //TODO : This test (US_505) should not be run standalone; it can only be executed via XML
        ElementsPage ep = new ElementsPage();

        for (int i = 0; i < ep.getListheader().size(); i++) {
            WebElement tabMenu = ep.getListheader().get(i);
            System.out.println(tabMenu.getText());
        }

        ep.elementfindersText(ep.getComputers());
        ep.elementfindersText(ep.getElectronics());
        ep.elementfindersText(ep.getApparel());
        ep.getSearch().sendKeys(elements, Keys.ENTER);
        int randomSelect = ElementsPage.RandomGenerator(ep.getProductTitle().size());
        wait.until(ExpectedConditions.urlContains(elements));
        List<String> stringList = new ArrayList<>();
        for (WebElement element : ep.getProductTitle()) {
            stringList.add(element.getText());
        }
        new Actions(driver).scrollByAmount(200, 0);
        ep.getProductTitle().get(randomSelect).click();
        Assert.assertTrue(stringList.contains(ep.getProductName().getText()), "Item not found");
    }


    @Test(priority = 6, groups = {"Smoke Test"})
    public void US_506() throws AWTException { // Performing a test for placing a gift order on the website as a user.

        ElementsPage ep = new ElementsPage();

        Robot robot = new Robot();

//        TODO This method is uncommented only for Mac users
        if (Platform.getCurrent().is(Platform.MAC)){
            Set<String> windowHandles = driver.getWindowHandles();

            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains("nopcom")) {
                    break;
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        int randomProducts = generalRandom(0, ep.getProductItems().size() - 1);
        ep.getProductItems().get(randomProducts).click();

        String chosenGiftCard = ep.getChosenGiftCard().getText();
        String randomUsername = "User" + generalRandom(1000, 9999);
        String randomRecipientEmail = "recipient" + generalRandom(1000, 9999) + "@test.com";
        String randomSenderName = "Sender" + generalRandom(1000, 9999);
        String randomSenderEmail = "sender" + generalRandom(1000, 9999) + "@test.com";
        String randomMessage = messages[generalRandom(0, messages.length - 1)];

        switch (chosenGiftCard) {
            case "$25 Virtual Gift Card":
                ep.getRecipientName25().sendKeys(randomUsername);
                ep.getRecipientEmail25().sendKeys(randomRecipientEmail);
                ep.getYourName25().sendKeys(randomSenderName);
                ep.getYourEmail25().sendKeys(randomSenderEmail);
                ep.getMessageField25().sendKeys(randomMessage);
                ep.getAddToCart25().click();
                break;

            case "$50 Physical Gift Card":
                ep.getRecipientName50().sendKeys(randomUsername);
                ep.getYourName50().sendKeys(randomSenderName);
                ep.getMessageField50().sendKeys(randomMessage);
                ep.getAddToCart50().click();
                break;

            case "$100 Physical Gift Card":
                ep.getRecipientName100().sendKeys(randomUsername);
                ep.getYourName100().sendKeys(randomSenderName);
                ep.getMessageField100().sendKeys(randomMessage);
                ep.getAddToCart100().click();
                break;
        }
        String actualMessage = ep.getMsg().getText();
        String expectedMessage = "The product has been added to your shopping cart";
        Assert.assertEquals(actualMessage, expectedMessage, "Product could not be added to the cart.");
    }

    String[] messages =
            {
                    "Have a good day of use!",
                    "Enjoy your shopping!",
                    "Use it with your loved ones!"
            };


    @Test(priority = 7, dataProvider = "inputData")
    public void US_506_Negative(String recipientName, String yourName, String message) {

        ElementsPage ep = new ElementsPage();

        ep.getGiftCardsLink().click();
        ep.getAddToCardsLink().click();
        ep.getRecipientName50().sendKeys(recipientName);
        ep.getYourName50().sendKeys(yourName);
        ep.getMessageField50().sendKeys(message);
        ep.getAddToCart50().click();
        String actualWarningText = ep.getWarningMessage().getText();
        System.out.println("Please fill in the required fields: " + actualWarningText);
    }

    @DataProvider(name = "inputData")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"Techno", "", "Have a good day of use!"},
                {"", "John Smith", "Congratulations!"}
        };
    }

    @Test(priority = 8, groups = {"Smoke Test"})
    public void US_507() { // The user places an order for a customizable computer on the website and verifies that the order is successfully completed.

        ElementsPage ep = new ElementsPage();

        ep.hoverFunction(ep.getComputerS());
        ep.getDesktopS().click();
        wait.until(ExpectedConditions.visibilityOf(ep.getBuildOwnComp()));
        ep.getBuildOwnComp().click();
        int chooseRandom = RandomGenerator(ep.getRamSelectContents().size());
        ep.getRamSelectContents().get(chooseRandom).click();
        chooseRandom = RandomGenerator(ep.getHddButtonS().size());
        ep.getHddButtonS().get(chooseRandom).click();
        ep.getBYOC_addToCardButton().click();
        ep.waitUntilVisible(ep.getChckShopCard());
        Assert.assertTrue(ep.getChckShopCard().getText().toLowerCase().contains("the product has been added"));
    }

    @Parameters("Search")
    @Test(priority = 9, groups = {"Smoke Test"})
    public void US_508(String data) { // The user finds a specific product on the website by performing a parameterized search and confirms that the product is listed correctly
        // TODO : The execution of this test (US_508) is not supported in standalone mode; it must be run through XML configuration.
        ElementsPage ep = new ElementsPage();

        ep.getSearchStore().sendKeys(data);
        ep.getSearchButton().click();
        ep.getProduct().click();
        Assert.assertTrue(ep.getProductName().getText().contains("Adobe Photoshop CS4"));
    }
}
