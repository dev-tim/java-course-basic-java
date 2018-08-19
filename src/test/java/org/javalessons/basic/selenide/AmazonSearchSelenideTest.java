package org.javalessons.basic.selenide;

import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.javalessons.basic.Constants.BASE_URL;
import static org.javalessons.basic.Constants.SEARCH_TERM;

public class AmazonSearchSelenideTest {

    @Test
    public void testAmazonWithSelenide() throws InterruptedException {
        MainPage mainPage = open(BASE_URL, MainPage.class);
        SearchResultsPage searchResultsPage = mainPage.search(SEARCH_TERM);
        Multimap<String, String> cheapItems = searchResultsPage.createContainerOfCheapItems();
        Thread.sleep(2000);

        for (int i = 0; i < 5; i++) {
            searchResultsPage.resultItemElements();
            searchResultsPage.collectCheapIteamsBelowThresholdLevel();
            searchResultsPage.nextPage();
        }

//        searchResultsPage.printCheapItems(cheapItems);

        Assert.assertTrue(cheapItems.size() > 0);
    }
}
