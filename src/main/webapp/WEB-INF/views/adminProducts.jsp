<%@include file="header.jsp" %>
<%@include file="adminLeftMenu.jsp" %>

<div class="col-md-4">
    <form class="form-addProduct" action="<c:url value="/servlet/addProduct"/>" method="post">

        <h4 class="mb-3">Add product</h4>

        <div class="mb-3">
            <label for="email">Name</label>
            <input name="name" type="text" class="form-control" id="email" placeholder="Product name" required>
            <div class="invalid-feedback">
                Valid name is required.
            </div>
        </div>
        <div class="mb-3">
            <label for="inputDescription">Description</label>
            <input name="description" type="text" id="inputDescription" class="form-control" placeholder="Description" required>
            <div class="invalid-feedback">
                Valid description is required.
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="inputPrice">Price</label>
                <input name="price" type="number" class="form-control" id="inputPrice" placeholder="Price" value="" required>
                <div class="invalid-feedback">
                    Valid price is required.
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label>Category</label>
                <select name="categoryId" class="custom-select d-block w-100" id="state" required=>
                    <option value="">Choose...</option>
                        <c:forEach var = "c" items="${categories}">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Category is required.
                </div>
            </div>
        </div>
        <button class="btn btn-primary btn-block" type="submit">Add new product</button>
    </form>
</div>

<div class="col-md-6">
    <table class="table table-striped table-sm">
        <thead align="center">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "p" items="${products}">
            <tr>
                <td><c:out value="${p.name}"/></td>
                <td><c:out value="${p.description}"/></td>
                <td><c:out value="${p.price}"/></td>
                <td><c:out value="${p.category.name}"/></td>
                <td align="center">
                    <div class="btn-group">
                        <a type="button" class="btn btn-sm btn-outline-secondary" href="/servlet/admin/edit-product?p_id=${p.id}">Edit</a>
                        <a type="button" class="btn btn-sm btn-outline-secondary" href="/servlet/admin/delete-product?p_id=${p.id}">Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="col-md-4">
        <c:if test="${msg_del}">
            <h5 style="color:red">The product was deleted</h5>
        </c:if>
        <c:if test="${msg_add_error}">
            <h5 style="color:red">The product with this name exist</h5>
        </c:if>
        <c:if test="${msg_add_success}">
            <h5 style="color:green">The product was added</h5>
        </c:if>
    </div>
</div>

</body>
</html>