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
	${login}<br>
	
	<% if(session.getAttribute("login")==null) { %>
		<input type="button" value="signIn" onClick="location.href='${pageContext.request.contextPath}/login.hk'">
		<input type="button" value="signUp" onClick="location.href='${pageContext.request.contextPath}/signUp.hk'">
	<% } else{ %>
		nickname : ${login}<br>
		<input type="button" value="logout" onClick="location.href='${pageContext.request.contextPath}/logout'"><br>
	<% } %>
	<br><br>
	
	<input type="button" value="board" onClick="location.href='${pageContext.request.contextPath}/board.hk'">
</main>

<aside>
</aside>

<footer>
</footer>

</body>
</html>