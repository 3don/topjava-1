package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {

    void add(Meal meal);
    void delete(int id);
    void update(int id, Meal meal);
    Meal getById(int id);
    List<MealTo> getAll();
}
