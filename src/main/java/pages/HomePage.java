package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends WebElementManipulator{

    public HomePage(WebDriver driver){
        super(driver);
        driver.get("http://store.demoqa.com/");
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu-item-15")
    private WebElement homeButton;

    @FindBy(id = "menu-item-33")
    private WebElement productCategoryButton;

    @FindBy(xpath = "//span[.='Cart']")
    private WebElement basket;

    @FindBy(css = "em[class='count']")
    private WebElement numberOfItems;

    public void clickProductCategoryMenu(){
        click(productCategoryButton);
    }

    public void clickHomeButton(){
        click(homeButton);
    }

    public void clickBasket(){
        click(basket);
    }

    public void checkNumberOfItems(int expectedNumberOfItems){
        int realNumberOfItems = Integer.parseInt(numberOfItems.getText());
        //asercja junita
    }
}
