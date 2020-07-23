<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="../preset.jsp"%>
<title>Insert title here</title>
</head>
<style>
</style>
<body class="d-flex flex-column h-100">

	<%@ include file="../header.jsp"%>


	<main role="main" class="flex-shrink-0">
		<div class="container">
			<br>
			<div class="float-right" style="padding:15px">
				<a class="btn btn-primary" href="javascript:test(5)">5</a>
				<a class="btn btn-primary" href="javascript:test(10)">10</a>
				<a class="btn btn-primary" href="javascript:test(15)">15</a>
			</div>
			<div class="container">
				<table cellpadding= "7px" cellspacing="0" class="table table-striped table-bordered">
				<colgroup>
					<c:choose>
					<c:when test="${login.user_type eq 'A'}">
					<col width="9%">
					<col width="14%">
					<col width="9%">
					<col width="20%">
					<col width="13%">
					<col width="13%">
					<col width="8%">
					<col width="9%">
					<col width="9%">
					</c:when>
					<c:otherwise>
					<col width="12%">
					<col width="20%">
					<col width="12%">
					<col width="22%">
					<col width="16%">
					<col width="16%">
					<col width="12%">
					</c:otherwise>
					</c:choose>
				</colgroup>
				<thead>
					<tr>
						<th>board_id</td>
						<th>title</td>
						<th>writer</td>
						<th>content</td>
						<th>create_date</td>
						<th>modify_date</td>
						<th>notice</td>
						<c:if test="${login.user_type eq 'A'}">
							<th>modify</td>
							<th>delete</td>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardList}" var="dto">
						<tr>
							<td>${dto.board_id}</td>
							<td><a href="/boardView.hk?board_id=${dto.board_id}">${dto.title}</a>
							</td>
							<td>${dto.writer}</td>
							<td>${dto.content}</td>
							<td>${dto.create_date}</td>
							<td>${dto.modify_date}</td>
							<td>${dto.notice}</td>
							<c:if test="${login.user_type eq 'A'}">
								<td><a href="/boardModify.hk?board_id=${dto.board_id}">Modify</a></td>
								<td><a href="/boardDelete?board_id=${dto.board_id}">Delete</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
				</table>
				
				<hr/>
				
				<div class="d-flex">

					<ul class="list-inline mx-auto justify-content-center pagination">
						<c:forEach var="i" begin="1" end="${boardCount}">
							<li class="page-item">
							<a href="/board.hk?boardPage=${i}" class="list-inline-item page-link">${i}</a>
							</li>
						</c:forEach>
					</ul>
					<div class="float-right">
						<a class="btn btn-primary" href="/boardWrite.hk">write</a>
					</div>
				</div>
				
			</div>

			<br>
		</div>

	</main>



	<%@ include file="../footer.jsp"%>



<script type="text/javascript">
	function test(num) {
		var url = new URL(window.location.href);
		url.searchParams.set('boardPage', 1);
		url.searchParams.set('maxSelectLimit', num);
		window.location.href = url;
	}
</script>


</body>
</html>