package pages;

import factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import template.User;

import java.math.BigDecimal;

public class FormPage extends WebElementManipulator{

    User user;

    public FormPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "#shippingSameBilling")
    private WebElement shippingSameBillingCheckbox;

    @FindBy(css = "input[title='billingfirstname']")
    private WebElement firstName;

    @FindBy(css = "input[title='billinglastname']")
    private WebElement lastName;

    @FindBy(css = "textarea[title='billingaddress']")
    private WebElement address;

    @FindBy(css = "input[title='billingcity']")
    private WebElement city;

    @FindBy(css = "input[title='billingstate']")
    private WebElement state;

    @FindBy(css = "#uniform-wpsc_checkout_form_7")
    private WebElement country;

    @FindBy(css = "select[title='billingcountry'] option[value='PL']")
    private WebElement poland;

    @FindBy(css = "input[title='billingphone']")
    private WebElement phone;

    @FindBy(css = ".input-button-buy")
    private WebElement purchase;

    @FindBy(css = ".total_item .checkout-shipping .pricedisplay")
    private WebElement itemCost;

    @FindBy(css = ".total_shipping .checkout-shipping .pricedisplay")
    private WebElement totalShipping;

    @FindBy(css = "#checkout_total .pricedisplay")
    private WebElement totalPrice;

    public void fillTheForm(){
        sendKeys(firstName, user.getFirstName());
        sendKeys(lastName, user.getLastName());
        sendKeys(address, user.getAddress());
        sendKeys(city, user.getCity());
        sendKeys(state, user.getStateProvince());
        country.click();
        click(poland);
        sendKeys(phone, String.valueOf(user.getPhone()));
    }

    public void checkShippingSameBillingCheckbox(){
        click(shippingSameBillingCheckbox);
    }

    public void createRandomUser(){
        UserFactory userFactory = new UserFactory();
        user = userFactory.randomUser();
    }

    public void clickPurchase(){
        click(purchase);
    }

    public void itemCostValidation(){
        BigDecimal itemCostBigDecimal = new BigDecimal(Double.parseDouble(itemCost.getText().substring(1).replaceAll("[,]", "")));
        Assertions.assertEquals(itemCostBigDecimal, BigDecimal.valueOf(BasketPage.sumOfProductsInBasket));
    }

    public void totalPriceValidation(){
        BigDecimal itemCostBigDecimal = new BigDecimal(Double.parseDouble(itemCost.getText().substring(1).replaceAll("[,]", "")));
        BigDecimal shippingCostBigDecimal = new BigDecimal(Double.parseDouble(totalShipping.getText().substring(1).replaceAll("[,]", "")));
        BigDecimal sumOfShippingAndItemCost = itemCostBigDecimal.add(shippingCostBigDecimal);
        BigDecimal expectedTotal = new BigDecimal(Double.parseDouble(totalPrice.getText().substring(1).replaceAll("[,]", "")));
        Assertions.assertEquals(sumOfShippingAndItemCost, expectedTotal);
    }


}
