<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="../preset.jsp"%>

<title>BoardWrite</title>

<script type="text/javascript" src="/resources/sehk2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>

<body class="d-flex flex-column h-100">

	<%@ include file="../header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">
			<a href="/board.hk" class="btn btn-outline-info">Board</a>
			 &gt; 
			<a href="/boardWrite.hk" class="btn btn-outline-info">Write</a>
			<hr>
			<form action="/boardWriteResult" id="noticeWriteForm" method="POST">
				<table cellpadding="7px" cellspacing="0" class="table table-bordered" style="width:80%">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
					<tr>
						<td>title</td>
						<td><input type="text" id="title" name="title" size="80%"></td>
					</tr>
					<tr>
						<td>writer</td>
						<td>${login.nickname} <input type="hidden" id="writer"
							name="writer" value="${login.nickname}">
						</td>
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
							<input type="button" class="btn btn-primary" value="write" id="savebutton">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</main>

	<%@ include file="../footer.jsp"%>

	<script type="text/javascript">
		function trim() { 
			return this.replace(/(?:^[\s\u00a0]+)|(?:[\s\u00a0]+$)/g, '');
		}
	
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "smartEditor",
			sSkinURI : "/resources/sehk2/SmartEditor2Skin.html",
			fCreator : "createSEditor2",
			htParams : {
				bUseToolbar : true,
				bUseVerticalResizer : false,
				bUseModeChanger : true
			}
		});

		$(function() {
			$("#savebutton").click(
					function() {
						oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
						$("#smartEditor").val($("#smartEditor").val().replace(/<br>$/, ""));
								
						var title = $("#title").val().trim(); 
						var content = $("#smartEditor").val().trim();
						
						if (title == null || title == "") { 
							alert("제목을 입력해주세요."); 
							$("#title").focus(); 
							return; 
						} 
						if(content == "" || content == null || content == '&nbsp;' || 
							content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
							alert("본문을 작성해주세요."); oEditors.getById["smartEditor"].exec("FOCUS");
							return; 
						} 
						$("#noticeWriteForm").submit();

					});
		});
	</script>

</body>
</html>