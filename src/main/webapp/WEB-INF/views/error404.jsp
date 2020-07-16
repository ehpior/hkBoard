<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true"%>
<%
	response.setStatus(200);
%>
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
			<input type="button" value="Home"
				onClick="location.href='${pageContext.request.contextPath}/home.hk'">
			<br>
			<br> <b>[ error ]</b> <br>
			<br>
			<%
				exception.printStackTrace(new java.io.PrintWriter(out));
			%>
		</div>
	</main>


	<%@ include file="footer.jsp"%>

</body>
</html>