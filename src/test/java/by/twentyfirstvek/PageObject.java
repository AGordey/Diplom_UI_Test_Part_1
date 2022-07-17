package by.twentyfirstvek;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.github.javafaker.Faker;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PageObject {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    Faker faker = new Faker();
    String fakerpassword = faker.numerify("####################"),
            fakeremail = faker.internet().emailAddress();


    public PageObject openPage() {
        step("Open 21Vek.by page", () ->
                open(Configuration.baseUrl));
        return this;
    }

    public PageObject checkHeader(String value) {
        $("#header").shouldHave(text(value));
        return this;
    }

    public PageObject checkFooter(String value) {
        $("#footer-inner").shouldHave(text(value));
        return this;
    }

    public PageObject fillAuthorizationTrueData() {
        step("Fill right auth data ", () -> {
            $(".userToolsText").click();
            $(".userToolsBtn").click();
            $("#login-email").setValue(config.login1());
            $("#login-password").setValue(config.password1()).pressEnter();
        });
        return this;
    }

    public PageObject fillAuthorizationFalseData() {
        step("Fill faker auth data", () -> {
            $(".userToolsText").click();
            $(".userToolsBtn").click();
            $("#login-email").setValue(fakeremail);
            $("#login-password").setValue(fakerpassword).pressEnter();
        });
        return this;
    }

    public PageObject openClientsMenu() {
        step("Open client's menu ", () -> {
            $(".userToolsText").shouldBe(visible);
            $(".userToolsText").click();
        });
        return this;
    }

    public PageObject checkAuthorization(String value) {
        step("Check Authorization ", () -> {
            $(".userToolsSubtitle").shouldHave(text(value));
        });
        return this;
    }

    public PageObject countErrorMessageInLoginForm(int value) {
        step("Count error message", () ->
                $$(".input-error-message__message").shouldHave(size(value)));
        return this;
    }

    public PageObject checkErrorMessageText(String value) {
        step("Check error messages ", () ->
                $$(".input-error-message__message").
                        findBy(text(value)).
                        shouldBe(visible));
        return this;
    }

    public PageObject correctingpassword(String value) {
        step("Correcting  password ", () -> {
            $("#login-password").click();
            $("#login-password").sendKeys(Keys.CONTROL + "a");
            $("#login-password").sendKeys(Keys.BACK_SPACE);
            $("#login-password").setValue(value).pressEnter();
        });
        return this;
    }

    public PageObject fillAuthDataWithoutEnter(String email, String password) {
        step("Fill auth data ", () -> {
            $(".userToolsText").click();
            $(".userToolsBtn").click();
            $("#login-email").setValue(email);
            $("#login-password").setValue(password);
            $("#login-password").has(value(password));
        });
        return this;
    }

    public PageObject openAuthForm() {
        step("Open Auth Form ", () -> {
            $(".userToolsText").click();
            $(".userToolsBtn").click();
        });
        return this;
    }

    public PageObject pressEnterInAuthForm() {
        step("Open Auth Form ", () ->
                $("#login-password").sendKeys(Keys.ENTER));
        return this;
    }

    public PageObject checkWindowWithDataId(String atributValueForDataTestID, String value) {
        step("Check form ", () ->
                $(Selectors.by("data-testid", atributValueForDataTestID)).
                        shouldHave(text(value)));
        return this;
    }

    public PageObject clickOnElementInAuthForm(String atributValueForDataTestID, String NameElement) {
        $(Selectors.by("data-testid", atributValueForDataTestID)).
                $(byText(NameElement)).click();
        return this;
    }

    public PageObject setFakerEmailInField(String NameFieldCSSSelector) {
        step("Set faker email in field with selector " + NameFieldCSSSelector, () ->
                $(NameFieldCSSSelector).setValue(fakeremail).pressEnter()
        );
        return this;
    }


    public PageObject checkElementsOnSelector(String Selector, String value) {
        $(Selector).shouldHave(text(value));
        return this;
    }

    public PageObject searchFunction(String value) {
        step("Use Search Function", () -> {
            $("#catalogSearch").setValue(value).pressEnter();
            $(".content__header.cr-category_header").
                    shouldHave(text("Результаты поиска"), Duration.ofSeconds(10));
        });
        return this;
    }

    public PageObject addToCard() {
        step("Add to card", () -> {
            $$("[data-ga_action=add_to_cart]").first().click();
            $$("[data-ga_action=add_to_cart]").get(1).click();
            $$("[data-ga_action=add_to_cart]").get(2).click();
        });
        return this;
    }

    public PageObject checkCard() {
        step("Check count card", () -> {
            $(".headerCart").$("[data-testid=header-count]").shouldHave(text("3"));
        });
        return this;
    }


}

