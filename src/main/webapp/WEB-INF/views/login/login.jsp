<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="../preset.jsp"%>
<title>Login</title>
<!-- RSA 자바스크립트 라이브러리 -->
<script type="text/javascript" src="/resources/rsa/jsbn.js"></script>
<script type="text/javascript" src="/resources/rsa/rsa.js"></script>
<script type="text/javascript" src="/resources/rsa/prng4.js"></script>
<script type="text/javascript" src="/resources/rsa/rng.js"></script>
<!-- RSA 암호화 처리 스크립트 -->
<script type="text/javascript" src="/resources/rsa/login.js"></script>
<title>Insert title here</title>
</head>
<body class="d-flex flex-column h-100">

	<%@ include file="../header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<a href="/login.hk" class="btn btn-outline-info">Login</a>
			<hr>
			
			<form action="/loginResult" method="POST" id="form">
				<table style="width:30%;" class="table table-bordered">
				<colgroup>
					<col width="30%">
					<col width="80%">
				</colgroup>
					<tr>
						<th>ID</th>
						<td><input type="text" id="id" name="id" autocomplete="on" size="18"></td>
					</tr>
					<tr>
						<th>PW</th>
						<td><input type="password" id="pw" name="pw" autocomplete="on" size="18"></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center;">
							<input type="button" value="signIn" class="btn btn-primary" id="signIn">
							<input type="button" value="signUp" class="btn btn-primary" onClick="location.href='/signUp.hk'">
							<input type="button" value="NaverLogin" class="btn btn-success" id="naverLogin" >
						</td>
					</tr>
				</table>
			</form>

			<br>
		</div>
	</main>


	<%@ include file="../footer.jsp"%>

	<script type="text/javascript">
		$('#signIn').click(function() {
		
			var uid = $("#id").val();
			var pwd = $("#pw").val();
			var user_type = "";
			
			$.ajax({
				url : "getRSA",
				type : "POST",
				async : false,
				success : function(data) {
					var RSAModulus = data.RSAModulus;
					var RSAExponent = data.RSAExponent;
					//RSA 암호화 생성
					var rsa = new RSAKey();
					rsa.setPublic(RSAModulus,RSAExponent);
					//사용자 계정정보를 암호화 처리
					uid = rsa.encrypt(uid);
					pwd = rsa.encrypt(pwd);
					user_type = rsa.encrypt(user_type);
				}
			});

			$.ajax({
				url : "loginResult",
				type : "POST",
				async : false,
				data : {
					"user_id" : uid,
					"user_pwd" : pwd,
					"user_type" : user_type
				}, //사용자 암호화된 계정정보를 서버로 전송
				success : function(data) {
					console.log(data.state);
					if (data.state == "true") {
						if(data.referer == null || data.referer == ""){
							window.location.href = "/home.hk";							
						}
						else{
							window.location.href = data.referer;
						}
					} else if (data.state == "id") {
						alert("id error");
						window.location.reload(true);
					} else if (data.state == "pw"){
						alert("pw error");
						window.location.reload(true);
					} else {
						alert("unknown error");
						window.location.reload(true);
					}
				}
			});
		});

		$('#naverLogin').click(function() {
			$.ajax({
				url : "loginNaverUrl",
				type : "POST",
				success : function(data) {
					window.location.href = data;
				}
			});
		});
	</script>
</body>
</html>