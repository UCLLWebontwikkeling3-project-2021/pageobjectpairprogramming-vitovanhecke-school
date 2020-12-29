/*
Vito Van Hecke
Rafael Hoek
 */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTestResultTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "F:\\Documents\\2020-2021\\Webontwikkeling 3\\Services\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path + "?command=Home");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void notLoggedInCantAccessPage() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        assertEquals("Home", driver.getTitle());
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        assertTrue(homePage.hasErrorMessage("Nog niet ingelogd!"));
    }

    @Test
    public void loggedInUserAccessesTestResultPage() {
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.login("vito", "lolislol");
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        assertEquals("Register Test Result", driver.getTitle());
    }

    @Test
    public void loggedInUserDoesNotFillInForm() {
        //login
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.login("vito", "lolislol");
        //
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        testRegisterPage.submit();
        assertTrue(testRegisterPage.hasErrorMessage("Vul de datum juist in!"));
    }

    @Test
    public void loggedInUserFillsInInvalidDate() {
        //login
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.login("vito", "lolislol");
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        testRegisterPage.setDateField("2020-12-35");
        testRegisterPage.submit();
        assertEquals("Register Test Result", driver.getTitle());
        assertTrue(testRegisterPage.hasErrorMessage("Vul de datum juist in!"));
    }

    @Test
    public void loggedInUserFillsInValidDate() {
        //login
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.login("vito", "lolislol");
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        testRegisterPage.setDateField("2020-12-13");
        testRegisterPage.submit();
        assertEquals("Contacts Overview", driver.getTitle());
    }

    @Test
    public void loggedInUserFillsInSameDate() {
        //login
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.login("vito", "lolislol");
        TestRegisterPage testRegisterPage = PageFactory.initElements(driver, TestRegisterPage.class);
        testRegisterPage.setDateField("2020-12-15");
        testRegisterPage.submit();
        driver.get(path + "?command=RegisterTestResult");
        testRegisterPage.setDateField("2020-12-15");
        testRegisterPage.submit();
        assertTrue(testRegisterPage.hasErrorMessage("Test result already registered"));
        assertEquals("Contacts Overview", driver.getTitle());
    }
}
