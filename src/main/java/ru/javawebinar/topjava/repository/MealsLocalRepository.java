package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealsLocalRepository {
    private static MealsLocalRepository repository;
    private final List<Meal> mealsList;

    public MealsLocalRepository() {
        mealsList = new ArrayList<>();
        initiate();
    }

    public static MealsLocalRepository getRepository() {
        if (repository == null) {
            repository = new MealsLocalRepository();
        }
        return repository;
    }

    public List<Meal> getList() {
        return mealsList;
    }

    public void add(Meal meal) {
        mealsList.add(meal);
    }

    public void delete(Meal meal) {
        mealsList.remove(meal);
    }

    public Meal getById(int id) {
        return mealsList.stream().filter((meal) -> meal.getId() == id).findFirst().get();
    }

    private void initiate() {
        mealsList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        mealsList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        mealsList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        mealsList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        mealsList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        mealsList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        mealsList.add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }
}
