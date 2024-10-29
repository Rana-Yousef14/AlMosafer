import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase {
	WebDriver driver = new ChromeDriver();
	String websiteUrl = "https://global.almosafer.com/en";
@BeforeTest
public void setUp() {
	driver.get(websiteUrl);
	driver.manage().window().maximize();
	WebElement currencyButton = driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
	currencyButton.click();
}
@Test (priority = 1)
public void checkTheEinglishLanguageIsDefault() {
	String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
	String expectedLanguage = "en";
	Assert.assertEquals(actualLanguage, expectedLanguage);
}
@Test (priority = 2)
public void checkTheCurrencyIsSAR() {
	String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']")).getText();
	String expectedCurrency = "SAR";
	Assert.assertEquals(actualCurrency, expectedCurrency);
}
@Test (priority = 3)
public void checkTheCotactNumber() {
	String actualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
	String expectedNumber = "+966554400000";
	Assert.assertEquals(actualNumber, expectedNumber);
}
@Test (priority = 4)
public void checkTheQitafLogoThereInTheFooter() {
	boolean actualResult = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
	boolean expectedResult = true;
	Assert.assertEquals(actualResult, expectedResult);
}
}
