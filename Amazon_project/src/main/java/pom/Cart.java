package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {

	@FindBy (xpath="//input[@data-feature-id='proceed-to-checkout-action']")
	private WebElement proceedToBuy;
	
	public Cart (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickProceedToBuy()
	{
		proceedToBuy.click();
	}
}
