<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/layout.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.2.js"></script>


<div id="side">
	<ul>
		<li><a href="attendance.jsp" target="mainFrame">首页</a>
			<ul>
			<li><a href="attendance.jsp" target="mainFrame">》考勤/门禁</a></li>
			<!-- <li><a href="recognize.jsp" target="mainFrame">》人脸识别</a></li> -->
			<li><a href="AttendLogServlet" target="mainFrame">》考勤记录</a></li>
			<!-- <li><a href="alert.jsp" target="mainFrame">》门禁</a></li> -->
				<!-- <li><a href="index.jsp?mainpage=attendance">》考勤/门禁</a></li>
				<li><a href="index.jsp?mainpage=recognize">》人脸识别</a></li>
				<li><a href="index.jsp?mainpage=attendLog">》考勤记录</a></li>
				<li><a href="index.jsp?mainpage=alert">》门禁</a></li> -->

			</ul></li>
			
			<li><a href="#">信息管理</a>
			<ul>
			<li><a href="register.jsp" target="mainFrame">》注册用户</a></li>
			<li><a href="regphoto.jsp" target="mainFrame">》注册照片</a></li>
				<li><a href="EmpListServlet" target="mainFrame">》已注册用户</a></li>
				<li><a href="nodes.jsp" target="mainFrame">》所有相机</a></li>
				<li></li>
			</ul></li>
			
		<li><a href="#">系统设置</a>
			<ul>
				<li><a href="index.jsp?mainpage=sysSetting">》系统设置</a></li>
				<li><a href="index.jsp?mainpage=authority">》权限控制</a></li>
				<li><a href="index.jsp?mainpage=operateLog">》操作记录</a>
				<li></li>
			</ul></li>
	</ul>

</div>