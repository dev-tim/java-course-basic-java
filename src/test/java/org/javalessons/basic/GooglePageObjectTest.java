package org.javalessons.basic;

import org.javalessons.basic.pageobject.MainPage;
import org.javalessons.basic.pageobject.SearchResultsPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GooglePageObjectTest {

    @Test
    public void testGoogleSearchWithEnterWorks(){
        WebDriver driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.go2Page();
        mainPage.init();
        SearchResultsPage searchResultsPage = mainPage
                .performSearchWithEnterHit("java");
        searchResultsPage.init();
        Assert.assertTrue(searchResultsPage.verifyUrlMatchesCurrentPage());
    }

    @Test
    public void testSearchResultsPageHasTenResults(){
        WebDriver driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.go2Page();
        mainPage.init();
        SearchResultsPage searchResultsPage = mainPage
                .performSearchWithEnterHit("java");
        searchResultsPage.init();
        Assert.assertTrue(searchResultsPage.verifyElementsVisible());
        Assert.assertEquals(searchResultsPage.countSearchResults(), 10);
    }

    @Test
    public void testSearchResultPageWhenClickingOnLogoShouldReturnBackToMainPage(){
        WebDriver driver = new ChromeDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.go2Page();
        mainPage.init();

        mainPage.inputSearchTerm("java");
        SearchResultsPage searchResultsPage = mainPage.clickSearchButton();
        searchResultsPage.init();

        MainPage returnedHomePage = searchResultsPage.clickHomeButton();
        Assert.assertTrue(returnedHomePage.verifyElementDisplayed());
    }
}
