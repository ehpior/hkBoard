
<footer class="footer mt-auto py-3">
	<div style="text-align:center;">
		<c:if test="${not empty login}">
			${login}<br><br>
		</c:if>
		<a href="https://github.com/ehpior/hkBoard" target="_blank" class="btn btn-outline-primary">GitHub</a>
		&nbsp;&nbsp;&nbsp;
		<span id="now"></span>
 	</div>
</footer>

<script>
 	var a = new Date();
	/* $('#now').text(a.toString()); */
	$("#now").text(a.getFullYear() + "." + ("0"+(a.getMonth()+1)).slice(-2)+"."+("0"+a.getDate()).slice(-2)+" "+
				("0"+a.getHours()).slice(-2)+":"+("0"+a.getMinutes()).slice(-2)+":"+("0"+a.getSeconds()).slice(-2));
	
	setInterval(function(){
		var a = new Date();
		/* $('#now').text(a.toString());},1000); */
		$("#now").text(a.getFullYear() + "." + ("0"+(a.getMonth()+1)).slice(-2)+"."+("0"+a.getDate()).slice(-2)+" "+
				("0"+a.getHours()).slice(-2)+":"+("0"+a.getMinutes()).slice(-2)+":"+("0"+a.getSeconds()).slice(-2));},1000);
				
				
</script>
<script src="/resources/bootstrap-4.5.0/js/bootstrap.bundle.min.js"></script>