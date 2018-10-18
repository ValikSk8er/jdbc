<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<div class="col-md-4">
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

        <button class="btn btn-primary btn-block" type="submit">Add category</button>
    </form>
    <form class="form-deleteCategoryById" action="<c:url value="/servlet/deleteCategory"/>" method="post">
        <h5 class="mb-3 font-weight-normal">Delete by id</h5>

        <c:if test="${msg_delete_id}">
            <p style="color:red">The category id not exist</p>
        </c:if>
        <c:if test="${msg_delete_id_success}">
            <p style="color:green">The category was deleted</p>
        </c:if>
        <label for="inputCategoryId" class="sr-only">Category id:</label>

        <input name="categoryId" type="number" id="inputCategoryId" class="form-control" placeholder="Category id" required autofocus>

        <button class="btn btn-secondary btn-block" type="submit">Delete</button>
    </form>
    <form class="form-deleteCategoryByName" action="<c:url value="/servlet/deleteCategory"/>" method="post">
        <h5 class="mb-3 font-weight-normal">Delete by name</h5>

        <c:if test="${msg_delete_name}">
            <p style="color:red">The category name not exist</p>
        </c:if>
        <c:if test="${msg_delete_name_success}">
            <p style="color:green">The category was deleted</p>
        </c:if>
        <label for="inputCategory" class="sr-only">Category name:</label>

        <input name="categoryName" type="text" id="inputCategory" class="form-control" placeholder="Category name" required autofocus>

        <button class="btn btn-secondary btn-block" type="submit">Delete</button>
    </form>
</div>
<div class="">
    <table class="table table-striped table-sm">
        <thead align="center">
        <tr>
            <th>Id</th>
            <th>Category name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "c" items="${categories}">
            <tr>
                <td><c:out value="${c.id}"/></td>
                <td><c:out value="${c.name}"/></td>
                <td><div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Remove</button>
                </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <button class="btn btn-primary btn-block" type="submit">Add</button>
    </div>
</div>

</body>
</html>