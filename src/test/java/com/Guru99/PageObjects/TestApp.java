package com.Guru99.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestApp 
{
	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver","C:/Testing/Guru99/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.demo.guru99.com/V4/index.php");
		
		driver.findElement(By.name("uid")).sendKeys("mngr319042");
		driver.findElement(By.name("password")).sendKeys("bAhutuj");
		driver.findElement(By.name("btnLogin")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		
		driver.findElement(By.name("name")).sendKeys("DummyName");
		driver.findElement(By.xpath("//input[@value='f']")).click();
		driver.findElement(By.name("dob")).sendKeys("12-31-1996");
		driver.findElement(By.name("addr")).sendKeys("Kings Street");
		driver.findElement(By.name("city")).sendKeys("Hyderabad");
		driver.findElement(By.name("state")).sendKeys("Telangana");
		driver.findElement(By.name("pinno")).sendKeys("500012");
		driver.findElement(By.name("telephoneno")).sendKeys("9811223344");
		driver.findElement(By.name("emailid")).sendKeys("adfk@gmail.com");
		driver.findElement(By.name("password")).sendKeys("test123");
		driver.findElement(By.name("sub")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		String custid=driver.findElement(By.xpath("//p[@class='heading3']//following::tr/td[2]")).getText();
		System.out.println(custid);
	

	}
}