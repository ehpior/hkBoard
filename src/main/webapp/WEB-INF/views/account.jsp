<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="preset.jsp"%>
<title>Insert title here</title>
</head>

<body class="d-flex flex-column h-100">

	<%@ include file="header.jsp"%>


	<nav></nav>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<input type="button" value="Home"
				onClick="location.href='${pageContext.request.contextPath}/home.hk'">
			<br>
			<br>
			<form action="${pageContext.request.contextPath}/deleteAccount"
				method="POST">
				DeleteByACCNT_ID : <input type="number" name="accnt_id"> <input
					type="submit">
			</form>
			<br>
			<br>
			<table width="900"	cellpadding= "7px"	cellspacing= "0" border= "1">
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
		</div>
	</main>

	<aside></aside>

	<%@ include file="footer.jsp"%>


</body>
</html>