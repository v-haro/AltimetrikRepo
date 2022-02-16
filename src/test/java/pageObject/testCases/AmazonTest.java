package pageObject.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageObject.pages.Amazon;

import java.io.IOException;


public class AmazonTest {

    WebDriver driver;

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"Bluetooth headset"},{"34 inch LED monitor"},{"USB c dock"},{"Smart watch"}
        };
    }

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chrome/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
    }

    @Test(dataProvider ="test-data")
    public void amazonTest(String item) throws InterruptedException, IOException {
        //Creating object of home page
        Amazon amazon = new Amazon(driver);
        amazon.searchItemAmazonWebPage(item);
        amazon.orderByPriceCheapestPrice();
        amazon.saveAtributesOfArticles();
    }

    @AfterTest
    public void after(){
        //Close browser instance
        driver.quit();
    }
}