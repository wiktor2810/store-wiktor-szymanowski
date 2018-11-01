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
    static BigDecimal shippingCostBigDecimal;

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

    @FindBy(css = "input[title='billingemail']")
    private WebElement email;

    public FormPage fillTheForm(){
        sendKeys(email, user.getEmail());
        sendKeys(firstName, user.getFirstName());
        sendKeys(lastName, user.getLastName());
        sendKeys(address, user.getAddress());
        sendKeys(city, user.getCity());
        sendKeys(state, user.getStateProvince());
        country.click();
        click(poland);
        sendKeys(phone, String.valueOf(user.getPhone()));
        return this;
    }

    public FormPage checkShippingSameBillingCheckbox(){
        click(shippingSameBillingCheckbox);
        return this;
    }

    public FormPage createRandomUser(){
        UserFactory userFactory = new UserFactory();
        user = userFactory.randomUser();
        return this;
    }

    public FormPage clickPurchase(){
        click(purchase);
        return this;
    }

    public FormPage itemCostValidation(){
        waitToBeVisible(itemCost);
        double itemCostDouble = Double.parseDouble(itemCost.getText().substring(1).replaceAll("[,]", ""));
        Assertions.assertEquals(BigDecimal.valueOf(itemCostDouble), BigDecimal.valueOf(BasketPage.sumOfProductsInBasket));
        return this;
    }

    public FormPage totalPriceValidation(){
        waitToBeVisible(totalPrice);
        BigDecimal itemCostBigDecimal = new BigDecimal(Double.parseDouble(itemCost.getText().substring(1).replaceAll("[,]", "")));
        shippingCostBigDecimal = new BigDecimal(Double.parseDouble(totalShipping.getText().substring(1).replaceAll("[,]", "")));
        BigDecimal sumOfShippingAndItemCost = itemCostBigDecimal.add(shippingCostBigDecimal);
        BigDecimal expectedTotal = new BigDecimal(Double.parseDouble(totalPrice.getText().substring(1).replaceAll("[,]", "")));
        Assertions.assertEquals(sumOfShippingAndItemCost, expectedTotal);
        return this;
    }


}