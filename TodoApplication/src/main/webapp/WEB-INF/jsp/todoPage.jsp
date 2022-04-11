<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>
        <br>
        <div class="container">
            <h1 style="color:green;">Todos for ${name} :</h1>
        </div>
        <br>
        <!--
<h3>Todo List values are:</h3>
<h3 style="color:orange;"> ${list}</h3>
 -->


        <div class="container">
            <table class="table table-striped">

                <thead>
                    <tr>
                        <th>Todo Id</th>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Is Done?</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${list}" var="todo">
                        <tr>
                            <td>${todo.id}</td>
                            <td>${todo.desc}</td>
                            <td>
                                <fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy" />
                            </td>
                            <td>${todo.done}</td>
                            <td><a type="button" class="btn btn-success" href="/updateTodo?id=${todo.id}">Update Todo
                                </a></td>
                            <td><a type="button" class="btn btn-warning" href="/deletetodo?id=${todo.id}">Delete Todo
                                </a></td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div><a type="button" class="btn btn-primary" href="/addtodo">Add a New Todo</a></div>
            <br>
            <div><a type="button" class="btn btn-warning" href="/deletetodoById">Delete a Todo by ID</a></div>
        </div>
        <%@ include file="common/footer.jspf" %>