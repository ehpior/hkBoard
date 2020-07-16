<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="preset.jsp"%>
<title>Insert title here</title>
<!-- RSA 자바스크립트 라이브러리 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/rsa/jsbn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/rsa/rsa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/rsa/prng4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/rsa/rng.js"></script>
<!-- RSA 암호화 처리 스크립트 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/rsa/login.js"></script>
<title>Insert title here</title>
</head>
<body class="d-flex flex-column h-100">

	<%@ include file="header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<input type="button" value="Home"
				onClick="location.href='${pageContext.request.contextPath}/home.hk'"><br>
			<br>

			<%-- <form action="${pageContext.request.contextPath}/loginResult" method="POST"> --%>
			<form action="${pageContext.request.contextPath}/loginResult"
				method="POST" id="form">
				<table border="1">
					<tr>
						<th>ID</th>
						<td><input type="text" id="id" name="id" autocomplete="on"></td>
					</tr>
					<tr>
						<th>PW</th>
						<td><input type="password" id="pw" name="pw"
							autocomplete="on"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" value="signIn"
							id="signIn"> <input type="button" value="signUp"
							onClick="location.href='${pageContext.request.contextPath}/signUp.hk'">
							<input type="button" value="logout"
							onClick="location.href='${pageContext.request.contextPath}/logout'">
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" id="naverLogin"
							value="NaverLogin"></td>
					</tr>
				</table>
			</form>

			<br>
		</div>
	</main>


	<%@ include file="footer.jsp"%>



	<script type="text/javascript">
		$('#signIn')
				.click(
						function() {

							$
									.ajax({
										url : "getRSA",
										type : "POST",
										success : function(data) {
											var RSAModulus = data.RSAModulus;
											var RSAExponent = data.RSAExponent;

											//RSA 암호화 생성
											var rsa = new RSAKey();
											rsa.setPublic(RSAModulus,
													RSAExponent);

											//사용자 계정정보를 암호화 처리
											var uid = rsa.encrypt($("#id")
													.val());
											var pwd = rsa.encrypt($("#pw")
													.val());

											$
													.ajax({
														type : "POST",
														url : "loginResult",
														data : {
															user_id : uid,
															user_pwd : pwd
														}, //사용자 암호화된 계정정보를 서버로 전송
														success : function(data) {
															if (data.state == "true") {
																window.location.href = "${pageContext.request.contextPath}/home.hk";
															} else if (data.state == "false") {
																window.location
																		.reload(true);
															} else {
																window.location
																		.reload(true);
															}
														}
													});
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