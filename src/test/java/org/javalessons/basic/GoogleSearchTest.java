package org.javalessons.basic;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleSearchTest {

    @Test
    public void testGoogleSearch(){
        open("https://www.google.com/");
        SelenideElement q = $(By.name("q"));
        q.setValue("foxes");
        q.pressEnter();
        $$("#ires.g").shouldBe(CollectionCondition.sizeGreaterThan(6));
    }
}
