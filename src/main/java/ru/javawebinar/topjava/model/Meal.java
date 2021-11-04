package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id =:user_id"),
        @NamedQuery(name = Meal.BY_ID, query = "SELECT m FROM Meal m WHERE m.user.id =:user_id AND m.id=:id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id =:user_id ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.FILTRERED_SORTED, query = "SELECT m FROM Meal m " +
                "WHERE m.user.id =:user_id AND m.dateTime >=: start_date_time AND m.dateTime <: end_date_time ORDER BY m.dateTime DESC"),
})

@Entity
@Table(name = "meals",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "uk_user_datetime")})
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String BY_ID = "Meal.getByID";
    public static final String ALL_SORTED = "Meal.getAllSorted";
    public static final String FILTRERED_SORTED = "Meal.getFilteredSorted";

    @Column(name = "date_time", nullable = false, unique = true)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "calories", nullable = false)
    @Range(min = 10, max = 10000)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}


