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


public class LayersTests {

    @BeforeClass
    public static void setupChromeDriverLocation(){
    	System.setProperty("webdriver.chrome.driver", "/Users/kaiboma/Documents/CPEN422/group2/group2/selenium-testcase/src/main/resources/chromedriver");
    }

    @Test
    public void chromeCycleLayerTest() throws InterruptedException
    {
    	WebDriver chrome = new ChromeDriver();
        openPage(chrome, "localhost:3000");

        OpenLayersTab(chrome);

        cycleLayers(chrome, "//*[@id='map-ui']/div[1]/div[2]/ul/li[2]/label");
        Thread.sleep(1000);
        String currentURL = chrome.getCurrentUrl();
        assertTrue(currentURL.contains("layers=H"));

        cycleLayers(chrome, "//*[@id='map-ui']/div[1]/div[2]/ul/li[1]/label");
        Thread.sleep(1000);
        currentURL = chrome.getCurrentUrl();
        assertFalse(currentURL.contains("layers=H"));

        cycleLayers(chrome, "//*[@id='map-ui']/div[1]/div[2]/ul/li[2]/label");
        currentURL = chrome.getCurrentUrl();
    	assertTrue(currentURL.contains("layers=H"));
        getCoverage(chrome);
    	chrome.quit();

        return;
    }
    
    private void OpenLayersTab(WebDriver driver) throws InterruptedException {
        List<WebElement> layerButtons = driver.findElements(By.className("layers"));
        WebElement layerButton = layerButtons.get(0);
        layerButton.click();
    	Thread.sleep(200);
    	
    	return;
    }
    
    private void cycleLayers(WebDriver chrome, String xpath) throws InterruptedException {
    	List<WebElement> mapLayers = chrome.findElements(By.xpath(xpath));
        WebElement cycleLayer = mapLayers.get(0);

    	Thread.sleep(200);

        cycleLayer.click();
    }
    
    @Test
    public void chromeHumanitarianLayerTest() throws InterruptedException
    {
        WebDriver chrome = new ChromeDriver();
        openPage(chrome, "localhost:3000");

        OpenLayersTab(chrome);

        cycleLayers(chrome, "//*[@id='map-ui']/div[1]/div[2]/ul/li[2]/label");

        String currentURL = chrome.getCurrentUrl();
        assertTrue(currentURL.contains("layers=H"));
        getCoverage(chrome);
        chrome.quit();

        return;
    } 
    
    public void openPage(WebDriver chrome, String url) {
    	chrome.get(url);
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