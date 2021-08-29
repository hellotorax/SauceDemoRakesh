package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.HelperUtil;
import utilities.WaitUtil;

public class YourCartPage extends BasePage{
	
	private HelperUtil helperUtil;
	private WaitUtil waitUtil;
	private int explicitTimeOut;

	@FindBy(id = "checkout")
	WebElement checkout;
	
	@FindBy(xpath="//span[@class='title']")
	WebElement yourCart;

	public YourCartPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
		waitUtil = new WaitUtil(driver);
		helperUtil = new HelperUtil(driver);
		explicitTimeOut = waitUtil.getExplicitTimeout();

	}
	
	public void verifyYourCartPage(){
		waitUtil.isElementVisible(yourCart, explicitTimeOut);
		yourCart.isDisplayed();
	}
	
	public void checkOutProducts() {
		checkout.click();
	}

}
