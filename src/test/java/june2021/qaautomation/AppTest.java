package june2021.qaautomation;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import june2021.qaautomation.pages.CommonPage;
import june2021.qaautomation.pages.LoginPage;
import june2021.qaautomation.pages.ProfilePage;
import june2021.qaautomation.utils.DataUtility;
import june2021.qaautomation.utils.JavascriptSnippets;
import june2021.qaautomation.utils.TestUtility;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseWebTest {
	LoginPage loginPage = new LoginPage(driver, explicitWait);
	ProfilePage profilePage = new ProfilePage(driver, explicitWait);
	CommonPage commonPage = new CommonPage(driver, explicitWait);

	@Test(testName = "Verify Login", description = "Verify that for correct credentials, login is working fine")
	public void test1() {
		String user = DataUtility.getDataFromExcel("TestData", "username");
		String password = DataUtility.getDataFromExcel("TestData", "password");

		loginPage.login(user, password);
		String actualUser = profilePage.getProfileText(user);

		assertEquals(actualUser, user);
	}

	@Test(testName = "Verify Login", description = "Verify that for correct credentials, login is working fine")
	public void test2() {

		String user = "fullstackdemo";
		String password = "fullstackdemo1";

		loginPage.login(user, password);
		String actualUser = profilePage.getProfileText(user);

		assertEquals(actualUser, user);
	}

	@Test
	public void testSwitching() {

		commonPage.openNewTab();
		commonPage.switchWindow(1);
		commonPage.openUrl("https://facebook.com");
		commonPage.openNewWindow();
		commonPage.switchWindow(2);
		commonPage.openUrl("https://twitter.com");
		commonPage.switchWindow(0);

	}

	@Test
	public void testAlert() {

		commonPage.runJavaScriptCommand(JavascriptSnippets.alertScript);
		commonPage.acceptAlert();

		for (int i = 0; i < 10; i++) {
			commonPage.runJavaScriptCommand(JavascriptSnippets.scrollDownScript);
			TestUtility.hardWait(2);

		}
	}

	@Test
	public void testBrowserNavigation() {

		commonPage.openUrl("https://facebook.com");
		commonPage.navigateBrowser("back");
		commonPage.navigateBrowser("forward");
		commonPage.navigateBrowser("reload");

	}

}
