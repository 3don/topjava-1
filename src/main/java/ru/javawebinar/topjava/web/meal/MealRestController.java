package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController extends AbstractMealController {

    public List<MealTo> getAll() {
        return super.getAll();
    }

    public List<MealTo> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return super.getAll(startDate, endDate, startTime, endTime);
    }

    public Meal create(Meal meal) {
        return super.create(meal);
    }

    public void update(Meal meal, int id) {
        super.update(meal, id);
    }

    public Meal getById(int id) {
        return super.getById(id);
    }

    public void delete(int id) {
        super.delete(id);
    }


}