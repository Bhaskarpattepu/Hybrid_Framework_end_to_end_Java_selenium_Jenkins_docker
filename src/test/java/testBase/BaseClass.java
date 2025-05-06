package testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"os","browser"})
    public void setUp(String os,String browser) throws IOException {

        //Loading config.prperties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);


        logger = LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase())
        {
            case "chrome" : driver =new ChromeDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" : driver = new FirefoxDriver(); break;
            default : System.out.println("invalid browser name in testng xml"); return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(p.getProperty("appUrl2"));  //reading value from properties file

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    public String RandomeString()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }
    public String RandomNumber()
    {

        return RandomStringUtils.randomNumeric(10);
    }
    public String RandomAlphanumeric()
    {
        String  generatedString =  RandomStringUtils.randomAlphanumeric(3);
        String  generatedNumber =  RandomStringUtils.randomNumeric(3);
        return (generatedString+"@"+generatedNumber);

    }
}
