<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="preset.jsp"%>
<title>Home</title>

</head>
<style>
/* 	.home{
		width:200px;
		text-align:justify;
		text-justify:inter-cluster;
	}
	.home:after{
		content: "";
		display: inline-block;
		width: 100%;
	} */
</style>
<body class="d-flex flex-column h-100">

	<%@ include file="header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<a href="/home.hk" class="btn btn-outline-info">Home</a>
			<hr>
			
			<div class="home">정 현 기</div><br>
			<div class="home">Jeong Hyun Ki</div><br>
			<div class="home">1995. 01. 01</div><br>
			
		</div>
	</main>

	<%@ include file="footer.jsp"%>
	
</body>
</html>