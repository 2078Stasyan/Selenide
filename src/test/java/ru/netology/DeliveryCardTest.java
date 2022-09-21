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

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryCardTest {

    @BeforeEach
    void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    void sendForm() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]").shouldHave(Condition.text("Успешно! Встреча успешно забронирована на "), Duration.ofSeconds(15));

    }

    @Test
    void submiTheFormCityWithDoubleName () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Великий Новгород");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]").shouldHave(Condition.text("Успешно! Встреча успешно забронирована на "), Duration.ofSeconds(15));
    }

    @Test
    void submitFormCityWithDash () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        $("[data-test-id=notification]").shouldHave(Condition.text("Успешно! Встреча успешно забронирована на "), Duration.ofSeconds(15));
    }

    @Test
    void submittingTheFormCityWithLatinLetters () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Kazan");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        String expectedText = "Доставка в выбранный город недоступна";
        String actualText = $("[data-test-id=city] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    void submittingFormWithAnEmptyCityField () {
        Configuration.holdBrowserOpen = true;
        //$("[data-test-id=city] input").setValue("");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        String expectedText = "Поле обязательно для заполнения";
        String actualText = $("[data-test-id=city] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    void submittingFormWithAnEmptyNameField () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        //$("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        String expectedText = "Поле обязательно для заполнения";
        String actualText = $("[data-test-id=name] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    void submittingFormWithLatinLettersInTheNameField () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Luzhbin Denis");
        $("[data-test-id=phone] input").setValue("+79085685525");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        String expectedText = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actualText = $("[data-test-id=name] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    void submittingFormWithInvalidPhoneNumber () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        String expectedText = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actualText = $("[data-test-id=phone] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    void submittingFormWithAnEmptyPhoneField () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        //$("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        String expectedText = "Поле обязательно для заполнения";
        String actualText = $("[data-test-id=phone] .input__sub").getText().trim();
        assertEquals(expectedText, actualText);
    }

    @Test
    void submittingFormWithoutConsent () {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String formattedDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);
        $("[data-test-id=name] input").setValue("Лужбин Денис");
        $("[data-test-id=phone] input").setValue("+79085685525");
        //$("[data-test-id=agreement] span").click();
        $(By.className("button__text")).click();
        String checkboxInvalid = $("[data-test-id=agreement].checkbox").getAttribute("className");
        assertTrue(checkboxInvalid.contains("input_invalid"));
    }
}
