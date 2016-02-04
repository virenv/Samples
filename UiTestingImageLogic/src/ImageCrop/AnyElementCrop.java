package ImageCrop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.galenframework.api.Galen;

public class AnyElementCrop {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new FirefoxDriver();

		driver.get("http://toolsqa.com/specflow/feature-file/");
		driver.manage().window().maximize();
		WebElement codeWindow = driver.findElement(By.cssSelector("#crayon-56a4f51a0cf72028882483 > div.crayon-main > table"));
			//*[@id="crayon-56a4f51a0cf72028882483"]/div[4]/table
		
		Dimension sizeOfCodeWindow = codeWindow.getSize();
		Point elementTTopLeft = codeWindow.getLocation();
		
		Double elementTop = Double
				.parseDouble(((JavascriptExecutor) driver).executeScript(
						"return arguments[0].getBoundingClientRect().top;",
						codeWindow).toString());
		Double elementLeft = Double
				.parseDouble(((JavascriptExecutor) driver)
						.executeScript(
								"return arguments[0].getBoundingClientRect().left;",
								codeWindow).toString());
		System.out.println("Element top " + elementTop);
		System.out.println("Element Left " + elementLeft);
		File screenShot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		File savedFile = new File(
				"c:\\users\\abc\\desktop\\server\\codeWindowScreenShot.jpg");
		FileUtils.copyFile(screenShot, savedFile);

		BufferedImage modImage = ImageIO.read(screenShot);
		BufferedImage svgScreenShot = modImage.getSubimage(elementTTopLeft.x,
				elementTTopLeft.y, sizeOfCodeWindow.width, sizeOfCodeWindow.height);
		File svgCroppedFile = new File(
				"c:\\users\\abc\\desktop\\server\\CodeWindowCropped.jpg");
		ImageIO.write(svgScreenShot, "png", svgCroppedFile);
				
	}

}
