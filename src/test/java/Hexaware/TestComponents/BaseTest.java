package Hexaware.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Hexaware.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public static LoginPage loginPage;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Hexaware\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			
			if(browserName.contains("headless"))
				opt.addArguments("headless");
			
			WebDriverManager.chromedriver().setup();			
			driver = new ChromeDriver(opt);
		}else if(browserName.equals("edge")) {
			EdgeOptions opt = new EdgeOptions();
			
			if(browserName.contains("headless"))
				opt.addArguments("headless");
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(opt);
		}else if(browserName.equals("firefox")) {
			FirefoxOptions opt = new FirefoxOptions();
			
			if(browserName.contains("headless"))
				opt.addArguments("headless");
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(opt);
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String filePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File source = ts.getScreenshotAs(OutputType.FILE);
		File fs = new File(filePath);
		FileUtils.copyFile(source, fs);
		return filePath;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchingApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToLoginPage();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
