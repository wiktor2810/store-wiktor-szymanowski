package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementManipulator extends BasePage{

    public WebElementManipulator(WebDriver driver){
        super(driver);
    }

    public void click(WebElement element){
        System.out.println("Clicking on: " + element.getText());
        waitToBeClickable(element);
        element.click();
    }

    public void move(WebElement element){
        System.out.println("Moving mouse to: " + element.getText());
        waitToBeVisible(element);
        actions.moveToElement(element).perform();
    }

    public void sendKeys(WebElement element, String text){
        System.out.println("Setting text: " + text + " to an element: " + element.getText());
        waitToBeVisible(element);
        element.sendKeys(text);
    }
}
