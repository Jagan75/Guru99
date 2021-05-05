package com.Guru99.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.Guru99.Utilities.ExtentManager;
import com.Guru99.Utilities.XlsxUtil;
import com.aventstack.extentreports.ExtentTest;

public class CurrentAccount_Page 
{
	WebDriver ldriver;
	XlsxUtil xlController = new XlsxUtil("./src/test/resources/TestData/TestData.xlsx");
	
	public CurrentAccount_Page(WebDriver driver)
	{
		ldriver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (name="uid")
	WebElement useridTxt;
	
	@FindBy (name="password")
	WebElement passwordTxt;
	
	@FindBy (name="btnLogin")
	WebElement loginBtn;
	
	@FindBy (xpath="//a[contains(text(),'New Account')]")
	WebElement newAccountLnk;
	
	@FindBy (name="cusid")
	WebElement customerIDTxt;
	
	@FindBy (name="selaccount")
	WebElement accountTypeDrpDown;
	
	@FindBy (name="inideposit")
	WebElement initDepositTxt;
	
	@FindBy (xpath="//input[@value='submit']")
	WebElement submitBtn;
	
	@FindBy (name="reset")
	WebElement resetBtn;
	
	@FindBy (xpath="//p[@class='heading3']")
	WebElement verifyRegistrationTxt;
	
	@FindBy (xpath="//p[@class='heading3']//following::tr[3]/td[2]")
	WebElement accountidTxt;
	
	public void implcitwait()
	{
		ldriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	public void openApplication(String baseURL, ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			ldriver.get(baseURL);
			extentManager.addstep("Pass", "Successfully opened Guru99 application", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail", "Unable to navigate to Guru99 application", extentTest);
		}		
	}
	
	public void setUserID(String userid)
	{
		useridTxt.sendKeys(userid);
	}
	
	public void setPassword(String pswd)
	{
		passwordTxt.sendKeys(pswd);
	}
	
	public void clickLogin()
	{
		loginBtn.click();
	}
	
	public void login(String userid, String pwd, ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			setUserID(userid);
			setPassword(pwd);
			clickLogin();
			extentManager.addstep("Pass","Successfully logged into Guru99 application", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to login to Guru99 application", extentTest);
		}
	}
	
	public void clickNewAccount(ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			newAccountLnk.click();
			extentManager.addstep("Pass","Successfully clicked on New Account",extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to click New Account",extentTest);
		}
	}
	
	public void setCustomerID(String customerId,ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			customerIDTxt.sendKeys(customerId);
			extentManager.addstep("Pass", "Customer ID Entered Successfully", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail", "Unable to enter Customer ID", extentTest);
		}
	}
	
	public void selectAccountType(String account,ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			Select accountType = new Select(accountTypeDrpDown);
			accountType.selectByIndex(1);
			extentManager.addstep("Pass", "Selected Current account", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail", "Unable to select Current account", extentTest);
		}
	}
	
	public void setDeposit(String deposit, ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			initDepositTxt.sendKeys(deposit);
			extentManager.addstep("Pass", "Initial deposit amount entered successfully", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail", "Unable to enter initial deposit amount", extentTest);
		}
	}
	
	public void clickSubmit(ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			submitBtn.click();
			extentManager.addstep("Pass", "New Account created successfully", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail", "Unable to create New Account create", extentTest);
		}
	}
	
	public boolean verifyAccountGenerateTitle(String accountRegist, ExtentManager extentManager, ExtentTest extentTest)
	{
		String registrationSuccessTitle = verifyRegistrationTxt.getText();
		boolean status=true;
		try
		{
			if(verifyRegistrationTxt.isDisplayed())
			{
				extentManager.addstep("Pass","Successfully verified account generation title..."+"Actual Text is --> "+registrationSuccessTitle+"... Expected Text is --> Account Generated Successfully!!!", extentTest);
			}			
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to verify Customer Registered Successfully Text",extentTest);
		}	
		return status;
	}
	
	public void setAccountID(String accountid)
	{
		String accountID = accountidTxt.getText();
		xlController.setCellData("TestData", "AccountID", 3, accountID);
	}
	
	public boolean verifyAccountIDText(String accid, ExtentManager extentManager, ExtentTest extentTest)
	{
		String accountID=accountidTxt.getText();
		boolean status=true;
		try
		{
			if(accountidTxt.isDisplayed())
			{
				extentManager.addstep("Pass", "Successfully verified Account ID..."+"Actual Text is --> "+accountID, extentTest);
			}
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to verify Account ID ",extentTest);
		}
		return status;
	}	
}