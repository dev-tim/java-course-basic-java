package org.javalessons.basic;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.javalessons.basic.Constants.BASE_URL;
import static org.javalessons.basic.Constants.PRICE_THRESHOLD;
import static org.javalessons.basic.Constants.SEARCH_TERM;

public class AmazonSearchTest {
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.GERMANY);
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
    public void testAmazonLampsPricesBelowTenEuro() throws ParseException, InterruptedException {
        driver.get(BASE_URL);
        WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
        element.sendKeys(SEARCH_TERM);
        element.submit();

        Multimap<String, String> cheapLamps = findCheapLamps();

        printCheapLamps(cheapLamps);
    }

    private Multimap<String, String> findCheapLamps() {
        Multimap<String, String> cheapLamps = ArrayListMultimap.create();

        for (int i = 0; i < 5; i++) {
            List<WebElement> lampsResultItemElements = driver.findElements(By.cssSelector(".s-result-item"));
            for (WebElement item : lampsResultItemElements) {
                try {
                    WebElement priceElement = item.findElement(By.cssSelector(".s-price"));
                    WebElement titleElement = item.findElement(By.cssSelector(".s-access-title"));
                    WebElement linkElement = item.findElement(By.tagName("a"));
                    String titleText = titleElement.getText();
                    String link = linkElement.getAttribute("href");
                    String priceText = priceElement.getText();

                    String price = priceText.substring(4);
                    Number number = NUMBER_FORMAT.parse(price);
                    Double dPrice = number.doubleValue();

                    if (dPrice <= PRICE_THRESHOLD) {
                        cheapLamps.put(titleText, priceText);
                        cheapLamps.put(titleText, link);
                    }

                } catch (NoSuchElementException | ParseException ignored) {
                    ignored.printStackTrace();
                } finally {
                    continue;
                }
            }
            driver.findElement(By.id("pagnNextString")).click();
        }
        return cheapLamps;
    }

    private void printCheapLamps(Multimap<String, String> cheapLamps) {
        Set<String> keys = cheapLamps.keySet();
        for (String key : keys) {
            System.out.println("Title : " + key);
            System.out.println("Price and link : " + cheapLamps.get(key));
        }

    }

}
