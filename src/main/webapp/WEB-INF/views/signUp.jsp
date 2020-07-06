<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

<header>
	<%@ include file="nav.jsp" %>
</header>

<nav>
</nav>

<main>
	<input type="button" value="Home" onClick="location.href='${pageContext.request.contextPath}/home.hk'">
	<br><br>
	<form action="${pageContext.request.contextPath}/signUpResult.hk" method="POST">
	
		<table border="1">
			<tr>
				<th>nickname</th>
				<td><input type="text" name="nickname" /></td>
			</tr>
			<tr>
				<th>name</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>user_type</th>
				<td>
					<input type="checkbox" name="user_type" value="A">
				</td>
			</tr>
			<tr>
				<th>phone</th>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="password" name="s_passwd" /></td>
			</tr>
		</table>
		<br>
		<input type="submit"><input type="reset">
	</form>
</main>

<aside>
</aside>

<footer>
</footer>



</body>
</html>