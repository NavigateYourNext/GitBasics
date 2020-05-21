package com.git;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GitBasics {

	public static void main(String[] args)throws Exception {
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("window-size=1400,800");
		opt.addArguments("headless");
		WebDriver driver = new ChromeDriver(opt);
		
		
		//WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // maximum time to load page if it loads before that then rest of the time will ignored
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // maximum time to load all elements of a page, if it loads before that then rest of the time will ignored


		driver.manage().window().maximize();

		driver.get("http://www.google.com");

		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Testing");

		List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@class='erkvQe']//li/descendant::div[@class='sbl1']"));

		for(int i=0; i<suggestions.size(); i++)
		{
			if(suggestions.get(i).getText().equalsIgnoreCase("Testing Time"))
			{
				suggestions.get(i).click();
				File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/screenshot/google.png"));
				
				break;
			}
		}
		
		driver.quit();
		
		
		System.out.println("Done");


	}

}
