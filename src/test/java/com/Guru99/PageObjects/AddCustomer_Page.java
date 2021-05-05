package com.Guru99.PageObjects;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Guru99.Utilities.ExtentManager;
import com.Guru99.Utilities.XlsxUtil;
import com.aventstack.extentreports.ExtentTest;

public class AddCustomer_Page 
{
	WebDriver ldriver;
	XlsxUtil xlController = new XlsxUtil("./src/test/resources/TestData/TestData.xlsx");
	
	public AddCustomer_Page(WebDriver driver) 
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
	
	@FindBy (xpath="//a[contains(text(),'New Customer')]")
	WebElement newcustomerLnk;
	
	@FindBy (xpath="//p[@class='heading3']")
	WebElement newcustomerTxt;
	
	@FindBy (name="name")
	WebElement customerNameTxt;
	
	@FindBy (xpath="//input[@value='m']")
	WebElement genderMale;
	
	@FindBy (xpath="//input[@value='f']")
	WebElement genderFemale;
	
	@FindBy (name="dob")
	WebElement dateofbirthTxt;
	
	@FindBy (name="addr")
	WebElement addressTxt;
	
	@FindBy (name="city")
	WebElement cityTxt;
	
	@FindBy (name="state")
	WebElement stateTxt;
	
	@FindBy (name="pinno")
	WebElement pinnoTxt;
	
	@FindBy (name="telephoneno")
	WebElement mobilenumberTxt;
	
	@FindBy (name="emailid")
	WebElement emailidTxt;
	
	@FindBy (name="password")
	WebElement custpasswordTxt;
	
	@FindBy (name="sub")
	WebElement submitBtn;
	
	@FindBy (xpath="//p[@class='heading3']")
	WebElement customerRegistrationTxt;
	
	@FindBy (xpath="//p[@class='heading3']//following::tr[3]/td[2]")
	WebElement customeridTxt;
	
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
	
	public void setUserID(String usrid)
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
			setUserID(userid);
			setPassword(pwd);
			clickLogin();
			
			extentManager.addstep("Pass","Successfully logged into Guru99 application", extentTest);
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail","Unable to login to Guru99 application", extentTest);
		}
	}
	
	public void waitForPageLoad()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions.visibilityOf(welcomeTxt));
	}
	
	public boolean verifyWelcomeMessage(String welcome, ExtentManager extentManager, ExtentTest extentTest)
	{
		String welcometext=welcomeTxt.getText();
		boolean status=true;
		
		try
		{
			if(welcomeTxt.isDisplayed())
			{
				extentManager.addstep("Pass","Successfully verified Welcome Text..."+"Actual Text is --> "+welcometext+"... Expected Text is --> Welcome To Manager's Page of Guru99 Bank", extentTest);
			}
		}
		catch (Exception e)
		{
			extentManager.addstep("Fail","Unable to verify Welcome Text",extentTest);
		}
		return status;
	}
	
	public void clickNewCustomer(ExtentManager extentManager, ExtentTest extentTest)
	{
		try
		{
			newcustomerLnk.click();
			extentManager.addstep("Pass","Successfully clicked on New Customer",extentTest);
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to click New Customer",extentTest);
		}
	}
	
	public void waitForNewCustomerPage()
	{
		WebDriverWait wait = new WebDriverWait(ldriver, 10);
		wait.until(ExpectedConditions.visibilityOf(newcustomerTxt));
	}
	
	public void setCustomerName(String cname)
	{
		customerNameTxt.sendKeys(cname);
	}
	
	public void clickGenderMale(String male)
	{
		genderMale.click();
	}
	
	public void clickGenderFemale(String female)
	{
		genderFemale.click();
	}
	
	public void setDOB(String date)
	{
		dateofbirthTxt.sendKeys(date);
	}
	
	public void setAddress(String add)
	{
		addressTxt.sendKeys(add);		
	}
	
	public void setCity(String city)
	{
		cityTxt.sendKeys(city);
	}
	
	public void setState(String state)
	{
		stateTxt.sendKeys(state);
	}
	
	public void setPINNo(String pin)
	{
		pinnoTxt.sendKeys(pin);
	}
	
	public void setMobilenumber(String mobile)
	{
		mobilenumberTxt.sendKeys(mobile);
	}
	
	public String randomstring()
	{
		String generatedstring=RandomStringUtils.randomAlphanumeric(5); //.randomAlphabetic(5);
		return(generatedstring);
	}
	
	public void setEmailID(String eMail)
	{
		String emailID=randomstring()+"@gmail.com";
		xlController.setCellData("TestData", "E-mail", 2, emailID);
		emailidTxt.sendKeys(xlController.getCellData("TestData", "E-mail", 2));
	}
	
	public void setCustPassword(String password)
	{
		custpasswordTxt.sendKeys(password);
	}
	
	public void clickSubmit()
	{
		submitBtn.click();
	}
	
	public void customerDetails(String custName, String gender, String dateOfBirth, String address, String city, String state, String pin, String mobile, String email, String custpassword, ExtentManager extentManager, ExtentTest extentTest)
	{		
		try
		{
			setCustomerName(custName);
			clickGenderFemale(gender);
			setDOB(dateOfBirth);
						
			setAddress(address);
			setCity(city);
			setState(state);
			setPINNo(pin);
			setMobilenumber(mobile);
			setEmailID(email);
			setCustPassword(custpassword);
			
			extentManager.addstep("Pass","Successfully added customer details... ", extentTest);	
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to add customer details... ",extentTest);
		}
	}
	
	public void setCustomerID(String customerid)
	{
		String custID=customeridTxt.getText();
		xlController.setCellData("TestData", "CustomerID", 3, custID);
		xlController.setCellData("TestData", "CustomerID", 4, custID);
	}	
	
	public boolean verifyCustomerRegistrationText(String registrationSuccess,  ExtentManager extentManager, ExtentTest extentTest)
	{
		String registrationSuccesstext=customerRegistrationTxt.getText();
		boolean status=true;
		try 
		{
			if(customerRegistrationTxt.isDisplayed())
			{
				extentManager.addstep("Pass","Successfully verified Registration Success Text..."+"Actual Text is --> "+registrationSuccesstext+"... Expected Text is --> Customer Registered Successfully!!!", extentTest);
			}			
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to verify Customer Registered Successfully!!! text",extentTest);
		}
		return status;
	}
	
	public boolean verifyCustomerIDText(String custid,  ExtentManager extentManager, ExtentTest extentTest)
	{
		String custidtext=customeridTxt.getText();
		boolean status=true;
		try 
		{
			if(customeridTxt.isDisplayed())
			{
				extentManager.addstep("Pass","Successfully verified Customer ID..."+"Actual Text is --> "+custidtext, extentTest);
			}			
		}
		catch(Exception e)
		{
			extentManager.addstep("Fail","Unable to verify Customer ID ",extentTest);
		}
		return status;
	}		
}