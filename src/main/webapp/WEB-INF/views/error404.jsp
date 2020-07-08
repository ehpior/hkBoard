<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
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
	error [ <%= exception.getMessage( ) %> ]
</main>

<aside>
</aside>

<footer>
</footer>
<br>
</body>
</html>