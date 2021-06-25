<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="container">
    <%--@elvariable id="userForm" type=""--%>
    <form:form method="post" modelAttribute="userForm" cssClass="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error':''}">
                    <form:input path="username" type="text" cssClass="form-control" placeHolder="Username" autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="form-group ${status.errors ? 'has-error' : ''}">
                <form:input path="password" cssClass="form-control" type="password"/>
                <form:errors path="confirmPassword"/>
            </div>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>