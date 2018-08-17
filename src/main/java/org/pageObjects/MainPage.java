package org.pageObjects;

import org.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{
    public static final String $ID_SEARCH_INPUT="lst-ib";

    public static final String $NAME_SEARCH_INPUT="btnK";

    public static final String $NAME_SEARCH_LUCKY_INPUT="btnI";

    public static final String URL = "/";

    private WebElement searchInput;

    private WebElement searchButton;

    private WebElement luckyButton;


    public MainPage(WebDriver driver){
        super(driver);
    }

    public MainPage go2page(){
        this.driver.get(Constants.BASE_URL +URL);
        return this;
    }

    public void init(){
        this.searchInput = this.driver.findElement(By.id($ID_SEARCH_INPUT));
        this.searchButton = this.driver.findElement(By.name($NAME_SEARCH_INPUT));
        this.luckyButton = this.driver.findElement(By.name($NAME_SEARCH_LUCKY_INPUT));
    }

    public SearchResultsPage performSearchWithEnterHit(String text){
    searchInput.sendKeys(text);
    searchInput.submit();
    return new SearchResultsPage(this.driver);
   }

    public SearchResultsPage clickSearchButton(){
    searchButton.click();
    return new SearchResultsPage(driver);
    }

    public boolean verifyElementsDisplayed(){
        return this.searchInput.isDisplayed()
                && this.searchButton.isDisplayed()
                && this.luckyButton.isDisplayed();
    }
    public void inputSearchTerm (String text){
    searchInput.sendKeys(text);
    }

    public void clickLuckyButton(){

    }

}
