package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealsListHardCoreRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealController extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static final long serialVersionUID = 1L;
    private static String EDIT = "/editMeal.jsp";
    private static String ADD = "/addMeal.jsp";
    private static String LIST_MEALS = "/meals.jsp";
    private MealsListHardCoreRepository repository;
    private List<MealTo> mealsList;

    public MealController() {
        super();
        repository = MealsListHardCoreRepository.getRepository();
        mealsList = MealsUtil.withoutFiltering(repository.getMealsList(), MealsUtil.CALORIES_PER_DAY);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("get Method MealController");
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            log.debug("get Method MealController delete");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            repository.deleteMeal(mealsList.get(mealId).getMeal());
            forward = LIST_MEALS;
            mealsList = MealsUtil.withoutFiltering(repository.getMealsList(), MealsUtil.CALORIES_PER_DAY);
            request.setAttribute("mealsList", mealsList);

            String path = request.getContextPath() + "/meals";
            response.sendRedirect(path);
        } else if (action.equalsIgnoreCase("edit")){
            log.debug("get Method MealController edit");
            forward = EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealsList.get(mealId).getMeal();
            request.setAttribute("meal", meal);
            request.getRequestDispatcher(forward).forward(request, response);
        } else if (action.equalsIgnoreCase("listUser")){
            log.debug("get Method MealController listUser");
            forward = LIST_MEALS;
            mealsList = MealsUtil.withoutFiltering(repository.getMealsList(), MealsUtil.CALORIES_PER_DAY);
            request.setAttribute("mealsList", mealsList);
            request.getRequestDispatcher(forward).forward(request, response);
        } else {
            log.debug("get Method MealController addMeal");
            forward = ADD;
            request.getRequestDispatcher(forward).forward(request, response);
        }
        //RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/meals");
        //requestDispatcher.forward(request, response);
        //response.sendRedirect("meals.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("post Method MealController edit");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
        String description = request.getParameter("description");
       // Map map = request.getParameterMap();
       //  String str = request.getParameter("calories");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);

        String mealId = request.getParameter("mealId");
        if(mealId == null || mealId.isEmpty())
        {
            repository.addMeal(meal);
        }
        else
        {
            int Id = Integer.parseInt(mealId);
            repository.updateMeal(mealsList.get(Id).getMeal(), meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        mealsList = MealsUtil.withoutFiltering(repository.getMealsList(), MealsUtil.CALORIES_PER_DAY);
        request.setAttribute("mealsList", mealsList);
        view.forward(request, response);
    }
}