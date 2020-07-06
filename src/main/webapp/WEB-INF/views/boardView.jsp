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

	<input type="button" value="board" onClick="location.href='${pageContext.request.contextPath}/board.hk'">
	<br><br>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>board_id</td>
			<td>title</td>
			<td>writer</td>
			<td>content</td>
			<td>create_date</td>
			<td>modify_date</td>
			<td>notice</td>
			<td>modify</td>
			<td>delete</td>
		</tr>
		<tr>
			<td>${selectBoard.board_id}</td>
			<td>${selectBoard.title}</td>
			<td>${selectBoard.writer}</td>
			<td>${selectBoard.content}</td>
			<td>${selectBoard.create_date}</td>
			<td>${selectBoard.modify_date}</td>
			<td>${selectBoard.notice}</td>
			<td><a href="${pageContext.request.contextPath}/boardModify.hk?board_id=${selectBoard.board_id}">Modify</a></td>
			<td><a href="${pageContext.request.contextPath}/boardDelete?board_id=${selectBoard.board_id}">Delete</a></td>
		</tr>
	</table>	
</main>

<aside>
</aside>

<footer>
</footer>

</body>
</html>