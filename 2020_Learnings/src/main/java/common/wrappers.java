package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class wrappers {
	public Logger logger = null;	
	RemoteWebDriver driver;


	public void logs() {

		logger = Logger.getLogger(common.wrappers.class);
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "logs" + File.separator +"log4j.properties";		
		PropertyConfigurator.configure(log4jConfigFile);

	}
	
	public RemoteWebDriver getDriver() {
		return driver;
	}
	public void invokeApplication(String browser) {

		logs();
		if(browser.equalsIgnoreCase("chrome")) {

			try {
				WebDriverManager.chromedriver().setup();	
				ChromeOptions options = new ChromeOptions();
				driver = new ChromeDriver();
				logger.info("Chrome Browser is invoked successfully...");
			} catch (Exception e) {
				logger.error("Error in initiating Chrome Broswer");
			}
		}

		else if(browser.equalsIgnoreCase("firefox")) {
			try {
				WebDriverManager.firefoxdriver().setup();	
				driver = new FirefoxDriver();
				logger.info("Firefox Browser is invoked successfully...");
			} catch (Exception e) {
				logger.error("Error in initiating Firefox Broswer");
			}
		}

		else if(browser.equalsIgnoreCase("edge")) {
			try {
				WebDriverManager.edgedriver().setup();	
				driver = new EdgeDriver();
				logger.info("Firefox Browser is invoked successfully...");
			} catch (Exception e) {
				logger.error("Error in initiating Firefox Broswer");
			}
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void getUrl(String url) {
		try {
			driver.get(url);
			logger.info(url +" URL is loaded successfully");
		} catch (Exception e) {
			logger.error("Error in passing URL");
			e.printStackTrace();
		}
	}

	public WebElement findElement(By by) {
		WebElement ele = null;
		try {
			ele = driver.findElement(by);
		} catch (Exception e) {
		}
		return ele;
	}

	public List<WebElement> findElements(By by) {
		List<WebElement> ele = null;
		try {
			ele = driver.findElements(by);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ele;

	}


	public WebElement clickElement(By by) {
		WebElement ele = null;

		try {
			ele = driver.findElement(by);
			ele.click();
			logger.info("Element clicked successfully .. ");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ele;

	}
	
public String  getdata(int rowNum , int col) throws IOException{
	
	//FileInputStream inputFile = new FileInputStream(new File("2020_Learnings/data/Book1.xlsx"));
	XSSFWorkbook book = new XSSFWorkbook("./data/excel.xlsx");
	XSSFSheet sheet = book.getSheet("login");
	String cellData;
	XSSFRow row = sheet.getRow(rowNum);
	XSSFCell Cell = row.getCell(col);
	DataFormatter data = new DataFormatter();
	cellData = data.formatCellValue(Cell);
	book.close();
	return cellData;
	}

public WebElement entertext(By by ,String text) {
	WebElement ele = null;
	ele = driver.findElement(by);
	ele.sendKeys(text);
	
	return ele;
}

public WebElement getText(By by) {
	WebElement ele = driver.findElement(by);
	ele.getText();
	logger.info("Get text value is " +ele);
	return ele;
	
}

	public void closeBrowser() {
		driver.close();
	}

	public void quitBroswer() {
		driver.quit();
	}

}
