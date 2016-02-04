package GalenBasicTest;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.galenframework.testng.GalenTestNgTestBase;

import static java.util.Arrays.asList;

public class MultipleResolutionTests extends GalenTestNgTestBase {

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
		
		List<String> tagList = new ArrayList<String>();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = device.getDisplayMode().getWidth();
		int height = device.getDisplayMode().getHeight();
		System.out.println("Screen width and height are " + width + " and " + height);
		tagList.add("desktop " + width + " X " + height);
		TestData[][] data = new TestData[][] {
//				{ new TestData("Mobile", new Dimension(400, 500),
//						asList("mobile")) },
				{ new TestData("Desktop", new Dimension(1024, 800),
						tagList) }
		};
		return data;
	}

	@Test(dataProvider = "DriverSettings")
	public void TestGalenSite(TestData data) throws IOException {
		getDriver().get("http://testapp.galenframework.com");
		System.out.println("Tag for test is " + data.GetTags().toString());
		checkLayout("Specs//MultipleResolution.spec", data.GetTags());
	}

}
