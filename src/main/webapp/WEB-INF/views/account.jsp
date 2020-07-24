<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="preset.jsp"%>
<title>Account</title>
</head>
<style>
td{
	text-overflow:ellipsis;  
	white-space:nowrap;
}
</style>

<body class="d-flex flex-column h-100">

	<%@ include file="header.jsp"%>


	<nav></nav>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<a href="/account.hk" class="btn btn-outline-info">Account</a>
			<hr>
			<table cellpadding="7px" cellspacing="0" class="table table-striped table-bordered">
			<colgroup>
				<col width="2">
				<col width="2">
				<col width="2">
				<col width="2">
				<col width="3">
				<col width="2">
				<col width="2">
				<col width="5">
				<col width="2">
			</colgroup>
			<thead>
				<tr>
					<th>accnt_id</td>
					<th>nickname</td>
					<th>navme</td>
					<th>user_type</td>
					<th>phone</td>
					<th>id</td>
					<th>s_passwd</td>
					<th>last_login</td>
					<th>block</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.accnt_id}</td>
						<td>${dto.nickname}</td>
						<td>${dto.name}</td>
						<td>${dto.user_type}</td>
						<td>${dto.phone}</td>
						<td>${dto.id}</td>
						<td>${dto.s_passwd}</td>
						<td>${dto.last_login}</td>
						<td>${dto.block}</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
			<br>
			<div style="text-align:center;">
				<form action="/deleteAccount" method="POST">
					ACCNT_ID : 
					<input type="number" name="accnt_id" style="vertical-align:middle"> 
					<input class="btn btn-primary" type="submit" style="vertical-align:middle" value="delete">
				</form>
			</div>
		</div>
	</main>

	<aside></aside>

	<%@ include file="footer.jsp"%>


</body>
</html>