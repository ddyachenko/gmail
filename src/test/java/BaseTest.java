import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.LoadProperties;

public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    public void setup() {
        String actualBrowser = LoadProperties.load("browser");
        switch (actualBrowser) {
            case "firefox":
                Reporter.log("Launching Firefox Browser");
                String driverPath = "bin/geckodriver.exe";
                System.setProperty("webdriver.firefox.driver", driverPath);
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "chrome":
                Reporter.log("Launching Chrome Browser");
                driverPath = "bin/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            default:
                break;
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}