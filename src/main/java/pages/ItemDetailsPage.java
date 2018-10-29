package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemDetailsPage extends WebElementManipulator{

    List<WebElement> listOfWebElements;

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

    public int getRandomNumberFrom1To3(){
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        return n;
    }

    public void clickAddToBasket(int n){
        for(int i = n; i > 0; i--) {
            addProductToBasket();
            click(addToBasket);
        }
    }



    public void checkCorrectnessOfChosenProduct(){

        String chosenProduct = ListOfProductsPage.correctName;
        String expectedProduct = prodTitle.getText();
        System.out.println("chosenProduct : " + chosenProduct + " while expectedProduct : " + expectedProduct);
    }


    public void addProductToBasket(){
        String name = prodTitle.getText();
        System.out.println("ItemDetailsPage: " + name);
        System.out.println("ItemDetailsPage: " + currentPrice.getText());
        String priceAfterSubstring = currentPrice.getText().substring(1);
        System.out.println(priceAfterSubstring);
        double price = Double.parseDouble(priceAfterSubstring);
        for(Product product : BasketPage.listOfProductInBasket){
            if(name.equals(product.getName())){
                product.setQuantity(product.getQuantity() + 1);
                product.setPrice(product.getTotalPrice() + price);
            } else {
                BasketPage.listOfProductInBasket.add(new Product(name, price));
            }
        }
    }

    public void waitForElements(){
        listOfWebElements = new ArrayList<WebElement>();
        listOfWebElements.add(prodTitle);
        listOfWebElements.add(addToBasket);
        waitToBeVisible(listOfWebElements);
    }



}
