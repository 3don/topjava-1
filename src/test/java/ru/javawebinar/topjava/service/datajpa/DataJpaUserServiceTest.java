package ru.javawebinar.topjava.service.datajpa;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Autowired
    UserService service;

    @Test
    public void getUserWithMeals() {
        User user1 = service.getUserWithMeals(USER_ID);
        USER_MATCHER.assertMatch(user1, user);
        MEAL_MATCHER.assertMatch(user1.getMeals(), meals);
    }

    @Test
    public void getUserWithMealsNotFound() {
        assertThrows(NotFoundException.class, () -> service.getUserWithMeals(NOT_FOUND));
    }
}