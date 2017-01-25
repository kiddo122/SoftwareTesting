/**
 * Created by kaiboma on 2016-11-09.
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.*;

import static org.junit.Assert.*;


public class LoadTest {

    @BeforeClass
    public static void setupChromeDriverLocation(){
    	System.setProperty("webdriver.chrome.driver", "/Users/kaiboma/Documents/CPEN422/group2/group2/selenium-testcase/src/main/resources/chromedriver");
    }

    @Test
    public void chromeTitleTest()
    {
        WebDriver chrome = new ChromeDriver();
    	openPage(chrome, "http://localhost:3000");
        assertTrue(chrome.getTitle().equals("OpenStreetMap"));
        getCoverage(chrome);
        chrome.quit();
    }
    
    @Test
    public void chromeSearchTest()
    {
    	WebDriver chrome = new ChromeDriver();
    	openPage(chrome, "localhost:3000");
    	String dest = "vancouver";
    	enterDestination(dest, chrome);
        String currentURL = chrome.getCurrentUrl();
        assertTrue(currentURL.contains("query=".concat(dest)));
        getCoverage(chrome);
        chrome.quit();
    }
    
    @Test
    public void chromeDirectionsTest() throws InterruptedException {

    	WebDriver chrome = new ChromeDriver();
    	openPage(chrome, "localhost:3000");
    	enterDirectionsDestinations("ubc", "vancouver", chrome); 
    	Thread.sleep(200);
    	assertTrue(chrome.getCurrentUrl().contains("directions"));
        getCoverage(chrome);
    	chrome.quit();
    }
    
    private void openPage(WebDriver chrome, String url) {
        chrome.get(url);
        return;
    }
    
    private void enterDestination(String dest, WebDriver chrome) {
    	String xpath = "//*[@id=\"query\"]";
    	
        List<WebElement> element = chrome.findElements(By.xpath(xpath));
        if (element.get(0).isDisplayed())
        {

        	element.get(0).sendKeys(dest);
            element.get(0).submit();
        }
        else {

        	element.get(1).sendKeys(dest);
        	element.get(1).submit();
        }
        return;
    }
    
    private void enterDirectionsDestinations(String from, String to, WebDriver chrome) {
    	String xpath1 = "//*[@id=\"sidebar\"]/div[1]/form[1]/a";
    	String xpath2 = "//*[@id=\"route_from\"]";
    	String xpath3 = "//*[@id=\"route_to\"]";
    	String xpath4 = "//*[@id=\"sidebar\"]/div[1]/form[2]/div[4]/input";
    	
    	WebElement element = chrome.findElement(By.xpath(xpath1));
    	element.click();
    	
    	List<WebElement> elements = chrome.findElements(By.xpath(xpath2));
        if (elements.get(0).isDisplayed())
        	elements.get(0).sendKeys(from);
        else 
        	elements.get(1).sendKeys(from);
    	
    	elements = chrome.findElements(By.xpath(xpath3));
        if (elements.get(0).isDisplayed())
        	elements.get(0).sendKeys(to);

        else 
        	elements.get(1).sendKeys(to);
    	element = chrome.findElement(By.xpath(xpath4));
    	element.click();
    	
    	return;
    }

    public void getCoverage(WebDriver chrome) {
        JavascriptExecutor js = (JavascriptExecutor) chrome;
        Object obj = js.executeScript("return window.__coverage__ ;");

        JSONObject coverage = new JSONObject((Map)obj);
        try {
            //Write to coverage.json file
            String date = new Date().toString();
            PrintWriter writer = new PrintWriter("coverage" + date + ".json", "UTF-8");
            writer.print(coverage);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
