<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css">
<!-- RSA 자바스크립트 라이브러리 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/jsbn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/rsa.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/prng4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/rng.js"></script>	
<!-- RSA 암호화 처리 스크립트 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/login.js"></script>	
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

	<%-- <form action="${pageContext.request.contextPath}/loginResult" method="POST"> --%>
	<form action="${pageContext.request.contextPath}/loginResult" method="POST" id="form">
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" id="id" name="id"></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="password" id="pw" name="pw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="signIn" id="signIn">
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
	<input type="hidden" id="RSAModulus" value="${RSAModulus}" /><!-- 서버에서 전달한값을 셋팅한다. -->
	<input type="hidden" id="RSAExponent" value="${RSAExponent}" /><!-- 서버에서 전달한값을 셋팅한다. -->
	
	<br>
</main>

<aside>
</aside>

<footer>
</footer>



<script type="text/javascript">

	

	$('#signIn').click(function(){

		$.ajax({
			url:"getRSA",
			type: "POST",
			success:function(data){
				var RSAModulus=data.RSAModulus;
				var RSAExponent=data.RSAExponent;
				
				var uid = $("#id").val();
				var pwd = $("#pw").val();
			 
				//RSA 암호화 생성
				var rsa = new RSAKey();
				rsa.setPublic(RSAModulus, RSAExponent);
				
				//사용자 계정정보를 암호화 처리
				uid = rsa.encrypt(uid);
				pwd = rsa.encrypt(pwd);
				
				$.ajax({ 
					  type: "POST",  
					  url: "loginResult",  
					  data: {user_id:uid, user_pwd: pwd},  //사용자 암호화된 계정정보를 서버로 전송
					  success:function(data){
						  if(data.state == "true")
						  {
							  window.location.href = "${pageContext.request.contextPath}/home.hk"; 
						  }
						  else if(data.state == "false")
						  {
							  window.location.reload(true);
						  }
						  else
						  {
							  window.location.reload(true);
						  } 
					  } 
				});
			}
		});
		
		
	});



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