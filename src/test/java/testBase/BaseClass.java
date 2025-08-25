package testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    /*WebDriver driver;*/
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public Logger logger;
    public Properties p;

    public static WebDriver getDriver() {
        return tlDriver.get();
    }



    @BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
    @Parameters({"os","browser"})
    public void setUp(@Optional("windows") String os, @Optional("chrome") String browser) throws IOException {

        //Loading config.prperties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);


        logger = LogManager.getLogger(this.getClass());

        WebDriver driver = null;

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            try {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability("platformName", os.toUpperCase());
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                        break;

                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.setCapability("platformName", os.toUpperCase());
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions);
                        break;

                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.setCapability("platformName", os.toUpperCase());
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                        break;

                    default:
                        System.out.println("No matching browser for remote execution.");
                        return;
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize remote driver: " + e.getMessage());
            }

        }


        else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (browser.toLowerCase())
            {
                case "chrome" :
                    ChromeOptions chromeoptions = new ChromeOptions();
                    //chromeoptions.addArguments("--headless=new");     // Headless mode (new headless API)
                    driver =new ChromeDriver(chromeoptions);
                    break;
                case "edge" :
                    EdgeOptions edgeOptions = new EdgeOptions();

                    //edgeOptions.addArguments("--headless=new");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                case "firefox" :
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    //firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions); break;
                default : System.out.println("invalid browser name in testng xml"); return;
            }

        }



        else
        {
            throw new RuntimeException("Invalid execution_env value in config.properties. Must be 'local' or 'remote'.");
        }

        // Set the driver into ThreadLocal
        tlDriver.set(driver);

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        String appUrl = p.getProperty("appUrl2"); //reading value from properties file
        if (appUrl == null || appUrl.trim().isEmpty())
        {
            throw new RuntimeException("appUrl2 is not defined in config.properties!");
        }

        System.out.println("Launching URL: " + appUrl);

        getDriver().get(appUrl);
        getDriver().manage().window().maximize();

    }

    @AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
    public void tearDown() throws InterruptedException {

        if(getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
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

    public String captureScreen(String tname) throws IOException
    {
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

}
