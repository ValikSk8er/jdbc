
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<h1>Categories</h1>
<c:forEach var = "c" items="${categories}">
    <h3>Category name: <a href="<c:url value="/servlet/category?c_id=${c.id}"/>"><c:out value="${c.name}"/></a></h3>
</c:forEach>

</body>
</html>