package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.HelperUtil;
import utilities.WaitUtil;

public class CheckOutCompletePage extends BasePage {
	private HelperUtil helperUtil;
	private WaitUtil waitUtil;
	private int explicitTimeOut;

	@FindBy(xpath="//span[@class='title']")
	WebElement checkOutTitle;

	@FindBy(id = "finish")
	WebElement btnFinish;

	public CheckOutCompletePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
		waitUtil = new WaitUtil(driver);
		helperUtil = new HelperUtil(driver);
		explicitTimeOut = waitUtil.getExplicitTimeout();
	}

	public void veriFyCheckoutOverView(){
		waitUtil.isElementVisible(checkOutTitle, explicitTimeOut);
		checkOutTitle.isDisplayed();
	}
	
	public void clickOnFinish() {
		btnFinish.click();
	}


}
