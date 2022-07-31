package by.twentyfirstvek;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class Tests extends TestBase {

    @Test
    @DisplayName("Pozitive authorization function  ")
    void checkAuthFunctionPozitive() {
        generalActions.openPage();
        authForm.fillAuthorizationTrueData();
        generalActions.openClientsMenu();
        authForm.checkAuthorization(config.login21vek());
    }

    @Test
    @DisplayName("Negative authorization function - incorrect login ")
    void checkAuthFunctionNegativeIncorrectLogin() {
        generalActions.openPage();
        authForm.fillAuthorizationFalseData()
                .countErrorMessageInLoginForm(1)
                .checkErrorMessageText("Нет такого аккаунта. Зарегистрироваться?");
    }


    @Test
    @DisplayName("Negative authorization function - without login and password")
    void checkRequiredLoginAndPassword() {
        generalActions.openPage();

        authForm.openAuthForm()
                .pressEnterInAuthForm()
                .countErrorMessageInLoginForm(2)
                .checkErrorMessageText("Электронная почта не указана")
                .checkErrorMessageText("Пароль не указан");
    }

    @Test
    @DisplayName("Correcting password in auth form ")
    void checkCorrectingPasswordInAuthForm() {
        generalActions.openPage();

        authForm.fillAuthDataWithoutEnter(config.login21vek(), fakerpassword)
                .correctingpassword(config.password21vek());
        generalActions.openClientsMenu();
        authForm.checkAuthorization(config.login21vek());
    }

    @Test
    @DisplayName("Check Password Reset function in auth form ")
    void checkPasswordResetfunctionInAuthForm() {
        generalActions.openPage();

        authForm.openAuthForm()
                .clickOnElementInAuthForm("loginForm", "Забыли пароль");
        generalActions.checkWindowWithDataId("modalCloseButton", "Сброс пароля")
        .checkWindowWithDataId("modalCloseButton", "Электронная почта")
        .checkWindowWithDataId("modalCloseButton",
                "Нажимая «Продолжить», вы соглашаетесь с политикой обработки персональных данных")
        .checkWindowWithDataId("modalCloseButton", "Продолжить");
        authForm.setFakerEmailInField("#reset-password-email")
                .checkErrorMessageText("Нет такого аккаунта");
    }

    @Test
    @DisplayName("Check Registration function in auth form ")
    void checkRegistrationFunctionInAuthForm() {
        generalActions.openPage();

        authForm.openAuthForm()
                .clickOnElementInAuthForm("loginForm", "Регистрация");
        generalActions.checkWindowWithDataId("modalCloseButton", "Регистрация")
                .checkWindowWithDataId("modalCloseButton", "Электронная почта")
                .checkWindowWithDataId("modalCloseButton",
                        "Нажимая «Продолжить», вы соглашаетесь с политикой обработки персональных данных")
                .checkWindowWithDataId("modalCloseButton", "Продолжить");
        authForm.setFakerEmailInField("#register-email");
        generalActions.checkWindowWithDataId("modalCloseButton", "Вы зарегистрированы")
                .checkWindowWithDataId("modalCloseButton",
                        "Письмо с паролем отправлено на вашу почту, если письма нет — проверьте папку «Спам».");
    }

    @Test
    @DisplayName("Check Header ")
    void checkHeaderMainPage() {
        generalActions.openPage();
        step("Check header", () -> {
            generalActions.checkHeader("г. Минск")
                    .checkHeader("Оплата частями")
                    .checkHeader("Бонусная программа")
                    .checkHeader("Еще");
        });
    }

    @Test
    @DisplayName("Check Footer ")
    void checkFooterMainPage() {
        generalActions.openPage();
        step("Check Footer", () -> {
            generalActions.checkFooter("Покупателям")
                    .checkFooter("Доставка")
                    .checkFooter("Обработка персональных данных")
                    .checkFooter("Оплата");


        });
    }

    @Test
    @DisplayName("Check first button *Еще* with dropdown ")
    void checkDropdownForButtonInHeaderWithoutAuth() {
        generalActions.openPage();
        step("Open dropdown button *Еще*", () ->
                $$(Selectors.by("type", "button")).findBy(text("Еще")).click());
        step("Open dropdown button *Еще*", () -> {
            mainPage.checkElementsOnSelector("#navMenu", "Оплата")
            .checkElementsOnSelector("#navMenu", "Доставка")
            .checkElementsOnSelector("#navMenu", "Самовывоз");
        });
    }

    @Test
    @DisplayName("Check second button *Еще* with dropdown ")
    void checkDropdownForButtonInHeader() {
        generalActions.openPage();
        step("Open dropdown second button *Еще*", () -> {
            $x("//*[@id=\"header\"]/div/div[1]/div/div/ul[2]/div/div/div/button").click();
        });
        step("Check dropdown button *Еще*", () -> {
            mainPage.checkElementsOnSelector("#dropdownCommunications", "Telegram")
            .checkElementsOnSelector("#dropdownCommunications", "+375 17 302 10 21")
            .checkElementsOnSelector("#dropdownCommunications", "Почта")
            .checkElementsOnSelector("#dropdownCommunications", "Заказать звонок")
            .checkElementsOnSelector("#dropdownCommunications", "Написать нам");
        });
    }

    @Test
    @DisplayName("Check Search Function ")
    void checkSearchFunction() {
        generalActions.openPage();
        mainPage.searchFunction("Детские коляски");
    }

    @Test
    @DisplayName("Add in Card ")
    void checkAddInCard() {
        generalActions.openPage();
        mainPage.searchFunction("Холодильники")
        .addToCard()
        .checkCard("3");
    }
}


