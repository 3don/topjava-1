package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final Meal meal1_4 = new Meal(100002, LocalDateTime.of(2021, 10, 28, 21, 30, 0), "ужин первого", 500);
    public static final Meal meal1_6 = new Meal(100003, LocalDateTime.of(2021, 10, 28, 7, 30, 0), "завтрак первого", 800);
    public static final Meal meal1_2 = new Meal(100004, LocalDateTime.of(2021, 10, 29, 14, 30, 0), "обед первого", 400);
    public static final Meal meal1_1 = new Meal(100005, LocalDateTime.of(2021, 10, 29, 21, 30, 0), "ужин первого", 500);
    public static final Meal meal1_3 = new Meal(100006, LocalDateTime.of(2021, 10, 29, 7, 30, 0), "завтрак первого", 800);
    public static final Meal meal1_5 = new Meal(100007, LocalDateTime.of(2021, 10, 28, 14, 30, 0), "обед первого", 800);

    public static final Meal meal2_1 = new Meal(100008, LocalDateTime.of(2021, 10, 28, 21, 30, 0), "ужин второго", 500);
    public static final Meal meal2_3 = new Meal(100009, LocalDateTime.of(2021, 10, 28, 7, 30, 0), "завтрак второго", 400);
    public static final Meal meal2_2 = new Meal(100010, LocalDateTime.of(2021, 10, 28, 14, 30, 0), "обед второго", 600);

    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, 10, 30, 7, 25, 00), "завтрак первого", 550);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1_1);
        updated.setDateTime(LocalDateTime.of(2021, 10, 29, 21, 25, 00));
        updated.setDescription("Ужин первого обновленный");
        updated.setCalories(549);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
