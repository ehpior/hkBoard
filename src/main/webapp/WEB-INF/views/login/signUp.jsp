<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="../preset.jsp"%>
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

	<%@ include file="../header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<input type="button" value="Home"
				onClick="location.href='${pageContext.request.contextPath}/home.hk'">
			<br>
			<br>

			<table border="1">
				<tr>
					<th>nickname
						<div id="nicknameVal" class="nickname val">wrong</div>
					</th>
					<td><input type="text" id="nickname_tmp" /></td>
					<td><input type="button" id="signUpCheckNickname"
						value="checkNickname"></td>
					<td id="checkNicknameResult"></td>
				</tr>
				<tr>
					<th>name</th>
					<td><input type="text" id="name_tmp"></td>
				</tr>
				<tr>
					<th>user_type</th>
					<td><input type="checkbox" id="user_type_tmp"></td>
				</tr>
				<tr>
					<th>phone
						<div id="phoneVal" class="phone val">wrong</div>
					</th>
					<td><select id="phone1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
							<option value="070">070</option>
					</select> <input type="text" id="phone2"> <input type="text"
						id="phone3"></td>
				</tr>
				<tr>
					<th>ID
						<div id="idVal" class="id val">wrong</div>
					</th>
					<td><input type="text" id="id_tmp" /></td>
					<td><input type="button" id="signUpCheckId" value="checkId"></td>
					<td id="checkIdResult"></td>
				</tr>
				<tr>
					<th>PW
						<div id="pwVal"></div>
					</th>
					<td><input type="password" id="s_passwd_tmp" /></td>
				</tr>
				<tr>
					<th>PW2
						<div id="pwVal2"></div>
					</th>
					<td><input type="password" id="s_passwd_tmp2" /></td>
					<td><input type="button" id="pw2"></td>
				</tr>
			</table>
			<br> <input type="button" id="signUp" value="signUp"><input
				type="reset" value="reset">

			<form id="signUpForm"
				action="${pageContext.request.contextPath}/signUpResult.hk"
				method="POST">
				<input type="hidden" id="nickname" name="nickname"> <input
					type="hidden" id="name" name="name"> <input type="hidden"
					id="user_type" name="user_type"> <input type="hidden"
					id="phone" name="phone"> <input type="hidden" id="id"
					name="id"> <input type="hidden" id="s_passwd"
					name="s_passwd">
			</form>
		</div>
	</main>

	<%@ include file="../footer.jsp"%>



	<script>
		$(".val").css("display", "none");

		$("#pw2").click(function() {
			if ($("#s_passwd_tmp").val() != $("#s_passwd_tmp2").val()) {
				$("#s_passwd_tmp2").focus();
			} else {
				alert("t");
			}
		});

		$("#signUpCheckNickname").click(function() {
			var nickname = $("#nickname_tmp").val();

			if (!(/^[a-zA-Z0-9가-힣]{4,12}$/).test(nickname)) {

				$("#nickname_tmp").focus();

				$("#checkNicknameResult").text("impossible-pattern");

				return;
			}

			$.ajax({
				url : "signUpCheckNickname",
				type : "POST",
				data : {
					"nickname" : nickname
				},
				success : function(data) {
					if (data.signUpCheckNickname == 1) {
						$("#checkNicknameResult").text("possible");
					} else {
						$("#nickname_tmp").focus();
						$("#checkNicknameResult").text("impossible-exist");
					}
				}
			});
		});

		$("#signUpCheckId").click(function() {
			var id = $("#id_tmp").val();

			if (!(/^[a-zA-Z0-9]{4,12}$/).test(id)) {

				$("#id_tmp").focus();

				$("#checkIdResult").text("impossible-pattern");

				return;
			}

			$.ajax({
				url : "signUpCheckId",
				type : "POST",
				data : {
					"id" : id
				},
				success : function(data) {
					if (data.signUpCheckId == 1) {
						$("#checkIdResult").text("possible");
					} else {
						$("#id_tmp").focus();
						$("#checkIdResult").text("impossible-exist");
					}
				}
			});
		});

		$("#signUp").click(
				function() {

					var nickname = $("#nickname_tmp").val();
					if (!(/^[a-zA-Z0-9가-힣]{4,12}$/).test(nickname)) {

						$(".val").css("display", "none");
						$(".nickname").css("display", "");

						$("#nickname_tmp").focus();

						return;
					}
					var name = $("#name_tmp").val();
					var user_type = $("#user_type_tmp").is(":checked") ? "A"
							: "";

					var phone = $("#phone1").val() + '-' + $("#phone2").val()
							+ '-' + $("#phone3").val();

					if (!(/^[0-9]{3}-[0-9]{3,4}-[0-9]{3,4}$/).test(phone)) {

						$(".val").css("display", "none");
						$(".phone").css("display", "");

						return;
					}

					var id = $("#id_tmp").val();
					if (!(/^[a-zA-Z0-9]{4,12}$/).test(id)) {

						$(".val").css("display", "none");
						$(".id").css("display", "");

						$("#id_tmp").focus();

						return;
					}

					var s_passwd = $("#s_passwd_tmp").val();

					if (!(/^[a-zA-Z0-9]{4,12}$/).test(s_passwd)) {

						$(".val").css("display", "none");
						$(".s_passwd").css("display", "");

						$("#s_passwd_tmp").focus();

						return;
					}
					if ($("#s_passwd_tmp").val() != $("#s_passwd_tmp2").val()) {
						$("#s_passwd_tmp2").focus();
						return;
					}

					$.ajax({
						url : "signUpCheckNickname",
						type : "POST",
						async : false,
						data : {
							"nickname" : nickname
						},
						success : function(data) {
							if (data.signUpCheckNickname == 1) {
								$("#checkNicknameResult").text("possible");
							} else {
								$("#nickname_tmp").focus();
								$("#checkNicknameResult").text(
										"impossible-exist");
								return;
							}
						}
					});

					$.ajax({
						url : "signUpCheckId",
						type : "POST",
						async : false,
						data : {
							"id" : id
						},
						success : function(data) {
							if (data.signUpCheckId == 1) {
								$("#checkIdResult").text("possible");
							} else {
								$("#id_tmp").focus();
								$("#checkIdResult").text("impossible-exist");
								return;
							}
						}
					});

					$.ajax({
						url : "getRSA",
						type : "POST",
						async : false,
						success : function(data) {
							var RSAModulus = data.RSAModulus;
							var RSAExponent = data.RSAExponent;

							//RSA 암호화 생성
							var rsa = new RSAKey();
							rsa.setPublic(RSAModulus, RSAExponent);

							//사용자 계정정보를 암호화 처리
							nickname = rsa.encrypt(nickname);
							name = rsa.encrypt(name);
							user_type = rsa.encrypt(user_type);
							phone = rsa.encrypt(phone);
							id = rsa.encrypt(id);
							s_passwd = rsa.encrypt(s_passwd);

							$("#nickname").val(nickname);
							$("#name").val(name);
							$("#user_type").val(user_type);
							$("#phone").val(phone);
							$("#id").val(id);
							$("#s_passwd").val(s_passwd);

							$("#signUpForm").submit();

						}
					});

				});
	</script>


</body>
</html>