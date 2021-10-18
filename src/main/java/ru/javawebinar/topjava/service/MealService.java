package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public void delete(int userId, int id) {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    public Meal getByID(int userId, int id) {
        return checkNotFoundWithId(repository.get(userId, id),id);
    }

    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getAll(userId, startDate, endDate);
    }

    public Meal create(int authUserId, Meal meal) {
        return repository.save(authUserId, meal);
    }

    public void update(int userId, Meal meal) {
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }
}
