package org.pageObjects;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage{

    public static final String URL = "/search";

    public static final String $CLASS_RESULT_ITEM = "rc";

    public static final String $CSS_HOME_BUTTON ="#logocont > a";

    public static final String $ID_NEXT_BUTTON ="pnnext";

    private WebElement homeButton;

    private WebElement nextButton;

    private List<WebElement> searchResults;

    public void init(){
        this.nextButton = this.driver.findElement(By.id($ID_NEXT_BUTTON));
        this.homeButton = this.driver.findElement(By.cssSelector($CSS_HOME_BUTTON));
        this.searchResults = this.driver.findElements(By.className($CLASS_RESULT_ITEM));
    }

    public MainPage clickHomeButton(){
        return new MainPage(this.driver);
    }


    public boolean verifyElementsVisible(){
        return this.nextButton.isDisplayed() && this.homeButton.isDisplayed()
                && this.searchResults.stream().allMatch(WebElement::isDisplayed);
    }

    public int countSearchResults(){
        return this.searchResults.size();
    }

    public SearchResultsPage(WebDriver driver){
        super(driver);
    }

   public boolean verifyUrlMatchesCurrentPage (){
   return this.driver.getCurrentUrl().contains(SearchResultsPage.URL);
   }
}
