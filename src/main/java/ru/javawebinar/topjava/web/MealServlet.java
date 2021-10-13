package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealService;
import ru.javawebinar.topjava.util.MealServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String EDIT = "/editMeal.jsp";
    private static final String ADD = "/addMeal.jsp";
    private static final String LIST_MEALS = "/meals.jsp";
    private final MealService mealService;

    public MealServlet(MealService mealService) {
        this.mealService = mealService;
    }

    public MealServlet() {
        mealService = new MealServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("get method MealServlet");
        String forward;
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "delete":
                log.debug("get method MealServlet delete");
                mealService.delete(Integer.parseInt(request.getParameter("mealId")));
                request.setAttribute("mealsList", mealService.getAll());
                String path = request.getContextPath() + "/meals";
                response.sendRedirect(path);
                break;
            case "edit":
                log.debug("get method MealServlet edit");
                forward = EDIT;
                request.setAttribute("meal",
                        mealService.getById(Integer.parseInt(request.getParameter("mealId"))));
                request.getRequestDispatcher(forward).forward(request, response);
                break;
            case "add":
                log.debug("get Method MealServlet addMeal");
                forward = ADD;
                request.getRequestDispatcher(forward).forward(request, response);
                break;
            default:
                log.debug("get Method MealServlet listUser");
                forward = LIST_MEALS;
                request.setAttribute("mealsList", mealService.getAll());
                request.getRequestDispatcher(forward).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("post Method MealServlet");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), DateTimeFormatter.ISO_DATE_TIME);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String mealId = request.getParameter("mealId");

        if (mealId == null || mealId.isEmpty()) {
            mealService.add(new Meal(dateTime, description, calories));
        } else {
            int id = Integer.parseInt(mealId);
            mealService.update(id, new Meal(id, dateTime, description, calories));
        }
        request.setAttribute("mealsList", mealService.getAll());
        String path = request.getContextPath() + "/meals";
        response.sendRedirect(path);
    }
}