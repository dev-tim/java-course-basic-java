package org.javalessons.basic.pageobject;

import org.javalessons.basic.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.javalessons.basic.Constants.$ID_SEARCH_INPUT;

public class MainPage extends BasePage {

    private WebElement searchInput;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage go2Page() {
        this.driver.get(Constants.BASE_URL);
        return this;
    }

    public void init() {
        this.searchInput = this.driver.findElement(By.id($ID_SEARCH_INPUT));
    }

    public SearchResultsPage performSearchWithEnterHit(String text) {
        searchInput.sendKeys(text);
        searchInput.submit();
        return new SearchResultsPage(this.driver);
    }

}
