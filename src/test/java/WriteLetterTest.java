import com.github.javafaker.Faker;
import com.gmail.pages.MainPage;
import com.gmail.pages.NewMessageDialogPage;
import com.gmail.pages.SigninPage;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.LoadProperties;

public class WriteLetterTest extends BaseTest {

    private String email = LoadProperties.load("email");
    private String password = LoadProperties.load("password");
    private String subject = "test write letter";

    private Faker faker = new Faker();

    @BeforeClass
    public void login() {
        SigninPage signinPage = new SigninPage(driver);
        signinPage.goSigninPage();
        signinPage.signinToGmail(email, password);
    }

    @Test
    public void writeLetter() {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);
        NewMessageDialogPage newMessageDialog = new NewMessageDialogPage(driver);
        mainPage.clickCompose();
        newMessageDialog.sendMessage(email, subject, faker.regexify("[a-z1-9]{30}"));
        softAssert.assertTrue(mainPage.waitMessageSent(), "Message sent: ");
        mainPage.waitLetter(subject);
        Reporter.log("Main page: assert letter with subject: '" + subject + "' received");
        softAssert.assertTrue(mainPage.isLetterPresent(subject), "message with subject '" + subject + "' present: ");
        softAssert.assertAll();
    }

    @AfterClass
    public void deleteLetter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectLetter(subject);
        mainPage.clickDelete();
        mainPage.waitMessageRemoved();
        mainPage.clickStarredLink();
        mainPage.clickInboxLink();
        mainPage.waitLetterRemoved(subject);
    }
}