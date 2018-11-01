package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketPage extends WebElementManipulator{

    static List<Product> listOfProductsAddedToBasket = new ArrayList<Product>();
    List<Product> listOfProductsInBasket = new ArrayList<Product>();
    static double sumOfProductsInBasket;


    public BasketPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".product_row")
    private List<WebElement> rowsOfProducts;

    @FindBy(css = ".yourtotal .pricedisplay")
    private WebElement yourTotal;

    @FindBy(css = ".step2")
    private WebElement continueButton;


    public BasketPage detailsOfRow(){
        for(int i = 0; i < rowsOfProducts.size(); i++){
            String name = getName(rowsOfProducts.get(i));
            int quantity = getQuantity(rowsOfProducts.get(i));
            BigDecimal price = BigDecimal.valueOf(getPrice(rowsOfProducts.get(i)));
            BigDecimal totalPrice = BigDecimal.valueOf(getTotalPrice(rowsOfProducts.get(i)));
            Product product = new Product(name, quantity, price, totalPrice);
            listOfProductsInBasket.add(product);
        }
        printingBasketSummary();
        validateBasket();
        validateTotalSum();

        return this;
    }

    public String getName(WebElement element){
        return element.findElement(By.cssSelector(".wpsc_product_name")).getText();
    }

    public int getQuantity(WebElement element){
        return Integer.parseInt(element.findElement(By.cssSelector(".wpsc_product_quantity input[name='quantity']")).getAttribute("value"));
    }

    public double getPrice(WebElement element){
        String priceString = element.findElement(By.cssSelector(".wpsc_product_quantity + td")).getText().substring(1).replaceAll("[,]", "");
        return Double.parseDouble(priceString);
    }

    public double getTotalPrice(WebElement element){
        String priceString = element.findElement(By.cssSelector(".wpsc_product_price")).getText().substring(1).replaceAll("[,]", "");
        return Double.parseDouble(priceString);
    }

    public void validateBasket(){
        for(Product product : listOfProductsInBasket){
            String name = product.getName();
            for(Product product1 : listOfProductsAddedToBasket){
                String name1 = product1.getName();
                if(name.equals(name1)){
                    int quantity = product.getQuantity();
                    int quantity1 = product1.getQuantity();
                    BigDecimal price = product.getPrice();
                    BigDecimal price1 = product1.getPrice();
                    BigDecimal totalPrice = product.getPrice();
                    BigDecimal totalPrice1 = product1.getPrice();
                    Assertions.assertEquals(quantity, quantity1);
                    Assertions.assertEquals(price, price1);
                    Assertions.assertEquals(totalPrice, totalPrice1);
                }
            }
        }
    }

    public void validateTotalSum(){
        BigDecimal totalSum = new BigDecimal(Double.parseDouble(yourTotal.getText().substring(1).replaceAll("[,]", "")));
        sumOfProductsInBasket = 0.0;
        for(Product product : listOfProductsInBasket){
            sumOfProductsInBasket += (product.getTotalPrice().doubleValue());
        }
        BigDecimal sumOfProductsInBasketBigDecimal = new BigDecimal(sumOfProductsInBasket);
        Assertions.assertEquals(sumOfProductsInBasketBigDecimal, totalSum);
    }

    public void printingBasketSummary(){
        for(Product product : listOfProductsInBasket){
            System.out.println(product.getName());
            System.out.println(product.getQuantity());
            System.out.println(product.getPrice());
            System.out.println(product.getTotalPrice());
        }
    }

    public BasketPage clickContinueButton(){
        click(continueButton);
        return this;
    }


}