#set($dollar = '$')
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="${groupId}.base.Constants" %>
<html>
<head>
<title>List Candidates</title>
<link href="css/default.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2>Candidates</h2>
<font color="green">${dollar}{sessionScope['success']}</font><br/>
<a href="addCandidate.html">Add new candidate w/ profile</a><br/>
<a href="admin.html">Test out authorization</a><br/>
<br/>
Total: ${dollar}{fn:length(profileList)}
<table border="1">
  <tr>
  <thead>
    <th>&nbsp;</th>
    <th>Id</th>
    <th>Full name</th>
    <th>E-mail</th>
  </thead>
  </tr>
  <c:forEach var="profile" items="${dollar}{profileList}">
    <tr>
      <td>
        <form style="display:inline;" action="showCandidate_${dollar}{profile.id}.html" method="get"><input type="Submit" value="View"/></form>
        <sec:authorize ifAllGranted="<%= Constants.ROLE_USER %>">
            <form style="display:inline;" action="editCandidate_${dollar}{profile.id}.html" method="get"><input type="Submit" value="Edit"/></form>
            <form style="display:inline;" action="deleteCandidate_${dollar}{profile.id}.html" method="post"><input onclick="return confirm('Are you sure?');" type="Submit" value="Delete"/></form>
        </sec:authorize>
        <form style="display:inline;" action="emailCandidate_${dollar}{profile.id}.html" method="post"><input type="Submit" value="Send e-mail"/></form>
      </td>
      <td>${dollar}{profile.id}</td>
      <td>${dollar}{profile.firstName} ${dollar}{profile.lastName}</td>
      <td>${dollar}{profile.email}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>