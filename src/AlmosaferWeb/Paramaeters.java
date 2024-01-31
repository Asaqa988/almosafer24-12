package AlmosaferWeb;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Paramaeters {

	WebDriver driver = new ChromeDriver();
	String URL = "https://global.almosafer.com/en";

	Random randForcity = new Random();

	String[] Websites = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };

	String[] CitiesInEnglish = { "dubai", "jeddah", "riyadh" };

	int randomEnglishCity = randForcity.nextInt(CitiesInEnglish.length);

	String[] CitiesInArabic = { "دبي", "جدة" };

	int randomArabicCity = randForcity.nextInt(CitiesInArabic.length);

}
