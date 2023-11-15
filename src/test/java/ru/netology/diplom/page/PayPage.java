package ru.netology.diplom.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.diplom.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
public class PayPage {
    private final SelenideElement header = $(byText("Оплата по карте"));
    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement year = $("[placeholder='22']");
    private final SelenideElement owner = $$(".input__control").get(3);
    private final SelenideElement cvv = $("[placeholder='999']");
    private final SelenideElement continueButton = $(".form-field button");
    private final SelenideElement error = $(withText("Ошибка! Банк отказал в проведении операции."));
    private final SelenideElement success = $(withText("Успешно"));
    private final SelenideElement invalidFormat = $(withText("Неверный формат"));
    private final SelenideElement invalidMonth = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement invalidYear = $(withText("Истёк срок действия карты"));
    private final SelenideElement invalidOwner = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement invalidCvv = $(withText("Неверный формат"));

    public void verifySuccessMassage() {
        success.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyErrorMassage() {
        error.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setData(DataGenerator.CardInfo cardInfo) {
        header.shouldBe(visible);
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getName());
        cvv.setValue(cardInfo.getCvv());
        continueButton.click();
    }

    public void invalidNumberCard() {
        invalidFormat.shouldBe(visible);
    }

    public void invalidMonth() {
        invalidMonth.shouldBe(visible);
    }

    public void invalidYear() {
        invalidYear.shouldBe(visible);
    }
    public void invalidOwner() {
        invalidOwner.shouldBe(visible);
    }

    public void invalidCvv() {
        invalidCvv.shouldBe(visible);
    }
    public void emptyValues() {
        invalidFormat.shouldBe(visible);
        invalidFormat.shouldBe(visible);
        invalidFormat.shouldBe(visible);
        invalidOwner.shouldBe(visible);
        invalidFormat.shouldBe(visible);
    }
}
