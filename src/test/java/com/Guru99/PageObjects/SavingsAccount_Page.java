package com.Guru99.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Guru99.Utilities.ExtentManager;
import com.Guru99.Utilities.XlsxUtil;
import com.aventstack.extentreports.ExtentTest;

public class SavingsAccount_Page 
{
	WebDriver ldriver;
	
	XlsxUtil xlController = new XlsxUtil("./src/test/resources/TestData/TestData.xlsx");
	
	public SavingsAccount_Page(WebDriver driver)
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
	
	@FindBy (xpath="//marquee[@class='heading3']")
	WebElement welcomeTxt;
	
	@FindBy (xpath="//a[contains(text(),'New Account')]")
	WebElement newAccountLnk;
	
	@FindBy (xpath="//*[contains(text(),'Add new account form')]")
	WebElement newAccountFormTxt;
	
	@FindBy (name="cusid")
	WebElement customerIDTxt;
	
	@FindBy (name="selaccount")
	WebElement accountTypeDrpDown;
	
	@FindBy (name="inideposit")
	WebElement initDepositTxt;
	
	@FindBy (xpath="//input[@value='submit']")
	WebElement submitBtn;
	

	@FindBy (xpath="//p[@class='heading3']")
	WebElement verifyRegistrationTxt;
	
	@FindBy (xpath="//p[@class='heading3']//following::tr[3]/td[2]")
	WebElement accountIdTxt;
	
	public void openApplication(String baseURL, ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			ldriver.get(baseURL);
			extentManager.addstep("Pass", "Successfully opened Guru99 application", extentTest);
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail", "Unable to navigate to Guru99 application", extentTest);
		}		
	}
	
	public void setUserdID(String usrid)
	{
		useridTxt.sendKeys(usrid);
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
			setUserdID(userid);
			setPassword(pwd);
			clickLogin();
			
			extentManager.addstep("Pass", "Login successfull", extentTest);
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail", "Login failed", extentTest);
		}		
	}
	
	public void waitForHomePage()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.visibilityOf(welcomeTxt));
	}
	
	public void clickNewAccount(ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			newAccountLnk.click();
			extentManager.addstep("Pass", "Navigated to New Account page...", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail", "Unable to navigate to New Account page...", extentTest);
		}
	}
	
	public void waitforAccountForm()
	{
		WebDriverWait wait = new WebDriverWait(ldriver,5);
		wait.until(ExpectedConditions.visibilityOf(newAccountFormTxt));
	}	
	
	public void setCustomerID(String customerid, ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			customerIDTxt.sendKeys(customerid);
			extentManager.addstep("Pass", "Customer ID Entered Successfully", extentTest);
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail", "Unable to navigate to New Account page...", extentTest);
		}
	}
	
	public void selectAccounType(String account,ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			Select accountType = new Select(accountTypeDrpDown);
			accountType.selectByVisibleText("Savings");
			extentManager.addstep("Pass", "Selected Savings account", extentTest);
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail", "Unable to select Savings account", extentTest);
		}
	}
	
	public void setDeposit(String deposit, ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			initDepositTxt.sendKeys(deposit);
			extentManager.addstep("Pass", "Initial deposit amount entered successfully", extentTest);
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail", "Unable to enter initial deposit amount", extentTest);
		}		
	}
	
	public void clickSubmit(ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			submitBtn.click();
			extentManager.addstep("Pass", "Savings account created successfully", extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail", "Unable to create Savings account", extentTest);
		}
	}
	
	public void implcitwait()
	{
		ldriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	public boolean verifyAccountGenerateTitle(String accountRegist, ExtentManager extentManager, ExtentTest extentTest)
	{
		String registrationSuccessTitle = verifyRegistrationTxt.getText();
		boolean status=true;
		try
		{
			if(verifyRegistrationTxt.isDisplayed())
			{
				extentManager.addstep("Pass", "Successfully verified account generation title..." +"Actual text is -->" +registrationSuccessTitle+ "... Expected Text is --> Account Generated Successfully!!!", extentTest);
			}
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail","Unable to verify Customer Registered Successfully Text",extentTest);
		}
		return status;
	}
	
	public void setAccountID(String accountid)
	{
		String accountID = accountIdTxt.getText();
		xlController.setCellData("TestData", "AccountID", 4, accountID);
	}
	
	public boolean verifyAccountText(String acntid, ExtentManager extentManager, ExtentTest extentTest)
	{
		String accountID=accountIdTxt.getText();
		boolean status=true;
		try
		{
			if(accountIdTxt.isDisplayed())
			{
				extentManager.addstep("Pass", "Successfully verified Account ID..." + "Actual Text is --> "+accountID, extentTest);
			}
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail","Unable to verify Account ID ",extentTest);
		}
		return status;
	}	
}