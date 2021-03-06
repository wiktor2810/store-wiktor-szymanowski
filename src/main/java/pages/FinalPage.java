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

public class FinalPage extends WebElementManipulator{

    List<Product> listOfProductsInFinalStep = new ArrayList<Product>();
    double sumOfProductsInFinalStep;
    BigDecimal shippingBigDecimal;

    public FinalPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "table tbody tr")
    private List<WebElement> rowsOfProducts;

    @FindBy(css = "table + p")
    private WebElement totalAndShipping;

    public FinalPage detailsOfRow(){
        waitToBeVisible(totalAndShipping);
        for(int i = 0; i < rowsOfProducts.size(); i++){
            String name = getName(rowsOfProducts.get(i));
            int quantity = getQuantity(rowsOfProducts.get(i));
            BigDecimal price = BigDecimal.valueOf(getPrice(rowsOfProducts.get(i)));
            BigDecimal totalPrice = BigDecimal.valueOf(getTotalPrice(rowsOfProducts.get(i)));
            Product product = new Product(name, quantity, price, totalPrice);
            listOfProductsInFinalStep.add(product);
        }
        printingBasketSummary();
        validateBasket();
        validateShipping();
        validateTotalSum();
        return this;
    }


    public String getName(WebElement element){
        return element.findElement(By.cssSelector("tbody > tr > td")).getText();
    }

    public int getQuantity(WebElement element){
        return Integer.parseInt(element.findElement(By.cssSelector("tbody > tr > td + td + td")).getText());
    }

    public double getPrice(WebElement element){
        String priceString = element.findElement(By.cssSelector("tbody > tr > td + td")).getText().substring(1).replaceAll("[,]", "");
        return Double.parseDouble(priceString);
    }

    public double getTotalPrice(WebElement element){
        String priceString = element.findElement(By.cssSelector("tbody > tr > td + td + td + td")).getText().substring(1).replaceAll("[,]", "");
        return Double.parseDouble(priceString);
    }

    public void validateBasket(){
        for(Product product : listOfProductsInFinalStep){
            String name = product.getName();
            for(Product product1 : BasketPage.listOfProductsAddedToBasket){
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
        String totalSum = totalAndShipping.getText().substring(totalAndShipping.getText().lastIndexOf("$")+1).replace("," , "");
        BigDecimal totalSumBigDecimal = new BigDecimal(Double.parseDouble(totalSum));
        sumOfProductsInFinalStep = 0.0;
        for(Product product : listOfProductsInFinalStep){
            sumOfProductsInFinalStep += (product.getTotalPrice().doubleValue());
        }
        BigDecimal sumOfProductsInBasketBigDecimal = new BigDecimal(sumOfProductsInFinalStep).add(shippingBigDecimal);
        Assertions.assertEquals(sumOfProductsInBasketBigDecimal, totalSumBigDecimal);
    }

    public void printingBasketSummary(){
        for(Product product : listOfProductsInFinalStep){
            System.out.println(product.getName());
            System.out.println(product.getQuantity());
            System.out.println(product.getPrice());
            System.out.println(product.getTotalPrice());
        }
    }

    public void validateShipping(){
        String shipping = totalAndShipping.getText().substring(totalAndShipping.getText().indexOf("$")+1, totalAndShipping.getText().indexOf("\n"));
        shippingBigDecimal = new BigDecimal(Double.parseDouble(shipping));
        Assertions.assertEquals(shippingBigDecimal, FormPage.shippingCostBigDecimal);
    }

}