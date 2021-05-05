package com.Guru99.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
    public WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;


    public void setExtent(String reportname) {
        // specify location of the report
       String reportPath = System.getProperty("user.dir") + File.separator+"src/test/resources/Reports/" + reportname;
        htmlReporter = new ExtentHtmlReporter(reportPath);

        htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
        htmlReporter.config().setReportName("Functional Testing"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setReportUsesManualConfiguration(true);

        // Passing General information
        extentReports.setSystemInfo("Host name", "localhost");
        extentReports.setSystemInfo("Environemnt", "QA Automation");
        extentReports.setSystemInfo("user", System.getProperty("user.name"));
    }

    public ExtentTest createTest(String testName){
        ExtentTest extentTest = extentReports.createTest(testName);
        return extentTest;
    }
    public void createNode(String name, String description, ExtentTest test){
        test.createNode(name,description);

    }
    public void addstep(String status, String details, ExtentTest test){

        try {
            switch (status.toUpperCase()) {
                case "INFO":
                    test.log(Status.INFO,details);
                    break;
                case "PASS":
                    test.pass(details);
                    //Assert.assertEquals(true, details);
                    break;
                case "FAIL":
                    test.fail(details);
                  //  Assert.assertEquals(false, details);
                    break;
                case "SKIP":
                    test.skip(details);

                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void endReport() {
        extentReports.flush();
    }

    public ExtentTest getExtentTest(String testName){
        ExtentTest extentTest=null;
        extentTest = createTest(testName);
//        if(execute.equalsIgnoreCase("YES")) {
//            extentTest = createTest(testName);
//        } else{
//            extentTest = createTest(testName).skip("Test Script :: "+testName+"  Skipped");
//        }
        return extentTest;
    }    
}