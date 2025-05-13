package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestClass;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestClass testContext)
    {
        /*
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt = new Date();
        String currentdatetimestamp= df.format(dt);

         */

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
        repName ="Test-Report-"+timeStamp+".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); //Specify location of the report

        sparkReporter.config().setDocumentTitle("opencart Automation Report"); //Tittle of report
        sparkReporter.config().setReportName("opencart Functional Testing"); // name of report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","opencart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customers");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");

        String os = testContext.getXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System",os);

        String browser = testContext.getXmlTest().getParameter("broser");
        extent.setSystemInfo("Browser",browser);

        List<String> includedGroups = testContext.getXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty())
        {
            extent.setSystemInfo("Groups",includedGroups.toString());
        }


    }

    public void onTestSuccess(ITestResult  result)
    {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups in reports
        test.log(Status.PASS,result.getName()+"successfully executed");

    }

    public void onTestFailure(ITestResult result) throws IOException {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups in reports

        test.log(Status.FAIL,result.getName()+"got failed");
        test.log(Status.INFO,result.getThrowable().getMessage());

        try
        {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

}
