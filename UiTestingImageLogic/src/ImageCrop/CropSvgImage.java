package ImageCrop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CropSvgImage {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new FirefoxDriver();

		driver.get("http://tutorials.jenkov.com/svg/simple-svg-example.html");
		driver.manage().window().maximize();
		try {
			Object element = ((JavascriptExecutor) driver)
					.executeScript("return document.getElementsByClassName('#mainBody > svg')[0]");
			System.out.println("Element is "
					+ ((element == null) ? "Is null" : ((WebElement) element)
							.getAttribute("outerHTML")));

			Collection<WebElement> svgElements = driver.findElements(By
					.tagName("svg"));

			WebElement targetSvg = null;
			System.out.println("Total SVG elements " + svgElements.size());
			for (WebElement svg : svgElements) {
				System.out.println("Outer HTML of SVG"
						+ svg.getAttribute("outerHTML"));
				targetSvg = svg;
			}

			// Svg Dimensions
			Point svgLocation = targetSvg.getLocation();
			Dimension svgDimesion = targetSvg.getSize();
			System.out.println("Svg location " + svgLocation
					+ " Svg Dimensions " + svgDimesion);
			Object floatX = ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].getBoundingClientRect();", targetSvg);
			System.out.println("Value type " + floatX.toString());
			// Scroll the element into view
			((JavascriptExecutor) driver).executeScript("window.scroll("
					+ svgLocation.x + "," + svgLocation.y + ");");

			svgElements = driver.findElements(By.tagName("svg"));
			for (WebElement svg : svgElements) {
				System.out.println("Outer HTML of SVG"
						+ svg.getAttribute("outerHTML"));
				targetSvg = svg;
			}

			Thread.sleep(4000);
			// new Svg Dimensions
			svgLocation = targetSvg.getLocation();
			svgDimesion = targetSvg.getSize();
			System.out.println("Svg location " + svgLocation
					+ " Svg Dimensions " + svgDimesion);
			// lets also get the read position after scroll
			//
			// HashMap<String, Double> map = (HashMap<String,
			// Double>)((JavascriptExecutor)
			// driver).executeScript("return arguments[0].getBoundingClientRect();",
			// targetSvg);
			// System.out.println("Value type " + floatX.toString());
			Double elementTop = Double
					.parseDouble(((JavascriptExecutor) driver).executeScript(
							"return arguments[0].getBoundingClientRect().top;",
							targetSvg).toString());
			Double elementLeft = Double
					.parseDouble(((JavascriptExecutor) driver)
							.executeScript(
									"return arguments[0].getBoundingClientRect().left;",
									targetSvg).toString());
			System.out.println("Element top " + elementTop);
			System.out.println("Element Left " + elementLeft);

			Thread.sleep(4000);
			// Get screenshot
			File screenShot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);

			File savedFile = new File(
					"c:\\users\\abc\\desktop\\server\\screenShot.jpg");
			FileUtils.copyFile(screenShot, savedFile);

			BufferedImage modImage = ImageIO.read(screenShot);
			BufferedImage svgScreenShot = modImage.getSubimage(svgLocation.x,
					svgLocation.y, svgDimesion.width, svgDimesion.height);
			File svgCroppedFile = new File(
					"c:\\users\\abc\\desktop\\server\\svgCroppedImage.jpg");
			ImageIO.write(svgScreenShot, "png", svgCroppedFile);
			List<String> command = new ArrayList<String>();
			command.add("C:\\Program Files\\ImageMagick-6.9.3-Q16\\compare.exe");
			command.add("-metric AE ");
			command.add(svgCroppedFile.getPath());
			command.add("svgCroppedImageBaseLine.png");
			command.add("c:\\users\\abc\\desktop\\difference.png");
			String[] a = (String[]) command.toArray(new String[1]);
			StringBuffer commandArgs = new StringBuffer();
			commandArgs
					.append("C:\\Program Files\\ImageMagick-6.9.3-Q16\\compare.exe ");
			commandArgs.append("-metric PSNR ");
			commandArgs.append("c:\\users\\abc\\desktop\\server\\svgCroppedImage.jpg ");
			commandArgs.append("c:\\users\\abc\\desktop\\server\\svgCroppedImageBaseLine.jpg ");
			commandArgs.append("c:\\users\\abc\\desktop\\server\\difference.png");

			Process execProcess = Runtime.getRuntime().exec(commandArgs.toString());
			System.out.println(commandArgs.toString());
			int errorCode = execProcess.waitFor();
			InputStream message = ((errorCode == 0) ? execProcess.getInputStream() : execProcess.getErrorStream());
			StringWriter writer = new StringWriter();
			IOUtils.copy(message, writer,Charset.defaultCharset());
			String theString = writer.toString();
			System.out.println(theString);
			
			if(theString.contains("1.#INF"))
			{
				System.out.println("Test Passes");
			}
			
		} catch (Exception exp) {
			exp.printStackTrace();
		}

		// Thread.sleep(4000);
		// WebElement svgElement =
		// driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/svg"));
		// System.out.println("SVG element " +
		// svgElement.getAttribute("outerHTML"));
		// //
		// System.out.println("The element is " +
		// PieChart.getAttribute("outerHTML"));
		// Dimension svgDimensions = PieChart.getSize();
		// Point startPoint = PieChart.getLocation();

		// System.out.println("Dimensions " + svgDimensions);
		// System.out.println("Location " + startPoint);
		// driver.close();
		// driver.quit();
	}
}
