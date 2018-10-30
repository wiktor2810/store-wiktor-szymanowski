package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductCategoryMenu extends WebElementManipulator{

    WebElement[] listOfCategories;
    List<WebElement> listOfWebElements;

    public ProductCategoryMenu(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        waitForElements();
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

    public void waitForElements(){
        listOfWebElements = new ArrayList<WebElement>();
        listOfWebElements.add(productCategory1);
        listOfWebElements.add(productCategory2);
        listOfWebElements.add(productCategory3);
        listOfWebElements.add(productCategory4);
        listOfWebElements.add(productCategory5);
        listOfWebElements.add(productCategory6);
        waitToBeVisible(listOfWebElements);
    }



}
