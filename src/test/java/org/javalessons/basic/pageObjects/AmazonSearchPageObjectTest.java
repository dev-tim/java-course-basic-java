package org.javalessons.basic.pageObjects;

import com.google.common.collect.Multimap;
import org.javalessons.basic.pageobject.MainPage;
import org.javalessons.basic.pageobject.SearchResultsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.javalessons.basic.Constants.PRICE_THRESHOLD;
import static org.javalessons.basic.Constants.SEARCH_TERM;

public class AmazonSearchPageObjectTest {

    private WebDriver driver;

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
    public void testAmazonPageObject() {
        MainPage mainpage = new MainPage(driver);
        mainpage.go2Page();
        mainpage.init();
        SearchResultsPage searchResultsPage = mainpage.performSearchWithEnterHit(SEARCH_TERM);
        Multimap<String, String> cheapItems = searchResultsPage.createContainerOfCheapItems();

        for (int i = 0; i < 5; i++) {
            searchResultsPage.init();
            searchResultsPage.collectCheapIteamsBelowGivenAmountEur(PRICE_THRESHOLD);
            searchResultsPage.nextPage();
        }

//        searchResultsPage.printCheapItems(cheapItems);

        Assert.assertTrue(cheapItems.size() > 0);
    }
}
