package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ItemDetailsPage;
import pages.ListOfProductsPage;
import pages.ProductCategoryMenu;

public class MyStepdefsStore {


    WebDriver driver = null;
    public MyStepdefsStore(){
        DriverFactory driverFactory = new DriverFactory();
        try {
            driver = driverFactory.getDriver();
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("http://store.demoqa.com/");
        driver.manage().window().maximize();
    }

    HomePage homePage;
    ProductCategoryMenu productCategoryMenu;
    ListOfProductsPage listOfProductsPage;
    ItemDetailsPage itemDetailsPage;

    @Given("^user is on home page$")
    public void userIsOnHomePage() throws Throwable {
        driver.get("http://store.demoqa.com/");
        homePage = new HomePage(driver);
        homePage.waitForHomePageElements();
    }

    @When("^user go into category$")
    public void userGoIntoCategory() throws Throwable {
        homePage.moveMouseToProductCategoryMenu();
        productCategoryMenu = new ProductCategoryMenu(driver);
        productCategoryMenu.chooseRandomCategory();
    }

    @And("^user choose product$")
    public void userChooseProduct() throws Throwable {
        listOfProductsPage = new ListOfProductsPage(driver);
        listOfProductsPage.clickRandomProduct();
    }

    @And("^user add product to basket$")
    public void userAddProductToBasket() throws Throwable {
        itemDetailsPage = new ItemDetailsPage(driver);
        itemDetailsPage.clickAddToBasket(1);
    }

    @Then("^validation name of chosen product$")
    public void validationNameOfChosenProduct() throws Throwable {
        itemDetailsPage.checkCorrectnessOfChosenProduct();
        driver.quit();
    }
}

