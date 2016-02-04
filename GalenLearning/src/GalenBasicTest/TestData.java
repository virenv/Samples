package GalenBasicTest;

import java.util.List;
import org.openqa.selenium.Dimension;

public class TestData {

	private String PlatformName;
	private Dimension BrowserDimensions;
	private List<String> Tags;
	
	
	public TestData(String platformName, Dimension browserDimensions, List<String> tags)
	{
		this.PlatformName = platformName;
		this.BrowserDimensions = browserDimensions;
		this.Tags = tags;
	}
	
	public Dimension GetBrowserDimensions()
	{
		return BrowserDimensions;
	}
	
	public String GetPlatformName()
	{
		return PlatformName;
	}

	public List<String> GetTags()
	{
		return Tags;
	}
}
