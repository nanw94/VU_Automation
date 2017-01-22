package com.xbosoft.automation;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageFactory.AccountPage;
import pageFactory.SignInPage;

public class VUChrome {
	private WebDriver driver;
	


	@BeforeTest
	public void setUp() throws Exception {
		
//		System.setProperty("webdriver.gecko.driver",Util_VU.GeckodriverPath);
		System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\extension\\chromedriver.exe");

//		File profileDirectory = new File("C:\\Users\\qabuild\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\5yv76l0t.Selenium");		
//		FirefoxProfile profile = new FirefoxProfile(profileDirectory);		
//		driver = new FirefoxDriver(profile);
		
//		WebDriver driver=new FirefoxDriver();
		driver=new ChromeDriver();
		
		// Setting Wait Time
		driver.manage().timeouts().implicitlyWait(Util_VU.WAIT_TIME, TimeUnit.SECONDS);
		
		
		// Go to BaseURL
		driver.get(Util_VU.BASEURL);
		System.out.println("page has been loaded");
//		Thread.sleep(5000);
	}
	
	
	// VU-10:Login-Correct Username/Password
	// If the verification code mode is on, we may need to manually login once before running the script to bypass it. 
	
	@Test
	public void login() throws Exception{
		
		SignInPage objSignInPage=new SignInPage(driver);
		
		//insert username and password
		objSignInPage.signIn(Util_VU.LOGIN_ID,Util_VU.PASSWORD);
		
		//get
		String step2=driver.getTitle();
		System.out.println(step2);
		
	}
	
	@Test
	public void verification(){
		//click Send Verification Code button
		driver.findElement(By.className("button block")).click();
		
		//open a new tab
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		
		//switch to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	    driver.get("https://www.textnow.com/login");
	    driver.findElement(By.id("loginUsername")).sendKeys("elaineren");
	    driver.findElement(By.id("loginPassword")).sendKeys("5691219-kk");
	    driver.findElement(By.id("submitLogin")).click();
	    
	    String scode=driver.findElement(By.id("new")).getText();
	    System.out.println(scode);
	    
	    driver.findElement(By.linkText("Log Out"));
	    
	    driver.switchTo().window(tabs.get(0));
	}
	
	//VU-11:Accounts Overview
	@SuppressWarnings("unused")
	@Test
	public void viewMyAccount() throws Exception{
		
		AccountPage objAccountPage=new AccountPage(driver);
//		objAccountPage.viewTodo();
		
	}
}
