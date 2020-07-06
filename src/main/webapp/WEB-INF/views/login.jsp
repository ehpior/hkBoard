<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	<input type="button" value="Home" onClick="location.href='${pageContext.request.contextPath}/home.hk'"><br><br>

	<form action="${pageContext.request.contextPath}/loginResult" method="POST">
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="signIn">
					<input type="button" value="signUp" onClick="location.href='${pageContext.request.contextPath}/signUp.hk'">
					<input type="button" value="logout" onClick="location.href='${pageContext.request.contextPath}/logout'">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="naverLogin" value="NaverLogin">
				</td>
			</tr>
		</table>
	</form>
	<br>
</main>

<aside>
</aside>

<footer>
</footer>



<script type="text/javascript">
	$('#naverLogin').click(function(){
		$.ajax({
			url:"loginNaverUrl",
			type: "POST",
			success:function(data){
				window.location.href=data;
			}
		});
	});
	/* function naverLogin(){
		$.ajax({
			url:"loginNaverUrl",
			type: "POST",
			success:function(data){
				window.location.href=data;
			}
		});
	}; */
</script>
</body>
</html>