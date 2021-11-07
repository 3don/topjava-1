package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query(name = Meal.DELETE)
        //@Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete(@Param("id") int id, @Param("userId") int userId);

    /*   @Modifying
       @Query(name = Meal.ALL_SORTED)
       List<Meal> getAll(@Param("userId") int user_id);
       */

    List<Meal> getMealsByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> getMealsByUserIdAndDateTimeGreaterThanEqualAndDateTimeBeforeOrderByDateTimeDesc(int userId, LocalDateTime from, LocalDateTime to);

}
