package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public int getRandomNumberFrom1To3(){
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        return n;
    }

    public void clickAddToBasket(){
        click(addToBasket);
    }

    public void checkCorrectnessOfChosenProduct(){
        //asercja ale na junit i jak pobrac nazwe elementu z poprzedniej strony, chyba ta metoda musi przyjac stringa w parametrze

    }



    public void waitForElements(){
        listOfWebElements = new ArrayList<WebElement>();
        listOfWebElements.add(prodTitle);
        listOfWebElements.add(addToBasket);
        waitToBeVisible(listOfWebElements);
    }



}
