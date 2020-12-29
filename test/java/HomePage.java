/*
Vito Van Hecke
Rafael Hoek
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {
    @FindBy(id = "userid")
    private WebElement userIdField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "Login")
    private WebElement loginButton;


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(path + "?command=Home");
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setUserIdField(String userId) {
        userIdField.clear();
        userIdField.sendKeys(userId);
    }

    public void login(String username, String password) {
        userIdField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
