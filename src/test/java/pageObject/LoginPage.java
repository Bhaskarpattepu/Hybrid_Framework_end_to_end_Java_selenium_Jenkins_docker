package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "input[@id='Login']")
    WebElement btnLogin;

    public void setEmail(String email)
    {
        txtEmailAdress.sendKeys(email);
    }

    public void setTxtPassword(String pwd)
    {
        txtPassword.sendKeys(pwd);
    }

    public void clickLogin()
    {
        btnLogin.click();
    }


}
