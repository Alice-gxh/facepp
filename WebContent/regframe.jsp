<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="js/jquery-1.11.2.js"></script>

<script type="text/javascript">
	function checkForm() {
		//1. 非空校验
		var cansub = checkNull("id", "工号不能为空！") ;

		alert(cansub);
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
		return true;
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
</head>
<body>
	<form class="form-horizontal" method="POST"
		action="http://192.168.0.110:8080/register"
		enctype="multipart/form-data" onsubmit="return checkForm()">
		<fieldset>
			<legend>添加照片</legend>
			<div>
				<input id="id" name="id"> <!--  type="hidden"  -->
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
</body>
</html>