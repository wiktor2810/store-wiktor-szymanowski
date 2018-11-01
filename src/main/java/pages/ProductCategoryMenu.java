package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class ProductCategoryMenu extends WebElementManipulator{

    WebElement[] listOfCategories;

    public ProductCategoryMenu(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu-item-34")
    private WebElement productCategory1;

    @FindBy(id = "menu-item-35")
    private WebElement productCategory2;

    @FindBy(id = "menu-item-36")
    private WebElement productCategory3;

    @FindBy(id = "menu-item-37")
    private WebElement productCategory4;

    @FindBy(id = "menu-item-38")
    private WebElement productCategory5;

    @FindBy(id = "menu-item-39")
    private WebElement productCategory6;

    public void chooseRandomCategory(){
        listOfCategories = new WebElement[]{productCategory1, productCategory2, productCategory3, productCategory4, productCategory5, productCategory6 };
        Random rand = new Random();
        int n = rand.nextInt(5) + 0;
        click(listOfCategories[n]);
    }
}