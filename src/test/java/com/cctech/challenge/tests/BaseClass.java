package com.cctech.challenge.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import freemarker.log.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	// public static WebDriver driver;
	// public static WebDriverWait wait;
	public static Actions actions;
	public static JavascriptExecutor js;
	public Properties p;

	private static ThreadLocal<WebDriver> ldriver = new ThreadLocal<>();
	private static ThreadLocal<WebDriverWait> lwait = new ThreadLocal<>();

	// public static WebDriver driver;
	WebDriver driver = null; // Local variable, NOT the global one
	// public WebDriverWait wait;
	WebDriverWait wait = null;
	public Logger logger;

	public void setDriver(WebDriver driver) {
		ldriver.set(driver);
	}

	public WebDriver getDriver() {
		if (ldriver.get() == null) {
			return driver;
		}
		return ldriver.get();
	}

	public void setWait(WebDriverWait wait) {
		lwait.set(wait);
	}

	public WebDriverWait getWait() {
		return lwait.get();
	}

	@Parameters({ "os", "browser" })
	@BeforeClass
	public void driverLaunch(String os, String browser) throws IOException {

		// Load properties file
		FileReader file = new FileReader(
				"C:\\Workspaces\\30-10-2024 On words\\demoqa-automation\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);

		WebDriverManager.chromedriver().setup();

		// String browser = "chrome";
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Brouser is not valid");

		}

		setDriver(driver);

		// Initialize the Actions object
		actions = new Actions(getDriver()); // Initialize action with driver

		// wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		setWait(new WebDriverWait(getDriver(), Duration.ofSeconds(10)));
		js = (JavascriptExecutor) getDriver();
		// logger = LogManager.getLogger(this.getClass()); // log4j2

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
		getDriver().get(p.getProperty("url"));

	}

	public void waitForElementToBeVisible(WebElement webelementvisible) {
		getWait().until(ExpectedConditions.visibilityOf(webelementvisible));
	}

	public void elementToBeClicable(WebElement webelementtobeclicable) {
		getWait().until(ExpectedConditions.elementToBeClickable(webelementtobeclicable));
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
		File SourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String path = "C:\\Workspaces\\30-10-2024 On words\\demoqa-automation\\screenshots\\" + tname + " " + timeStamp
				+ ".png";
		File targetfile = new File(path);

		SourceFile.renameTo(targetfile);
		return path;

	}

	@AfterClass
	void tearDown() {
		getDriver().quit();
		ldriver.remove();
		lwait.remove();
	}

}
