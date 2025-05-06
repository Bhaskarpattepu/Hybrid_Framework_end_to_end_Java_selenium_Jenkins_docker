package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test
    public void verify_account_registration()
    {
        logger.debug("*****Starting TC01_AccountRegistrationTest********");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.debug("Clicked on MyAccount Link");

            hp.clickRegister();
            logger.debug("Clicked on Register Link");
            AccountRegistrationPage repage = new AccountRegistrationPage(driver);
            logger.debug("Providing Customer details...");
            repage.setTxtFirstName(RandomeString().toUpperCase());
            repage.setTxtLastname(RandomeString().toUpperCase());
            repage.setTxtEmail(RandomeString() + "@gmail.com");
            repage.setTxtTelephone(RandomNumber());
            String password = RandomAlphanumeric();
            repage.setTxtPassword(password);
            repage.setTxtConfirmPassword(password);
            repage.setChkdPolicy();
            logger.debug("Before signing in..");
            repage.setBtnContinue();
            logger.debug("After registration...");
            logger.debug("Validating Expected message..");
            String confmsg = repage.getConfirmationMessage();
            if(confmsg.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else
            {
                logger.error("Test failed..");
                logger.debug("Debug logs");
                Assert.assertTrue(false);
            }
            //Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        }
        catch (Exception e)
        {
            logger.error("Exception occured"+e.getMessage());
            Assert.fail();
        }

        logger.debug("***** Finished TC001_AccountRegistrationTest *****");

        }


}
