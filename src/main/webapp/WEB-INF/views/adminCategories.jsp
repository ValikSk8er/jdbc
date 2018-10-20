<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>


<div class="col-md-4">
    <table class="table table-striped table-sm">
        <thead align="center">
        <tr>
            <th>Category</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "c" items="${categories}">
            <tr>
                <td><c:out value="${c.name}"/></td>
                <td align="center">
                    <div class="btn-group">
                        <a type="button" class="btn btn-sm btn-outline-secondary" href="/servlet/admin/edit-category?c_id=${c.id}">Edit</a>
                        <a type="button" class="btn btn-sm btn-outline-secondary" href="/servlet/admin/delete-category?c_id=${c.id}">Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form class="form-addCategory" action="<c:url value="/servlet/addCategory"/>" method="post">
        <h5 class="mb-3 font-weight-normal">Add category</h5>
        <label for="inputCategory" class="sr-only">Category name:</label>
        <input name="categoryName" type="text" id="inputCategory" class="form-control" placeholder="Category name" required autofocus>
        <button class="btn btn-primary btn-block" type="submit">Add category</button>
    </form>
</div>
<div class="col-md-4">
    <c:if test="${msg_del}">
        <h5 style="color:red">The category was deleted</h5>
    </c:if>
    <c:if test="${msg_add}">
        <h5 style="color:red">The category already exist</h5>
    </c:if>
    <c:if test="${msg_add_success}">
        <h5 style="color:green">The category was added</h5>
    </c:if>
</div>
</body>
</html>