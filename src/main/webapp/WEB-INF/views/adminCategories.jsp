<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<div>
<form class="form-addCategory" action="<c:url value="/servlet/addCategory"/>" method="post">
    <h4 class="h3 mb-3 font-weight-normal">Add category</h4>

    <c:if test="${msg_add}">
        <p style="color:red">The category already exist</p>
    </c:if>
    <c:if test="${msg_add_success}">
        <p style="color:green">The category was added</p>
    </c:if>

    <label for="inputCategory" class="sr-only">Category name:</label>

    <input name="categoryName" type="text" id="inputCategory" class="form-control" placeholder="Category name" required autofocus>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Add category</button>
</form>
<form class="form-deleteCategoryByName" action="<c:url value="/servlet/deleteCategory"/>" method="post">
    <h4 class="h3 mb-3 font-weight-normal">Delete category by name</h4>

    <c:if test="${msg_delete_name}">
        <p style="color:red">The category not exist</p>
    </c:if>
    <c:if test="${msg_delete_name_success}">
        <p style="color:green">The category was deleted</p>
    </c:if>
    <label for="inputCategory" class="sr-only">Category name:</label>

    <input name="categoryName" type="text" id="inputCategory" class="form-control" placeholder="Category name" required autofocus>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Delete category</button>
</form>
    <form class="form-deleteCategoryById" action="<c:url value="/servlet/deleteCategory"/>" method="post">
        <h4 class="h3 mb-3 font-weight-normal">Delete category by id</h4>

        <c:if test="${msg_delete_id}">
            <p style="color:red">The category id not exist</p>
        </c:if>
        <c:if test="${msg_delete_id_success}">
            <p style="color:green">The category was deleted</p>
        </c:if>
        <label for="inputCategoryId" class="sr-only">Category id:</label>

        <input name="categoryId" type="text" id="inputCategoryId" class="form-control" placeholder="Category id" required autofocus>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Delete category</button>
    </form>
</div>
<div>
    <h3>Categories</h3>
    <ul>
        <c:forEach var = "c" items="${categories}">
            <li> <c:out value="ID: ${c.id} - Name: ${c.name}"/></li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
