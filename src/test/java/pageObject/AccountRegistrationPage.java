package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountRegistrationPage extends BasePage{
    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    public void setTxtFirstName(String fname)
    {
        txtFirstName.sendKeys(fname);
    }

    public void setTxtLastname(String lname)
    {
        txtLastname.sendKeys(lname);
    }

    public void setTxtEmail(String email)
    {
        txtEmail.sendKeys(email);
    }

    public void setTxtTelephone(String tel)
    {
        txtTelephone.sendKeys(tel);
    }

    public void setTxtPassword(String pwd)
    {
        txtPassword.sendKeys(pwd);
    }

    public void setTxtConfirmPassword(String pwd)
    {
        txtConfirmPassword.sendKeys(pwd);
    }

    public void setChkdPolicy()
    {
        chkdPolicy.click();
    }

    public void setBtnContinue()
    {
        //sol1
        //btnContinue.click();
        //Sol2
        //btnContinue.submit();

        //sol3
        /*
        Actions act = new Actions(driver);
        act.moveToElement(btnContinue).click().perform();
         */

        //sol4
        /*
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("argument[0].click();",btnContinue);
         */

        //sol5
        //btnContinue.sendKeys(Keys.RETURN);

        //sol6
        //WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //mywait.until(ExpectedConditions.elementToBeClickable(btnContinue));
        btnContinue.click();


    }
    public String getConfirmationMessage()
    {
        try{
            return (msgConfirmation.getText());
        }
        catch (Exception e)
        {
            return (e.getMessage());
        }
    }
}
