package tests;

import builder.YourInfoBuilder;
import junit.framework.Assert;
import model.YourInfoModel;
import pages.CheckOutCompletePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.TransactionComplete;
import pages.YourCartPage;
import pages.YourInformationPage;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.CsvReader;
import utilities.HelperUtil;
import utilities.LogUtil;

import java.util.Arrays;
import java.util.List;
import java.io.File;

/**
 * Author: Rakesh Kumar Padhiary
 */
public class PurchaseSwagLabs extends BaseTest {

	private static Logger _log = LoggerFactory.getLogger(PurchaseSwagLabs.class);
	private String testCaseName = this.getClass().getName().trim();

	private CsvReader readCsv = new CsvReader();
	private String baseUrl = ConfigReader.get("baseURL");
	private String userName = ConfigReader.get("username");
	private String password = ConfigReader.get("password");
	private String sortValue = ConfigReader.get("sortFilter");
	private String totalProducts = ConfigReader.get("totalProducts");

	public LoginPage loginPage;
	public ProductsPage productsPage;
	public YourCartPage yourCartPage;
	public YourInformationPage yourInformationPage;
	public CheckOutCompletePage checkoutCompletePage;
	public HelperUtil helperUtil;
	public TransactionComplete transactionCompletePage;

	@BeforeClass
	public void setup(){
		LogUtil.info("End to End testcases for SauceDemoSite");
		LogUtil.startTestCase(testCaseName);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		productsPage = PageFactory.initElements(driver, ProductsPage.class);
		yourCartPage = PageFactory.initElements(driver, YourCartPage.class);
		yourInformationPage = PageFactory.initElements(driver, YourInformationPage.class);
		checkoutCompletePage = PageFactory.initElements(driver, CheckOutCompletePage.class);
		yourInformationPage = PageFactory.initElements(driver, YourInformationPage.class);
		transactionCompletePage=PageFactory.initElements(driver, TransactionComplete.class);
		helperUtil = new HelperUtil(driver);
	}

	@Test(priority=1,description="Login")
	public void loginSauceDemo() {
		helperUtil.navigatePage(baseUrl);
		loginPage.sauceDemoLogin(userName, password);
	}

	@Test(priority=2,description = "Sort items")
	public void sortFilterZtoA() {
		productsPage.verifyProductsPage();
		productsPage.selectSortDropDownValue(sortValue);
	}

	@Test(priority=3,description = "Get all the items in the page")
	public void validateProductsSorted() {
		List<String> expectedSortedList = Arrays.asList("Test.allTheThings() T-Shirt (Red)",
				"Sauce Labs Onesie",
				"Sauce Labs Fleece Jacket",
				"Sauce Labs Bolt T-Shirt",
				"Sauce Labs Bike Light",
				"Sauce Labs Backpack"
				);

		List<String> listofItems= productsPage.getListofItems();
		Assert.assertEquals(expectedSortedList, listofItems);

	}

	@Test(priority=4,description = "Add items to cart")
	public void addItemstoCart() {
		productsPage.addProductstoCart(Integer.parseInt(totalProducts));
		productsPage.clickOnCart();
	}

	@Test(priority=5,description = "Click on CheckOut")
	public void clickOnCheckOut() {

		yourCartPage.verifyYourCartPage();
		yourCartPage.checkOutProducts();
	}

	@DataProvider(name= "saucedemo")
	public Object[][] sauceDemoData(){
		Object[][] rawData;
		List<String[]> saucedemos = readCsv.parseFile(new File(ConfigReader.get("YourInfoCsv"))
				.getAbsolutePath());
		rawData = new Object[saucedemos.size()][1];

		for(int i = 0; i< saucedemos.size(); i++){
			for(String[] saucedemodata : saucedemos){
				rawData[i++][0] = new YourInfoBuilder(saucedemodata);
			}
		}
		return rawData;
	}

	@Test(priority=6,description = "Enter your Information", dataProvider = "saucedemo")
	public void fillYourInformation(YourInfoBuilder data){

		yourInformationPage.veriFyYourInformationPage();
		yourInformationPage.enterFirstName(data.getInfoDetails().getfirstname());
		yourInformationPage.enterLastName(data.getInfoDetails().getLastname());
		yourInformationPage.enterPostalCode(data.getInfoDetails().getPostalCode());
		yourInformationPage.clickOnContinue();
	}

	@Test(priority=7,description = "ClickOnFinishPage")
	public void clickOnFinishPage() {
		checkoutCompletePage.clickOnFinish();

	}

	@Test(priority=8,description ="Transaction Complete Page")
	public void transactionComplete() {		

		transactionCompletePage.verifyConfirmationMessage();

	}

	@Test(priority=9,description = "Verify Confirmatioin message",dependsOnMethods ="clickOnFinishPage")
	public void verifyConfirmatioinMessage() {
		transactionCompletePage.verifyConfirmationMessage();
	}

	@AfterTest(description = "After test info")
	public void afterTest() {
		LogUtil.endTestCase(testCaseName);
		driver.quit();
	}

}
