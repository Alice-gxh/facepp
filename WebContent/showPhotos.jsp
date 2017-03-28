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
	/* window.onload=function() {
		$("#listphotos").click(function(){
			var userid=$("#userid").val();
			$.
			
			
			
			alert(userid);
			
		});
	} */
</script>
<title>用户已注册照片</title>
</head>
<body>

<input type="text" id="userid" name="userid" value="${requestScope.userid }">

	<table class="table table-striped table-bordered">

		<tbody id="orderItems">
		<thead>
			<tr>
				<th>编号</th>
				<th>用户</th>
				<th>照片编号</th>
				<th>照片</th>
				<th>操作</th>
			</tr>
		</thead>

 <c:forEach items="${requestScope.list }" var="entry">
		<tr>
			<td>${entry.id }</td>
			<td>${entry.user_id }</td>
			<td>${entry.photo_id }</td>
			<td><img width="100px" height="120px" src="http://192.168.0.110:8080/photo?id=${entry.photo_id }" ></td>
			<td>删除</td>
		</tr>
	</c:forEach> 
		</tbody>
	</table>

</body>