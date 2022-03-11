package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductdetailsPage {

	@FindBy (xpath="//input[@id='add-to-cart-button']")
	private WebElement addToCart;
	
	@FindBy (xpath="//input[@id='add-to-wishlist-button-submit']")
	private WebElement addToWishlist;
	
	@FindBy (xpath="//button[@data-action='a-popover-close']")
	private WebElement closeAddToWishlist;
	
	@FindBy (xpath="//div[@id='attach-added-to-cart-message']//div[2]//div[3]//form//input")
	private WebElement goToCart;
	
	public ProductdetailsPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddtoCartButton()
	{
		addToCart.click();
	}
	public void clickAddToWishlistButton()
	{
		addToWishlist.click();
	}
	public void clickCloseAddToWishlist()
	{
		closeAddToWishlist.click();
	}
	
	public void clickCartButton()
	{
		goToCart.click();
	}
}
