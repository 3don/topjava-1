package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> filteredList = new ArrayList<>();
        List<UserMeal> tempFilteredUserMeal = new ArrayList<>();
        Map<LocalDate, Boolean> dayExcesses = new HashMap<>();
        Map<LocalDate, Integer> daylySumCallories = new HashMap<>();
        for (UserMeal meal: meals) {
            dayExcesses.putIfAbsent(meal.getDateTime().toLocalDate(), false);
            daylySumCallories.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
            if (daylySumCallories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay) {
                dayExcesses.put(meal.getDateTime().toLocalDate(), true);
            }
            if ((meal.getDateTime().toLocalTime().isAfter(startTime)
                    || meal.getDateTime().toLocalTime().equals(startTime))
                    && meal.getDateTime().toLocalTime().isBefore(endTime)) {
                tempFilteredUserMeal.add(meal);
            }
        }
        for (UserMeal meal: tempFilteredUserMeal) {
            filteredList.add(new UserMealWithExcess(meal, dayExcesses.get(meal.getDateTime().toLocalDate())));
        }
        return filteredList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}

