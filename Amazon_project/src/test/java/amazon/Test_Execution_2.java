package amazon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import pom.ProductPage;
import pom.ProductdetailsPage;
import util.Utility;

public class Test_Execution_2 extends Browser{

	private String testID;
	private WebDriver driver;
	private AmazonHomePageHeader amazonHomePage;
	private LoginPage loginPage;
	private ProductPage productPage;
	private ProductdetailsPage productdetailsPage;
	private ArrayList<String> add ;
	static ExtentHtmlReporter reporter;
	static ExtentTest test;
	
	@BeforeTest
	@Parameters("browser")
	public void enterBrowser(String browserName) {
		reporter =new ExtentHtmlReporter("test-output"+File.separator+"ExtentReport"+File.separator+"Extent.html");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
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
	public void launchBrowserAndHitUrl()
	{
		amazonHomePage=new AmazonHomePageHeader(driver);
		loginPage=new LoginPage(driver);
		productPage=new ProductPage(driver);
		productdetailsPage=new ProductdetailsPage(driver);
	}
	
	@BeforeMethod
	public void loginToApplication() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		Thread.sleep(3000);
		amazonHomePage.moveToAccountDetails();
		amazonHomePage.clickSignIn();
		
		String emailID=Utility.extractDataFromExcel("Sheet1", 5, 0);
		String pass=Utility.extractDataFromExcel("Sheet1", 5, 1);
		loginPage.sendEmailId(emailID);
		loginPage.clickContinue();
		loginPage.sendPassword(pass);
		loginPage.clickLogin();
		
		String searchData=Utility.extractDataFromExcel("Sheet1", 16, 0);
		amazonHomePage.sendText(searchData);
		amazonHomePage.ClicksearchButton();
		productPage.selectProduct();
		add=new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(3000);;
	}
	
	@Test 
	public void verifyAddToCart() throws InterruptedException
	{
		testID="-Amazon_6";
		driver.switchTo().window(add.get(1));
		productdetailsPage=new ProductdetailsPage(driver);
		productdetailsPage.clickAddtoCartButton();
		productdetailsPage.clickCartButton();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/gp/cart/view.html/ref=dp_atch_dss_cart?");
	}
	@Test
	public void verifyAddToWishlist() throws InterruptedException
	{
		testID="-Amazon_7";
		driver.switchTo().window(add.get(2));
		productdetailsPage.clickAddToWishlistButton();
		Thread.sleep(2000);
		productdetailsPage.clickCloseAddToWishlist();

	}
	
	@AfterMethod
	public void logoutOfApplication(ITestResult result) throws InterruptedException, IOException
	{
//		driver.switchTo().window(add.get(0));
//		driver.close();
//		driver.switchTo().window(add.get(1));
		
		if(ITestResult.FAILURE==result.getStatus()) {
		Utility.takeScreenshot(driver, testID);
		}
		Thread.sleep(1000);
		amazonHomePage.moveToAccountDetails();
		Thread.sleep(1000);
		amazonHomePage.clickSignOut();
		
	}
	
	@AfterClass
	public void deleteObjectReferences() {
		amazonHomePage=null;
		loginPage=null;
		productPage=null;
		productdetailsPage=null;
	}
	@AfterTest
	public void exitBrowser() {
		driver.quit();
//		System.gc();
	}
}
