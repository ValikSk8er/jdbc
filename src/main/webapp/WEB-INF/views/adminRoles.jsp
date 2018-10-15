<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>
<div class="col-md-4">
<div class="row">

    <div class="col-md-4">
        <form class="form-addRole" action="<c:url value="/servlet/addRole"/>" method="post">
            <div class="mb-3">

            <h4 class="mb-3 font-weight-normal">Add role</h4>

            <c:if test="${msg_add_error}">
                <p style="color:red">The role already exist</p>
            </c:if>
            <c:if test="${msg_add_success}">
                <p style="color:green">The role was added</p>
            </c:if>

            <label for="inputRole" class="sr-only">Role name:</label>

            <input name="categoryName" type="text" id="inputRole" class="form-control" placeholder="Role name" required autofocus>
                <button class="btn btn-primary btn-block" type="submit">Add role</button>
            </div>
        </form>
    </div>
    <div class="col-md-4">
        <form class="form-addRole" action="<c:url value="/servlet/deleteRole"/>" method="post">
            <div class="mb-3">

                <h4 class="mb-3 font-weight-normal">Delete role</h4>


                <c:if test="${msg_delete_error}">
                    <p style="color:red">The role not exist</p>
                </c:if>
                <c:if test="${msg_delete_success}">
                    <p style="color:green">The role was deleted</p>
                </c:if>
                <label for="inputRole" class="sr-only">Role name:</label>

                <input name="categoryName" type="text" id="inputRole" class="form-control" placeholder="Role name" required autofocus>
                <button class="btn btn-secondary btn-block" type="submit">Delete role</button>

            </div>
        </form>
    </div>
</div>
</div>
<div class="col-md-4">
    <div class="col-md-4">
        <h5 class="mb-3">Roles</h5>

        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>Role name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var = "r" items="${roles}">
                <tr>
                    <td><c:out value="${r}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
