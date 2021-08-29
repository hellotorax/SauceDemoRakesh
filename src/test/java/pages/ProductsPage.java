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

public class ProductsPage extends BasePage{
	private HelperUtil helperUtil;
	private WaitUtil waitUtil;
	private int explicitTimeOut;

	@FindBy(xpath = "//select[@data-test='product_sort_container']")
	WebElement sortFilter;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement cartBadge;
	
	@FindBy(xpath="//span[@class='title']")
	WebElement productsTitle;
	
	public ProductsPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
		waitUtil = new WaitUtil(driver);
		helperUtil = new HelperUtil(driver);
		explicitTimeOut = waitUtil.getExplicitTimeout();

	}

	public void verifyProductsPage(){
		waitUtil.isElementVisible(productsTitle, explicitTimeOut);
		productsTitle.isDisplayed();
	}
	
	
	public void selectSortDropDownValue(String value) {
		waitUtil.isElementVisible(sortFilter, explicitTimeOut);
		helperUtil.selectByValue(sortFilter, value);
	}

	public List<String> getListofItems() {
		List<String> listOfItems = new ArrayList<String>();
		List<WebElement> list = driver.findElements(By.className("inventory_item_name"));
		for(int i=0;i<list.size();i++) {
			listOfItems.add(list.get(i).getText());
		}
		return listOfItems;

	}

	public void addProductstoCart(int totalProducts) {

		List<WebElement> productAdded = driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
		for(int i=0;i<totalProducts;i++) {
			productAdded.get(i).click();
		}
	}
	
	public void clickOnCart() {
		cartBadge.click();
	}
}



