package amazon;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import base.Browser;
import pom.AmazonHomePageHeader;
import pom.LoginPage;
import util.Utility;

public class Test_Execution_1 extends Browser{
	
	private String testID;
	private WebDriver driver;
	private AmazonHomePageHeader amazonHomePage;
	private LoginPage loginPage;
	static ExtentHtmlReporter reporter;
	static ExtentTest test;

	@BeforeTest
	@Parameters("browser")
	public void enterBrowser(String browserName) {
		reporter =new ExtentHtmlReporter("test-output"+File.separator+"ExtentReport"+File.separator+"Extent.html");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		System.out.println("Launching Browser........");
		if(browserName.equals("Chrome")) {
			driver=launchChromeBrowser();
		}
		if(browserName.equals("Firefox")) {
			driver=launchFirefoxBrowser();
		}
		if(browserName.equals("Opera")) {
			driver=launchOperaBrowser();
		}
		if(browserName.equals("Edge")) {
			driver=launchEdgeBrowser();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void createObjects()
	{
		amazonHomePage=new AmazonHomePageHeader(driver);
		loginPage=new LoginPage(driver);
	}
	
	@BeforeMethod
	public void loginToApplication() throws EncryptedDocumentException, IOException, InterruptedException, InvalidFormatException
	{
		System.out.println("Launching Webpage using URL..............");
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		amazonHomePage.moveToAccountDetails();
		amazonHomePage.clickSignIn();
		
		System.out.println("Logging In.....................");
		String emailID=Utility.extractDataFromExcel("Sheet1", 5, 0);
		String pass=Utility.extractDataFromExcel("Sheet1", 5, 1);
		loginPage.sendEmailId(emailID);
		loginPage.clickContinue();
		loginPage.sendPassword(pass);
		loginPage.clickLogin();
	}
	
	@Test (priority=-1)
	public void verifyAmazonHomePage() throws InterruptedException
	{
		testID="-Amazon_1";
		Thread.sleep(2000);
//		Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/ap/signin");
		Assert.assertEquals(driver.getTitle(), "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	
	}
	@Test(dependsOnMethods= {"verifyAmazonHomePage"})
	public void verifyAccountPage()
	{
		testID="-Amazon_2";
		amazonHomePage.moveToAccountDetails();
		amazonHomePage.clickYourAccount();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/gp/css/homepage.html?ref_=nav_AccountFlyout_ya");

	}
	@Test(dependsOnMethods= {"verifyAmazonHomePage"})
	public void verifyOrderPage()
	{
		testID="-Amazon_3";
		amazonHomePage.moveToAccountDetails();
		amazonHomePage.clickYourOrders();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/gp/css/order-history?ref_=nav_AccountFlyout_orders");
	}
	@Test(dependsOnMethods= {"verifyAmazonHomePage"})
	public void verifyWishlistPage()
	{
		testID="-Amazon_4";
		amazonHomePage.moveToAccountDetails();
		amazonHomePage.clickYourWishList();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/hz/wishlist/ls?requiresSignIn=1&ref_=nav_AccountFlyout_wl");
		
	}
	@Test(dependsOnMethods= {"verifyAmazonHomePage"})
	public void verifyProductPage() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		testID="-Amazon_5";
		String searchData=Utility.extractDataFromExcel("Sheet1", 16, 0);
		amazonHomePage.sendText(searchData);
		amazonHomePage.ClicksearchButton();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/s?k=realmi+8i&ref=nb_sb_noss");
	}
	
	@AfterMethod
	public void logoutOfApplication(ITestResult result) throws IOException
	{
		if(ITestResult.FAILURE==result.getStatus()) {
		Utility.takeScreenshot(driver, testID);
		}
		System.out.println("Logging Out.................");
		amazonHomePage.moveToAccountDetails();
		amazonHomePage.clickSignOut();
		
	}
	
	@AfterClass
	public void deleteObjectReferences() {
		amazonHomePage=null;
		loginPage=null;
	}
	@AfterTest
	public void exitBrowser() {
		System.out.println("Exiting Browser..........");
		driver.quit();
		System.gc();
	}
}
