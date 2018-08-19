package org.javalessons.basic.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.javalessons.basic.Constants.$ID_SEARCH_INPUT;

public class MainPage {
    public SearchResultsPage search(String query) {
        $(By.id($ID_SEARCH_INPUT)).setValue(query).pressEnter();
        return page(SearchResultsPage.class);
    }

}
