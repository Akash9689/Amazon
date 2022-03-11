package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

	@FindBy (xpath= "//input[@type='text']")
	private WebElement googleEnterText;
	
	@FindBy (xpath= "(//input[@aria-label='Google Search'])[2]")
	private WebElement googleSearch;
	
	public GoogleSearchPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void SendgoogleText()
	{
		googleEnterText.sendKeys("https://www.amazon.com/");
	}
	
	public void ClickgoogleSearch()
	{
		googleSearch.click();
	}
}
