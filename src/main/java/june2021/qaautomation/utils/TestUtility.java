package june2021.qaautomation.utils;

public class TestUtility {

	public static void hardWait(int seconds) {

		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
