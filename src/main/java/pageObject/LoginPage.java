package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Step5
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmailAdress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement linkLogin;

    public void setEmail(String email)
    {
        if(email != null)
        {
        txtEmailAdress.clear();
        txtEmailAdress.sendKeys(email);
        }

    }
    public void setPassword(String pwd)
    {
        if(pwd != null)
        {
            txtPassword.clear();
            txtPassword.sendKeys(pwd);
        }
    }

    public void clickLogin()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Login_button = wait.until(ExpectedConditions.elementToBeClickable(linkLogin));
        Login_button.click();
    }
}
