package org.javalessons.basic;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GoogleSearch {

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
        String chromeDriverPath = "C:\\Users\\Gainulina\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

    }

/*

        @Test
        public void testGoogleSearchWorks() {
            initialize();

            driver = new ChromeDriver();
            driver.get(HTTPS_WWW_GOOGLE_COM);

            WebElement element = driver.findElement(By.name("q"));
            element.sendKeys(QUERY_TELRAN);
            element.submit();


            Assert.assertTrue(driver.getCurrentUrl().startsWith(HTTPS_WWW_GOOGLE_COM+"search?"));

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

            boolean foundUrl = urls.stream().anyMatch(e-> e.getText().contains(TEL_RAN_DE));
            Assert.assertTrue(foundUrl);

            finalize();
        }
*/

    @Test
    public void testAmazonLinks() throws ParseException {

        initialize();

        driver = new ChromeDriver();
        driver.get(HTTPS_WWW_AMAZON_DE);



        for(int j = 1 ; j < 4 ; j++) {
            if (j==1){
                element = driver.findElement(By.id("twotabsearchtextbox"));
                element.sendKeys(QUERY_AMAZON);
                element.submit();
            }
            if (j > 1) {// we don't need to navigate to the first page
                driver.findElement(By.id("pagnNextString")).click(); // navigate to page number j
            }

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
                }catch (NoSuchElementException ignored){
                   ignored.printStackTrace();
                }finally {
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







