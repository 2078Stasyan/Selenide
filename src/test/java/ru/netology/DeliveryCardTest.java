package ru.netology;

import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @BeforeEach
    void openPage() {
        open("http://localhost:9999/");
    }
}
