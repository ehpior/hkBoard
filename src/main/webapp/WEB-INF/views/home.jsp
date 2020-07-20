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


	<main role="main" class="flex-shrink-0">
		<div class="container">
			jenKins_test
			<br>
			<br>
			${login}

			<br>
			<br>

			<c:choose>
				<c:when test="${login eq null}">
					<input type="button" value="signIn"
						onClick="location.href='${pageContext.request.contextPath}/login.hk'">
					<input type="button" value="signUp"
						onClick="location.href='${pageContext.request.contextPath}/signUp.hk'">
				</c:when>
				<c:otherwise>
			nickname : ${login.nickname}<br>
					<input type="button" value="logout"
						onClick="location.href='${pageContext.request.contextPath}/logout'">
					<br>
				</c:otherwise>
			</c:choose>
			<br> <br> <input type="button" value="board"
				onClick="location.href='${pageContext.request.contextPath}/board.hk'">
		</div>
	</main>



	<%@ include file="footer.jsp"%>

	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>