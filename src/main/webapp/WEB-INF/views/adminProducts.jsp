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
                <input name="price" type="text" class="form-control" id="inputPrice" placeholder="Price" value="" required>
                <div class="invalid-feedback">
                    Valid price is required.
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="inputCategoryId">Category id</label>
                <input type="text" class="form-control" id="inputCategoryId" name="categoryId" placeholder="Category id" value="" required>
                <div class="invalid-feedback">
                    Valid category id is required.
                </div>
            </div>
        </div>

        <c:if test="${msg_success}">
            <p style="color:green">The product was added</p>
        </c:if>
        <button class="btn btn-primary btn-block" type="submit">Add new product</button>
    </form>

</div>

<div class="col-md-4">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category Id</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "p" items="${products}">
            <tr>
                <td><c:out value="${p.id}"/></td>
                <td><c:out value="${p.name}"/></td>
                <td><c:out value="${p.description}"/></td>
                <td><c:out value="${p.price}"/></td>
                <td><c:out value="${p.category_id}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form class="form-deleteProduct" action="<c:url value="/servlet/deleteProduct"/>" method="post">
        <h4 class="mb-3">Delete by id</h4>

        <c:if test="${msg_delete_error}">
            <p style="color:red">Product with id not exist</p>
        </c:if>
        <c:if test="${msg_delete_success}">
            <p style="color:green">The product was deleted</p>
        </c:if>
        <label for="inputProductId" class="sr-only">Product id:</label>
        <input name="productId" type="text" id="inputProductId" class="form-control" placeholder="Product id" required autofocus>

        <button class="btn btn-secondary btn-block" type="submit">Delete product</button>
    </form>
</div>
</body>
</html>