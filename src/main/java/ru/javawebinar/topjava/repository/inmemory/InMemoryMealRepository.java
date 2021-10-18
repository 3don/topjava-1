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
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    {
        MealsUtil.meals.forEach(x -> save(1, x));
        save(2, new Meal(LocalDateTime.now(), "Обед", 1200));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        log.info("save {}, to user {}", meal, userId);
        synchronized (repository) {
            if (repository.get(userId) == null) {
                repository.put(userId, new ConcurrentHashMap<>());
            }
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                repository.get(userId).put(meal.getId(), meal);
                return meal;
            }
            repository.get(userId).computeIfPresent(meal.getId(), (id, oldUser) -> meal);
        }
        return meal;
    }

    @Override
    public boolean delete(int userId, int id) {
        log.info("delete {} from user {}", id, userId);
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        log.info("get {} from user {}", id, userId);
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("get all from user {}", userId);
        return repository.get(userId).values().stream()
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDate).reversed()).collect(Collectors.toList());
    }
}

