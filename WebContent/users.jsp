<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/layout.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.2.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
/* 	window.onload = function() {
		$("#listusers").click(function() {
			alert("in");
			$.get("GetUsers", {}, function(data) {
				var json = eval("(" + data + ")");
				alert(json)
			});
		});
	} */
</script>
<title>所有用户列表</title>
</head>
<body>
	<a href="EmpListServlet">列出所有用户</a>

	<table class="table table-striped table-bordered">

		<tbody id="orderItems">
		<thead>
			<tr>
				<th>工号</th>
				<th>姓名</th>
				<th>部门</th>
				<th>照片</th>
				<th>操作</th>
			</tr>
		</thead>

		<c:forEach items="${requestScope.list }" var="entry">
			<tr>
				<td>${entry.id }</td>
				<td>${entry.name }</td>
				<td>${entry.dept }</td>
				<td><a href="GetPhotosByUserId?userid=${entry.id }">查看照片</a> <%-- <a href="" onclick="showphoto(this, ${entry.id })">show</a> --%>
				</td>
				<td>修改 删除</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

</body>