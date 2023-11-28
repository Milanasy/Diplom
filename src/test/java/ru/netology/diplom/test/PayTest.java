package ru.netology.diplom.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Selenide.open;

import ru.netology.diplom.data.DataGenerator;
import ru.netology.diplom.data.SQLHelper;
import ru.netology.diplom.page.MainPage;
import ru.netology.diplom.page.PayPage;

public class PayTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    public void shouldConfirmCardWithValidData() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getValidCardInfo("approved");

        mainPage.checkPage();
        mainPage.toPayment();
        payPage.setData(cardInfo);
        payPage.verifySuccessMassage();

        var statusPay = SQLHelper.getStatusPay();
        Assertions.assertEquals("APPROVED", statusPay.getStatus());
    }

    @Test
    public void shouldNotConfirmCardWithDeclineCard() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getValidCardInfo("declined");
        mainPage.checkPage();
        mainPage.toPayment();
        payPage.setData(cardInfo);
        payPage.verifyErrorMassage();

        var statusPay = SQLHelper.getStatusPay();
        Assertions.assertEquals("DECLINED", statusPay.getStatus());
    }


    @Test
    public void shouldNotConfirmCardWithInvalidNumberCard() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getInvalidCardInfo();
        mainPage.toPayment();
        payPage.setData(cardInfo);
        payPage.invalidNumberCard();
    }

    @Test
    public void shouldPaymentExpectedDataCard() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getExpectedDataCard();
        mainPage.toPayment();
        payPage.setData(cardInfo);
        payPage.invalidYear();
    }

    @Test
    public void shouldPaymentNonexistentMonth() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getNonexistentMonth();
        mainPage.toPayment();
        payPage.setData(cardInfo);
        payPage.invalidMonth();
    }

    @Test
    public void shouldPaymentWithAnEmptyNameValue() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getInvalidOwner();
        mainPage.toPayment();
        payPage.setData(cardInfo);
        payPage.invalidOwner();
    }


    @Test
    public void shouldPaymentWithInvalidCvv() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getInvalidCvv();
        mainPage.toPayment();
        payPage.setData(cardInfo);
       payPage.invalidCvv();
    }

    @Test
    public void shouldPaymentWithEmptyValues() {
        PayPage payPage = new PayPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getEmptyValues();
        mainPage.toPayment();
        payPage.setData(cardInfo);
        payPage.emptyValues();
}
}