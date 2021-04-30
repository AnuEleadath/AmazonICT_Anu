import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListerner implements ITestListener {

    public void onTestFailure(ITestResult result) {
        System.out.println("Testcase is failed and Screenshot is captured");
    }

    public void onTestSuccess (ITestResult result) {
        System.out.println("Testcase is passed and Screenshot is captured");
    }

}