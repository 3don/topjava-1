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

    public List<MealTo> getAll(String startDateString, String endDateString, String startTimeString, String endTimeString) {
        LocalDate startDate = LocalDate.MIN;
        LocalDate endDate = LocalDate.MAX;
        LocalTime startTime = LocalTime.MIN;
        LocalTime endTime = LocalTime.MAX;

        if (!startDateString.equals("")) startDate = LocalDate.parse(startDateString);
        if (!endDateString.equals("")) endDate = LocalDate.parse(endDateString);
        if(!startTimeString.equals("")) startTime = LocalTime.parse(startTimeString);
        if (!endTimeString.equals("")) endTime = LocalTime.parse(endTimeString);
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