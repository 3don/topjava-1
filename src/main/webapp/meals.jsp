<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 11.10.2021
  Time: 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
<h4><a href="MealController?action=insert">Add meal</a></h4>
<table width="700" border="1">
    <thead>
        <tr>
            <th width="100">Date</th>
            <th width="200">Description</th>
            <th width="50">Calories</th>
            <th width="100"></th>
            <th width="100"></th>
        <tr/>
    </thead>
    <tbody>

    <c:forEach items="${mealsList}" var="meal" varStatus="loop">
        <c:if test="${meal.excess eq true}"> <tr style="color: red;" > </c:if>
        <c:if test="${meal.excess ne true}"> <tr style="color: green;"> </c:if>
            <td>${f:formatLocalDateTime(meal.dateTime, 'yyyy-dd-MM HH:mm')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="MealController?action=edit&mealId=<c:out value="${loop.index}"/>">Update</a></td>
            <td><a href="MealController?action=delete&mealId=<c:out value="${loop.index}"/>">Delete</a></td>
        </tr>
    </c:forEach>


    </tbody>
</table>

</body>
</html>