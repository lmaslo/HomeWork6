package ru.mail;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Selenide.$;


public class CheckCreateAccount {

    //locators
    SelenideElement registerTitle = $(".ph-project__register");
    SelenideElement accountInput = $("[data-test-id=\"account__input\"]");
    SelenideElement phoneInput = $("[data-test-id=\"phone-input\"]");
    SelenideElement textErrorForm = $("[data-test-id=\"account-form-field\"]");

    @BeforeEach
    void precondition() {
        Selenide.open("https://mail.ru/");
        Configuration.browserSize = "1920x1080";
        $(registerTitle).click();
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }


    @CsvSource({
            "1234, Введено некорректное имя аккаунта. Имя аккаунта должно быть длиной от 5 до 31 символов.",
            "12345678912345678912345678912345, Введено некорректное имя аккаунта. Имя аккаунта должно быть длиной от 5 до 31 символов.",
            "Елена, В имени аккаунта нельзя использовать кириллицу",
            "name1, Аккаунт с таким именем уже существует"
    })
    @ParameterizedTest(name = "Check text error for login \"{0}\".")
    void checkTextError(String testName, String textError) {
        //Ввести в поле "Имя аккаунта" "{test_name}"
        $(accountInput).setValue(testName);

        //Изменения фокуса на поле телефон, для появления ошибки
        $(phoneInput).click();

        //Проверка "{text_error}"
        $(textErrorForm).shouldBe(Condition.text(textError));
    }
}
