package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchResultPage {

	@FindBy (xpath="(//h3[text()='Amazon.in'])[1]")
	private WebElement amazonSite;
	
	public GoogleSearchResultPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void ClickAmazonSite()
	{
		amazonSite.click();
	}
}
