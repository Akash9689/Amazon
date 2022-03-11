package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	@FindBy (xpath="(//div[@class='a-section'])[1]//div[2]//h2//span")
	private WebElement product;
	
	public ProductPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void selectProduct()
	{
		product.click();
	}
}
