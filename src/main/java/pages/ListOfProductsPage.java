package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ListOfProductsPage extends WebElementManipulator{

    static String correctName;

    public ListOfProductsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a.wpsc_product_title")
    private List<WebElement> listOfProducts;

    public void clickRandomProduct(){
        int size = listOfProducts.size();
        Random rand = new Random();
        int n = rand.nextInt(size) + 1;
        correctName = listOfProducts.get(n-1).getText();
        click(listOfProducts.get(n-1));
    }

}