package com.Guru99.TestCases;

import org.testng.annotations.Test;
import com.Guru99.ConfigBase.ConfigBase;
import com.Guru99.PageObjects.CurrentAccount_Page;
import com.aventstack.extentreports.ExtentTest;

public class CurrentAccount_TC extends ConfigBase
{
	@Test
	public void CurrentAccount() throws Exception
	{
		ExtentTest extentTest = extentManager.getExtentTest(testName);
		CurrentAccount_Page currentAccount = new CurrentAccount_Page(driver); 
		
		currentAccount.openApplication(excelData.get("BaseURL"), extentManager, extentTest);
		logger.info("Application Opened....");		
		currentAccount.login(excelData.get("UserID"), excelData.get("Password"), extentManager, extentTest);
		captureScreen(driver,"Home Page");
		
		currentAccount.implcitwait();
		currentAccount.clickNewAccount(extentManager, extentTest);
		currentAccount.setCustomerID(excelData.get("CustomerID"), extentManager, extentTest);
		currentAccount.selectAccountType(excelData.get("AccountType"), extentManager, extentTest);
		currentAccount.setDeposit(excelData.get("InitialDeposit"), extentManager, extentTest);
		logger.info("Entered customer id and Initial Deposit ....");
		
		currentAccount.implcitwait();
		captureScreen(driver,"Current Account Details");		
		currentAccount.clickSubmit(extentManager, extentTest);
		logger.info("Created Current Account Successfully ....");
		
		currentAccount.implcitwait();
		currentAccount.verifyAccountIDText(excelData.get("AccountID"), extentManager, extentTest);
		currentAccount.setAccountID("AccountID");
		currentAccount.verifyAccountGenerateTitle(excelData.get("AccountGeneratedTitleVerify"), extentManager, extentTest);
		logger.info("Verified Account Id ....");
		
		currentAccount.implcitwait();
		captureScreen(driver,"Account ID Verification");		
	}
}