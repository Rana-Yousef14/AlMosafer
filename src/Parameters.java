import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Parameters {
	WebDriver driver = new ChromeDriver();
	String websiteUrl = "https://global.almosafer.com/en";
	Random rand = new Random();
	String expectedLanguage = "en";
	String expectedCurrency = "SAR";
	String expectedNumber = "+966554400000";
	boolean expectedResultFOrQitafLogo = true;
	String expectedRelsultForTheHotelTab = "false";
	int today = LocalDate.now().getDayOfMonth();
	int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
	String expectedDepatureDate = String.format("%02d", tomorrow);
	int dayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
	String expectedReturnDate = String.format("%02d", dayAfterTomorrow);
	String[] myWebsites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
	int randomIndex = rand.nextInt(myWebsites.length);
	String[] arabicCitiesNames = { "دبي", "جدة" };
	String[] englishCitiesNames = { "jeddah", "riyad", "dubai" };
	int randomArabiccities = rand.nextInt(arabicCitiesNames.length);
	int randomEnglishcities = rand.nextInt(englishCitiesNames.length);
	boolean expectedValueForFinishingSearchAboutHotel = true;

	public void mySetUpToEnterTheWebsite() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(websiteUrl);
		driver.manage().window().maximize();
		WebElement currencyButton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		currencyButton.click();
	}

	public void screenShot() throws InterruptedException, IOException {

		Thread.sleep(3000);

		Date myDate = new Date();

		String fileName = myDate.toString().replace(":", "-");

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);

		File destFile = new File("./ScreenShot/" + fileName + ".png");
		FileUtils.copyFile(SrcFile, destFile);
	}

	public void checkTheWebsiteLanguageToFillTheCity(WebElement hotelSearchBar) throws InterruptedException {
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
	}

	public void enterNumberOfVisitorsAfterClickOnTheFirstCity() {
		WebElement citiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		citiesList.findElements(By.tagName("li")).get(1).click();
		WebElement selectNumerOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		Select select = new Select(selectNumerOfVistor);
		int randomVistorNumber = rand.nextInt(2);
		select.selectByIndex(randomVistorNumber);
		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchButton.click();
	}

	public void randomlyEnterTheWebsite() {
		driver.get(myWebsites[randomIndex]);

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
	}

	public void sortOptionChecker() throws InterruptedException {
		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("en")) {

			/*
			 * WebElement container = driver.findElement(By.cssSelector(
			 * ".__ds__comp.undefined.MuiBox-root.muiltr-1smo8f0")); List<WebElement>
			 * priceList = container .findElements(By.cssSelector(
			 * ".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"
			 * )); int lowestPrice =
			 * Integer.parseInt(priceList.get(0).getText().replace("SAR ", "")); int
			 * HighestPrice = Integer.parseInt(priceList.get(priceList.size() -
			 * 1).getText().replace("SAR ", ""));
			 */

			List<WebElement> priceList = driver.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			int lowestPrice = Integer.parseInt(priceList.get(1).getText().replace("SAR ", ""));
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("SAR ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);
		} else {

			WebElement container = driver
					.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muirtl-gxyv4m"));
			List<WebElement> priceList = container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-85ahbu"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("ر.س. ", ""));
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("ر.س. ", ""));

//			List<WebElement> priceList = driver
//					.findElements(By.cssSelector(".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-85ahbu"));
//			int lowestPrice = Integer.parseInt(priceList.get(1).getText().replace("ر.س. ", ""));
//			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("ر.س. ", ""));
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
