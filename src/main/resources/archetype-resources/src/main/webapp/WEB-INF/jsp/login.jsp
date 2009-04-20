#set($dollar = '$')
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<html>
<head>
<title>Login User</title>
<link href="css/default.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2>Edit Candidate</h2>

<c:if test="${dollar}{not empty param.login_error}">
    <font color="red"><c:out value="${dollar}{SPRING_SECURITY_LAST_EXCEPTION.message}"/></font>
</c:if>

<form action="<c:url value='j_spring_security_check'/>" method="post">
Login: <input type='text' name='j_username' value='<c:if test="${dollar}{not empty param.login_error}"><c:out value="${dollar}{SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/> <br/>
Password: <input type='password' name='j_password'> <br/>
<input type="checkbox" name="_spring_security_remember_me"> Don't ask for my password for two weeks <br/>
<input name="submit" type="submit" value="Login">
</form>

</body>
</html>