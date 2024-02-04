package AlmosaferWeb;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyTestCases extends Paramaeters {

	@BeforeTest
	public void mySetup() {

		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement popUPScreen = driver.findElement(By.className("sc-iBmynh"));

		if (popUPScreen.isDisplayed()) {
			WebElement SARBUTTOn = driver.findElement(By.className("cta__saudi"));
			SARBUTTOn.click();

		}

	}

	@Test(priority = 1, enabled = false)
	public void CheckTheDeafultLanguageIsEnglish() {
		String ExpectedLanaguage = "EN";
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang").toUpperCase();
		assertEquals(ActualLanguage, ExpectedLanaguage);

	}

	@Test(priority = 2, enabled = false)
	public void CheckTheDefaultCurrencyIsSAR() throws InterruptedException {
		String ExpectedCurrency = "SAR";
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3, enabled = false)
	public void CheckContactNumber() throws InterruptedException {
		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4, enabled = false)
	public void CheckQitafLogo() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));

		System.out.println();

		assertEquals(theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed(), true);
	}

	@Test(priority = 5, enabled = false)
	public void CheckHotelTabIsNotSelectedByDefault() {

		;
		assertEquals(driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected"),
				"false");

	}

	@Test(priority = 6, enabled = false)
	public void CheckDepatureDateAndReturnDate() {

		LocalDate today = LocalDate.now();

		int expectedDepatureDate = today.plusDays(1).getDayOfMonth();
		int expectedReturnDate = today.plusDays(2).getDayOfMonth();

		int ActualDepatureDate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		int ActualReturn = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		assertEquals(ActualReturn, expectedReturnDate);
		assertEquals(ActualDepatureDate, expectedDepatureDate);

	}

	@Test(priority = 7)
	public void RandomMethodToChangeTheLanguage() {
		Random rand = new Random();

		int randomIndexForTheWebSite = rand.nextInt(Websites.length);

		driver.get(Websites[randomIndexForTheWebSite]);

		if (driver.getCurrentUrl().contains("ar")) {
			String ExpectedLang = "ar";

			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");

			assertEquals(ActualLang, ExpectedLang);
		} else {
			String ExpectedLang = "en";

			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");

			assertEquals(ActualLang, ExpectedLang);

		}

	}

	@Test(priority = 8)
	public void switchToHotelTab() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		if (driver.getCurrentUrl().contains("ar")) {
			WebElement searchCityInput = driver
					.findElement(By.cssSelector("input[placeholder='البحث عن فنادق أو وجهات']"));

			searchCityInput.sendKeys(CitiesInArabic[randomArabicCity]);
		} else {
			WebElement SearchCityInput = driver
					.findElement(By.cssSelector("input[placeholder='Search for hotels or places']"));

			SearchCityInput.sendKeys(CitiesInEnglish[randomEnglishCity]);
		}
		WebElement theList = driver.findElement(By.className("UzzIN"));

		System.out.println(theList.findElements(By.tagName("li")).size());

		theList.findElements(By.tagName("li")).get(1).click();

		// hard assert
		// if this test case failed i will not go to the following test cases

		org.testng.Assert.assertEquals(false, true);
	}

	@Test(priority = 9)
	public void randomlySelectThevistorNumber() {
		WebElement Vistors = driver.findElement(By.tagName("select"));
		Select selector = new Select(Vistors);

		if (driver.getCurrentUrl().contains("ar")) {
			selector.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");
			;

		} else {
			selector.selectByVisibleText("1 Room, 1 Adult, 0 Children");
		}
		driver.findElement(
				By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div[2]/div/div[4]/button")).click();

	}

	@Test(priority = 10)

	public void makeSurePageIsFullyLoaded() throws InterruptedException {
		Thread.sleep(25800);
		String SearchResult = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/section/span")).getText();

		if (driver.getCurrentUrl().contains("ar")) {
			boolean Actualresult = SearchResult.contains("وجدنا");
			assertEquals(Actualresult, true);
		}

		else {
			boolean Actualresult = SearchResult.contains("found");
			assertEquals(Actualresult, true);

		}

		org.testng.Assert.assertEquals(false, true);

	}

	@Test(priority = 11)

	public void sortTheItemsBasedOnThePrice() throws InterruptedException {
		Thread.sleep(5000);
		WebElement LowestPriceButton = driver
				.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/div[2]/section[1]/div/button[2]"));

		LowestPriceButton.click();

		WebElement HotelsContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> thePricesList = HotelsContainer.findElements(By.className("Price__Value"));

		System.out.println(thePricesList.size() + "this is the total prices found ");

		String LowestPriceOnTheList = thePricesList.get(0).getText();
		int LowestPriceOnTheListAsNumber = Integer.parseInt(LowestPriceOnTheList);

		String HighestPriceOntheList = thePricesList.get(thePricesList.size() - 1).getText();
		int HighestPriceOntheListAsNumber = Integer.parseInt(HighestPriceOntheList);

		System.out.println("this the minimum value " + LowestPriceOnTheList);
		System.out.println("this the maximum value " + HighestPriceOntheList);

		assertEquals(HighestPriceOntheListAsNumber > LowestPriceOnTheListAsNumber, true);

	}

	@AfterTest
	public void myPostTest() {
	}

}
