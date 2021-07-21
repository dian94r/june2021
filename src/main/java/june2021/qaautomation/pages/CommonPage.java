package june2021.qaautomation.pages;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage extends BasePage {

	/**
	 * @param driver
	 */
	public CommonPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	public void openUrl(String url) {

		driver.get().get(url);

	}

	public void openNewTab() {
		driver.get().switchTo().newWindow(WindowType.TAB);
	}

	public void openNewWindow() {
		driver.get().switchTo().newWindow(WindowType.WINDOW);
	}

	public void switchWindow(int index) {
		Set<String> handles = driver.get().getWindowHandles();

		ArrayList<String> tabs = new ArrayList<String>(handles);

		driver.get().switchTo().window(tabs.get(index));

	}

	public void acceptAlert() {

		driver.get().switchTo().alert().accept();

	}

	public void runJavaScriptCommand(String script) {

		JavascriptExecutor js = (JavascriptExecutor) driver.get();

		js.executeScript(script);

	}

	public void navigateBrowser(String actionName) {

		if (actionName.equalsIgnoreCase("forward")) {
			driver.get().navigate().forward();
		}

		if (actionName.equalsIgnoreCase("back")) {
			driver.get().navigate().back();
		}

		if (actionName.equalsIgnoreCase("refresh")) {
			driver.get().navigate().refresh();
		}

	}

}
