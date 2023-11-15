package ru.netology.diplom.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;


public class MainPage {

    private final SelenideElement header = $(byText("Путешествие дня"));
    private SelenideElement payButton = $$(".button__text").find(exactText("Купить"));
    private SelenideElement creditButton = $$(".button__text").find(exactText("Купить в кредит"));

    public void checkPage() {
        header.shouldBe(visible);
    }
    public PayPage toPayment() {
        payButton.click();
        return new PayPage();
    }

    public CreditPage toCredit() {
        creditButton.click();
        return new CreditPage();
    }
}

