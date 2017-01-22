package com.xbosoft.automation;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TabSwitch {
	WebDriver driver;
	
	@BeforeTest
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.gecko.driver",Util_VU.GeckodriverPath);
		System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\extension\\chromedriver.exe");

		//WebDriver driver=new FirefoxDriver();
		driver=new FirefoxDriver();
		
		// Setting Wait Time
		driver.manage().timeouts().implicitlyWait(Util_VU.WAIT_TIME, TimeUnit.SECONDS);
		
		
//		// Go to BaseURL
//		driver.get(Util_VU.BASEURL);
//		System.out.println("page has been loaded");
////		Thread.sleep(5000);
	}
	
	
	@Test
	public void testTabs() {
	    driver.get("https://business.twitter.com/start-advertising");
	    System.out.println(driver.getTitle());
	    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");

	    
	    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	    
	    // change focus to new tab
	    driver.switchTo().window(tabs.get(1));
	    driver.get("http://baidu.com");
	    System.out.println(driver.getTitle());

	    // Do what you want here, you are in the new tab

//	    driver.close();
	    // change focus back to old tab
	    driver.switchTo().window(tabs.get(0));
	    System.out.println(driver.getTitle());

//	    // Do what you want here, you are in the old tab
	}
}
