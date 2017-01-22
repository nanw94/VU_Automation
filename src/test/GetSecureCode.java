package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


import com.xbosoft.automation.Util_VU;

public class GetSecureCode {
	
//	public void waitForLoad(WebDriver driver) {
//	    new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
//	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
//	}
	
	@Test
	void getSecureCode() throws Exception{
		System.setProperty("webdriver.gecko.driver",Util_VU.GeckodriverPath);
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Util_VU.WAIT_TIME, TimeUnit.SECONDS);
		driver.get("https://www.textnow.com/login");
	    Thread.sleep(3000);
	    
	    //click login area, insert username;
	    driver.findElement(By.xpath("html/body/div/div[2]/div/div/div/form/div/div[1]/div/div/p[1]")).click();
	    driver.findElement(By.id("loginUsername")).sendKeys("elaineren");
	    
	    //click password area, insert password;
	    driver.findElement(By.xpath("html/body/div/div[2]/div/div/div/form/div/div[2]/div/div/p[1]")).click();
	    driver.findElement(By.id("loginPassword")).sendKeys("5691219-kk");
	    
	    //click sigin button
	    driver.findElement(By.id("submitLogin")).click();	    
//	    waitForLoad(driver);
	    
	    //findout the last message and get the text message
	    String secureMsg=driver.findElement(By.xpath("//li[@class='chat-item'][last()]//span")).getText();
	    System.out.println(secureMsg);	    
	    
	    //trim the message to get the secure code only (the last 6 digital number)
	    String secureCode=secureMsg.substring(secureMsg.length()-6, secureMsg.length());
	    System.out.println(secureCode);
	}

	 
	
}
