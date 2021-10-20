<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>

    <hr/>
    <h3 class="text-center">Моя еда</h3>
    <form method="get" action="meals">
        <input type="hidden" name="action" value="filter">
        <label for="startDate">От даты (включая)</label>
        <input class="form-control" type="date" name="startDate" id="startDate" autocomplete="off">

        <label for="endDate">До даты (включая)</label>
        <input class="form-control" type="date" name="endDate" id="endDate" autocomplete="off">

        <label for="startTime">От времени (включая)</label>
        <input class="form-control" type="time" name="startTime" id="startTime" autocomplete="off">

        <label for="endTime">До времени (не включая)</label>
        <input class="form-control" type="time" name="endTime" id="endTime" autocomplete="off">

        <button class="btn btn-danger" type="reset" onclick="location.href=''"/>
        Отменить
        </button>
        <button class="btn btn-primary" onclick="location.href=''"/>
        Отфильтровать
        </button>
    </form>

    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>