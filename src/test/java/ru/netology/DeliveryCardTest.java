package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryCardTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    String planningDate = generateDate(3);

    @BeforeEach
    void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    void sendForm() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        //String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на "), Duration.ofSeconds(15));

    }

    @Test
    void sendForm2Data() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

    @Test
    void submiTheFormCityWithDoubleName () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Великий Новгород");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на "), Duration.ofSeconds(15));
    }

    @Test
    void submitFormCityWithDash () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на "), Duration.ofSeconds(15));
    }

    @Test
    void submittingTheFormCityWithLatinLetters () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Kazan");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=city] .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"), Duration.ofSeconds(15));
    }

    @Test
    void submittingFormWithAnEmptyCityField () {
        Configuration.holdBrowserOpen = true;
        //$("[data-test-id=city] input").setValue("");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=city] .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(15));
    }

    @Test
    void submittingFormWithAnEmptyNameField () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        //$("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=name] .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(15));
    }

    @Test
    void submittingFormWithLatinLettersInTheNameField () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Luzhbin Denis");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=name] .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."), Duration.ofSeconds(15));
    }

    @Test
    void submittingFormWithInvalidPhoneNumber () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=phone] .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), Duration.ofSeconds(15));
    }

    @Test
    void submittingFormWithAnEmptyPhoneField () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        //$("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=phone] .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"), Duration.ofSeconds(15));
    }

    @Test
    void submittingFormWithoutConsent () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        //$("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=agreement].input_invalid .checkbox__text")
                .shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
