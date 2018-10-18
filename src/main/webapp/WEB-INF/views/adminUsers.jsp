<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<div class="col-md-4">
    <form class="form-addUser" action="<c:url value="/servlet/addUser"/>" method="post">
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
    <form class="form-deleteUser" action="<c:url value="/servlet/deleteUser"/>" method="post">
        <h4 class="mb-3">Delete by email</h4>

        <c:if test="${msg_delete_error}">
            <p style="color:red">Email not exist</p>
        </c:if>
        <c:if test="${msg_delete_success}">
            <p style="color:green">The user was deleted</p>
        </c:if>
        <label for="inputUserEmail" class="sr-only">User email:</label>
        <input name="email" type="email" id="inputUserEmail" class="form-control" placeholder="User email" required autofocus>

        <button class="btn btn-secondary btn-block" type="submit">Delete user</button>
    </form>
</div>

<div class="col-md-4">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>Id</th>
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
                <td><c:out value="${u.id}"/></td>
                <td><c:out value="${u.email}"/></td>
                <td><c:out value="${u.firstName}"/></td>
                <td><c:out value="${u.lastName}"/></td>
                <td>
                    <c:forEach items="${u.roles}" var = "r">
                        <div class="col-md-4"><c:out value="${r.roleName}"/></div>
                    </c:forEach>
                <td>
                    <div class="btn-group">
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