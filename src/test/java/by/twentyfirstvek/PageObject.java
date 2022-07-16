package by.twentyfirstvek;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PageObject extends TestBase {


    public PageObject openPage() {
        step("Open 21Vek.by page", () ->
                open(Configuration.baseUrl));
        return this;
    }

    public PageObject checkHeader (String value) {
        $("#header").shouldHave(text(value));
        return this;
    }

    public PageObject authorizationTrue() {
        step("Authorization ", () -> {
            $(".userToolsText").click();
            $(".userToolsBtn").click();
            $("#login-email").setValue(config.login1());
            $("#login-password").setValue(config.password1()).pressEnter();
        });
        return this;
    }

    public PageObject authorizationFalse() {
        step("Authorization ", () -> {
            $(".userToolsText").click();
            $(".userToolsBtn").click();
            $("#login-email").setValue("заменить");
            $("#login-password").setValue(config.password1()).pressEnter();
        });
        return this;
    }

    public PageObject openClientsMenu() {
        step("Open client's menu ", () -> {
            $(".userToolsText").click();
        });
        return this;
    }  

        public PageObject  checkAuthorization () {
        step("Check Authorization ", () -> {
            $(".userToolsSubtitle").shouldHave(text(config.login1()));
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