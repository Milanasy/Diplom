package ru.netology.diplom.data;

import com.github.javafaker.Faker;

import lombok.Value;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    private Faker faker = new Faker();

    private String getCardNumber(String card) {
        if (card.equalsIgnoreCase("approved")) {
            return "4444 4444 4444 4441";
        } else if (card.equalsIgnoreCase("declined")) {
            return "4444 4444 4444 4442";
        } else return card;
    }

    private String generateMonth() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM");
        String date = LocalDate.now().plusMonths(2).format(format);
        return date;
    }

    private String generateYear() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        String date = LocalDate.now().plusYears(2).format(format);
        return date;
    }

    private String generateName() {
        String ownerName = faker.name().lastName().toUpperCase() + " " + faker.name().firstName().toUpperCase();
        return ownerName;
    }

    private String generateCvv() {
        return Integer.toString(faker.number().numberBetween(100, 999));
    }

    @Value
    public static class CardInfo {
        String cardNumber; // 1. cardNumber: "312312" 2. cardNumber: getCard("appr")
        String month;
        String year;
        String name;
        String cvv;
    }

    public CardInfo getValidCardInfo(String card) {
        return new CardInfo(
                getCardNumber(card),
                generateMonth(),
                generateYear(),
                generateName(),
                generateCvv()
        );
    }

    public CardInfo getInvalidCardInfo() {
        return new CardInfo(
                "4444 4444 444",
                generateMonth(),
                generateYear(),
                generateName(),
                generateCvv()
        );
    }

    public CardInfo getExpectedDataCard() {
        return new CardInfo(
                getCardNumber("approved"),
                generateMonth(),
                "22",
                generateName(),
                generateCvv()
        );
    }

    public CardInfo getNonexistentMonth() {
        return new CardInfo(
                getCardNumber("approved"),
                "13",
                generateYear(),
                generateName(),
                generateCvv()
        );
    }

    public CardInfo getInvalidOwner() {
        return new CardInfo(
                getCardNumber("approved"),
                generateMonth(),
                generateYear(),
                " ",
                generateCvv()
        );
    }

        public CardInfo getInvalidCvv() {
            return new CardInfo(
                    getCardNumber("approved"),
                    generateMonth(),
                    generateYear(),
                    generateName(),
                    "2"
            );
        }

        public CardInfo getEmptyValues() {
            return new CardInfo(
                    "",
                    "",
                    "",
                    "",
                    "");
        }

    @Data
    @NoArgsConstructor
    public static class StatusPay {
        private String status;
    }

    @Data
    @NoArgsConstructor
    public static class StatusCredit {
        private String status;
    }
    }
