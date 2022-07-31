package by.twentyfirstvek;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TestBase {

    ObjectForGeneralActions generalActions = new ObjectForGeneralActions();
    ObjectForAuthForm authForm = new ObjectForAuthForm();

    ObjectForMainPage mainPage = new ObjectForMainPage();

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    Faker faker = new Faker();
    String fakerpassword = faker.numerify("####################"),
            fakeremail = faker.internet().emailAddress();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = System.getProperty("base_url", "https://www.21vek.by");
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");
        Configuration.remote = "https://" + config.loginForSelenoid() + ":" + config.passwordForSelenoid() + "@" + System.getProperty("server_selenoid", "selenoid.autotests.cloud/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

}
