package org.javalessons.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pageObjects.MainPage;
import org.pageObjects.SearchResultsPage;

public class GooglePageObjectTest {
    WebDriver driver;
    @Before
    public void initialize() {
        String chromeDriverPath = "C:\\Users\\Gainulina\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

    }
    @Test
    public void testGoogleSearchWithEnterWorks(){
        initialize();

        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.go2page();
        mainPage.init();
        SearchResultsPage searchResultsPage = mainPage
                .performSearchWithEnterHit("java");
        searchResultsPage.init();
        Assert.assertTrue(searchResultsPage.verifyUrlMatchesCurrentPage());

        finalize();
    }

    @Test
    public void testSearchResultsPageHasTenResults(){
        initialize();

        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.go2page();
        mainPage.init();
        SearchResultsPage searchResultsPage = mainPage
                .performSearchWithEnterHit("java");
        searchResultsPage.init();
        Assert.assertTrue(searchResultsPage.verifyElementsVisible());
        Assert.assertEquals(searchResultsPage.countSearchResults(),10);

        finalize();
    }

    @Test
    public void testClickOnLogosReturnToMain(){
        driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.go2page();
        mainPage.init();

        mainPage.inputSearchTerm("java");
        SearchResultsPage searchResultsPage = mainPage.clickSearchButton();
        searchResultsPage.init();

        MainPage returnedHomePage = searchResultsPage.clickHomeButton();
        Assert.assertTrue(returnedHomePage.verifyElementsDisplayed());
    }

    @After
    public void finalize() {
        driver.quit();
    }

}
