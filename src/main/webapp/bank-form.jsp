<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Bank Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Bank Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Bank</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${bank != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${bank == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>	
                                <c:if test="${bank != null}">
                                    Edit Account Details
                                </c:if>
                                <c:if test="${bank == null}">
                                    Add New Account Details
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${bank != null}">
                            <input type="hidden" name="id" value="<c:out value='${bank.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Account_Holder</label> <input type="text" value="<c:out value='${bank.account_holder}' />" class="form-control" name="account_holder" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Account_Number</label> <input type="text" value="<c:out value='${bank.account_number}' />" class="form-control" name="account_number">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Phone_Number</label> <input type="text" value="<c:out value='${bank.phone_number}' />" class="form-control" name="phone_number">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Balence</label> <input type="text" value="<c:out value='${bank.balence}' />" class="form-control" name="balence">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>
</html>
                        
