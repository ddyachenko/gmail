import com.github.javafaker.Faker;
import com.gmail.pages.MainPage;
import com.gmail.pages.NewMessageDialogPage;
import com.gmail.pages.SigninPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.LoadProperties;

public class RemoveLetterTest extends BaseTest {

    private String email = LoadProperties.load("email");
    private String password = LoadProperties.load("password");
    private Faker faker = new Faker();
    private String subject = "test removal letter";

    @BeforeClass
    public void loginAndSendLetter() {
        SigninPage signinPage = new SigninPage(driver);
        MainPage mainPage = new MainPage(driver);
        NewMessageDialogPage newMessageDialog = new NewMessageDialogPage(driver);

        signinPage.goSigninPage();
        signinPage.signinToGmail(email, password);
        mainPage.clickCompose();
        newMessageDialog.sendMessage(email, subject, faker.regexify("[a-z1-9]{30}"));
        mainPage.waitMessageSent();
    }

    @Test
    public void removeLetter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickInboxLink();
        mainPage.waitLetter(subject);
        mainPage.selectLetter(subject);
        mainPage.clickDelete();
        mainPage.waitMessageRemoved();
        mainPage.waitLetterRemoved(subject);
        mainPage.clickStarredLink();
        mainPage.clickInboxLink();
        Reporter.log("Main page: assert letter with subject: '" + subject + "' removed");
        Assert.assertTrue(mainPage.isLetterNotPresent(subject), "message present: ");
    }
}