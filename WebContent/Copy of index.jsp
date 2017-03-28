<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.2.js"></script>
<!-- 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> -->
<title>人脸识别测试</title>

<script>

	function writeToScreen(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;
		output.appendChild(pre);
	}
	
	window.onload = function() {
		$("#list_users").click(function() {
			//alert("list_users");
			//$.get("http://192.168.0.110:8080/users", {
			$.get("GetUsersServlet", {
				"" : ""
			}, function(data) {
				alert(aaaa);
				var json = eval("(" + data + ")");
				//alert("users:" + json.users);
				//document.getElementById("users_msg")
				writeToScreen("list users: " + json + json.users);
			});
		});

		$("#list_camara").click(function() {
			alert("list_camara");
			$.get("http://192.168.0.110:8000/nodes", {
				"" : ""
			}, function(data) {
				var json = eval("(" + data + ")");
				alert("cameras:" + json.nodes);
			});
		});
	};
</script>


</head>
<body>
	<br />

	<h1>人脸识别</h1>
	<a href="#" id="recognize">人脸识别</a>
	<br /> 使用WebSocket WebSocket /recognize
	<div id="output"> </div>

	<br />

	<h1>自动注册</h1>
	POST /autoregister 可选输入: user_id=0ab4d 必选输入: location=frontdoor
	<form action="http://192.168.0.110:8000/autoregister" method="get">
		<div>
			location (*)<input type="text" id="location">
		</div>
		<div>
			user id <input type="text" id="user_id">
		</div>
		<button>自动注册</button>
	</form>

	<h1>手动注册</h1>


	<h1>列出所有用户</h1>

	<a id="list_users" href="GetUsersServlet">列出所有用户</a>
	<form action="http://192.168.0.110:8080/users" method="get">
		<input type="button" value="列出所有用户">
	</form>

	<label>Users: </label>
	<p id="users_msg">
	<h1>获取注册照片</h1>

	<h1>删除照片/用户</h1>


	<h1>列举相机</h1>
	<a href="#" id="list_camera">列举相机</a>

	<label>Camera: </label>
	<p id="cameras_msg"> </p>
	<h1> </h1>



</body>
</html>