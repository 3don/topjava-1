package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//Синглтон для списка Meals
public final class MealsListHardCoreRepository {
    private static MealsListHardCoreRepository repository;
    private CopyOnWriteArrayList<Meal> mealsList;

    public MealsListHardCoreRepository() {
        initiate();
    }

    public static MealsListHardCoreRepository getRepository() {
        if (repository == null) {
            repository = new MealsListHardCoreRepository();
        }
        return repository;
    }

    public CopyOnWriteArrayList<Meal> getMealsList() {
        return mealsList;
    }

    public void addMeal(Meal meal){
        mealsList.add(meal);
    }

    public void deleteMeal(Meal meal){
        mealsList.remove(meal);
    }

    public void updateMeal(Meal toDelete, Meal toAdd){
        deleteMeal(toDelete);
        addMeal(toAdd);
    }

    public Meal getMeal(int index){
        return mealsList.get(index);
    }

    //Инициируем начальные значения списка
    private void initiate(){
        mealsList = new CopyOnWriteArrayList<>( new Meal[]{
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)}
        );
    }
}
