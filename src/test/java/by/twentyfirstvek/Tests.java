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
        pageObject.openPage()
                .fillAuthorizationTrueData()
                .openClientsMenu()
                .checkAuthorization(config.login1());
    }

    @Test
    @DisplayName("Negative authorization function - incorrect login ")
    void checkAuthFunctionNegativeIncorrectLogin() {
        pageObject.openPage()
                .fillAuthorizationFalseData()
                .countErrorMessageInLoginForm(1)
                .checkErrorMessageText("Нет такого аккаунта. Зарегистрироваться?");
    }


    @Test
    @DisplayName("Negative authorization function - without login and password")
    void checkRequiredLoginAndPassword() {
        pageObject.openPage()
                .openAuthForm()
                .pressEnterInAuthForm()
                .countErrorMessageInLoginForm(2)
                .checkErrorMessageText("Электронная почта не указана")
                .checkErrorMessageText("Пароль не указан");
    }

    @Test
    @DisplayName("Correcting password in auth form ")
    void checkCorrectingPasswordInAuthForm() {
        pageObject.openPage()
                .fillAuthDataWithoutEnter(config.login1(), pageObject.fakerpassword)
                .correctingpassword(config.password1())
                .openClientsMenu()
                .checkAuthorization(config.login1());
    }

    @Test
    @DisplayName("Check Password Reset function in auth form ")
    void checkPasswordResetfunctionInAuthForm() {
        pageObject.openPage()
                .openAuthForm()
                .clickOnElementInAuthForm("loginForm", "Забыли пароль")
                .checkWindowWithDataId("modalCloseButton", "Сброс пароля")
                .checkWindowWithDataId("modalCloseButton", "Электронная почта")
                .checkWindowWithDataId("modalCloseButton",
                        "Нажимая «Продолжить», вы соглашаетесь с политикой обработки персональных данных")
                .checkWindowWithDataId("modalCloseButton", "Продолжить")
                .setFakerEmailInField("#reset-password-email")
                .checkErrorMessageText("Нет такого аккаунта");
    }

    @Test
    @DisplayName("Check Registration function in auth form ")
    void checkRegistrationFunctionInAuthForm() {
        pageObject.openPage()
                .openAuthForm()
                .clickOnElementInAuthForm("loginForm", "Регистрация")
                .checkWindowWithDataId("modalCloseButton", "Регистрация")
                .checkWindowWithDataId("modalCloseButton", "Электронная почта")
                .checkWindowWithDataId("modalCloseButton",
                        "Нажимая «Продолжить», вы соглашаетесь с политикой обработки персональных данных")
                .checkWindowWithDataId("modalCloseButton", "Продолжить")
                .setFakerEmailInField("#register-email")
                .checkWindowWithDataId("modalCloseButton", "Вы зарегистрированы")
                .checkWindowWithDataId("modalCloseButton",
                        "Письмо с паролем отправлено на вашу почту, если письма нет — проверьте папку «Спам».");
    }

    @Test
    @DisplayName("Check Header ")
    void checkHeaderMainPage() {
        pageObject.openPage();
        step("Check header", () -> {
            pageObject.checkHeader("г. Минск")
                    .checkHeader("Оплата частями")
                    .checkHeader("Бонусная программа")
                    .checkHeader("Еще");
        });
    }

    @Test
    @DisplayName("Check Footer ")
    void checkFooterMainPage() {
        pageObject.openPage();
        step("Check Footer", () -> {
            pageObject.checkFooter("Покупателям")
                    .checkFooter("Доставка")
                    .checkFooter("Обработка персональных данных")
                    .checkFooter("Оплата");
        });
    }

    @Test
    @DisplayName("Check first button *Еще* with dropdown ")
    void checkDropdownForButtonInHeaderWithoutAuth() {
        pageObject.openPage();
        step("Open dropdown button *Еще*", () ->
                $$(Selectors.by("type", "button")).findBy(text("Еще")).click());
        step("Open dropdown button *Еще*", () -> {
            pageObject.checkElementsOnSelector("#navMenu", "Оплата")
                    .checkElementsOnSelector("#navMenu", "Доставка")
                    .checkElementsOnSelector("#navMenu", "Самовывоз");
        });
    }

    @Test
    @DisplayName("Check second button *Еще* with dropdown ")
    void checkDropdownForButtonInHeader() {
        pageObject.openPage();
        step("Open dropdown second button *Еще*", () -> {
            $x("//*[@id=\"header\"]/div/div[1]/div/div/ul[2]/div/div/div/button").click();
        });
        step("Check dropdown button *Еще*", () -> {
            pageObject.checkElementsOnSelector("#dropdownCommunications", "Telegram")
                    .checkElementsOnSelector("#dropdownCommunications", "+375 17 302 10 21")
                    .checkElementsOnSelector("#dropdownCommunications", "Почта")
                    .checkElementsOnSelector("#dropdownCommunications", "Заказать звонок")
                    .checkElementsOnSelector("#dropdownCommunications", "Написать нам");
        });
    }

    @Test
    @DisplayName("Check Search Function ")
    void checkSearchFunction() {
        pageObject.openPage()
                .searchFunction("Детские коляски");
    }

    @Test
    @DisplayName("Add in Card ")
    void checkAddInCard() {
        pageObject.openPage().
                searchFunction("Холодильники")
                .addToCard().checkCard("3");
    }
}


