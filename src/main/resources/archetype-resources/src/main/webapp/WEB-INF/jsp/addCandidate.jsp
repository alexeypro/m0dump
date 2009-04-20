<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>New Candidate</title>
<link href="css/default.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2>New Candidate</h2>
<form:form modelAttribute="profile">
  <table>
    <tr>
      <th>
        First Name: <form:errors path="firstName" cssClass="errors"/>
        <br/>
        <form:input path="firstName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Last Name: <form:errors path="lastName" cssClass="errors"/>
        <br/>
        <form:input path="lastName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        E-Mail: <form:errors path="email" cssClass="errors"/>
        <br/>
        <form:input path="email" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <td>
          <p class="submit"><input type="submit" value="Add Profile"/> or <a href="candidates.html">Cancel</a></p>
      </td>
    </tr>
  </table>
</form:form>

</body>
</html>