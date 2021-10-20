package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public List<MealTo> getFilteredList(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getAll with filters");
        startDate = startDate == null ? LocalDate.MIN : startDate.plusDays(1);
        endDate = endDate == null ? LocalDate.MAX : endDate.plusDays(1);
        startTime = startTime == null ? LocalTime.MIN : startTime;
        endTime = endTime == null ? LocalTime.MAX : endTime;

        return MealsUtil.getFilteredTos(service.getFilteredList(authUserId(), startDate, endDate),
                authUserCaloriesPerDay(), startTime, endTime);
    }

    public Meal create(Meal meal) {
        log.info("create{}", meal);
        checkNew(meal);
        return service.create(authUserId(), meal);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(authUserId(), meal);
    }

    public Meal getById(int id) {
        log.info("getByID {}", id);
        return service.getById(authUserId(), id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(authUserId(), id);
    }


}