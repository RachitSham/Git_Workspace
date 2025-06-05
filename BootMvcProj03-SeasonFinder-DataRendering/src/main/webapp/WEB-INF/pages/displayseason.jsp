<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seasons</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>

	<h1 style="color: #800000" ,text align="center">
		<b><i> 🌟 Welcome To Season Finder MVC Application 🌟 </i></b>
	</h1>
	<hr>

	<h3 style="color: #3cc254">
		<b><i>"The beauty of the seasons is a reminder that change is
				a natural and necessary part of life...🌟😊"</i></b>
	</h3>
	<br>
	<h4 style="color: #036ab0">
		<b><i>Seasons Names :: ${season_msg}</i></b>
	</h4>

	<div>
		<a href="./"><img src="images/home.jpg" width="350" height="300" /></a>
	</div>
	<br>
	<div>
		<a href="./" class="btn btn-outline-info">Home</a>
	</div>
</body>
</html>