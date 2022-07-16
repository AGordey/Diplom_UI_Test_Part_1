package by.twentyfirstvek;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Visible;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
import static io.qameta.allure.Allure.step;


public class Tests extends TestBase {

    @Test
    @DisplayName("Check Header ")
    void checkHeaderMainPage() {
        pageObject.openPage();
        step("Check header", () -> {
            pageObject.checkHeader("г. Минск");
            pageObject.checkHeader("Оплата частями");
            pageObject.checkHeader("Бонусная программа");
            pageObject.checkHeader("Еще");
        });
    }

    @Test
    @DisplayName("Pozitive authorization function  ")
    void checkAuthFunctionPozitive() {
        pageObject.openPage();
        pageObject.fillAuthorizationTrueData();
        pageObject.openClientsMenu();
        pageObject.checkAuthorization(config.login1());
    }

    @Test
    @DisplayName("Negative authorization function - incorrect login ")
    void checkAuthFunctionNegativeIncorrectLogin() {
        pageObject.openPage();
        pageObject.fillAuthorizationFalseData();
        pageObject.countErrorMessageInLoginForm(1);
        pageObject.checkErrorMessageText("Нет такого аккаунта. Зарегистрироваться?");
    }


    @Test
    @DisplayName("Negative authorization function - without login and password")
    void checkRequiredLoginAndPassword() {
        pageObject.openPage();
        $(".userToolsText").click();
        $(".userToolsBtn").click();
        $("#login-password").sendKeys(Keys.ENTER);
//        $$(By."type"="submit")).ByText("Войти")).click();
////        $(byText("Войти")).click();
        pageObject.countErrorMessageInLoginForm(2);
        pageObject.checkErrorMessageText("Электронная почта не указана");
        pageObject.checkErrorMessageText("Пароль не указан");
    }

    @Test
    @DisplayName("Correcting password in auth form ")
    void checkCorrectingPasswordInAuthForm() {
        pageObject.openPage();
        pageObject.fillAuthDataWithoutEnter(config.login1(), pageObject.fakerpassword);
        pageObject.correctingpassword(config.password1());
        pageObject.openClientsMenu();
        pageObject.checkAuthorization(config.login1());
    }

    @Test
    @DisplayName("Check Footer 21Vek.by")
    void checkFooterMainPage() {
        step("Open 21Vek.by page", () ->
                open(Configuration.baseUrl));
        step("Check Footer", () -> {
            $("#footer-inner").shouldHave(
                    text("Покупателям"),
                    text("Доставка"),
                    text("Обработка персональных данных"),
                    text("Оплата"));
        });
    }

    @Test
    @DisplayName("Check Search Function 21Vek.by")
    void checkSearchFunction() {
        step("Open 21Vek.by page", () ->
                open(Configuration.baseUrl));
        step("Check Search Function", () -> {
            $("#catalogSearch").setValue("Детские коляски").pressEnter();
            //sleep(7000);
            $(".content__header.cr-category_header").shouldHave(text("Результаты поиска"), Duration.ofSeconds(10));
        });
    }
}



