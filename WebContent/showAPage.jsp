<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/layout.css"/>
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.2.js"></script>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- [if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
 <![endif]-->
<title>网吧管理</title>
</head>

<body>
 <c:if test="${sessionScope.employee != null }">



<div class="container-fluid">
	<div id="header"><jsp:include page="header.jsp" /></div>
	
	<div id="nav">
		<jsp:include page="nav.jsp" />
	</div>
	
	<%-- <div  class="row-fluid" id="maincontent">
		<div class="col-xs-2" id="side">
		 <jsp:include page="sidebar.jsp" />
		</div> --%>
		<div id="maincontent">
		<div id="side"><jsp:include page="sidebar.jsp" />
		</div>

		<div class="col-xs-10" >
		
		<%
 			String pagename = request.getParameter("mainpage").concat(".jsp"); 
		%>		
			<iframe src=<%=pagename%> height="800" width="100%" frameborder="0"></iframe>
		</div><br/>
	</div>
	
<%--     <div id="footer"><jsp:include page="footer.jsp" /></div>		
 --%>	
</div>

</c:if>

 <c:if test="${sessionScope.employee == null }">
   请先登录
   <jsp:forward page="login.jsp"></jsp:forward>
</c:if>


</body>
</html>