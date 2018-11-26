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



    HomePage homePage = new HomePage(driver);
    ProductCategoryMenu productCategoryMenu = new ProductCategoryMenu(driver);
    ListOfProductsPage listOfProductsPage = new ListOfProductsPage(driver);
    ItemDetailsPage itemDetailsPage = new ItemDetailsPage(driver);

    @Given("^user is on home page$")
    public void userIsOnHomePage() throws Throwable {
        homePage.waitForHomePageElements();
    }

    @When("^user go into category$")
    public void userGoIntoCategory() throws Throwable {
        homePage.moveMouseToProductCategoryMenu();
        productCategoryMenu.chooseRandomCategory();
    }

    @And("^user choose product$")
    public void userChooseProduct() throws Throwable {
        listOfProductsPage.clickRandomProduct();
    }

    @And("^user add product to basket$")
    public void userAddProductToBasket() throws Throwable {
        itemDetailsPage.clickAddToBasket(1);
    }

    @Then("^validation name of chosen product$")
    public void validationNameOfChosenProduct() throws Throwable {
        itemDetailsPage.checkCorrectnessOfChosenProduct();
    }
}

