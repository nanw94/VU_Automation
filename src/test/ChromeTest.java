package test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChromeTest {

	@Test

	void openNewTab() throws Exception{
		System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\extension\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://google.com");
		((JavascriptExecutor)driver).executeScript("window.open();");
		
		//switch to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		 driver.get("https://www.textnow.com/login");
		
//		 Assert.assertTrue(driver.findElement(By.id("loginUsername")).isEnabled());
		    driver.findElement(By.id("loginUsername")).sendKeys("dffff");
		    


	
	}
}
