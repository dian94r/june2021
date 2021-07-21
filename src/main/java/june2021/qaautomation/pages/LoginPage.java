package june2021.qaautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	By loginLink = By.xpath("//a[normalize-space()='Log In/Register As Student']");
	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.xpath("//button[normalize-space()='Login']");

	/**
	 * @param driver
	 */
	public LoginPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	public void login(String user, String pass) {
		clickAndWaitByXpath(loginLink);
		setText(username, user);
		setText(password, pass);
		clickAndWaitByXpath(loginBtn);

	}

}
