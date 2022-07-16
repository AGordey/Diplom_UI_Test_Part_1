package by.twentyfirstvek;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class Tests extends TestBase {

    Faker faker = new Faker();
    String fakerfirstName = faker.name().firstName(),
            fakerlastName = faker.name().lastName(),
            fakeremail = faker.internet().emailAddress();

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
    @DisplayName("Pozitive check authorization function  ")
    void checkAuthorizationFunction() {
        pageObject.openPage();
        pageObject.authorizationTrue();
        pageObject.openClientsMenu();
        pageObject.checkAuthorization();
    }

    @Test
    @DisplayName("Negative check authorization function  ")
    void cction() {
        pageObject.openPage();
        pageObject.authorizationTrue();
    }

    @Test
    @DisplayName("Что делает фэйкер ")
    void checkAuthorizationFunction2() {
        System.out.println("Пароль" + fakerfirstName);
        System.out.println("Имэйл" + fakeremail);
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




    @Test
    @Disabled
    @DisplayName("Check Grass cuts 21Vek.by")
    void checkGrassCuts() {
        step("Open 21Vek.by page", () ->
                open(Configuration.baseUrl));
        step("Check Grass cuts ", () -> {
            $(byText("Газонокосилки")).click();
            $(".content__header.cr-category_header").shouldHave(text("Газонокосилки"));
        });
    }
}

