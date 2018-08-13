package org.javalessons.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class PopupWindowContentTest {
    public static final String SCHOOL_SAMPLES = "https://school-samples-qa-modal.azurewebsites.net/";
    public static final long WAIT_TIME = 15;
    WebDriver driver;

    @Before
    public void executeBeforeEach() {
        String chromeDriverPath = "C:\\Users\\Nikolay\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
    }

    @After
    public void executeAfterEach() {
        driver.quit();
    }

    @Test
    public void testIfDynamicElementContainsText() {
        driver.get(SCHOOL_SAMPLES);
        driver.findElement(By.id("open-modal-button")).click();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        WebElement myDynamicElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-body")));

        String myDynamicElementText = myDynamicElement.getText();
//        System.out.println(myDynamicElementText);
        assertEquals("Secret content", myDynamicElementText);
    }
}
