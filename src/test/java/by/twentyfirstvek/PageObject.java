package by.twentyfirstvek;

import com.codeborne.selenide.Selectors;
import com.github.javafaker.Faker;
import config.CredentialsConfig;
import io.qameta.allure.Step;
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

    @Step("Open 21Vek.by page!")
    public PageObject openPage() {
        open("");
//      open(Configuration.baseUrl);
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

    @Step("Fill right auth data")
    public PageObject fillAuthorizationTrueData() {
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        $("#login-email").setValue(config.login1());
        $("#login-password").setValue(config.password1()).pressEnter();
        return this;
    }

    @Step("Fill faker auth data")
    public PageObject fillAuthorizationFalseData() {
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        $("#login-email").setValue(fakeremail);
        $("#login-password").setValue(fakerpassword).pressEnter();
        return this;
    }

    @Step("Open client's menu")
    public PageObject openClientsMenu() {
//      $(".userToolsText").shouldBe(visible);
        $(".userToolsText").click();
        return this;
    }

    @Step("Check Authorization ")
    public PageObject checkAuthorization(String value) {
        $(".userToolsSubtitle").shouldHave(text(value), Duration.ofSeconds(10));
        return this;
    }

    @Step("Count error message")
    public PageObject countErrorMessageInLoginForm(int value) {
        $$(".input-error-message__message")
                .shouldHave(size(value), Duration.ofSeconds(10));
        return this;
    }

    @Step("Check error messages")
    public PageObject checkErrorMessageText(String value) {
        $$(".input-error-message__message").
                findBy(text(value)).
                shouldBe(visible);
        return this;
    }

    @Step("Correcting  password")
    public PageObject correctingpassword(String value) {
        $("#login-password").click();
        $("#login-password").sendKeys(Keys.CONTROL + "a");
        $("#login-password").sendKeys(Keys.BACK_SPACE);
        $("#login-password").setValue(value).pressEnter();
        return this;
    }

    @Step("Fill auth data")
    public PageObject fillAuthDataWithoutEnter(String email, String password) {
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        $("#login-email").setValue(email);
        $("#login-password").setValue(password);
        $("#login-password").has(value(password));
        return this;
    }

    @Step("Open Auth Form ")
    public PageObject openAuthForm() {
            $(".userToolsText").click();
            $(".userToolsBtn").click();
        return this;
    }

    @Step("Open Auth Form ")
    public PageObject pressEnterInAuthForm() {
                $("#login-password").sendKeys(Keys.ENTER);
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

    @Step("Use Search Function")
    public PageObject searchFunction(String value) {
        $("#catalogSearch").setValue(value).pressEnter();
        $(".content__header.cr-category_header").
                shouldHave(text("Результаты поиска"), Duration.ofSeconds(10));
        return this;
    }

    @Step("Add to card")
    public PageObject addToCard() {
        $$("[data-ga_action=add_to_cart]").first().click();
        $$("[data-ga_action=add_to_cart]").get(1).click();
        $$("[data-ga_action=add_to_cart]").get(2).click();
        return this;
    }

    @Step("Check count card")
    public PageObject checkCard() {
        $(".headerCart").$("[data-testid=header-count]").shouldHave(text("3"));
        return this;
    }

}

