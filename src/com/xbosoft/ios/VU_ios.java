package com.xbosoft.ios;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.gargoylesoftware.htmlunit.javascript.host.ScreenOrientation;
import com.xbosoft.automation.Util_VU;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.JavascriptExecutor;

import pageFactory.AccountPage;
import pageFactory.SignInPage;

public class VU_ios {
	private IOSDriver driver;
	private String scrFolder;
	public String url;
	public String currentUserID;
	public WebDriverWait wait;

	public void takeScreenshot(String fileName) throws IOException, Throwable {
		Thread.sleep(3000);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(scrFolder + "/" + fileName + ".png"));
	}

	public void waitForLoad(WebDriver driver) {
		new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	@SuppressWarnings("rawtypes")
	@BeforeTest
	public void setUp() throws Exception {

		// Set Chrome driver
		System.setProperty("webdriver.chrome.driver", Util_VU.ChromedriverMac);

        // Create Object of DesiredCapability class 
		DesiredCapabilities capabilities = new DesiredCapabilities();

                // Set the device Name- You can change based on your requirement
		capabilities.setCapability("deviceName", "iPhone 6");

                // Set the platform name- This will ramain same 
		capabilities.setCapability("platformName", "iOS");
                 
                // This the version- it is important so change it if required 
		capabilities.setCapability("platformVersion", "9.3");

                // set the browser in Emulator
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		// Create object of AndroidDriver class and pass the url and capability
		// that we created
		// @SuppressWarnings("rawtypes")
		driver = new IOSDriver(url, capabilities);

		// Setting Wait Time
		driver.manage().timeouts().implicitlyWait(Util_VU.WAIT_TIME, TimeUnit.SECONDS);

		// create folder for screenshots
		scrFolder = Util_VU.screenshotPathMac
				+ new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime()).toString();
		new File(scrFolder).mkdir();

		// Go to BaseURL
		driver.get(Util_VU.BASEURL);

	}

	/**
	 * The test below covers 1 VU-8:Login-No Username/Password 2
	 * VU-46:Login-Wrong Username/Password 3 VU-10:Login-Correct
	 * Username/Password 4 VU-49:Login-2-Step Verification-With 2-step
	 * verification completed in enrollment
	 * 
	 * @throws Throwable
	 */

	@Test(priority = 0)

	public void login() throws Throwable {

		// Create Login Page object
		SignInPage objSignInPage = new SignInPage(driver);
		// Take screenshots
		takeScreenshot("loginPage");
		
//		((AppiumDriver) driver).rotate(ScreenOrientation.LANDSCAPE);

		// //VU-8:Login-No Username/Password
		// objSignInPage.signInNoUsernamePassword();
		// // Take screenshots
		// takeScreenshot("loginPageNoUsernamePassword");
		//
		// //VU-46:Login-Wrong Username/Password
		// objSignInPage.signInWrongUsernamePassword();
		// // Take screenshots
		// takeScreenshot("loginWrongUsernamePassword");

		// VU-10:Login-Correct Username/Password
		objSignInPage.signIn(Util_VU.LOGIN_ID, Util_VU.PASSWORD);


	}

	/**
	 * The below test will be run when the 2-step verification mode is on steps:
	 * 1. click send secure code to phone number 2. open a new tab, go to
	 * textnot.com to get the number and 3. get back to the original tab and
	 * input the number 4. click verify button
	 * 
	 * @throws Throwable
	 */
	@Test(priority = 1)
	public void verification() throws Throwable {
		waitForLoad(driver);
		if (driver.getTitle().startsWith("MyVeteransUnited")) {
			System.out.println("2FA is off, ignore 2FA verification and proceed");
			takeScreenshot("afterLogin");
		} else {
			takeScreenshot("afterLogin");
			// click Send Verification Code button
			driver.findElement(By.xpath("//*[@id='login']/body/div/div[1]/form/div/ul/li[2]/button")).click();

			// Take screenshots for VU49 TFA page after click send button
			takeScreenshot("TFAPageAfterClickSend");

			// input secure code and click Verify button
			driver.findElement(By.id("AuthyCode")).sendKeys(getToken());
			driver.findElement(By.id("VerifyAuthCode")).click();

		}
	}

	/**
	 * The test below will cover UI of the pages below Account
	 * https://68.66.0.106/#/accounts/ Details
	 * https://68.66.0.106/#/accounts/125274/details Documents
	 * https://68.66.0.106/#/documents/125274 Payments
	 * https://68.66.0.106/#/payments/125274 Team
	 * https://68.66.0.106/#/accounts/125274/team Profile
	 * https://68.66.0.106/#/profile/ Support https://68.66.0.106/#/support/
	 * 
	 * @throws Throwable
	 */

	@Test(priority = 2, dependsOnMethods = { "verification" })
	
	public void viewMyAccount() throws Throwable {
		 wait= new WebDriverWait(driver, 120);
//		 Create Login Page object
		 AccountPage objAccountPage = new AccountPage(driver);
		 
		 takeScreenshot("AccountPage");
		 
		wait.until(ExpectedConditions.visibilityOf(objAccountPage.Tasklist));
		objAccountPage.Tasklist.click();

		
		takeScreenshot("Tasklist");
		
		wait.until(ExpectedConditions.visibilityOf(objAccountPage.navDetails));
		objAccountPage.navDetails.click();
		takeScreenshot("Details");

		wait.until(ExpectedConditions.visibilityOf(objAccountPage.navDocuments));
		objAccountPage.navDocuments.click();
		takeScreenshot("Documents");
		
		wait.until(ExpectedConditions.visibilityOf(objAccountPage.navPayments));
		objAccountPage.navPayments.click();
		
		takeScreenshot("Payments");
		
		wait.until(ExpectedConditions.visibilityOf(objAccountPage.navTeam));
		
		objAccountPage.navTeam.click();
		takeScreenshot("Team");
		
		objAccountPage.navTasklist.click();
		takeScreenshot("Tasklist2");
		
		driver.findElement(By.cssSelector("div[class='global-back ng-binding']")).click();
		takeScreenshot("backToAccount");

		
		objAccountPage.footerSettings.click();
		takeScreenshot("Settings");
		
		objAccountPage.footerSupport.click();
		takeScreenshot("Support");
		
//		driver.findElement(By.cssSelector("div[class='global-back ng-binding']")).click();
//		takeScreenshot("backToAccount");


		
		

		
		
		
		
		objAccountPage.Tasklist.click();
		
//		WebElement document = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Documents']/svg/use")));

	}



//	@Test(priority = 3)
//	public void detial() throws Throwable {
//		// Create Login Page object
//		// AccountPage objAccountPage = new AccountPage(driver);
//		WebElement detail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Details']/svg")));
//		detail.click();
////		driver.navigate().to("https://68.66.0.106/#/accounts/125270/details");
//		takeScreenshot("detail");

		// //
		// // System.out.println("viewMyAccount " + driver.getTitle());
		//
		// // take screen shot for Account page
		// Thread.sleep(5000);
		// System.out.println("take screenshot for account page");
		// takeScreenshot("account1");
		//
		// driver.findElement(By.xpath("//*[@id='wrapper']/div/div[2]/div/vu-opportunity-card/div/div[3]/a[1]")).click();
		// System.out.println("take screenshot for todo page");
		// takeScreenshot("todo1");
		//
		// url = driver.getCurrentUrl();
		// currentUserID = url.substring(url.indexOf("/") + 1);
		// System.out.println(currentUserID);
		//
		// driver.get("https://68.66.0.106/#/checklist/" + currentUserID);

//	}

	/**
	 * the method below will use a new Web Driver to get the token
	 */
	public String getToken() {
		// open a new browser from PC
		WebDriver pcDriver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(pcDriver, 120);

		// Go to textnow.com
		pcDriver.get("https://www.textnow.com/messaging");
		new WebDriverWait(pcDriver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("loginUsername")));

		// Sign in
		pcDriver.findElement(By.id("loginUsername")).sendKeys("elaineren");
		pcDriver.findElement(By.id("loginPassword")).sendKeys("5691219-kk");
		pcDriver.findElement(By.id("submitLogin")).click();

		WebElement lastMessage;
		lastMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chat-item'][last()]//span")));
		String secureMsg = lastMessage.getText();

		System.out.println(secureMsg);

		// trim the message to get the secure code only (the last 6 digital
		// number)
		String token = secureMsg.substring(secureMsg.length() - 6, secureMsg.length());
		System.out.println(token);

		// close the tab for textnow.com
		pcDriver.quit();

		return (token);

	}
}

//
// // VU-11:Accounts Overview
// /**
// * VU-11:Accounts Overview * Check Points: (Automation will verify this by
// * take screenshot only.) 1'MyVeteransUnited' page displays. There are two
// * parts: 'myAccounts' card with 'Account ID', 'Finance Account' and
// * 'Current Stage' info displays, a button named 'View Account'; 'I want to
// * ...' sidebar with 'Start a New Home Loan', 'View Helpful Resources',
// * 'Speak to a Representative' and 'Change my Account Settings'. All these
// * with no format confusion. 2 A sub-navigator displays with four items:
// * 'To-Do's', 'Details', 'Documents', ' My Loan Team' under 'myAccounts'
// * navigator; Current page is 'To-Do's', 'To-Do's CheckList' display with
// * your current stage, and with no format confusion.
// *
// */
// @Test(priority = 2, dependsOnMethods = { "verification" })
// public void viewMyAccount() throws Exception {
//
// // AccountPage objAccountPage = new AccountPage(driver);
// //
// // System.out.println("viewMyAccount " + driver.getTitle());
//
// // take screen shot for Account page
// Thread.sleep(5000);
// System.out.println("take screenshot for account page");
// takeScreenshot("account1");
//
// driver.findElement(By.xpath("//*[@id='wrapper']/div/div[2]/div/vu-opportunity-card/div/div[3]/a[1]")).click();
// System.out.println("take screenshot for todo page");
// takeScreenshot("todo1");
//
// url = driver.getCurrentUrl();
// currentUserID = url.substring(url.indexOf("/") + 1);
// System.out.println(currentUserID);
//
// driver.get("https://68.66.0.106/#/checklist/" + currentUserID);
//
// // Details
// // //*[@id="wrapper"]/div/div[2]/div/vu-opportunity-card/div/div[3]/a[2]
// //
// /html/body/div[2]/div[1]/vu-chrome-header/div/div[2]/vu-local-navigation/div/nav/ul/li[2]
// //
// /html/body/div[2]/div[1]/vu-chrome-header/div/div[2]/vu-local-navigation/div/nav/ul/li[2]
// //
// /html/body/div[2]/div[1]/vu-chrome-header/div/div[2]/vu-local-navigation/div/nav/ul/li[2]/a
// // <vu-icon id="Details"><svg xmlns="http://www.w3.org/2000/svg"
// // version="1.1" x="0" y="0" viewBox="0 0 64 64"
// // xml:space="preserve"><use xmlns:xlink="http://www.w3.org/1999/xlink"
// // xlink:href="#svgIconDetails"></use></svg></vu-icon>
// }
// }
//
/// **

