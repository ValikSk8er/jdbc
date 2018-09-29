
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Categories</title>
</head>
<body>
<h1>Categories</h1>
<c:forEach var = "c" items="${categories}">
    <h3>Category name: <c:out value="${c.name}"/></h3>
</c:forEach>

</body>
</html>