import org.testng.annotations.DataProvider;

public class DataProviderAmazon {

    @DataProvider(name = "AmazonDataProvider")

    public Object[][] dpMethod() {

        return new Object[][]{{"Laptop"}};

    }
}