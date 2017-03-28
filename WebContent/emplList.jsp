<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/layout.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.2.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<legend>员工列表</legend>

<div class="search">
	<form action="">
		员工编号<input type="text" /> 员工姓名<input type="text" /> <input
			type="button" value="搜索" />
	</form>
</div>

<br />
<a href="EmpListServlet">显示所有员工</a>
<br />

<table class="table table-striped table-bordered">
	<thead>
		<tr>
			<th>工号</th>
			<th>姓名</th>
			<th>手机</th>
			<th>生日</th>
			<th>入职时间</th>
			<th>编辑</th>
		</tr>
	</thead>

	<c:forEach items="${requestScope.list }" var="empl">
		<tr>
			<td>${empl.id }</td>
			<td>${empl.name }</td>
			<td>${empl.cellphone }</td>
			<td>${empl.birthday }</td>
			<td>${empl.createTm }</td>
			<td><%-- <a href="EmpDetailServlet?readonly=true&id=${empl.id }">详情</a> 
			  |  --%><a href="EmpEditServlet?readonly=false&id=${empl.id }">编辑</a> 
			<%-- | <a href="EmpDelServlet?id=${empl.id }">删除</a> --%></td>
		</tr>
	</c:forEach>

</table>
<br />


<div>
<a href="">首页</a> 
<a href="">上页</a>
<a href="">下页</a>
<a href="">尾页</a></div>
<div>共  条记录  共  页</div>

