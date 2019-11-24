package au.com.parcelpoint.automation;

import au.com.parcelpoint.automation.pageobjects.BookParcelPage;
import au.com.parcelpoint.automation.pageobjects.ReturnDetailsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Automation test for Book Parcel page.
 *

 */
public class ReturnParcelTesting {

    private static WebDriver driver;
    private static BookParcelPage bookParcelPage;
    private static ReturnDetailsPage returnDetailsPage;

    @BeforeClass
    public static void setUp() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "C:\\Pooja\\software\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://staging.parcelpoint.com.au/adidas/return/method");
        WebElement innerIFrameElement = driver.findElement(By.id("ppIframeWidget-parcelpoint-stores-widget"));
        driver.switchTo().frame(innerIFrameElement);
        driver.manage().window().maximize();
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ppIframeWidget-parcelpoint-stores-widget")));
        //Not sure why the above statement is not working - need to investigate more
        Thread.sleep(10000); //sleeping for 10 seconds
        //initialize BookParcePage
        bookParcelPage = new BookParcelPage(driver);
        returnDetailsPage = new ReturnDetailsPage(driver);
    }


    @Test
    public void shouldBeAbleToChooseParcelPickUpForValidPostCode() throws InterruptedException {
        String postCode = "2150";
        bookParcelPage.enterPostCode(postCode);
        bookParcelPage.selectStore();
        bookParcelPage.clickNextButton();
    }

    @Test
    public void fillReturnDetails() {
        String orderNumber = "AAU123245666";
        String returnReason = "Change of mind (6)";
        returnDetailsPage.fillUpTheReturnDetailsForm(orderNumber, returnReason);
        String uniqueRandomNumber = ""+(int)(Math.random()*Integer.MAX_VALUE);
        String emailId = "Pooja"+uniqueRandomNumber+"@example.com";
        returnDetailsPage.enterContactDetails("Pooja", emailId, "0435371274");
        returnDetailsPage.submitReturnDetails();

        //validate the order confirmation details
        WebElement leadPElement = driver.findElement(By.className("lead"));
        String msg = leadPElement.getText();
        Assert.assertEquals("The parcel success return message is not correct",
                "Thanks Pooja, your return has been booked!", msg);

        //validate booking summary details
        WebElement wellDiv = driver.findElements(By.className("well")).get(3);
        WebElement orderIdPElement = wellDiv.findElements(By.tagName("p")).get(0);
        String summaryOrderNumber = orderIdPElement.getText();
        //order number should match
        Assert.assertEquals("Order number doesn't match", "adidas Order ID: " + orderNumber, summaryOrderNumber);

    }


    @AfterClass
    public static void exit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
