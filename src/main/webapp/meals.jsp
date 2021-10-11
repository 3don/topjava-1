<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 11.10.2021
  Time: 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="mealsList" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
<%@taglib uri="http://example.com/functions" prefix="f" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h4>Add meals</h4>
<table width="700" border="">
    <thead>
        <td width="100">Date</td>
        <td width="200">Description</td>
        <td width="50">Calories</td>
        <td width="100"></td>
        <td width="100"></td>
    </thead>
    <tbody>

    <c:forEach items="${mealsList}" var="meal">
        <c:if test="${meal.excess eq true}"> <tr style="color: red;" > </c:if>
        <c:if test="${meal.excess ne true}"> <tr style="color: green;"> </c:if>
            <td>${f:formatLocalDateTime(meal.dateTime, 'yyyy-dd-MM HH:MM')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>"Update"</td>
            <td>"Delete"</td>
        </tr>
    </c:forEach>


    </tbody>
</table>

</body>
</html>