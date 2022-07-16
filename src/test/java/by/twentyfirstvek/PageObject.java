package by.twentyfirstvek;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
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


}


 /*
    public PageObject checkHeader () {

        return this;
    }
}

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }
    */