package pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='My Account']")
    WebElement lnkMyAcoount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    //Step5
    @FindBy(linkText = "Login")
    WebElement linkLogin;

    public void clickMyAccount()
    {
        lnkMyAcoount.click();
    }
    public void clickRegister()
    {
        lnkRegister.click();
    }
    public void clickLogin(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Login_button = wait.until(ExpectedConditions.elementToBeClickable(linkLogin));
        Login_button.click();
    }

}
