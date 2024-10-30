import java.time.LocalDate;

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
	WebElement theFooter = driver.findElement(By.tagName("footer"));
	boolean actualResult = theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
	boolean expectedResult = true;
	Assert.assertEquals(actualResult, expectedResult);
}
@Test (priority = 5)
public void checkHotelTabIsNotSelected() {
	WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	String actualValue = hotelTab.getAttribute("aria-selected");
	String expectedValue = "false";
	Assert.assertEquals(actualValue, expectedValue);
}
@Test (priority = 6)
public void checkDepatureDate() {
	int today = LocalDate.now().getDayOfMonth();
	int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
	
//	System.out.println(today);
//	System.out.println(tomorrow);
//	System.out.println(dayAfterTomorrow);
	
	String actualDepatureDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
	String expectedDepatureDate = Integer.toString(tomorrow);
	Assert.assertEquals(actualDepatureDate, expectedDepatureDate);
}
@Test (priority = 7)
public void checkReturnDate() {
	int today = LocalDate.now().getDayOfMonth();
	int dayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
	
	String actualReturnDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
	String expectedReturnDate = Integer.toString(dayAfterTomorrow);
	Assert.assertEquals(actualReturnDate, expectedReturnDate);
}
}
