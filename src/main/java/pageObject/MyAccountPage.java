package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver)
    {
        super(driver);
    }


    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement txt_for_MyAccount;


    @FindBy(xpath = "//a[normalize-space()='My Account']")
    WebElement lnkMyAcoount;


    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    WebElement element;

    @FindBy(xpath = "//div[@class='pull-right']//a[text()='Continue']")
    WebElement continue_logout;

    public boolean isMyAccountPageExist()
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean bool = wait.until(ExpectedConditions.textToBePresentInElement(txt_for_MyAccount,"My Account"));
            return bool;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }
    }

    public void Logout()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement my_account = wait.until(ExpectedConditions.elementToBeClickable(lnkMyAcoount));
        Actions actions = new Actions(driver);
        actions.moveToElement(lnkMyAcoount).perform();
        System.out.println("Logout enabled? " + element.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    public void click_continue_to_logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continue_logout)).click();
    }

    public boolean is_txtdisplyed()
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean bool = wait.until(ExpectedConditions.textToBePresentInElement(txt_for_MyAccount,"My Account"));
            return bool;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }

    }
}
