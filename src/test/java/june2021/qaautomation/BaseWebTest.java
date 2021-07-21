package june2021.qaautomation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import june2021.qaautomation.utils.DataUtility;

public class BaseWebTest implements IDriverManager {
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<WebDriverWait>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see june2021.qaautomation.pages.IDriverManager#createChromeDriver()
	 */
	@BeforeMethod
	public void createChromeDriver() {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		driver.get().get(DataUtility.getDataFromExcel("Config", "BaseUrlApp"));
		driver.get().manage().window().maximize();
		explicitWait.set(new WebDriverWait(driver.get(),
				Duration.ofSeconds(Long.parseLong(DataUtility.getDataFromExcel("Config", "SeleniumWait")))));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see june2021.qaautomation.pages.IDriverManager#quitChromeDriver()
	 */
	@AfterMethod
	public void quitChromeDriver() {
		driver.get().quit();
	}
}
