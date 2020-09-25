package testCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import common.wrappers;

public class TestCase extends wrappers{

	
	
	@Test
	public void test1() throws InterruptedException, IOException {
		invokeApplication("chrome");
		getUrl("http://www.leafground.com/");
		//clickElement(By.linkText("Edit"));
		Thread.sleep(1000);
		//entertext(By.id("email"), getdata(3,0));Thread.sleep(1000);
		//entertext(By.xpath("(//input[@type='text'])[2]"), "Well Hello");Thread.sleep(1000);
		
		//getDriver().findElementByXPath(("(//input[@type='text'])[2]")).sendKeys(Keys.TAB);Thread.sleep(1000);
		//getText(By.name("username"));
		
		clickElement(By.linkText("Window"));
		Thread.sleep(2000);
		clickElement(By.id("home"));
		Thread.sleep(2000);
		Set<String> handle = getDriver().getWindowHandles();
		List<String> hand = new ArrayList<String>();
		hand.addAll(handle);
		getDriver().switchTo().window(hand.get(0));
		
		clickElement(By.xpath("//button[text()=\"Open Multiple Windows\"]"));
		Thread.sleep(2000);
		clickElement(By.xpath("//button[text()=\"Do not close me \"]"));
		Thread.sleep(2000);
		String originalHandle = getDriver().getWindowHandle();
		Set<String> win = getDriver().getWindowHandles();
		List<String> wind = new ArrayList<String>();
		wind.addAll(win);
		for (String each : wind) {
			if(!each.equals(originalHandle))
			getDriver().switchTo().window(each).close();
			logger.info("Window" + wind.indexOf(each) + "is closed successfully");
		}
		quitBroswer();
	}
	
}
