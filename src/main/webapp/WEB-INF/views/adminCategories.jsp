<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<div>
<form class="form-addCategory" action="<c:url value="/servlet/addCategory"/>" method="post">
    <h4 class="h3 mb-3 font-weight-normal">Add category</h4>

    <c:if test="${msg_add}">
        <p style="color:red">The category already exist</p>
    </c:if>

    <label for="inputCategory" class="sr-only">Category name:</label>

    <input name="categoryName" type="text" id="inputCategory" class="form-control" placeholder="Category name" required autofocus>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Add category</button>
</form>
<form class="form-deleteCategory" action="<c:url value="/servlet/deleteCategory"/>" method="post">
    <h4 class="h3 mb-3 font-weight-normal">Delete category</h4>

    <c:if test="${msg_delete}">
        <p style="color:red">The category not exist</p>
    </c:if>

    <label for="inputCategory" class="sr-only">Category name:</label>

    <input name="categoryName" type="text" id="inputCategory" class="form-control" placeholder="Category name" required autofocus>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Delete category</button>
</form>
</div>

</body>
</html>
