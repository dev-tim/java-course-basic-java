package org.javalessons.basic;


import org.javalessons.basic.pageobject.MainPage;
import org.javalessons.basic.pageobject.SearchResultsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class GoogleSearch {

    /**
     * Elements are present on the page
     * Header:
     *  - header exists
     *  - type letter in header should show autosuggestion box
     *  - click on auto suggestion triggers search
     * Catalog:
     *  - returned items page
     *  - should have promo block
     *  - should contain navigation pane
     *  - should not allow to go back on the first page
     *  Catalog filters:
     *  - a
     *  - b
     *  - c
     *
     *  Catalog item
     *    - Item has image, title, price
     *    - click on item, title price should page page
     *
     *
     *
     */


    public static final String HTTPS_WWW_AMAZON_DE = "https://www.amazon.de/";
    public static final String QUERY_AMAZON = "buecher";
    public static final NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
    public static final int PRICE_LIMIT = 10;
    public static final String HTTPS_WWW_GOOGLE_COM = "https://www.google.com/";
    public static final String QUERY_TELRAN = "telran berlin";
    public static final String TEL_RAN_DE = "tel-ran.de";

    WebDriver driver;
    WebElement element;
    WebElement element2;
    private TimeUnit SECONDS;
    WebDriverWait wait;

    @Before
    public void initialize() {
    }

    @Test
    public void testGoogleSearchWorks() {
        initialize();

        driver = new ChromeDriver();
        driver.get(HTTPS_WWW_GOOGLE_COM);

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(QUERY_TELRAN);
        element.submit();

        Assert.assertTrue(driver.getCurrentUrl().startsWith(HTTPS_WWW_GOOGLE_COM + "search?"));

        finalize();

    }

    @Test
    public void testGoogleSearchLinks() {
        initialize();

        driver = new ChromeDriver();
        driver.get(HTTPS_WWW_GOOGLE_COM);

        element = driver.findElement(By.name("q"));
        element.sendKeys(QUERY_TELRAN);
        element.submit();

        List<WebElement> urls = driver.findElements(By.className("iUh30"));

        boolean foundUrl = urls.stream().anyMatch(e -> e.getText().contains(TEL_RAN_DE));
        Assert.assertTrue(foundUrl);

        finalize();
    }

    @Test
    public void testAmazonLinks() throws ParseException {

        initialize();

        driver = new ChromeDriver();
        driver.get(HTTPS_WWW_AMAZON_DE);

        element = driver.findElement(By.id("twotabsearchtextbox"));
        element.sendKeys(QUERY_AMAZON);
        element.submit();


        for (int i = 1; i < 4; i++) {

            driver.findElement(By.id("pagnNextString")).click(); // navigate to page number i

            List<WebElement> booksResultsItems = driver.findElements(By.cssSelector(".s-result-item"));

            List<String> cheapBooksList = findCheapBooks(booksResultsItems);
            printCheapBooksList(cheapBooksList);
            Assert.assertTrue(cheapBooksList.size() > 0);
        }
    }

    private List<String> findCheapBooks(List<WebElement> booksResultItems) throws ParseException {

        List<String> priceNameList = new ArrayList<>();
        for (WebElement resElem : booksResultItems) {
            try {
                WebElement priceElement = resElem.findElement(By.cssSelector(".s-price"));
                WebElement nameElement = resElem.findElement(By.cssSelector(".s-access-title"));
                WebElement linkElement = resElem.findElement(By.tagName("a"));
                String priceText = priceElement.getText();
                String nameText = nameElement.getText();
                String linkText = linkElement.getAttribute("href");


                String priceLowest = priceText.substring(4);
                Number number = format.parse(priceLowest);
                double currentPrice = number.doubleValue();

                if (currentPrice < PRICE_LIMIT) {
                    priceNameList.add(nameText);
                    priceNameList.add(priceText);
                    priceNameList.add(linkText);
                }
            } catch (NoSuchElementException ignored) {
                ignored.printStackTrace();
            } finally {
                continue;
            }
        }


        return priceNameList;
    }

    private void printCheapBooksList(List<String> cheapBooksList) {
        for (String nameElem : cheapBooksList) {
            System.out.println(nameElem);
        }
    }

    @After
    public void finalize() {
        driver.quit();
    }
}







