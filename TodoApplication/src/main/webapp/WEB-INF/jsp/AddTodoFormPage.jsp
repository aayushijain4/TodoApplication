<!--Spring form library tag-->
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
        <div class="container">
            <h2 style="color:slateblue;">Add a New Todo for ${name}.</h2>

            <br>
            <form:form method="post" modelAttribute="todo">
                <form:hidden path="id" />
                <fieldset class="form-group">
                    <form:label path="desc">
                        <h5 style="color:black;">Enter Todo Description:</h5>
                    </form:label>
                    <form:input path="desc" type="text" class="form-control" required="required" />
                    <form:errors path="desc" cssClass="text-warning" />
                </fieldset>

                <fieldset class="form-group">
                    <form:label path="targetDate">
                        <h5 style="color:black;">Target Date:</h5>
                    </form:label>
                    <form:input path="targetDate" type="text" class="form-control" required="required" />
                    <form:errors path="targetDate" cssClass="text-warning" />
                </fieldset>

                <br>
                <button type="submit" class="btn btn-success">Add</button>

            </form:form>
        </div>
        <%@ include file="common/footer.jspf" %>