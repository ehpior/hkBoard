<%-- <ul>
	<li style="float:right;"><a href="${pageContext.request.contextPath}/account.hk">accountCheck</a></li>
	<li style="float:right;"><a href="${pageContext.request.contextPath}/board.hk">board</a></li>
	<li style="float:right;"><a href="${pageContext.request.contextPath}/login.hk">signIn</a></li>
	<li style="float:right;"><a href="${pageContext.request.contextPath}/signUp.hk">signUp</a></li>
	<li style="float:right;"><a href="${pageContext.request.contextPath}/logout">logout</a></li>
	<li style="float:right;"><a href="${pageContext.request.contextPath}/home.hk">home</a></li>
	<li style="float:right;"><a href="#" id="now"></a></li>
</ul>
<script>
 	var a = new Date();
	document.getElementById('now').innerHTML=a.toString();
	
	setInterval(function(){
		var a = new Date();
		document.getElementById('now').innerHTML=a.toString();},1000);
		$("#now").text((a.getMonth()+1)+"/"+a.getDate()+" "+a.getHours()+":"+a.getMinutes()+":"+a.getSeconds());},1000);
</script> --%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="#"></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarCollapse" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/home.hk">home</a>
			</li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/logout">logout</a>
			</li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/signUp.hk">signUp</a>
			</li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/login.hk">signIn</a>
			</li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/board.hk">board</a>
			</li>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/account.hk">accountCheck</a>
			</li>
			<!-- <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li> -->
		</ul>
		<form class="form-inline mt-2 mt-md-0">
			<!-- <input class="form-control mr-sm-2" type="text" placeholder=""
				aria-label="Search"> -->
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit"></button>
		</form>
	</div>
</nav>