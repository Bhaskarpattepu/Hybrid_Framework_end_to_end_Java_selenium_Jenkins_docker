package pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnkMyAcoount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement lnkRegister;

    public void clickMyAccount()
    {
        lnkMyAcoount.click();
    }
    public void clickRegister()
    {
        lnkRegister.click();
    }

}
