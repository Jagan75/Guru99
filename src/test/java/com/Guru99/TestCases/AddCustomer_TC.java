package com.Guru99.TestCases;

import org.testng.annotations.Test;

import com.Guru99.ConfigBase.ConfigBase;
import com.Guru99.PageObjects.AddCustomer_Page;
import com.aventstack.extentreports.ExtentTest;

public class AddCustomer_TC  extends ConfigBase
{
	@Test
	public void AddCustomer() throws Exception 
	{
		ExtentTest extentTest = extentManager.getExtentTest(testName);
		AddCustomer_Page addCustomer = new AddCustomer_Page(driver);
		addCustomer.openApplication(excelData.get("BaseURL"), extentManager, extentTest);
		logger.info("Application Opened....");	
		addCustomer.login(excelData.get("UserID"), excelData.get("Password"), extentManager, extentTest);		
		addCustomer.implcitwait();
		
		addCustomer.verifyWelcomeMessage(excelData.get("Welcome"), extentManager, extentTest);
		logger.info("Successfully verified welcome text..."); 
		captureScreen(driver,"Home Page");
		addCustomer.implcitwait();
		
		addCustomer.clickNewCustomer(extentManager, extentTest);
		addCustomer.waitForNewCustomerPage();
		
		addCustomer.customerDetails(excelData.get("CustomerName"), excelData.get("Gender"), excelData.get("DateOfBirth"), excelData.get("Address"), excelData.get("City"), excelData.get("State"), excelData.get("PIN"), excelData.get("MobileNumber"), excelData.get("E-mail"), excelData.get("CustomerPassword"), extentManager, extentTest);
		logger.info("Customer added successfully...");		
		addCustomer.implcitwait();
		
		captureScreen(driver,"Customer added");
		addCustomer.clickSubmit();
		addCustomer.implcitwait();
		addCustomer.verifyCustomerIDText("CustomerID", extentManager, extentTest);
		addCustomer.setCustomerID("CustomerID");
		addCustomer.verifyCustomerRegistrationText("RegistrationSuccess", extentManager, extentTest);
		logger.info("Verified Customer Registration Text...");
		addCustomer.implcitwait();
		captureScreen(driver,"Registered Customer");		
	}
}