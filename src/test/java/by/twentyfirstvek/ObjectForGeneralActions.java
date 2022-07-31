package by.twentyfirstvek;

import com.codeborne.selenide.Selectors;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ObjectForGeneralActions {


    @Step("Open 21Vek.by page")
    public ObjectForGeneralActions openPage() {
        open("");
//      open(Configuration.baseUrl);
        return this;
    }


    public ObjectForGeneralActions checkHeader(String value) {
        $("#header").shouldHave(text(value));
        return this;
    }

    public ObjectForGeneralActions checkFooter(String value) {
        $("#footer-inner").shouldHave(text(value));
        return this;
    }


    @Step("Open client's menu")
    public ObjectForGeneralActions openClientsMenu() {
//      $(".userToolsText").shouldBe(visible);
        $(".userToolsText").click();
        return this;
    }


    public ObjectForGeneralActions checkWindowWithDataId(String atributValueForDataTestID, String value) {
        step("Check form ", () ->
                $(Selectors.by("data-testid", atributValueForDataTestID)).
                        shouldHave(text(value)));
        return this;
    }

}
