package org.javalessons.basic.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;
import static org.javalessons.basic.Constants.*;

public class SearchResultsPage {

    private Multimap<String, String> cheapItems;
    private WebElement nextPage = $("#" + $ID_NEXT_PAGE);
    private ElementsCollection resultItemElements;

    public ElementsCollection resultItemElements() {
        return this.resultItemElements = $$($CSS_RESULT_ITEM_ELEMENTS);
    }

    public Multimap<String, String> createContainerOfCheapItems() {
        return this.cheapItems = ArrayListMultimap.create();
    }

    public SearchResultsPage nextPage() {
        nextPage.click();
        return page(SearchResultsPage.class);
    }

    public void collectCheapIteamsBelowThresholdLevel() {
        for (SelenideElement item : resultItemElements) {
            try {
                WebElement priceElement = item.$($CSS_PRICE);
                WebElement titleElement = item.$($CSS_TITLE);
                WebElement linkElement = item.$($TAG_NAME_LINK);
                String titleText = titleElement.getText();
                String link = linkElement.getAttribute(LINK);
                String priceText = priceElement.getText();

                String price = priceText.substring(4);
                Number number = NUMBER_FORMAT.parse(price);
                Double dPrice = number.doubleValue();

                if (dPrice <= PRICE_THRESHOLD) {
                    cheapItems.put(titleText, priceText);
                    cheapItems.put(titleText, link);
                }

            } catch (NoSuchElementException | ParseException ignored) {
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
