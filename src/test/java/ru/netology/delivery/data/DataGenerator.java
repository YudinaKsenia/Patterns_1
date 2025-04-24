package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate (int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = new String[]{
                "Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик","Петропавловск-Камчатский",
                "Краснодар", "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск",
                "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары","Пенза", "Псков",
                "Барнаул", "Чита",  "Красноярск", "Пермь",  "Екатеринбург","Нарьян-Мар", "Тула","Биробиджан","Курск",
                "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск",  "Астрахань","Рязань","Калуга","Южно-Сахалинск",
                "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Иваново", "Орёл","Анадырь",
                "Иркутск", "Калининград", "Кемерово", "Киров", "Кострома", "Курган", "Челябинск","Салехард","Тамбов",
                 "Магадан", "Москва", "Мурманск", "Нижний Новгород","Тюмень", "Ульяновск","Ханты-Мансийск","Саратов",
                "Великий Новгород", "Новосибирск", "Омск", "Оренбург","Санкт-Петербург", "Липецк","Ярославль", "Севастополь",
                "Ростов-на-Дону",  "Самара",  "Архангельск","Смоленск",  "Тверь", "Томск"
        };
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}