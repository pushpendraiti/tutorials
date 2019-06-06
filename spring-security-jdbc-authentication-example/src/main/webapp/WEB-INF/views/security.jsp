<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
     <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- <form  name="form" action="${contextRoot}/login" method="post"> --%>
 <form name='loginForm' action="<c:url value='login' />" method='POST'>


	<table>
	<tr>
	<td>User Name</td>
	<td><input type="text" name="username"/></td>
	</tr>
	<tr>
	<td>Password</td><td><input type="password" name="password"/></td>
	</tr>
	<tr><td>  <label for="remember"> Remember me</label>  
        <input type="checkbox" name="remember" /> </td></tr>
	<tr><td> <input type="text"                          
        name="${_csrf.parameterName}"  
        value="${_csrf.token}"/></td></tr>
	<tr><td><input type="submit" value="Submit"/></td></tr>
	</table>
	
	</form>
</body>
</html>