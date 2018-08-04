package org.javalessons.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GoogleSearchTest {
    WebDriver driver;

    @Before
    public void executeBeforeEach(){
        String chromeDriverPath = "C:\\Users\\Nikolay\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
    }

    @After
    public void executeAfterEach(){
        driver.quit();
    }

    @Test
    public void testGoogleSearchWorks(){
        driver.get("https://google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("telran berlin");

        element.submit();

        String url = "https://www.google.com/search";
        Assert.assertTrue(driver.getCurrentUrl().startsWith(url));
    }

    @Test
    public void testAmazonLampsPricesBelowTenEuro() throws ParseException {
        driver.get("https://www.amazon.de/");

        WebElement element = driver.findElement(By.name("field-keywords"));
        element.sendKeys("tischlampe");

        element.submit();

        List<WebElement> lamps = driver.findElements(By.xpath("//span[@class='a-size-base a-color-price s-price a-text-bold']"));
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        for (WebElement l: lamps){
            String price = l.getText().substring(4);
            Number number = format.parse(price);
            double d = number.doubleValue();
            if(d<10){
                return;
            }
        }
        Assert.fail();
    }
}
