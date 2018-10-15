<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<div>
    <form class="form-addUser" action="<c:url value="/servlet/adminAddUser"/>" method="post">

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
            <label for="firstName">First name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name" value="" required>
            <div class="invalid-feedback">
                Valid first name is required.
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="lastName">Last name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name" value="" required>
            <div class="invalid-feedback">
                Valid last name is required.
            </div>
        </div>
    </div>

    <h4 class="mb-3">Role</h4>
    <div class="d-block my-3">
        <c:forEach var = "r" items="${roles}">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" name="<c:out value="${r}"/>" id="role">
                <label class="custom-control-label" for="role"><c:out value="${r}"/></label>
            </div>
        </c:forEach>
    </div>


        <c:if test="${msg_error}">
            <p style="color:red">The user with current email exist</p>
        </c:if>
        <c:if test="${msg_success}">
            <p style="color:green">The user was added</p>
        </c:if>
    <button class="btn btn-primary btn-lg btn-block" type="submit">Add new user</button>
</form>
</div>
<div>
    <h3>Users</h3>
    <ul>
        <c:forEach var = "u" items="${users}">
            <li> <c:out value="ID: ${u.id} - Email: ${u.email}"/></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>