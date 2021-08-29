package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.HelperUtil;
import utilities.WaitUtil;

public class YourInformationPage extends BasePage{
	private HelperUtil helperUtil;
	private WaitUtil waitUtil;
	private int explicitTimeOut;

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;
	
	@FindBy(id = "postal-code")
	WebElement postalCode;
	
	@FindBy(id="continue")
	WebElement btnContinue;
	
	@FindBy(xpath="//span[@class='title']")
	WebElement checkOutTitle;

	public YourInformationPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
		waitUtil = new WaitUtil(driver);
		helperUtil = new HelperUtil(driver);
		explicitTimeOut = waitUtil.getExplicitTimeout();
	}
	
	 public void veriFyYourInformationPage(){
	        waitUtil.isElementVisible(checkOutTitle, explicitTimeOut);
	        checkOutTitle.isDisplayed();
	    }


	    public void enterFirstName(String value){
	        waitUtil.isElementVisible(firstName, explicitTimeOut);
	        firstName.clear();
	        firstName.sendKeys(value);
	
	    }
	    
	    public void enterLastName(String value){
	        waitUtil.isElementVisible(lastName, explicitTimeOut);
	        lastName.clear();
	        lastName.sendKeys(value);
	
	    }
	    
	    public void enterPostalCode(String value){
	        waitUtil.isElementVisible(postalCode, explicitTimeOut);
	        postalCode.clear();
	        postalCode.sendKeys(value);
	
	    }
	    public void clickOnContinue() {
	    	btnContinue.click();
	    }
}
