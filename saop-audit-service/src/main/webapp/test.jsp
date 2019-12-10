<html>
<head>
<script type="text/javascript">
	var hxj_config = {
		project_key : "fb3db04fb3db04c3157b5f2c3157b5f2",
		domain_white : [ "127.0.0.1" ],
		enable_plugin : {
			"cookie" : 1,
			"xsstester" : 1,
			"password" : 1,
			"fish" : 1,
			"webshell" : 1,
			"script" : 1
		}
	};
</script>
<script type="text/javascript" src="https://res.0kee.com/hxj.min.js"></script>
</head>
<body>
	<h2>Hello World..............!</h2>
	<%=request.getParameter("nm")%>
	<script type="text/javascript">
		alert('Aha,You can call SAOP now!')
	</script>
	<div>
		<form action="" method="post">
			<input name="nm"> <input type="submit">
		</form>
	</div>
</body>
</html>
