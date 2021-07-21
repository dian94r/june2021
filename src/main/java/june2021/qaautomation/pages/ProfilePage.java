package june2021.qaautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

	String profileText = "//div[normalize-space()='%s']";

	/**
	 * @param driver
	 */
	public ProfilePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	public String getProfileText(String username) {
		By finalXpath = By.xpath(String.format(profileText, username));
		return getText(finalXpath);

	}

}
