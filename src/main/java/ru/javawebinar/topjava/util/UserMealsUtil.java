package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


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

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

        System.out.println(filteredByStreamsInOneStream(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExcess> filteredList = new ArrayList<>();
        List<UserMeal> tempFilteredUserMeal = new ArrayList<>();
        Map<LocalDate, Integer> dailySumCalories = new HashMap<>();

        for (UserMeal meal: meals) {
            dailySumCalories.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(),startTime, endTime)) {
                tempFilteredUserMeal.add(meal);
            }
        }
        for (UserMeal meal: tempFilteredUserMeal) {
            filteredList.add(new UserMealWithExcess(meal,
                    dailySumCalories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
        }
        return filteredList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> dailySumCalories =meals.stream()
                .collect(Collectors.groupingBy((p)->p.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(s -> TimeUtil.isBetweenHalfOpen(s.getDateTime().toLocalTime(),startTime, endTime))
                .collect(
                        ArrayList::new,
                        (list, item)-> list.add(new UserMealWithExcess(item,
                                dailySumCalories.get(item.getDateTime().toLocalDate()) > caloriesPerDay)),
                        ArrayList::addAll);
    }

    public static List<UserMealWithExcess> filteredByStreamsInOneStream(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> dailySumCalories =meals.stream()
                .collect(Collectors.groupingBy((p)->p.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(s -> TimeUtil.isBetweenHalfOpen(s.getDateTime().toLocalTime(),startTime, endTime))
                .collect(
                        ArrayList::new,
                        (list, item)-> list.add(new UserMealWithExcess(item,
                                dailySumCalories.get(item.getDateTime().toLocalDate()) > caloriesPerDay)),
                        ArrayList::addAll);
    }


}

