<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.ByteArrayOutputStream"%>
<%
	response.setStatus(200);
%>
<!DOCTYPE html>
<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../preset.jsp"%>
<title>Error</title>
</head>
<body class="d-flex flex-column h-100">

	<%@ include file="../header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<a href="/home.hk" class="btn btn-outline-info">Home</a>
			<hr>
			<hr>
			<b>[ error ]</b>
			<br>
			<c:if test="${login.user_type eq 'A'}">
				<%
					try{
					exception.printStackTrace(new java.io.PrintWriter(out));
					}catch(Exception e){
					}
				%>
			</c:if>
		</div>
	</main>


	<%@ include file="../footer.jsp"%>

</body>
</html>