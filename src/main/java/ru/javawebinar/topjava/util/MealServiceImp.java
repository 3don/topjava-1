package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealsLocalRepository;

import java.time.LocalTime;
import java.util.List;

public class MealServiceImp implements MealService {
    private final MealsLocalRepository repository;

    public MealServiceImp() {
        this.repository = MealsLocalRepository.getRepository();
    }

    @Override
    public Meal getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void add(Meal meal) {
        repository.add(meal);
    }

    @Override
    public void delete(int id) {
        repository.delete(getById(id));
    }

    @Override
    public void update(int id, Meal meal) {
        delete(id);
        add(meal);
    }

    @Override
    public List<MealTo> getAll() {
        return MealsUtil.filteredByStreams(repository.getList(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
    }
}
