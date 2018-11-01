import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

public class StoreTest extends Base{

    HomePage homePage;
    ProductCategoryMenu productCategoryMenu;
    ListOfProductsPage listOfProductsPage;
    ItemDetailsPage itemDetailsPage;
    BasketPage basketPage;
    FormPage formPage;
    FinalPage finalPage;

    @BeforeEach
    public void beforeStore(){
        homePage = new HomePage(driver);
        productCategoryMenu = new ProductCategoryMenu(driver);
        listOfProductsPage = new ListOfProductsPage(driver);
        itemDetailsPage = new ItemDetailsPage(driver);
        basketPage = new BasketPage(driver);
        formPage = new FormPage(driver);
        finalPage = new FinalPage(driver);
    }

    @Test
    public void storeTest(){

        homePage.moveMouseToProductCategoryMenu();

        for(int i = 0; i < 4; i++) {
            productCategoryMenu.chooseRandomCategory();
            listOfProductsPage.clickRandomProduct();
            itemDetailsPage.clickAddToBasket(itemDetailsPage.getRandomNumberFrom1To3())
                    .checkCorrectnessOfChosenProduct()
                    .moveMouseToProductCategoryMenu();
        }
        itemDetailsPage.clickBasket();
        basketPage.detailsOfRow()
                .clickContinueButton();
        formPage.checkShippingSameBillingCheckbox()
                .createRandomUser()
                .fillTheForm()
                .itemCostValidation()
                .totalPriceValidation()
                .clickPurchase();
        finalPage.detailsOfRow();
    }


    @Test
    public void shortTestForOneProduct(){

        homePage.moveMouseToProductCategoryMenu();
        productCategoryMenu.chooseRandomCategory();
        listOfProductsPage.clickRandomProduct();
        itemDetailsPage.clickAddToBasket(itemDetailsPage.getRandomNumberFrom1To3())
                .checkCorrectnessOfChosenProduct()
                .clickBasket();
        basketPage.detailsOfRow()
                .clickContinueButton();
        formPage.checkShippingSameBillingCheckbox()
                .createRandomUser()
                .fillTheForm()
                .itemCostValidation()
                .totalPriceValidation()
                .clickPurchase();
        finalPage.detailsOfRow();

    }
}