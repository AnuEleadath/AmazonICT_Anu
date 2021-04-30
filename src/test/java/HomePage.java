import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    private WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    //**Enter pincode (For TestCase 1 & 2)**
    //Xpath of select address tab in amazon home page
    private By SelectYourAddress = By.id("nav-global-location-popover-link");
    //Xpath of text field to enter pincode
    private By EnterPincode = By.id("GLUXZipUpdateInput");
    //Xpath of Apply button
    private By Apply = By.xpath("//span[@id='GLUXZipUpdate' and contains(@class,'button-span12')]");
    //Xpath of error message
    private By message = By.id("GLUXZipError");

    public void InvalidPincode(String pincode) throws IOException {
        driver.findElement(SelectYourAddress).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(EnterPincode).sendKeys(pincode);
        driver.findElement(Apply).click();
        driver.findElement(message).click();
        Reports.extentTest.log(Status.INFO,"Pincode Entered is: "+pincode+" Error message 'Please enter a valid pincode' is displayed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }
    public void ValidPincode(String pincode) throws IOException, InterruptedException {
        driver.findElement(SelectYourAddress).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(EnterPincode).sendKeys(pincode);
        driver.findElement(Apply).click();
        Thread.sleep(5000);
        //locateElement(By.xpath("//span[@id='GLUXZipUpdate' and contains(@class,'button-span12')]"));
        Reports.extentTest.log(Status.INFO, "Pincode Entered is: "+pincode+" The entered pincode is populated in the home screen.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //**Verify navigation to Home page (For TestCase 6)**
    //Xpath for Customer Service tab
    private By CustomerService = By.linkText("Customer Service");
    //Xpath of AmazonLogo
    private By AmazonLogo = By.id("nav-logo-sprites");
    public void CustomerService() throws IOException {
        driver.findElement(CustomerService).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(AmazonLogo).click();
        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actualURL= driver.getTitle();
        Assert.assertEquals(expected, actualURL);
        System.out.println("Assertion!");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Reports.extentTest.log(Status.INFO,"Amazon home is displayed after clicking on the Amazon logo", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //**Invalid account opening (For TestCase 8)**
    //Xpath for Hello Sign in
    private By HelloSignin = By.id("nav-link-accountList");
    //Xpath for Create your Amazon Account
    private By CreateAccount = By.linkText("Create your Amazon account");
    //Xpath for Continue Button
    private By Continue = By.id("continue");
    public void HelloSignin() throws IOException {
        driver.findElement(HelloSignin).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(CreateAccount).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(Continue).click();
        Reports.extentTest.log(Status.INFO,"Enter your name, Enter your mobile number, Enter your password messages are displayed in Red colour.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //**Verify footer elements (For TestCase 13)**
    //Xpath for Hello Sign in
    private By AboutUs = By.linkText("About Us");
    public void AboutUs() throws IOException {
        driver.findElement(AboutUs).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String actualURL= driver.getTitle();
        String expected = "Amazon India transforms small business in India";
        Assert.assertEquals(expected, actualURL);
        System.out.println("Assertion!");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Reports.extentTest.log(Status.INFO,"User is navigated to new page https://www.aboutamazon.in/.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //**Verify sign in page is displayed (For TestCase 7)**
    //Xpath for Prime tab
    private By Prime = By.id("nav-link-prime");
    //Xpath for Login to join Prime
    private By LoginToJoinPrime = By.linkText("Login to join Prime");
    public void Prime() throws IOException {
        driver.findElement(Prime).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(LoginToJoinPrime).click();
        Reports.extentTest.log(Status.INFO,"Sign-In header is present.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }


    //**Verify sorting is done  (For TestCase 10)**
    //Xpath for Search Space
    private By ClickSearch = By.id("twotabsearchtextbox");
    //Xpath for Submitting Search option
    private By SubmitSearch = By.id("nav-search-submit-button");
    //Xpath for Sort by Option
    private By SortBy = By.className("a-dropdown-label");
    //Xpath for Price:High to low
    private By PriceHighToLow = By.id("s-result-sort-select_2");
    public void verifySorting(String Phone) throws IOException, InterruptedException {
        driver.findElement(ClickSearch).sendKeys(Phone);
        driver.findElement(SubmitSearch).click();
        driver.findElement(SortBy).click();
        driver.findElement(PriceHighToLow).click();
        Thread.sleep(3000);
        Reports.extentTest.log(Status.INFO,"Items are sorted by Price high to low", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //**Verify Verify navigation to product details page (For TestCase 11)**
    //Xpath for Hello Sign in
    private By HamburgerIcon = (By.className("hm-icon-label"));
    //Xpath for Category
    private By Category = By.linkText("Echo & Alexa");
    //Xpath for Sub-Category
    private By SubCategory = By.linkText("All-new Echo Dot (4th Gen)");
    public void Hamburger() throws IOException, InterruptedException {
        driver.findElement(HamburgerIcon).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(Category).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(SubCategory).click();
        Thread.sleep(3000);
        Reports.extentTest.log(Status.INFO,"Product details page is displayed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //**Verify  filter option in the Search (For TestCase 15)**
    //Xpath for DropDown
    private By DropdownSearch = By.id("searchDropdownBox");
    //Xapth for the text field
    private By EnterTextToSearch = By.id("twotabsearchtextbox");
    //Xpath for the Search button
    private By SearchButton = By.id("nav-search-submit-button");
    public void FilterOptionInSearch(String BookName) throws IOException {
        driver.findElement(DropdownSearch).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //Create the object of the Select class
        Select select = new Select(driver.findElement(By.id("searchDropdownBox")));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //Select the option using the visible text
        select.selectByVisibleText("Books");
        //Search for the book Da Vinci code
        driver.findElement(EnterTextToSearch).sendKeys(BookName);
        //Click on Search Button
        driver.findElement(SearchButton).click();
        Reports.extentTest.log(Status.INFO,"Filter option with category BOOK and search option with book 'Da Vinci code' is performed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //**Verify filter options(TestCase 14)**
    //Xpath for Search Space
    private By ClickSearchButton = By.id("twotabsearchtextbox");
    //Xpath for Submitting Search option
    private By SubmitSearchButton = By.id("nav-search-submit-button");
    //Xpath for filter
    private By FilterCustomerReview = By.linkText("₹40,000 – ₹50,000");
    public void FilterOption(String Phone) throws IOException {
        driver.findElement(ClickSearch).sendKeys(Phone);
        driver.findElement(SubmitSearchButton).click();
        Reports.extentTest.log(Status.INFO,"Search result count before filter option", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        driver.findElement(FilterCustomerReview).click();
        Reports.extentTest.log(Status.INFO,"Filter is applied and the search result count is updated", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }


    //**Validate Adding item to cart(TestCase 4)**
    //Xpath for Search Space
    private By ClickOnSearch = By.id("twotabsearchtextbox");
    //Xpath for Submitting Search option
    private By SubmitOnSearch = By.id("nav-search-submit-button");
    //Xpath to select the item
    private By SelectItem = By.linkText("New Apple iPhone 12 (128GB) - Blue");
    //Xpath for Add to cart
    private By AddToCart = By.id("add-to-cart-button");
    //Xpath for Cart
    private By Cart = By.className("nav-cart-icon nav-sprite");
    //Xpath for quantity
    private By Quantity = By.id("quantity");
    public void AddToCart(String Phone) throws IOException {
        driver.findElement(ClickOnSearch).sendKeys(Phone);
        driver.findElement(SubmitOnSearch).click();
        driver.findElement(SelectItem).click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                driver.findElement(AddToCart).click();
            }
        }
    }
}