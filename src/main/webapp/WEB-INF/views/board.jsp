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
<style>
</style>
<body>

<header>
	<%@ include file="nav.jsp" %>
</header>

<nav>
</nav>

<main>
	<input type="button" value="Home" onClick="location.href='${pageContext.request.contextPath}/home.hk'">
	<br><br>
	<input type="button" value="boardWrite" onClick="location.href='${pageContext.request.contextPath}/boardWrite.hk'">
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
		<c:forEach items="${boardList}" var="dto">
			<tr>
				<td>${dto.board_id}</td>
				<td><a href="${pageContext.request.contextPath}/boardView.hk?board_id=${dto.board_id}">${dto.title}</a></td>
				<td>${dto.writer}</td>
				<td>${dto.content}</td>
				<td>${dto.create_date}</td>
				<td>${dto.modify_date}</td>
				<td>${dto.notice}</td>
				<td><a href="${pageContext.request.contextPath}/boardModify.hk?board_id=${dto.board_id}">Modify</a></td>
				<td><a href="${pageContext.request.contextPath}/boardDelete?board_id=${dto.board_id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>	
		
	<c:forEach var="i" begin="1" end="${boardCount}">
		<a href="${pageContext.request.contextPath}/board.hk?boardPage=${i}">
			${i}
		</a>
	</c:forEach>
	<br>
	<!-- 
	<input type="radio" name="maxSelectLimit" value="5">5
	<input type="radio" name="maxSelectLimit" value="10">10
	<input type="radio" name="maxSelectLimit" value="15">15
	<script type="text/javascript">$('input[name="maxSelectLimit"]:checked').val()</script>
	-->
</main>

<aside>
</aside>

<footer>
</footer>














</body>
</html>