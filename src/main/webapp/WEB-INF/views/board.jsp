<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="preset.jsp"%>
<title>Insert title here</title>
</head>
<style>
</style>
<body class="d-flex flex-column h-100">

	<%@ include file="header.jsp"%>


	<main role="main" class="flex-shrink-0">
		<div class="container">
			<input type="button" value="Home"
				onClick="location.href='${pageContext.request.contextPath}/home.hk'">
			<br>
			<br> <input type="button" value="boardWrite"
				onClick="location.href='${pageContext.request.contextPath}/boardWrite.hk'">
			<br>
			<br> <input type="button" onclick="test(5)"
				name="maxSelectLimit" value="5"> <input type="button"
				onclick="test(10)" name="maxSelectLimit" value="10"> <input
				type="button" onclick="test(15)" name="maxSelectLimit" value="15">
			<script type="text/javascript">
				function test(num) {
					var url = new URL(window.location.href);
					url.searchParams.set('boardPage', 1);
					url.searchParams.set('maxSelectLimit', num);
					window.location.href = url;
				}
			</script>

			<br>
			<br>
			<div style="overflow: auto;">
				<table width="900" cellpadding="5px" cellspacing="0" border="1">
					<tr>
						<td>board_id</td>
						<td>title</td>
						<td>writer</td>
						<td>content</td>
						<td>create_date</td>
						<td>modify_date</td>
						<td>notice</td>
						<td>modify</td>
						<td>delete</td>
					</tr>
					<c:forEach items="${boardList}" var="dto">
						<tr>
							<td>${dto.board_id}</td>
							<td><a
								href="${pageContext.request.contextPath}/boardView.hk?board_id=${dto.board_id}">${dto.title}</a>
							</td>
							<td>${dto.writer}</td>
							<td>${dto.content}</td>
							<td>${dto.create_date}</td>
							<td>${dto.modify_date}</td>
							<td>${dto.notice}</td>
							<td><a
								href="${pageContext.request.contextPath}/boardModify.hk?board_id=${dto.board_id}">Modify</a></td>
							<td><a
								href="${pageContext.request.contextPath}/boardDelete?board_id=${dto.board_id}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<c:forEach var="i" begin="1" end="${boardCount}">
				<a href="${pageContext.request.contextPath}/board.hk?boardPage=${i}">
					${i} </a>
			</c:forEach>
			<br>
		</div>

	</main>



	<%@ include file="footer.jsp"%>







	<script>
</script>






</body>
</html>