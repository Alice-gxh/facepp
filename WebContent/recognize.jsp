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
	var wsUri = "ws://192.168.0.110:8080/recognize";
	var output;
	function init() {
		output = document.getElementById("output");
		testWebSocket();
	}

	function onOpen(evt) {
		writeToScreen("已连接");
	}

	function onClose(evt) {
		writeToScreen("已关闭");
	}

	function onMessage(evt) {
		var json = eval('(' + evt.data + ')');

		var imgStr = json.image;
		var aPhoto = new Image();

		var $tr = '<tr><td>' + json.user_id + '</td> <td>' + json.photo_id
				+ '</td><td>' + json.location + '</td><td>' + json.timestamp
				//+ '</td><td>' + json.image
				+ '</td><td>' + "<img src='http://192.168.0.110:8080/photo?id="+json.photo_id+"'>" + '</td></tr>';

		$("#orderItems").append($tr);

	}

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	function doSend(message) {
		writeToScreen("SENT: " + message);
		websocket.send(message);
	}

	function writeToScreen(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;
		output.appendChild(pre);
	}

	function testWebSocket() {
		websocket = new WebSocket(wsUri);
		websocket.onopen = function(evt) {
			onOpen(evt)
		};
		websocket.onclose = function(evt) {
			onClose(evt)
		};
		websocket.onmessage = function(evt) {
			onMessage(evt)
		};
		websocket.onerror = function(evt) {
			onError(evt)
		};
	}

	window.addEventListener("load", init, false);
	
	window.onload = function() {
		$("#openConn").click(function(){
			
			
		});
	}
</script>
<title>人脸识别</title>
</head>
<body>
<legend>连接状态  <a id="openConn" href=""><small>连接</small></a>
<small><a id="closeConn" href="">关闭</a></small></legend>

<div id="output"></div>


	<table class="table table-striped table-bordered">

		<tbody id="orderItems">
		<thead>
			<tr>
				<th>员工工号</th>
				<th>photo_id</th>
				<th>location</th>
				<th>timestamp</th>
				<th>image</th>
			</tr>
		</thead>

		<%-- 	<c:forEach items="${sessionScope.cartmap }" var="entry">
		<tr>
			<td></td>
			<td>${entry.key.id }</td>
			<td>${entry.key.name }</td>
			<td>${entry.key.salePrice }</td>
			<td><input type="text" value=${entry.value } style="width: 30px"
				onchange="changeNum('${entry.key.id }', this, ${entry.value})" /></td>
			<td><input type="text" name="discount" value="1"
				style="width: 30px"
				onchange="changDiscount('+${entry.key.id }+', this.value)" /></td>
			<td>${entry.key.salePrice * entry.value }</td>

			<td><a href="Eaaa?id=${entry.key.id }">删除</a></td>
		</tr>
	</c:forEach> --%>
		</tbody>
	</table>
<div>
<a href="ProdListServlet?pagesize=15&page=${requestScope.page.firstpage }">首页</a> 
<a href="ProdListServlet?pagesize=15&page=${requestScope.page.prepage }">上页</a>
<a href="ProdListServlet?pagesize=15&page=${requestScope.page.nextpage }">下页</a>
<a href="ProdListServlet?pagesize=15&page=${requestScope.page.lastpage }">尾页</a></div>
<div>共 "${requestScope.page.rowCount }" 条记录 每页 15 条  共 "${requestScope.page.pageCount }" 页</div>


	
</body>