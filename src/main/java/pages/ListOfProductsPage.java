package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ListOfProductsPage extends WebElementManipulator{


    public ListOfProductsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElements();
    }

    @FindBy(css = ".default_product_display")
    private List<WebElement> listOfProducts;

    public void clickRandomProduct(){
        int size = listOfProducts.size();
        Random rand = new Random();
        int n = rand.nextInt(size) + 1;
        click(listOfProducts.get(n-1));
    }

    public void addProductRandomNumberOfTimes(int n){
        clickRandomProduct();
        //tu w wywolaniu tej metody podamy wywolanie metody losowania randomfrom1to3
        ItemDetailsPage itemDetailsPage = new ItemDetailsPage(driver);
        itemDetailsPage.checkCorrectnessOfChosenProduct();
        for(int i = n; i > 0; i--){
            itemDetailsPage.clickAddToBasket();
        }
    }

    public void waitForElements(){
        waitToBeVisible(listOfProducts);
    }
}
