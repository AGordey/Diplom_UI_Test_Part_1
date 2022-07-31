package by.twentyfirstvek;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ObjectForMainPage {


    @Step("Use Search Function")
    public ObjectForMainPage searchFunction(String value) {
        $("#catalogSearch").setValue(value).pressEnter();
        $(".content__header.cr-category_header").
                shouldHave(text("Результаты поиска"), Duration.ofSeconds(10));
        return this;
    }

    @Step("Add to card")
    public ObjectForMainPage addToCard() {
        $$("[data-ga_action=add_to_cart]").first().click();
        $$("[data-ga_action=add_to_cart]").get(1).click();
        $$("[data-ga_action=add_to_cart]").get(2).click();
        return this;
    }
    public ObjectForMainPage checkElementsOnSelector(String selector, String value) {
        $(selector).shouldHave(text(value));
        return this;
    }


    @Step("Check count card")
    public ObjectForMainPage checkCardAmount(String value) {
        $(".headerCart").$("[data-testid=header-count]").shouldHave(text(value));
        return this;
    }
}
