package com.Guru99.TestCases;

import org.testng.annotations.Test;
import com.Guru99.ConfigBase.ConfigBase;
import com.Guru99.PageObjects.SavingsAccount_Page;
import com.aventstack.extentreports.ExtentTest;

public class SavingsAccount_TC extends ConfigBase
{
	@Test
	public void savingsAccount() throws Exception
	{
		ExtentTest extentTest = extentManager.getExtentTest(testName);	
		SavingsAccount_Page savingsAccount = new SavingsAccount_Page(driver); 
		
		savingsAccount.openApplication(excelData.get("BaseURL"), extentManager, extentTest);
		savingsAccount.login(excelData.get("UserID"), excelData.get("Password"), extentManager, extentTest);
		logger.info("Login Success...");
		savingsAccount.waitForHomePage();	
		//savingsAccount.implcitwait();
		
		savingsAccount.clickNewAccount(extentManager, extentTest);
		logger.info("New Account page opened...");
		
		savingsAccount.setCustomerID(excelData.get("CustomerID"), extentManager,extentTest); 
		savingsAccount.selectAccounType(excelData.get("AccountType"), extentManager, extentTest);
		savingsAccount.setDeposit(excelData.get("InitialDeposit"), extentManager, extentTest); savingsAccount.implcitwait();
		  
		captureScreen(driver,"Savings Account");
		savingsAccount.clickSubmit(extentManager, extentTest);
		logger.info("Savings Account Created...");
		savingsAccount.implcitwait();
		savingsAccount.verifyAccountText(excelData.get("AccountID"), extentManager, extentTest); 
		savingsAccount.setAccountID("AccountID");
		savingsAccount.verifyAccountGenerateTitle(excelData.get("AccountGeneratedTitleVerify"), extentManager, extentTest);
		  
		savingsAccount.implcitwait();
		logger.info("Verified account registered title...");
		captureScreen(driver,"Registered Savings Account");
		
	}
}