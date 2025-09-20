package testCases;

import org.testng.Assert;
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
    @Test(dataProvider="LoginData",dataProviderClass = utilities.DataProviders.class,groups="Datadriven")    //getting dataprovider from different class
    public void verify_loginDDT(String email,String pwd,String exp) {
        try {
            logger.info("***** Starting TC003_LoginDDT ******");
            System.out.println(email+""+pwd+""+exp);
            //Homepage
            HomePage hp = new HomePage(getDriver());
            hp.clickMyAccount();
            hp.clickLogin();
            //Login Page
            LoginPage lp = new LoginPage(getDriver());
            lp.setEmail(email);
            lp.setPassword(pwd );
            lp.clickLogin();
            MyAccountPage myacc = new MyAccountPage(getDriver());
            String Expected_Result=exp;
            String trim_exp="";
            if(Expected_Result!=null)
            {
                trim_exp=Expected_Result.trim().toLowerCase();
            }
            if(myacc.is_txtdisplyed())
            {
                String Actual_Result = "Valid";
                myacc.Logout();
                myacc.click_continue_to_logout();
                Assert.assertEquals(Actual_Result.trim().toLowerCase(),trim_exp);
            }
            else
            {
                String Actual_Result = "InValid";
                Assert.assertEquals(Actual_Result.trim().toLowerCase(),trim_exp);
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
