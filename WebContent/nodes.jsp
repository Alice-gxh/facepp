<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script src="js/jquery-1.11.2.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" />



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	window.onload = function() {
		$("#listcam").click(function() {
			alert("in");
			$.get("http://192.168.0.110:8080/register", 
					{
			}, function(data) {
				var json = eval("(" + data + ")");
				alert(json)
			});
		});
	}
</script>
<title>人脸识别</title>
</head>
<body>

	<a id="listcam" href="">列出所有相机</a>
	<hr />

	<table class="table table-striped table-bordered">

		<tbody id="orderItems">
		<thead>
			<tr>
				<th>user_id</th>
				<th>photo_id</th>
				<th>location</th>
				<th>timestamp</th>
				<th>image</th>
			</tr>
		</thead>

		</tbody>
	</table>

<form action="http://192.168.0.110:8080/nodes" method="get">
<button type="submit" id="regphoto" class="btn btn-primary">添加</button> 
</form>

</body>