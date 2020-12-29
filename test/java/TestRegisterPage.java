/*
Vito Van Hecke
Rafael Hoek
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestRegisterPage extends Page {
    @FindBy(id = "registerTestResult")
    private WebElement button;

    @FindBy(id = "date")
    private WebElement dateField;

    public TestRegisterPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath() + "?command=RegisterTestResult");
    }

    public void submit() {
        button.click();
    }

    public void setDateField(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return (message.equals(errorMsg.getText()));
    }

}
