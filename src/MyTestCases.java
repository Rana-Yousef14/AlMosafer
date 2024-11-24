import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {

	@BeforeTest
	public void setUp() {
		mySetUpToEnterTheWebsite();
	}

	@Test(priority = 1, enabled = true)
	public void checkTheEinglishLanguageIsDefault() throws IOException, InterruptedException {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(actualLanguage, expectedLanguage);
		screenShot();
	}

	@Test(priority = 2, enabled = true)
	public void checkTheCurrencyIsSAR() throws IOException, InterruptedException {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(actualCurrency, expectedCurrency);
		screenShot();
	}

	@Test(priority = 3, enabled = true)
	public void checkTheCotactNumber() throws IOException, InterruptedException {
		String actualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		Assert.assertEquals(actualNumber, expectedNumber);
		screenShot();
	}

	@Test(priority = 4, enabled = true)
	public void checkTheQitafLogoThereInTheFooter() throws IOException, InterruptedException {
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean actualResult = theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();
		Assert.assertEquals(actualResult, expectedResultFOrQitafLogo);
		screenShot();
	}

	@Test(priority = 5, enabled = true)
	public void checkHotelTabIsNotSelected() throws InterruptedException, IOException {
		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String actualValue = hotelTab.getAttribute("aria-selected");
		Assert.assertEquals(actualValue, expectedRelsultForTheHotelTab);
		screenShot();
	}

	@Test(priority = 6, enabled = true)
	public void checkDepatureDate() throws IOException, InterruptedException {
		String actualDepatureDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(actualDepatureDate, expectedDepatureDate);
		screenShot();
	}

	@Test(priority = 7, enabled = true)
	public void checkReturnDate() throws IOException, InterruptedException {
		String actualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(actualReturnDate, expectedReturnDate);
		screenShot();
	}

	@Test(priority = 8, enabled = true)
	public void randomlyChangeTheLanguage() throws InterruptedException, IOException {
		randomlyEnterTheWebsite();
		WebElement hotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		checkTheWebsiteLanguageToFillTheCity(hotelSearchBar);
		enterNumberOfVisitorsAfterClickOnTheFirstCity();
		screenShot();
	}

	@Test(priority = 9, enabled = true)

	public void checkThatThePageIsFullyLoaded() throws InterruptedException, IOException {
		Thread.sleep(20000);
		WebElement searchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		boolean actualResult = searchResult.getText().contains("found") || searchResult.getText().contains("مكان");

		Assert.assertEquals(actualResult, expectedValueForFinishingSearchAboutHotel);
	}

	@Test(priority = 10)

	public void checkTheSortOption() throws InterruptedException, IOException {
		WebElement lowestPriceButton = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		lowestPriceButton.click();
		sortOptionChecker();
	}
}