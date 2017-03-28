<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/jquery-1.11.2.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>

<script type="text/javascript">
	
var interval= window.setInterval(queryOrder, 2000);
	
	function queryOrder() {
		//alert("query order");
		window.clearInterval(interval);
		//var framemsg= $("#framemsg").srcdoc;$("iframe").contents().find("body")
		//var f=document.getElementById("hidFrame")
		//var win = f.contentWindow || x.contentDocument;
		//var frameCont = win.document.body.innerText;  
		//$(document.getElementById('hidFrame').contentWindow.document.body).html();
			//win.document.body.innerText; //$("#hidFrame").contentWindow.document.body.innerText;
		var frameCont = $("hidFrame").contents().find("body").innerText;
		
		if ( frameCont == "normal"){
			//alert("framecount="+frameCont);
			alert("frameCont="+frameCont);;
		} else {
			alert("framecount===="+frameCont);
			//$("#hidFrame").innerHTML="unnormal";
		}
		 
	}

	function checkForm() {
		//1. 非空校验
		var cansub = checkNull("userid", "工号不能为空！")
				&& checkNull("name", "姓名不能为空！") && checkNull("dept", "dept");//&& insertEmp();

		return cansub;
	}

	function checkNull(name, msg) {
		//1. 非空校验
		document.getElementById(name + "_msg").innerHTML = "";
		var val = document.getElementsByName(name)[0].value;
		if (val == null || val == "") {
			document.getElementById(name + "_msg").innerHTML = "<font color='red'>"
					+ msg + "</font>";
			return false;
		}
		
		interval = window.setInterval(queryOrder, 5000);
		alert("set interval");
		return true;
	}

	window.onload = function() {
		$("#userid").keyup(function() {
			var e = e || window.event;
			if (e.keyCode == 13) {
				var id = $(this).val();
				if (id == null || id == "") {
					return;
				} else {
					$.get("GetEmpByIdServlet", {
						id : id
					}, function(data) {
						var json = eval("(" + data + ")");

						$("#id").attr("value", id);
						$("#name").attr("value", json.name);
						$("#dept").attr("value", json.dept);
					})
				}
			}
		});
	}
</script>
<style type="text/css">
#preview {
	border: 1px solid #000;
	overflow: hidden;
}

#imghead {
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
}
</style>

<script type="text/javascript">
	function previewImage(file) {
		var MAXWIDTH = 400;
		var MAXHEIGHT = 300;
		var div = document.getElementById('preview');
		if (file.files && file.files[0]) {
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.onload = function() {
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
						img.offsetWidth, img.offsetHeight);
				img.width = rect.width;
				img.height = rect.height;
				//                 img.style.marginLeft = rect.left+'px';
				img.style.marginTop = rect.top + 'px';
			};
			var reader = new FileReader();
			reader.onload = function(evt) {
				img.src = evt.target.result;
			};
			reader.readAsDataURL(file.files[0]);
		} else//兼容IE
		{
			var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width
					+ ',' + rect.height);
			div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
		}
	}

	function clacImgZoomParam(maxWidth, maxHeight, width, height) {
		var param = {
			top : 0,
			left : 0,
			width : width,
			height : height
		};
		if (width > maxWidth || height > maxHeight) {
			rateWidth = width / maxWidth;
			rateHeight = height / maxHeight;

			if (rateWidth > rateHeight) {
				param.width = maxWidth;
				param.height = Math.round(height / rateWidth);
			} else {
				param.width = Math.round(width / rateHeight);
				param.height = maxHeight;
			}
		}

		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}
</script>

<legend>注册照片</legend>
<div class="row">

	<div class="control-group col-xs-4">
		<label class="control-label inline" for="userid">用户ID</label>
		<div class="controls">
			<input type="text" id="userid" name="userid"
				value="${requestScope.employee.id }"
				onkeydown="if(event.keyCode==13)return false;" placeholder="输入工号并回车" /><label
				id="userid_msg"></label>
		</div>

	</div>
	<div class="control-group col-xs-4">
		<label class="control-label" for="name">姓名</label>
		<div class="controls inline">
			<input type="text" id="name" name="name"
				value="${requestScope.employee.name }" /><label id="name_msg"></label>
		</div>
	</div>
	<div class="control-group col-xs-4">
		<label class="control-label" for="dept">部门</label>
		<div class="controls">
			<input type="text" id="dept" name="dept" /><label id="dept_msg"></label>
		</div>
	</div>
	<div>
		<div class="control-group col-xs-4">
			<div class="form-action">
				<label>${requestScope.rstMsg }</label>
			</div>
		</div>
	</div>
</div>
<div>

	<div class="control-group col-xs-4">
		<form class="form-horizontal" 
		method="POST"
			action="http://192.168.0.110:8080/register"
			enctype="multipart/form-data" 
			onsubmit="return checkForm()"
			target="hidFrame">
			<fieldset>
				<div>
					<input id="id" name="id" type="hidden">
				</div>

				<div class="control-group">
					<div class="controls inline">
						<label class="control-label" for="name">选择照片</label>
						<div id="preview">
							<img id="imghead" width=200 height=240 border=0
								src='<%=request.getContextPath()%>/images/defaul.jpg' />
						</div>
						<input type="file" id="photo" name="photo"
							onchange="previewImage(this)" />
					</div>
				</div>


				<br>
				<div class="row">
					<div class="control-group ">
						<div class="form-action">
							<button type="submit" id="regphoto" class="btn btn-primary">添加</button>
							<label id="id_msg"></label>
						</div>
					</div>
				</div>

			</fieldset>
		</form>

<!-- style="display: none;" -->
		<iframe id="hidFrame" src="" srcdoc="normal" width="200px" height="60px"></iframe>
		<div id="divrst"></div>
	</div>
</div>