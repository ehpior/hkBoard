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
	<form action="${pageContext.request.contextPath}/deleteAccount.hk" method="POST">
		DeleteByACCNT_ID : <input type="number" name="accnt_id">
		<input type="submit">
	</form>
	<br><br>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>ACCNT_ID</td>
			<td>NICKNAME</td>
			<td>NAME</td>
			<td>USER_TYPE</td>
			<td>PHONE</td>
			<td>ID</td>
			<td>S_PASSWD</td>
			<td>LAST_LOGIN</td>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.accnt_id}</td>
				<td>${dto.nickname}</td>
				<td>${dto.name}</td>
				<td>${dto.user_type}</td>
				<td>${dto.phone}</td>
				<td>${dto.id}</td>
				<td>${dto.s_passwd}</td>
				<td>${dto.last_login}</td>
			</tr>
		</c:forEach>
	</table>
</main>

<aside>
</aside>

<footer>
</footer>


</body>
</html>