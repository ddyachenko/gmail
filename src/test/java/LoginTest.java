import com.gmail.pages.MainPage;
import com.gmail.pages.SigninPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import utils.LoadProperties;

public class LoginTest extends BaseTest {

    private String email = LoadProperties.load("email");
    private String password = LoadProperties.load("password");

    @Test
    public void loginTest() {
        SigninPage signinPage = new SigninPage(driver);
        MainPage mainPage = new MainPage(driver);

        signinPage.goSigninPage();
        signinPage.signinToGmail(email, password);
        Reporter.log("Main page: assert we are inside of test mail");
        Assert.assertTrue(mainPage.isComposeButtonPresent(), "Compose Button Present: ");
    }
}