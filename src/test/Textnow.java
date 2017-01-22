package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.xbosoft.automation.Util_VU;

public class Textnow {

	@Test
	public void test() throws Exception {
		System.setProperty("webdriver.gecko.driver", Util_VU.GeckodriverPath);
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Util_VU.WAIT_TIME, TimeUnit.SECONDS);

		driver.get("https://www.textnow.com/messaging");
		Thread.sleep(20000);

//		waitForLoad(driver);

		driver.findElement(By.id("loginUsername")).sendKeys("elaineren");
		
		driver.findElement(By.id("loginPassword")).sendKeys("5691219-kk");
		driver.findElement(By.id("submitLogin")).click();
		Thread.sleep(40000);

		// //go to text message page
		// driver.findElement(By.xpath("//*[@id='textnow-navbar']/div/nav[1]/div/div[2]/a[1]")).click();
		//
		// // find out the last message and get the text message
		// Thread.sleep(40000);
		// if (driver.findElement(By.id("buttons")).isEnabled()) {
		// driver.findElement(By.id("buttons")).click();
		//
		// }
		String secureMsg = driver.findElement(By.xpath("//li[@class='chat-item'][last()]//span")).getText();
		System.out.println(secureMsg);
		//
		// // trim the message to get the secure code only (the last 6 digital
		// // number)
		// String secureCode = secureMsg.substring(secureMsg.length() - 6,
		// secureMsg.length());
		// System.out.println(secureCode);
	}

	private void waitForLoad(WebDriver driver) {
		// TODO Auto-generated method stub

		new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

}
