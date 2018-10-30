package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.Product;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends WebElementManipulator{

    static List<Product> listOfProductInBasket = new ArrayList<Product>();
    List<Product> listOfProductsSummary = new ArrayList<Product>();


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
            String name = getName(rowsOfProducts.get(i));
            int quantity = getQuantity(rowsOfProducts.get(i));
            double price = getPrice(rowsOfProducts.get(i));
            double totalPrice = getTotalPrice(rowsOfProducts.get(i));
//            List<WebElement> details = rowsOfProducts.get(i).findElements(By.tagName("td"));
//            String name = details.get(1).getText();
//            int quantity = Integer.parseInt(details.get(2).getAttribute("value"));
//            int price = Integer.parseInt(details.get(3).getText());
//            int totalPrice = Integer.parseInt(details.get(4).getText());
            Product product = new Product(name, quantity, price, totalPrice);
            listOfProductsSummary.add(product);
        }
        printingBasketSummary();
    }

    public String getName(WebElement element){
        return element.findElement(By.cssSelector(".wpsc_product_name")).getText();
    }

    public int getQuantity(WebElement element){
        return Integer.parseInt(element.findElement(By.cssSelector(".wpsc_product_quantity input[name='quantity']")).getAttribute("value"));
    }

    public double getPrice(WebElement element){
        String priceString = element.findElement(By.cssSelector(".wpsc_product_quantity + td")).getText().substring(1);
        return Double.parseDouble(priceString);
    }

    public double getTotalPrice(WebElement element){
        String priceString = element.findElement(By.cssSelector(".wpsc_product_price")).getText().substring(1);
        return Double.parseDouble(priceString);
    }

    public void ValidateBasket(){

    }
    public void validateTotalSum(){
        //asercja na yourtotal
    }

    public void printingBasketSummary(){
        for(Product product : listOfProductsSummary){
            System.out.println(product.getName());
            System.out.println(product.getQuantity());
            System.out.println(product.getPrice());
            System.out.println(product.getTotalPrice());
        }
    }


    public void waitForElements(){
        waitToBeVisible(rowsOfProducts);
    }


}
