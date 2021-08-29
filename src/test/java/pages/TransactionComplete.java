package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.HelperUtil;
import utilities.WaitUtil;

public class TransactionComplete extends BasePage{
	
	private HelperUtil helperUtil;
	private WaitUtil waitUtil;
	private int explicitTimeOut;

	@FindBy(xpath="//span[@class='title']")
	WebElement checkOutTitleComplete;

	@FindBy(xpath="//h2[@class='complete-header']")
	WebElement confirmationMsg;

	public TransactionComplete(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
		waitUtil = new WaitUtil(driver);
		helperUtil = new HelperUtil(driver);
		explicitTimeOut = waitUtil.getExplicitTimeout();
	}

	public void veriFyCheckoutComplete(){
		waitUtil.isElementVisible(checkOutTitleComplete, explicitTimeOut);
		checkOutTitleComplete.isDisplayed();
	}
	public void verifyConfirmationMessage() {
		waitUtil.isElementVisible(confirmationMsg, explicitTimeOut);
		confirmationMsg.isDisplayed();
	}
	
}
