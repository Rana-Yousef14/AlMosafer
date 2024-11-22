import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	WebDriver driver = new ChromeDriver();
	String websiteUrl = "https://global.almosafer.com/en";
	Random rand = new Random();

	@BeforeTest
	public void setUp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(websiteUrl);
		driver.manage().window().maximize();
		WebElement currencyButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		currencyButton.click();
	}

	@Test(priority = 1, enabled = true)
	public void checkTheEinglishLanguageIsDefault() {
		String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String expectedLanguage = "en";
		Assert.assertEquals(actualLanguage, expectedLanguage);
	}

	@Test(priority = 2, enabled = true)
	public void checkTheCurrencyIsSAR() {
		String actualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		String expectedCurrency = "SAR";
		Assert.assertEquals(actualCurrency, expectedCurrency);
	}

	@Test(priority = 3, enabled = true)
	public void checkTheCotactNumber() {
		String actualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String expectedNumber = "+966554400000";
		Assert.assertEquals(actualNumber, expectedNumber);
	}

	@Test(priority = 4, enabled = true)
	public void checkTheQitafLogoThereInTheFooter() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean actualResult = theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();
		boolean expectedResult = true;
		Assert.assertEquals(actualResult, expectedResult);
	}

	@Test(priority = 5, enabled = true)
	public void checkHotelTabIsNotSelected() {
		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String actualValue = hotelTab.getAttribute("aria-selected");
		String expectedValue = "false";
		Assert.assertEquals(actualValue, expectedValue);
	}

	@Test(priority = 6, enabled = true)
	public void checkDepatureDate() {
		int today = LocalDate.now().getDayOfMonth();
		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

//	System.out.println(today);
//	System.out.println(tomorrow);
//	System.out.println(dayAfterTomorrow);

		String actualDepatureDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		if (tomorrow < 10) {
			String expectedDepatureDate = "0" + Integer.toString(tomorrow);
			Assert.assertEquals(actualDepatureDate, expectedDepatureDate);
		} else {
			String expectedDepatureDate = Integer.toString(tomorrow);
			Assert.assertEquals(actualDepatureDate, expectedDepatureDate);
		}
	}

	@Test(priority = 7, enabled = true)
	public void checkReturnDate() {
		int today = LocalDate.now().getDayOfMonth();
		int dayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
		String actualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		if (dayAfterTomorrow < 10) {

			String expectedReturnDate = "0" + Integer.toString(dayAfterTomorrow);
			Assert.assertEquals(actualReturnDate, expectedReturnDate);
		} else {
			String expectedReturnDate = Integer.toString(dayAfterTomorrow);
			Assert.assertEquals(actualReturnDate, expectedReturnDate);
		}
	}

	@Test(priority = 8, enabled = true)
	public void randomlyChangeTheLanguage() throws InterruptedException {
		String[] myWebsites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
		int randomIndex = rand.nextInt(myWebsites.length);
		driver.get(myWebsites[randomIndex]);

		String[] arabicCitiesNames = { "دبي", "جدة" };
		String[] englishCitiesNames = { "jeddah", "riyad", "dubai" };
		int randomArabiccities = rand.nextInt(arabicCitiesNames.length);
		int randomEnglishcities = rand.nextInt(englishCitiesNames.length);

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		WebElement hotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) // .contains("ar"))
		{
			String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedLanguage = "ar";
			Assert.assertEquals(actualLanguage, expectedLanguage);
			hotelSearchBar.sendKeys(arabicCitiesNames[randomArabiccities]);
		} else {
			String actualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedLanguage = "en";
			Assert.assertEquals(actualLanguage, expectedLanguage);
			hotelSearchBar.sendKeys(englishCitiesNames[randomEnglishcities]);
		}
		Thread.sleep(2000);
		WebElement citiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		citiesList.findElements(By.tagName("li")).get(1).click();
		WebElement selectNumerOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		Select select = new Select(selectNumerOfVistor);
		int randomVistorNumber = rand.nextInt(2);
		select.selectByIndex(randomVistorNumber);
		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchButton.click();
	}

	@Test(priority = 9, enabled = true)

	public void checkThatThePageIsFullyLoaded() throws InterruptedException {
		Thread.sleep(35000);
		WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		boolean ActualResult = SearchResult.getText().contains("found") || SearchResult.getText().contains("مكان");

		boolean ExpectedResult = true;

		Assert.assertEquals(ActualResult, ExpectedResult);
	}

	@Test(priority = 10)

	public void checkTheSortOption() throws InterruptedException {
		Thread.sleep(20000);
		WebElement lowestPriceButton = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		lowestPriceButton.click();
		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("en")) {
			List<WebElement> priceList = driver
					.findElements(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muiltr-1nylpq2"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("SAR ", ""));
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("SAR ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);
		} else {

			List<WebElement> priceList = driver
					.findElements(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muirtl-1nylpq2"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("ر.س. ", ""));
			System.out.println();
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("ر.س. ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);

		}
	}
}