import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listeners(TestListerner.class)
public class Amazon{

    private WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver=Browser.openBrowser();
    }

    @Test(priority = 1)
    @Parameters("inputInvalidPincode")
    public void invalidPincode(String inputPincode) throws IOException {
        Reports.createTest("Verify pincode - negative!");
        HomePage homepage = new HomePage(driver);
        homepage.InvalidPincode(inputPincode);
    }
    @Test(priority = 2)
    @Parameters("inputValidPincode")
    public void validPincode(String inputValidPincode) throws IOException, InterruptedException {
        Reports.createTest("Verify pincode - positive!");
        driver.navigate().to("https://www.amazon.in/");
        HomePage homepage = new HomePage(driver);
        homepage.ValidPincode(inputValidPincode);
    }

    @Test(priority = 3)
    public void VerifyNavigationToHomePage() throws IOException {
        Reports.createTest("Navigation to Hompage");
        HomePage homepage = new HomePage(driver);
        homepage.CustomerService();
    }

     @Test(priority = 4)
     public void InvalidAccountOpening() throws IOException {
         Reports.createTest("Invalid account opening");
         HomePage homepage = new HomePage(driver);
         homepage.HelloSignin();
     }

    @Test(priority = 5)
    public void VerifyFooterElements() throws IOException {
        Reports.createTest("Verify footer elements: About us");
        driver.navigate().to("https://www.amazon.in/");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 5000);");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        HomePage homepage = new HomePage(driver);
        homepage.AboutUs();
    }

    @Test(priority = 6)
    public void VerifySignInPageIsDisplayed() throws IOException {
         Reports.createTest("Verify Sign In page is displayed: Prime");
         driver.navigate().to("https://www.amazon.in/");
         HomePage homepage = new HomePage(driver);
         homepage.Prime();
     }

     @Test(priority = 7)
     @Parameters("inputPhone")
     public void VerifySortingIsDone(String inputPhone) throws IOException, InterruptedException {
         Reports.createTest("Verify Sorting is done");
         driver.navigate().to("https://www.amazon.in/");
         HomePage homepage = new HomePage(driver);
         homepage.verifySorting(inputPhone);
     }

    @Test(priority = 8)
    public void VerifyNavigationToProductDetailsPage() throws IOException, InterruptedException {
        Reports.createTest("Verify navigation to Product-Details-Page");
        driver.navigate().to("https://www.amazon.in/");
        HomePage homepage = new HomePage(driver);
        homepage.Hamburger();
    }

    @Test(priority = 9)
    @Parameters("inputBookName")
    public void VerifyFilterOptionInTheSearch(String inputBookName) throws IOException
    {
        Reports.createTest("Verify filter option in the Search");
        driver.navigate().to("https://www.amazon.in/");
        HomePage homepage = new HomePage(driver);
        homepage.FilterOptionInSearch(inputBookName);
    }

    @Test(priority = 10, dataProviderClass = DataProviderAmazon.class, dataProvider = "AmazonDataProvider")
    public void VerifyFilterOptions(String inputSearchItem) throws IOException
    {
        Reports.createTest("Verify filter options");
        driver.navigate().to("https://www.amazon.in/");
        HomePage homepage = new HomePage(driver);
        homepage.FilterOption(inputSearchItem);
    }

    @Test(priority = 11)
    public void VerifyAddToCart() throws IOException {
        driver.navigate().to("https://www.amazon.in/");
        HomePage homepage = new HomePage(driver);
        homepage.AddToCart("Iphone12");
    }

    @AfterTest
    public void closeBrowser() throws IOException
    {
        driver.quit();
    }

}
