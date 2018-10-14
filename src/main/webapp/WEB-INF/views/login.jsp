<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: valik
  Date: 01-Oct-18
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
    <form class="form-signin" action="<c:url value="/servlet/login"/>" method="post">

        <h1 class="h3 mb-3 font-weight-normal">Please Login</h1>

        <c:if test="${msg}">
            <p style="color:red">Invalid email or password</p>
        </c:if>
        <label for="inputEmail" class="sr-only">Email address</label>

        <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>

        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>

        <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
</body>
</html>
