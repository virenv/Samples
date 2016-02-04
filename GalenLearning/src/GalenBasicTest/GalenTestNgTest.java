package GalenBasicTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.galenframework.testng.GalenTestNgTestBase;

public class GalenTestNgTest extends GalenTestNgTestBase {
	
	@Override
	public WebDriver createDriver(Object[] arg0) {
		
		System.out.println("Size of the array is " + arg0.length);
		
		for(Object obj : arg0)
		{
			System.out.println(" -> " + obj.toString());
		}
		
		return new FirefoxDriver();
	}
	
	@Test
	public void testGalenTestWebSite() throws IOException
	{
		System.out.println("Simple test to verify the look and feel of elements on Galen website");
		getDriver().get("http://testapp.galenframework.com");
		List<String> tags = new ArrayList<String>();
		tags.add("desktop");
		checkLayout("Specs//ToolsqaHomePage.spec", tags);
	}
	
	@Test
	public void testHighChartSample() throws IOException
	{
		System.out.println("Simple test to verify the advanced look and feel of an High chart sample");
		getDriver().get("http://highcharttable.org/#demo");
		List<String> tags = new ArrayList<String>();
		tags.add("desktop");
		checkLayout("Specs//HighchartSample.spec", tags);
	}

}
