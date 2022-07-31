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
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class ObjectForAuthForm {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    Faker faker = new Faker();
    String fakerpassword = faker.numerify("####################"),
            fakeremail = faker.internet().emailAddress();

    @Step("Fill right auth data")
    public ObjectForAuthForm fillAuthorizationTrueData() {
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        $("#login-email").setValue(config.login21vek());
        $("#login-password").setValue(config.password21vek()).pressEnter();
        return this;
    }

    @Step("Fill faker auth data")
    public ObjectForAuthForm fillAuthorizationFalseData() {
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        $("#login-email").setValue(fakeremail);
        $("#login-password").setValue(fakerpassword).pressEnter();
        return this;
    }

    @Step("Check Authorization ")
    public ObjectForAuthForm checkAuthorization(String value) {
        $(".userToolsSubtitle").shouldHave(text(value), Duration.ofSeconds(10));
        return this;
    }

    @Step("Count error message")
    public ObjectForAuthForm countErrorMessageInLoginForm(int value) {
        $$(".input-error-message__message")
                .shouldHave(size(value), Duration.ofSeconds(10));
        return this;
    }

    @Step("Check error messages")
    public ObjectForAuthForm checkErrorMessageText(String value) {
        $$(".input-error-message__message").
                findBy(text(value)).
                shouldBe(visible);
        return this;
    }

    @Step("Open Auth Form ")
    public ObjectForAuthForm openAuthForm() {
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        return this;
    }


    @Step("Send Auth Form ")
    public ObjectForAuthForm pressEnterInAuthForm() {
        $("#login-password").sendKeys(Keys.ENTER);
        return this;
    }


    @Step("Correcting  password")
    public ObjectForAuthForm correctingpassword(String value) {
        $("#login-password").click();
        $("#login-password").sendKeys(Keys.CONTROL + "a");
        $("#login-password").sendKeys(Keys.BACK_SPACE);
        $("#login-password").setValue(value).pressEnter();
        return this;
    }

    @Step("Fill auth data")
    public ObjectForAuthForm fillAuthDataWithoutEnter(String email, String password) {
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        $("#login-email").setValue(email);
        $("#login-password").setValue(password);
        $("#login-password").has(value(password));
        return this;
    }

    public ObjectForAuthForm clickOnElementInAuthForm(String atributValueForDataTestID, String nameElement) {
        $(Selectors.by("data-testid", atributValueForDataTestID)).
                $(byText(nameElement)).click();
        return this;
    }


    public ObjectForAuthForm setFakerEmailInField(String nameFieldCSSSelector) {
        step("Set faker email in field with selector " + nameFieldCSSSelector, () ->
                $(nameFieldCSSSelector).setValue(fakeremail).pressEnter()
        );
        return this;
    }


}
