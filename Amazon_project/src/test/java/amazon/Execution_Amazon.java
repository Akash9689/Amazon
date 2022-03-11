package amazon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.AmazonHomePageHeader;
import pom.Cart;
import pom.GoogleSearchPage;
import pom.GoogleSearchResultPage;
import pom.LoginPage;
import pom.ProductPage;
import pom.ProductdetailsPage;
import util.Utility;

public class Execution_Amazon {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		System.setProperty("webdriver.chrome.driver","F:\\Java Classes\\Webdrivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		GoogleSearchPage googleSearchPage=new GoogleSearchPage(driver);
		googleSearchPage.SendgoogleText();
		googleSearchPage.ClickgoogleSearch();
		
		GoogleSearchResultPage GoogleSearchResultPage=new GoogleSearchResultPage(driver);
		GoogleSearchResultPage.ClickAmazonSite();
		
		AmazonHomePageHeader amazonHomePage=new AmazonHomePageHeader(driver);
		amazonHomePage.clickSignIn();
		
		LoginPage loginPage=new LoginPage(driver);
		String emailID=Utility.extractDataFromExcel("Sheet1", 5, 0);
		String pass=Utility.extractDataFromExcel("Sheet1", 5, 1);
		loginPage.sendEmailId(emailID);
		loginPage.clickContinue();
		loginPage.sendPassword(pass);
		loginPage.clickLogin();
		
		String searchData=Utility.extractDataFromExcel("Sheet1", 16, 0);
		amazonHomePage.sendText(searchData);
		amazonHomePage.ClicksearchButton();
		
		ProductPage productPage=new ProductPage(driver);
		productPage.selectProduct();
		
		ArrayList<String> add =new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(add.get(1));
		
		ProductdetailsPage productdetailsPage=new ProductdetailsPage(driver);
		productdetailsPage.clickAddtoCartButton();
		productdetailsPage.clickCartButton();
		
		Cart cart=new Cart(driver);
		cart.clickProceedToBuy();
		
		
		
		
		
	}
}
