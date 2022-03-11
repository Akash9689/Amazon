package pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePageHeader {
	
	private WebDriver driver;
	
	@FindBy (xpath="(//span[text()='Sign in'])[2]")
	private WebElement onPageSignInElement;

	@FindBy (xpath="//a[@id='nav-link-accountList']")
	private WebElement userAccountDetails;
	
	@FindBy (xpath="(//span[text()='Sign in'])[1]")
	private WebElement signIn;
	
	@FindBy (xpath="//span[text()='Your Account']")
	private WebElement yourAccount;
	
	@FindBy (xpath="//span[text()='Your Orders']")
	private WebElement yourOrders;
	
	@FindBy (xpath="//span[text()='Your Wish List']")
	private WebElement yourWishList;
	
	@FindBy (xpath="//span[text()='Sign Out']")
	private WebElement signOut;
	
	@FindBy (xpath="//input[@id='twotabsearchtextbox']")
	private WebElement enterText;
	
	@FindBy (xpath="//input[@type='submit']")
	private WebElement amazonSearch;
	
	private Actions actions;
	
	public AmazonHomePageHeader (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
		actions=new Actions(driver);
	}
	
	public void clickSignIn()
	{
//		onPageSignInElement.click();
		actions.moveToElement(signIn).click().build().perform();
	}
	
	public void moveToAccountDetails()
	{
		JavascriptExecutor javascriptExecutor=(JavascriptExecutor)driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)",userAccountDetails );
		actions.moveToElement(userAccountDetails).perform();
	}
	
//	public void clickSignIn()
//	{
//		actions.moveToElement(userAccountDetails).perform();
//		actions.moveToElement(signIn).click().build().perform();
//	}
	
	public void clickYourAccount()
	{
//		actions.moveToElement(userAccountDetails).perform();
		actions.moveToElement(yourAccount).click().build().perform();
	}
	
	public void clickYourOrders()
	{
//		actions.moveToElement(userAccountDetails).perform();
		actions.moveToElement(yourOrders).click().build().perform();
	}
	
	public void clickYourWishList()
	{
//		actions.moveToElement(userAccountDetails).perform();
		actions.moveToElement(yourWishList).click().build().perform();
	}
	
	public void clickSignOut()
	{
//		actions.moveToElement(userAccountDetails).perform();
		actions.moveToElement(signOut).click().build().perform();
	}
	
	public void sendText(String searchData)
	{
		enterText.sendKeys(searchData);
	}
	
	public void ClicksearchButton()
	{
		amazonSearch.click();
	}
}
