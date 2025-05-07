package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

/*
Data is valid  - Login Success - test pass -logout
                    Login failed - test fail

Data is invalid  - Login Success - test fail
                    Login failed - test pass -logout

 */
public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider="LoginData",dataProviderClass = utilities.DataProviders.class)    //getting dataprovider from different class
    public void verify_loginDDT(String email,String pwd,String exp) {
        try {


            logger.info("***** Starting TC003_LoginDDT ******");
            System.out.println(email+""+pwd+""+exp);
            //Homepage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
            //Login Page
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            Thread.sleep(3000);
            //MyAccount
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetpage = macc.isMyAccountPageExist();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetpage == true) {
                    macc.clickLogout();
                    Assert.assertTrue(true);

                } else {
                    Assert.assertTrue(false);
                }

            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetpage ==true) {
                    macc.clickLogout();
                    Assert.assertTrue(false);  //After assertion properrly actions are not performed
                } else {
                    Assert.assertTrue(true);
                }
            }
            logger.info("****** Finished TC003_LoginDDT ******");

        }
        catch (Exception e)
        {
            logger.info(e.toString());
            Assert.fail("The testcase is failed due to"+e.toString());
        }
    }

}
