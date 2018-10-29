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
        System.out.println("wylosowana liczba od 1 do 3 to: " + n);
        return n;
    }

    public void clickAddToBasket(int n){
        for(int i = n; i > 0; i--) {
            addProductToBasket();
            click(addToBasket);
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
        System.out.println("ItemDetailsPage: " + name);
        System.out.println("ItemDetailsPage: " + currentPrice.getText());
        String priceAfterSubstring = currentPrice.getText().substring(1);
        System.out.println(priceAfterSubstring);
        double price = Double.parseDouble(priceAfterSubstring);
        for(Product product : BasketPage.listOfProductInBasket){
            if(name.equals(product.getName())){
                product.setQuantity(product.getQuantity() + 1);
                product.setTotalPrice(product.getTotalPrice() + price);
                showDetailsAboutBasket();
            } else {
                BasketPage.listOfProductInBasket.add(new Product(name, price));
                showDetailsAboutBasket();
            }
        }
        if(BasketPage.listOfProductInBasket.size() == 0){
            BasketPage.listOfProductInBasket.add(new Product(name, price));
            showDetailsAboutBasket();
        }

    }

    public void showDetailsAboutBasket(){
        for(int j = 0; j < BasketPage.listOfProductInBasket.size(); j++) {
            System.out.println("nazwa produktu w baskiecie: " + BasketPage.listOfProductInBasket.get(j).getName());
            System.out.println("quantity tego produktu w baskecie: " + BasketPage.listOfProductInBasket.get(j).getQuantity());
            System.out.println("price tego produktu w baskecie: " + BasketPage.listOfProductInBasket.get(j).getPrice());
            System.out.println("Totalprice tego produktu w baskecie to: " + BasketPage.listOfProductInBasket.get(j).getTotalPrice());
        }
    }

    public void waitForElements(){
        listOfWebElements = new ArrayList<WebElement>();
        listOfWebElements.add(prodTitle);
        listOfWebElements.add(addToBasket);
        waitToBeVisible(listOfWebElements);
    }



}
