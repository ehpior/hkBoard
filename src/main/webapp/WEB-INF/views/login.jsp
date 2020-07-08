<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css">
<!-- RSA �ڹٽ�ũ��Ʈ ���̺귯�� -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/jsbn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/rsa.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/prng4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/rsa/rng.js"></script>	
<!-- RSA ��ȣȭ ó�� ��ũ��Ʈ -->
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
	<input type="hidden" id="RSAModulus" value="${RSAModulus}" /><!-- �������� �����Ѱ��� �����Ѵ�. -->
	<input type="hidden" id="RSAExponent" value="${RSAExponent}" /><!-- �������� �����Ѱ��� �����Ѵ�. -->
	
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
			 
				//RSA ��ȣȭ ����
				var rsa = new RSAKey();
				rsa.setPublic(RSAModulus, RSAExponent);
				
				//����� ���������� ��ȣȭ ó��
				uid = rsa.encrypt(uid);
				pwd = rsa.encrypt(pwd);
				
				$.ajax({ 
					  type: "POST",  
					  url: "loginResult",  
					  data: {user_id:uid, user_pwd: pwd},  //����� ��ȣȭ�� ���������� ������ ����
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