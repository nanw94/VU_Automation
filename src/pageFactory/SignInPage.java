package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	WebDriver driver;

	@FindBy(id = "LoginId")
	public WebElement username;

	@FindBy(id = "Password")
	public WebElement password;

	@FindBy(id = "myvuloginsubmit")
	public WebElement singinButton;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void signIn(String loginId, String Password) {
		username.clear();
		username.sendKeys(loginId);

		password.clear();
		password.sendKeys(Password);

		singinButton.click();
	}
	
	public void signInNoUsernamePassword() {
		username.clear();
		password.clear();
		singinButton.click();
	}
	
	public void signInWrongUsernamePassword() {
		username.clear();
		username.sendKeys("somethingwrong");
		password.clear();
		password.sendKeys("wrongpassword");
		singinButton.click();
		

	}
	
	

}
