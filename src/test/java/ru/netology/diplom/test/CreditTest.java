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
import ru.netology.diplom.page.CreditPage;
import ru.netology.diplom.page.MainPage;


public class CreditTest {
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
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getValidCardInfo("approved");

        mainPage.checkPage();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.verifySuccessMassage();

        var statusCredit = SQLHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusCredit.getStatus());
    }

    @Test
    public void shouldNotConfirmCardWithDeclineCard() {
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getValidCardInfo("declined");
        mainPage.checkPage();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.verifyErrorMassage();

        var statusCredit = SQLHelper.getStatusCredit();
        Assertions.assertEquals("DECLINED", statusCredit.getStatus());
    }

    @Test
    public void shouldNotConfirmCardWithInvalidNumberCard() {
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getInvalidCardInfo();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.invalidNumberCard();
    }

    @Test
    public void shouldPaymentExpectedDataCard() {
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getExpectedDataCard();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.invalidYear();
    }

    @Test
    public void shouldPaymentNonexistentMonth() {
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getNonexistentMonth();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.invalidMonth();
    }

    @Test
    public void shouldPaymentWithAnEmptyNameValue() {
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getInvalidOwner();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.invalidOwner();
    }

    @Test
    public void shouldPaymentWithInvalidCvv() {
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getInvalidCvv();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.invalidCvv();
    }

    @Test
    public void shouldPaymentWithEmptyValues() {
        CreditPage creditPage = new CreditPage();
        DataGenerator dataGenerator = new DataGenerator();
        MainPage mainPage = new MainPage();

        DataGenerator.CardInfo cardInfo = dataGenerator.getEmptyValues();
        mainPage.toCredit();
        creditPage.setData(cardInfo);
        creditPage.emptyValues();
    }
}