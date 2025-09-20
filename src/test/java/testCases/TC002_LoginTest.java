package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_login()
    {
        logger.info("******* Starting  TC002_LoginTest *****");

        try {
            //HomePage
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();
            //Login Page
            LoginPage lp = new LoginPage(getDriver());
            lp.setEmail(p.getProperty("email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();

            //MyAccount
            MyAccountPage mycc = new MyAccountPage(getDriver());
            boolean login_success = mycc.is_txtdisplyed();
            if(login_success)
            {
                Assert.assertTrue(true);
            }
            else
                Assert.assertTrue(false);

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            //Here it comes to catch block means it should fail
            Assert.fail();
        }
        logger.info("*****Finished TC_002_LoginTest*****");

    }

}
