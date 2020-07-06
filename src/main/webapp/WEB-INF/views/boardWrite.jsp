<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css" type="text/css">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>

<style>
</style>

<body>

<header>
	<%@ include file="nav.jsp" %>
</header>

<nav>
</nav>

<main>

	<input type="button" value="Home" onClick="location.href='${pageContext.request.contextPath}/home.hk'">
	<br><br>
	
	<input type="button" value="board" onClick="location.href='${pageContext.request.contextPath}/board.hk'">
	<br><br>
	
	<form action="${pageContext.request.contextPath}/boardWriteResult" id="noticeWriteForm" method="POST">
		<table width="900" cellpadding="20px" cellspacing="0" border="1">
			<tr>
				<td>title</td>
				<td><input type="text" id="title" name="title"></td>
			</tr>
			<tr>
				<td>writer</td>
				<td><input type="number" id="writer" name="writer" value="${login.accnt_id}"></td>
			</tr>
			<tr>
				<td>content</td>
				<td><textarea name="content" id="smartEditor" style="width: 100%; height: 200px;"></textarea></td>
			</tr>
			<c:if test="${login.user_type eq 'A'}">
				<tr>
					<td>notice</td>
					<td><input type="checkbox" name="notice" value="A"></td>
				</tr>			
			</c:if>
			<tr>
				<td colspan="2">
					<input type="button" value="write" id="savebutton">
				</td>
			</tr>
		</table>
	</form>
</main>

<aside>
</aside>

<footer>
</footer>
<script type="text/javascript">
var oEditors = []; 
nhn.husky.EZCreator.createInIFrame({ 
	oAppRef : oEditors, elPlaceHolder : "smartEditor", //���� textarea�� id�� �Ȱ��� ��������ϴ�. 
	sSkinURI : "/hkBoard/resources/se2/SmartEditor2Skin.html", //��θ� �� �����ּ���! 
	fCreator : "createSEditor2", htParams : { // ���� ��� ���� (true:���/ false:������� ����) 
	bUseToolbar : true, // �Է�â ũ�� ������ ��� ���� (true:���/ false:������� ����) 
	bUseVerticalResizer : false, // ��� ��(Editor | HTML | TEXT) ��� ���� (true:���/ false:������� ����) 
	bUseModeChanger : true } });

$(function() { $("#savebutton").click(function() { 
	oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); //textarea�� id�� �����ݴϴ�. 
	/* var selcatd = $("#selcatd > option:selected").val(); 
	var title = $("#title").val(); 
	var content = document.getElementById("smartEditor").value;
	if (selcatd == "") { 
		alert("ī�װ��� �������ּ���."); 
		return; 
	} 
	if (title == null || title == "") { 
		alert("������ �Է����ּ���."); 
		$("#title").focus(); 
		return; 
	} 
	if(content == "" || content == null || content == '&nbsp;' || 
		content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
		alert("������ �ۼ����ּ���."); oEditors.getById["smartEditor"].exec("FOCUS"); //��Ŀ�� 
		return; 
	}  *///�� �κ��� ����Ʈ������ ��ȿ�� �˻� �κ��̴� �����Ͻñ� �ٶ��ϴ�. 
	
	//var result = confirm("���� �Ͻðڽ��ϱ�?"); 
	//if(result){ 
		alert($("#smartEditor").val());
		$("#noticeWriteForm").submit(); 
	//}else{ 
	//	return; 
	//} 
	}); 
})

</script>

</body>
</html>