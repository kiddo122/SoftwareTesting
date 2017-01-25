import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;

public class ZoomTests {

    @BeforeClass
    public static void setupChromeDriverLocation(){
    	System.setProperty("webdriver.chrome.driver", "/Users/kaiboma/Documents/CPEN422/group2/group2/selenium-testcase/src/main/resources/chromedriver");
    }
    
    @Test
    public void chromeTestZoomingIn() throws InterruptedException
    {
    	WebDriver chrome = new ChromeDriver();
    	openPage(chrome, "localhost:3000");
        
        zoomIn(chrome);
        
        Thread.sleep(200);
        
    	String currentURL = chrome.getCurrentUrl();
    	assertTrue(currentURL.contains("map=8"));
        getCoverage(chrome);
    	chrome.quit();
    } 
    
    @Test
    public void chromeTestZoomingOut() throws InterruptedException
    {
    	WebDriver chrome = new ChromeDriver();
    	openPage(chrome, "localhost:3000");
        
        zoomOut(chrome);
        
        Thread.sleep(200);
        
    	String currentURL = chrome.getCurrentUrl();
    	assertTrue(currentURL.contains("map=2"));
        getCoverage(chrome);
    	chrome.quit();
    }  
    
    @Test
    public void chromeTestZoomingInMaxed() throws InterruptedException
    {
    	WebDriver chrome = new ChromeDriver();
    	openPage(chrome, "localhost:3000");
        
        zoomInMax(chrome);
        
    	String currentURL = chrome.getCurrentUrl();
    	assertTrue(currentURL.contains("map=19"));
        getCoverage(chrome);
    	chrome.quit();
    }  
    
    @Test
    public void chromeTestZoomingOutMaxed() throws InterruptedException
    {
    	WebDriver chrome = new ChromeDriver();
    	openPage(chrome, "localhost:3000");
        
        zoomOutMax(chrome);
        
    	String currentURL = chrome.getCurrentUrl();
    	assertTrue(currentURL.contains("map=0"));
        getCoverage(chrome);
    	chrome.quit();
    }  
    private void openPage(WebDriver chrome, String url) {
        chrome.get(url);
        return;
    }
    private void zoomOutMax(WebDriver chrome) throws InterruptedException {
    	List<WebElement> zoomOutButtons = chrome.findElements(By.className("zoomout"));
        WebElement zoomOutButton = zoomOutButtons.get(0);
        
        for(int i = 0; i<20; i++) {
        	zoomOutButton.click();
        	Thread.sleep(200);
        }
    }
    private void zoomOut(WebDriver chrome) throws InterruptedException {
        List<WebElement> zoomOutButtons = chrome.findElements(By.className("zoomout"));
        WebElement zoomOutButton = zoomOutButtons.get(0);
        
        for(int i = 0; i<5; i++) {
        	zoomOutButton.click();
        	Thread.sleep(200);
        }
    }
    private void zoomIn(WebDriver chrome) throws InterruptedException {
    	List<WebElement> zoomInButtons = chrome.findElements(By.xpath("//*[@id=\"map\"]/div[2]/div[2]/div[1]/a[1]"));
        WebElement zoomInButton = zoomInButtons.get(0);
        
        for(int i = 0; i<5; i++) {
        	zoomInButton.click();
        	Thread.sleep(200);
        }
    }
    private void zoomInMax(WebDriver chrome) throws InterruptedException {
        List<WebElement> zoomInButtons = chrome.findElements(By.xpath("//*[@id=\"map\"]/div[2]/div[2]/div[1]/a[1]"));
        WebElement zoomInButton = zoomInButtons.get(0);
        
        for(int i = 0; i<30; i++) {
        	zoomInButton.click();
        	Thread.sleep(600);
        }
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