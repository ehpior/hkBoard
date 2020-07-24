<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="../preset.jsp"%>
<title>BoardView</title>
</head>
<body>

<body class="d-flex flex-column h-100">

	<%@ include file="../header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">

			<a href="/board.hk" class="btn btn-outline-info">Board</a>
			 &gt; 
			<a href="/boardView.hk?board_id=${selectBoard.board_id}" class="btn btn-outline-info">${selectBoard.board_id}</a>
			<hr>
						
			<table cellpadding="7px" cellspacing="0" class="table table-bordered">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
				<tr>
					<td>board_id</td>
					<td>${selectBoard.board_id}</td>
				</tr>
				<tr>
					<td>title</td>
					<td>${selectBoard.title}</td>
				</tr>
				<tr>
					<td>writer</td>
					<td>${selectBoard.writer}</td>
				</tr>
				<tr>
					<td>content</td>
					<td style="height: 400px;">${selectBoard.content}</td>
				</tr>
				<tr>
					<td>create_date</td>
					<td>${selectBoard.create_date}</td>
				</tr>
				<c:if test="${not empty selectBoard.modify_date}">
				<tr>
					<td>modify_date</td>
					<td>${selectBoard.modify_date}</td>
				</tr>
				</c:if>
			</table>
			<br>
			<c:if test="${login.user_type eq 'A' || selectBoard.writer eq login.nickname }">
				<div style="text-align:center;">
					<a class="btn btn-primary" href="/boardModify.hk?board_id=${selectBoard.board_id}">Modify</a>
					<a class="btn btn-primary" href="/boardDelete?board_id=${selectBoard.board_id}">Delete</a>
				</div>
			</c:if>
		</div>
	</main>

	<aside></aside>

	<%@ include file="../footer.jsp"%>

</body></html>