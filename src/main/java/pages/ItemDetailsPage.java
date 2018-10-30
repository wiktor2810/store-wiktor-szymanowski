package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemDetailsPage extends WebElementManipulator{

    List<WebElement> listOfWebElements;
    int randomNumberFrom1to3;

    public ItemDetailsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElements();
    }


    @FindBy(css = ".prodtitle")
    private WebElement prodTitle;

    @FindBy(css = "input[value='Add To Cart']")
    private WebElement addToBasket;

    @FindBy(css = ".currentprice")
    private WebElement currentPrice;

    @FindBy(id = "menu-item-33")
    private WebElement productCategoryButton;

    @FindBy(xpath = "//span[.='Cart']")
    private WebElement basket;


    public int getRandomNumberFrom1To3(){
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        System.out.println("wylosowana liczba od 1 do 3 to: " + n);
        randomNumberFrom1to3 = n;
        return n;
    }

    public void clickAddToBasket(int n){
        for(int i = n; i > 0; i--) {
            addProductToBasket();
            click(addToBasket);
            waitToBeClickable(addToBasket);
            System.out.println("clickAddToBasket");
        }
    }



    public void checkCorrectnessOfChosenProduct(){

        String chosenProduct = ListOfProductsPage.correctName;
        String expectedProduct = prodTitle.getText();
        System.out.println("chosenProduct : " + chosenProduct + " while expectedProduct : " + expectedProduct);
    }


    public void addProductToBasket(){
        String name = prodTitle.getText();
        String priceAfterSubstring = currentPrice.getText().substring(1);
        System.out.println(priceAfterSubstring);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceAfterSubstring));

        boolean isItNewProductInBasket = false;

        if(BasketPage.listOfProductsAddedToBasket.size() == 0){
            BasketPage.listOfProductsAddedToBasket.add(new Product(name, price));
            System.out.println("000new product");
            showDetailsAboutBasket();
            return;
        }

        for(Product product : BasketPage.listOfProductsAddedToBasket){
            if(name.equals(product.getName())){
                System.out.println("111name of chosen product is equal to that in basket");
                product.setQuantity(product.getQuantity() + 1);
                product.setTotalPrice(product.getTotalPrice().add(price));
                isItNewProductInBasket = false;
                break;
            } else {
                isItNewProductInBasket = true;
            }
        }

        if(isItNewProductInBasket){
            BasketPage.listOfProductsAddedToBasket.add(new Product(name, price));
            System.out.println("222new product cuz name is different to that in a list");
        }

        showDetailsAboutBasket();
    }

    public void showDetailsAboutBasket(){
        for(int j = 0; j < BasketPage.listOfProductsAddedToBasket.size(); j++) {
            System.out.println("nazwa produktu w baskiecie: " + BasketPage.listOfProductsAddedToBasket.get(j).getName());
            System.out.println("quantity tego produktu w baskecie: " + BasketPage.listOfProductsAddedToBasket.get(j).getQuantity());
            System.out.println("price tego produktu w baskecie: " + BasketPage.listOfProductsAddedToBasket.get(j).getPrice());
            System.out.println("Totalprice tego produktu w baskecie to: " + BasketPage.listOfProductsAddedToBasket.get(j).getTotalPrice());
        }
    }

    public void moveMouseToProductCategoryMenu(){
        move(productCategoryButton);
    }

    public void clickBasket(){
        click(basket);
    }

    public void waitForElements(){
        listOfWebElements = new ArrayList<WebElement>();
        listOfWebElements.add(prodTitle);
        listOfWebElements.add(addToBasket);
        waitToBeVisible(listOfWebElements);
    }



}
