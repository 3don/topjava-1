<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Edit Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Add meal</h2>
<form method="POST" action="meals">
    <label>ID:
        <input type="number" readonly="readonly" name="mealId"
               value=<%= request.getParameter("mealId") %>><br/>
    </label>

    <label>DateTime:
        <input
                type="datetime-local" name="dateTime"
                value="<value="${meal.dateTime}"/>"><br/>
    </label>
    <label>Description:
        <input
                type="text" name="description"
                value="<value="${meal.description}" />"><br/>
    </label>
    <label>Calories:
        <input
                type="number" name="calories"
                value="<c:out value="${meal.calories}" />"><br/>
    </label>
    <button type="submit">Save</button>
    <button type="reset" onclick="location.href='/topjava/meals'">Cancel</button>
</form>

</body>
</html>
