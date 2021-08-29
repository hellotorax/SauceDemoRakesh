package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.HelperUtil;
import utilities.WaitUtil;

public class LoginPage  extends BasePage {

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement pwd;

	@FindBy(id = "login-button")
	WebElement loginButton;

	public LoginPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void sauceDemoLogin(String userName,String password){
		username.sendKeys(userName);
		pwd.sendKeys(password);
		loginButton.click();
	}
}
