<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/layout.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/jquery-1.11.2.js"></script>
<script src="js/bootstrap.js"></script>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	
</script>
<title>考勤</title>
</head>
<body>
<legend>考勤记录</legend>
	<table class="table table-striped table-bordered">

		<tbody id="orderItems">
		<thead>
			<tr>
				<th>工号</th>
				<th>姓名</th>
				<th>部门</th>
				<th>位置</th>
				<th>时间</th>
				<th>照片</th>
			</tr>
		</thead>
	<c:forEach items="${requestScope.list }" var="entry">
		<tr>
			<td>${entry.user_id }</td>
			<td>${entry.user_name }</td>
			<td>${entry.dept }</td>
			<td>${entry.location }</td>
			<td>${entry.timestamp }</td>
			<td>${entry.photo_id }</td>
			
		</tr>
	</c:forEach>
	
		</tbody>
	</table>

	
<a href="AttendLogServlet?pagesize=15&page=${requestScope.page.firstpage }">首页</a> 
<a href="AttendLogServlet?pagesize=15&page=${requestScope.page.prepage }">上页</a>
<a href="AttendLogServlet?pagesize=15&page=${requestScope.page.nextpage }">下页</a>
<a href="AttendLogServlet?pagesize=15&page=${requestScope.page.lastpage }">尾页</a></div>
<div>共 [${requestScope.page.rowCount }] 条记录 每页 15 条  共 [${requestScope.page.pageCount }] 页</div>
</body>