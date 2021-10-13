<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Add meal</h2>
<form method="POST">
    <label>ID:
        <input hidden="hidden" type="number" readonly="readonly" name="mealId"
        ><br/>
    </label>

    <label>DateTime:
        <input
                type="datetime-local" name="dateTime"
        ><br/>
    </label>
    <label>Description:
        <input type="text" name="description"><br/>
    </label>
    <label>Calories:
        <input
                type="number" name="calories"
        ><br/>
    </label>
    <button type="submit">Save</button>
    <button type="reset" onclick="location.href='/topjava/meals'">Cancel</button>
</form>

</body>
</html>
