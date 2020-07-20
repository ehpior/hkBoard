<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<%@ include file="../preset.jsp"%>
<title>Insert title here</title>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/sehk2/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script> --%>
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>

<style>
</style>

<body class="d-flex flex-column h-100">

	<%@ include file="../header.jsp"%>

	<main role="main" class="flex-shrink-0">
		<div class="container">

			<input type="button" value="Home"
				onClick="location.href='${pageContext.request.contextPath}/home.hk'">
			<br>
			<br> <input type="button" value="board"
				onClick="location.href='${pageContext.request.contextPath}/board.hk'">
			<br>
			<br>

			<form action="${pageContext.request.contextPath}/boardWriteResult"
				id="noticeWriteForm" method="POST">
				<table width="900"	cellpadding= "7px"	cellspacing= "0" border= "1">
					<tr>
						<td>title</td>
						<td><input type="text" id="title" name="title"></td>
					</tr>
					<tr>
						<td>writer</td>
						<td>${login.accnt_id} <input type="hidden" id="writer"
							name="writer" value="${login.accnt_id}">
						</td>
					</tr>
					<tr>
						<td>content</td>
						<td><textarea name="content" id="smartEditor"
								style="width: 100%; height: 200px;"></textarea></td>
					</tr>
					<c:if test="${login.user_type eq 'A'}">
						<tr>
							<td>notice</td>
							<td><input type="checkbox" name="notice" value="A"></td>
						</tr>
					</c:if>
					<tr>
						<td colspan="2"><input type="button" value="write"
							id="savebutton"></td>
					</tr>
				</table>
			</form>
		</div>
	</main>

	<%@ include file="../footer.jsp"%>

	<script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "smartEditor", //저는 textarea의 id와 똑같이 적어줬습니다. 
			/* sSkinURI : "/hkBoard/resources/se2/SmartEditor2Skin.html", //경로를 꼭 맞춰주세요!  */
			sSkinURI : "/hkBoard/resources/sehk2/SmartEditor2Skin.html", //경로를 꼭 맞춰주세요!  
			fCreator : "createSEditor2",
			htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseToolbar : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseVerticalResizer : false, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
				bUseModeChanger : true,
				bSkipXssFilter : true
			}
		});

		$(function() {
			$("#savebutton").click(
					function() {
						oEditors.getById["smartEditor"].exec(
								"UPDATE_CONTENTS_FIELD", []); //textarea의 id를 적어줍니다. 
						$("#smartEditor").val(
								$("#smartEditor").val().replace(/<br>$/, ""));
						/* var selcatd = $("#selcatd > option:selected").val(); 
						var title = $("#title").val(); 
						var content = document.getElementById("smartEditor").value;
						if (selcatd == "") { 
							alert("카테고리를 선택해주세요."); 
							return; 
						} 
						if (title == null || title == "") { 
							alert("제목을 입력해주세요."); 
							$("#title").focus(); 
							return; 
						} 
						if(content == "" || content == null || content == '&nbsp;' || 
							content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
							alert("본문을 작성해주세요."); oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱 
							return; 
						}  *///이 부분은 스마트에디터 유효성 검사 부분이니 참고하시길 바랍니다. 
						//var result = confirm("발행 하시겠습니까?");
						//if(result){ 
						$("#noticeWriteForm").submit();
						//}else{ 
						//	return; 
						//} 
					});
		});
	</script>

</body>
</html>