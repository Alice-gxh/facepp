<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/layout.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.2.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <c:if test="${sessionScope.employee == null }">
  欢迎光临guest，请先<a href="login.jsp">登录</a>
</c:if>

<c:if test="${sessionScope.employee != null }">
  欢迎你，${sessionScope.employee.name }，<a href="LogoutServlet">注销</a>
</c:if>

