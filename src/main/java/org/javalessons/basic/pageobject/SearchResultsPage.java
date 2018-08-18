package org.javalessons.basic.pageobject;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.javalessons.basic.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SearchResultsPage extends BasePage implements Constants {

    private List<WebElement> resultItemElements;
    private WebElement nextPage;
//    private WebElement priceElement;
//    private WebElement titleElement;
//    private WebElement linkElement;

    private Multimap<String, String> cheapItems;

    WebDriverWait wait = new WebDriverWait(driver, 15);

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void init() {
        this.resultItemElements = this.driver.findElements(By.cssSelector($CSS_RESULT_ITEM_ELEMENTS));
//        this.priceElement = this.driver.findElement(By.cssSelector($CSS_PRICE));
//        this.titleElement = this.driver.findElement(By.cssSelector($CSS_TITLE));
//        this.linkElement = this.driver.findElement(By.tagName($TAG_NAME_LINK));
        this.nextPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id($ID_NEXT_PAGE)));
    }

    public boolean verifyUrlMatchesCurrentPage() {
        return this.driver.getCurrentUrl().contains(SearchResultsPage.URL);
    }

    public SearchResultsPage nextPage() {
        nextPage.click();
        return new SearchResultsPage(this.driver);
    }

    public Multimap<String, String> createContainerOfCheapItems() {
        return this.cheapItems = ArrayListMultimap.create();
    }

    public void collectCheapIteamsBelowGivenAmountEur(int amount) {
        for (WebElement item : resultItemElements) {
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

                if (dPrice <= amount) {
                    cheapItems.put(titleText, priceText);
                    cheapItems.put(titleText, link);
                }

            } catch (NoSuchElementException | ParseException | StaleElementReferenceException ignored) {
                ignored.printStackTrace();
            } finally {
                continue;
            }
        }
    }

    public void printCheapItems(Multimap<String, String> cheapItems) {
        Set<String> keys = cheapItems.keySet();
        for (String key : keys) {
            System.out.println("Title : " + key);
            System.out.println("Price and link : " + cheapItems.get(key));
        }
    }
}
