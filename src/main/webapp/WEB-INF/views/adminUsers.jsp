<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<div class="col-md-4">
    <form class="form-addUser" action="<c:url value="/servlet/add-user"/>" method="post">
        <h4 class="mb-3">Add user</h4>
        <div class="mb-3">
            <label for="email">Email</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="you@example.com" required>
            <div class="invalid-feedback">
                Please enter a valid email address.
            </div>
        </div>
        <div class="mb-3">
            <label for="inputPassword">Password</label>
            <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
            <div class="invalid-feedback">
                Please enter a valid email address.
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="firstName">First name<span class="text-muted"> (Optional)</span></label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name" value="">
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="lastName">Last name<span class="text-muted"> (Optional)</span></label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name" value="">
                <div class="invalid-feedback">
                    Valid last name is required.
                </div>
            </div>
        </div>
        <div class="d-block my-3">
            <h6 class="mb-3">Role</h6>
            <c:forEach var = "r" items="${roles}">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" name="${r}" id="${r}"
                    <c:if test="${r == 'USER'}">
                        checked required disabled
                    </c:if>
                    >
                    <label class="custom-control-label" for="${r}"><c:out value="${r}"/></label>
                </div>
            </c:forEach>
        </div>
        <c:if test="${msg_error}">
            <p style="color:red">The user with current email exist</p>
        </c:if>
        <c:if test="${msg_success}">
            <p style="color:green">The user was added</p>
        </c:if>
        <button class="btn btn-primary btn-block" type="submit">Add new user</button>
    </form>
</div>

<div class="col-md-4">
    <table class="table table-striped table-sm">
        <thead align="center">
        <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Roles</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "u" items="${users}">
            <tr>
                <td><c:out value="${u.email}"/></td>
                <td><c:out value="${u.firstName}"/></td>
                <td><c:out value="${u.lastName}"/></td>
                <td>
                    <c:forEach items="${u.roles}" var = "r">
                        <div class="col-md-4"><c:out value="${r.roleName}"/></div>
                    </c:forEach>
                <td align="center">
                    <div class="btn-group">
                        <a type="button" class="btn btn-sm btn-outline-secondary" href="/servlet/admin/edit-user?u_id=${u.id}">Edit</a>
                        <a type="button" class="btn btn-sm btn-outline-secondary" href="/servlet/admin/delete-user?u_id=${u.id}">Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <c:if test="${msg_delete_success}">
            <h5 style="color:red">The user was deleted</h5>
        </c:if>
    </div>
</div>
</body>
</html>