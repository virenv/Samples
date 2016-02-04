package GalenBasicTest;

import java.io.IOException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.galenframework.testng.GalenTestNgTestBase;

import static java.util.Arrays.asList;

public class MobileLayoutTests extends GalenTestNgTestBase {

	/*
	 * This is a place where you will have a choice to create webdriver anf
	 * resize it (non-Javadoc)
	 * 
	 * @see
	 * com.galenframework.support.GalenJavaTestBase#createDriver(java.lang.Object
	 * [])
	 */
	@Override
	public WebDriver createDriver(Object[] arg0) {
		if (arg0[0] instanceof TestData) {
			TestData data = (TestData) arg0[0];
			WebDriver driver = new FirefoxDriver();
			if (!data.GetTags().get(0).equals("desktop")) {
				driver.manage().window().setSize(data.GetBrowserDimensions());
			}
			else
			{
				driver.manage().window().maximize();
			}
			return driver;
		}
		return null;
	}

	@DataProvider(name = "DriverSettings")
	public static Object[][] DriverDataProvider() {
		return new TestData[][] {
				{ new TestData("Mobile", new Dimension(400, 500),
						asList("mobile")) },
				{ new TestData("Desktop", new Dimension(1024, 800),
						asList("desktop")) }
		};
	}

	@Test(dataProvider = "DriverSettings")
	public void TestGalenSite(TestData data) throws IOException {
		getDriver().get("http://testapp.galenframework.com");
		System.out.println("Tag for test is " + data.GetTags().toString());
		checkLayout("Specs//ToolsqaHomePage.spec", data.GetTags());
	}

}
