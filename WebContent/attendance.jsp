<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/layout.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.2.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	var ns4up1 = (document.layers) ? 1 : 0; // browser sniffer

	var ie4up1 = (document.all && (navigator.appVersion.indexOf("MSIE 4") == -1)) ? 1
			: 0;

	var ns6up1 = (document.getElementById && !document.all) ? 1 : 0;

	function nowclock() {
		if (!ns4up1 && !ie4up1 && !ns6up1)
			return false;
		var digital = new Date();
		var hours = digital.getHours();
		var minutes = digital.getMinutes();
		var seconds = digital.getSeconds();
		var day = digital.getDate();
		var month = digital.getMonth() + 1;
		var year = digital.getYear();

		dispTime = hours + ":" + minutes + ":" + seconds + " " + amOrPm;
		dispDate = year + "年" + month + "月" + day + "日, ";
		if (ns4up1) {
			document.layers.nowTime.document.write(dispTime);
			document.layers.nowTime.document.close();
			document.layers.nowDate.document.write(dispDate);
			document.layers.nowDate.document.close();
		} else if (ns6up1) {
			document.getElementById("nowTime").innerHTML = dispTime;
			document.getElementById("nowDate").innerHTML = dispDate;
		} else if (ie4up1) {
			nowTime.innerHTML = dispTime;
			nowDate.innerHTML = dispDate;
		}
		setTimeout("nowclock()", 1000);
	}
	nowclock();
</script>
<script>
	setInterval(
			"curTime.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",
			1000);
</script>
<script type="text/javascript">
$(function(){  
   // $("#chatData").focus();//输入框获得焦点 
    $('<audio id="chatAudio"><source src="snd/notify.ogg" type="audio/ogg"><source src="snd/notify.mp3" type="audio/mpeg"><source src="snd/notify.wav" type="audio/wav"> </audio>').appendTo('body');//载入声音文件 
    $('#chatAudio')[0].play();
    
    /* $("#trig").click(function(){ 
        var a = $("#chatData").val().trim();//获取输入的内容 
        if(a.length > 0){ 
            $("#chatData").val(''); //清空输入框 
            $("#chatData").focus(); //获得焦点 
            $("<li></li>").html('<img src="qq.gif"/><span>'+a+'</span>') 
            .appendTo("#chatMessages");//将输入的内容追加的聊天区 
            $("#chat").animate({"scrollTop": $('#chat')[0].scrollHeight}, "slow");//调整滚动条 
            $('#chatAudio')[0].play(); //播放声音 
        } 
    });  */
}); 
</script>
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

		var userid=json.user_id;
		var location=json.location;
		var photoid=json.photo_id;
		var dept=null;
		var name=null;
		
		$("#id").attr("value", userid);
		$("#location").attr("value", location);

		$.post("GetEmpByIdServlet", {
			id : userid
		}, function(data) {
			var json1 = eval("(" + data + ")");
			name=json1.name;
			dept=json1.dept;
			$("#name").attr("value", json1.name);
			$("#dept").attr("value", json1.dept);
			$('#chatAudio')[0].play();
		})
		
		$("#photo").attr("src", "http://192.168.0.110:8080/photo?id=" + photoid);
		
		var $tr = '<tr><td>' + userid 
				+ '</td> <td>' + name
				+ '</td><td>' + dept
				+ '</td><td>' + location
				+ '</td><td>' + json.timestamp
				+ '</td></tr>';

		$("#orderItems").append($tr);
		
		$.post("RecgServlet", {
			userid : userid,
			photoid: photoid,
			location: location
		}, function(data) {
			var json2 = eval("(" + data + ")");
		})
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
</script>
<title>考勤</title>
</head>
<body>
	<legend>考勤门禁</legend>
	<div class="row">
		<div class="control-group  col-xs-4">
			<img id="photo" width="200px" height="240px" alt="" src="a">
		</div>

		<div class="control-group  col-xs-6">
			<h1>
				<small><div id="curTime"></div></small>
			</h1>
			<label class="control-label inline" for="id">工号</label>
			<div class="controls">
				<input type="text" id="id" name="id" /><label id="id_msg"></label>
			</div>
			<label class="control-label inline" for="name">姓名</label>
			<div class="controls">
				<input type="text" id="name" name="name" /><label
					id="name_msg"></label>
			</div>
			<label class="control-label inline" for="dept">部门</label>
			<div class="controls">
				<input type="text" id="dept" name="dept" /><label
					id="name_msg"></label>
			</div>
			<label class="control-label inline" for="dept">识别地点</label>
			<div class="controls">
				<input type="text" id="location" name="location" /><label
					id="name_msg"></label>
			</div>
		</div>

	</div>

	<legend>考勤日志</legend>
	<table class="table table-striped table-bordered">
		<tbody id="orderItems">
		<thead>
			<tr>
				<th>工号</th>
				<th>姓名</th>
				<th>部门</th>
				<th>地点</th>
				<th>时间</th>
			</tr>
		</thead>


		</tbody>
	</table>

</body>