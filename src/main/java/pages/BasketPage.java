package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.Product;

import java.util.List;

public class BasketPage extends WebElementManipulator{

    List<Product> listOfProductsFromBasket;

    public BasketPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElements();
    }

    @FindBy(css = ".product_row")
    private List<WebElement> rowsOfProducts;

    @FindBy(css = ".yourtotal")
    private WebElement yourTotal;


    public void detailsOfRow(){
        for(int i = 0; i < rowsOfProducts.size(); i++){
            List<WebElement> details = rowsOfProducts.get(i).findElements(By.tagName("td"));
            String name = details.get(1).getText();
            int quantity = Integer.parseInt(details.get(2).getText());
            int price = Integer.parseInt(details.get(3).getText());
            int totalPrice = Integer.parseInt(details.get(3).getText());
            Product product = new Product(name, quantity, price, totalPrice);
            listOfProductsFromBasket.add(product);
        }
    }

    public void ValidateBasket(){
        //asercje sprawdzajace po kolei nazwe, ilosc, cene, laczna cene.
    }
    public void validateTotalSum(){
        //asercja na yourtotal
    }


    public void waitForElements(){
        waitToBeVisible(rowsOfProducts);
    }


}
