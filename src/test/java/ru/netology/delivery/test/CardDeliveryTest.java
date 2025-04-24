package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
    }

    @Test

    public void shouldSuccessfullyRescheduleMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        var daysForFirstMeeting = 3;
        var firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);

        var daysForSecondMeeting = 6;
        var secondMeetingDate = DataGenerator.generateDate(daysForSecondMeeting);

        $("[data-test-id=city] input").setValue(validUser.getCity());

        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);

        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());

        $("[data-test-id=agreement]").click();
        $$("button").findBy(text("Запланировать")).click();

        $(Selectors.withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        $("[data-test-id='success-notification'] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);

        $$("button").findBy(text("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible);
        $(byText("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(visible);
    }
}
