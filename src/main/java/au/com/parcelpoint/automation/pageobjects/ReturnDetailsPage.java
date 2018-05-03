package au.com.parcelpoint.automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ReturnDetailsPage {

    private WebDriver driver;

    @FindBy(id = "order-number-returns")
    private WebElement orderNumberTextBox;

    @FindBy(id = "retailerReturnsReasonsId")
    private WebElement reasonDropDown;

    @FindBy(id = "delivery-name-createAccount")
    private WebElement deliveryNameNewAccount;

    @FindBy(id = "delivery-email-createAccount")
    private WebElement deliveryEmailNewAccount;

    @FindBy(id = "delivery-mobile-createAccount")
    private WebElement deliveryMobileNewAccount;

    @FindBy(id = "submitReturnsForm")
    private WebElement nextButton;

    public ReturnDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillUpTheReturnDetailsForm(String orderId, String reasonOptionValue) {
        orderNumberTextBox.sendKeys(orderId);
        Select select = new Select(reasonDropDown);
        select.selectByVisibleText(reasonOptionValue);
    }

    public void enterContactDetails(String newUserName, String newUserEmail, String newUserMobile) {
        deliveryNameNewAccount.sendKeys(newUserName);
        deliveryEmailNewAccount.sendKeys(newUserEmail);
        deliveryMobileNewAccount.sendKeys(newUserMobile);
    }

    public void submitReturnDetails() {
        nextButton.click();
    }
}
