
<footer class="footer mt-auto py-3">
	<div class="container" style="text-align:center;">
		<c:if test="${not empty login}">
			${login}<br>
		</c:if>
		<a href="#" id="now" style="color:black;">FOOTER</a>
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