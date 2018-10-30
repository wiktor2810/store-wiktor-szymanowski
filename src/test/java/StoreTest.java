import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.ItemDetailsPage;
import pages.ListOfProductsPage;
import pages.ProductCategoryMenu;

public class StoreTest {

    WebDriver driver;
    HomePage homePage;

    @Test
    public void storeTest(){

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
        homePage.moveMouseToProductCategoryMenu();


        ProductCategoryMenu productCategoryMenu = new ProductCategoryMenu(driver);
        productCategoryMenu.chooseRandomCategory();
        ListOfProductsPage listOfProductsPage = new ListOfProductsPage(driver);
        listOfProductsPage.clickRandomProduct();
        ItemDetailsPage itemDetailsPage = new ItemDetailsPage(driver);
        itemDetailsPage.clickAddToBasket(itemDetailsPage.getRandomNumberFrom1To3());
        itemDetailsPage.checkCorrectnessOfChosenProduct();
        itemDetailsPage.moveMouseToProductCategoryMenu();
        productCategoryMenu.chooseRandomCategory();
        listOfProductsPage.clickRandomProduct();
        itemDetailsPage.clickAddToBasket(itemDetailsPage.getRandomNumberFrom1To3());
        itemDetailsPage.checkCorrectnessOfChosenProduct();
        itemDetailsPage.moveMouseToProductCategoryMenu();
        productCategoryMenu.chooseRandomCategory();
        listOfProductsPage.clickRandomProduct();
        itemDetailsPage.clickAddToBasket(itemDetailsPage.getRandomNumberFrom1To3());
        itemDetailsPage.checkCorrectnessOfChosenProduct();
        itemDetailsPage.moveMouseToProductCategoryMenu();
        productCategoryMenu.chooseRandomCategory();
        listOfProductsPage.clickRandomProduct();
        itemDetailsPage.clickAddToBasket(itemDetailsPage.getRandomNumberFrom1To3());
        itemDetailsPage.checkCorrectnessOfChosenProduct();

    }
}
