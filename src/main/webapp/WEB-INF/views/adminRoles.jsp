<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<form class="form-addRole" action="<c:url value="/servlet/addRole"/>" method="post">
    <h4 class="h3 mb-3 font-weight-normal">Add role</h4>


    <c:if test="${msg}">
        <p style="color:red">The roel already exist</p>
    </c:if>

    <label for="inputRole" class="sr-only">Role name:</label>

    <input name="categoryName" type="text" id="inputRole" class="form-control" placeholder="Role name" required autofocus>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Add role</button>
</form>


</body>
</html>
