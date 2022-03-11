package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy (xpath="//input[@type='email']")
	private WebElement enterEmail;	

	@FindBy (xpath="//input[@id='continue']")
	private WebElement proceed;
	
	@FindBy (xpath="//input[@type='password']")
	private WebElement enterPassword;
	
	@FindBy (xpath="//input[@id='signInSubmit']")
	private WebElement login;
	
	
	public LoginPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void sendEmailId(String emailID)
	{
		enterEmail.sendKeys(emailID);;
	}
	
	public void clickContinue()
	{
		proceed.click();
	}
	
	public void sendPassword(String pass)
	{
		enterPassword.sendKeys(pass);;
	}
	
	public void clickLogin()
	{
		login.click();
	}
}
