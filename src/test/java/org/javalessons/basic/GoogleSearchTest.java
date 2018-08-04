package org.javalessons.basic;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleSearchTest {

    @Test
    public void testGoogleSearchWorks (){
        String chromeDriverPath = "C:\\Users\\Gainulina\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("telran berlin");

        element.submit();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.google.com/search?"));
    }
}
