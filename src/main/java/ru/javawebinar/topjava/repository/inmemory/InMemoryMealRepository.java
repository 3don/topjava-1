package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(1, meal));
        save(2, new Meal(LocalDateTime.of(2021, 10, 21, 10, 0), "Обед второго пользователя", 1200));
        save(2, new Meal(LocalDateTime.of(2021, 10, 21, 20, 0), "Ужин второго пользователя", 1200));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        log.info("save {}, to user {}", meal, userId);
        repository.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        log.info("delete {} from user {}", id, userId);
        return repository.get(userId) != null && repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        log.info("get {} from user {}", id, userId);
        return repository.get(userId) == null ? null : repository.get(userId).get(id);
    }

    @Override
    public List<Meal> getFilteredList(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("get filtered from user {}", userId);
        return filterByPredicate(userId, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDate(), startDate, endDate));
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("get all from user {}", userId);
        return filterByPredicate(userId, meal -> true);
    }

    private List<Meal> filterByPredicate(int userId, Predicate<Meal> filter) {
        if (repository.get(userId) == null) return new ArrayList<>();
        return repository.get(userId).values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

