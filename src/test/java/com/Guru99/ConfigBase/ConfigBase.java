package com.Guru99.ConfigBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
/*import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;*/
import org.testng.annotations.*;


import com.Guru99.Constants.BrowserPath;
import com.Guru99.Utilities.ExtentManager;
import com.Guru99.Utilities.XlsxUtil;

public class ConfigBase 
{	
	public static WebDriver driver;
	public static Logger logger;
	Map<String, String> dataMap = new HashMap<String, String>();
	public HashMap<String, String> excelData = new HashMap<>();
	public static ExtentManager extentManager = new ExtentManager();
	public static String testName=null;
	
	public void setDataMap(String testName) 
	{ 
		XlsxUtil xlsxUtil = new	XlsxUtil(System.getProperty("user.dir") + File.separator+"src/test/resources/TestData/TestData.xlsx"); 
		
		excelData = xlsxUtil.getTestData("TestData", testName); 
	}
	
	@Parameters("Browser")
	  
	@BeforeMethod 
	public void setUp(Method testMethod, String browser) 
	{		
		logger = logger.getLogger("Guru99");  
		PropertyConfigurator.configure("Log4j.properties"); //PropertiesConfigurator is used to configure logger from properties file
	  
		String tcName = testMethod.getName();
		System.out.println("TestCase Name... "+tcName); 
		//setDataMap(tcName);
	  
		testName = testMethod.getName(); setDataMap(testName);
		if(browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", BrowserPath.chromepath); 
			driver = new ChromeDriver(); 
		}
		
		driver.manage().window().maximize();	  
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException 
	{
		TakesScreenshot screenshot = (TakesScreenshot) driver; 
		File source = screenshot.getScreenshotAs(OutputType.FILE); 
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname+ ".png");
		FileUtils.copyFile(source, target); 
		System.out.println("Screenshot taken");
	}
		
	public static String getDateTime()
	{
		String TimeNow=null;
		String DateNow=null;
		try
		{
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy"); 
			DateNow = dateFormat.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm"); 
			TimeNow = dateFormat.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return DateNow+" "+TimeNow;
	}
	
	@BeforeSuite 
	public void setReports(ITestContext context) 
	{
		String suiteXmlName = context.getSuite().getName();
		System.out.println("Suite XML Name is... "+suiteXmlName); 
		String reportName = suiteXmlName+" "+getDateTime(); 
		extentManager.setExtent(reportName+".html");
	} 
	
	
	@AfterMethod 
	public void tearDown() 
	{ 
		driver.quit(); 
	}
	 
	
	@AfterTest(alwaysRun = true)
	public void endReport()
	{
		extentManager.endReport();
	}	
}