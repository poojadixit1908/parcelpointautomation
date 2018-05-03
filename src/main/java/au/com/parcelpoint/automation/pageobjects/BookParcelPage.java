package au.com.parcelpoint.automation.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * The page object model (POM) using Page Factory for book parcel page.
 */
public class BookParcelPage {

    private WebDriver driver;

    @FindBy(id = "ppSearchInput")
    private WebElement searchInputTextBoxElement;

    public BookParcelPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterPostCode(String postCode) {
        searchInputTextBoxElement.sendKeys(postCode);
        searchInputTextBoxElement.sendKeys(Keys.RETURN);
    }

    public void selectStore() {
        WebElement storeListContainerDiv = driver.findElement(By.className("stores-list-container"));
        WebElement ul = storeListContainerDiv.findElement(By.tagName("ul"));
        WebElement firstStore = ul.findElements(By.tagName("li")).get(0);
        WebElement selectStoreButton = firstStore.findElement(By.className("select"));
        selectStoreButton.click();
    }

    public void clickNextButton() {
        driver.switchTo().defaultContent();
        WebElement nextButtonElement = driver.findElement(By.id("parcelPointSubmitButton"));
        nextButtonElement.click();
    }


}
