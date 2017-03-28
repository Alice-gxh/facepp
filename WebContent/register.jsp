<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/jquery-1.11.2.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>

<script type="text/javascript">
	function checkForm() {
		//1. 非空校验
		var cansub = checkNull("id", "工号不能为空！") 
				&& checkNull("name", "姓名不能为空！")
				&& checkNull("dept", "部门信息不能为空！");//&& insertEmp();

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

	window.onload = function() {
		$("#id").keyup(function() {
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
						$("#name").attr("value", json.name);
						$("#dept").attr("value", json.dept);
					})
				}
			}
		});
	}
</script>


<div class="row">
	<div class="col-xs-4">
		<form class="form-horizontal" method="POST" action="RegEmpServlet"
			onsubmit="return checkForm()">
			<fieldset>
				<legend>注册用户</legend>
				<div class="control-group ">
					<label class="control-label inline" for="id">用户ID</label>
					<div class="controls">
						<input type="text" id="id" name="id" value="${requestScope.employee.id }" onkeydown="if(event.keyCode==13)return false;"/><label id="id_msg"></label>
					</div>

					<label class="control-label" for="name">姓名</label>
					<div class="controls inline">
						<input type="text" id="name" name="name"  value="${requestScope.employee.name }" /><label id="name_msg"></label>
					</div>

					<label class="control-label" for="gender" hidden="true">性别</label>
					<div class="controls">
						<select id="categoryName" name="gender" hidden="true">
							<option>男</option>
							<option>女</option>
						</select><label id="gender_msg"></label>
					</div>

					<label class="control-label" for="dept">部门</label>
					<div class="controls">
						<input type="text" id="dept" name="dept" /><label id="dept_msg"></label>
					</div>

					<label class="control-label" for="idcard">身份证号</label>
					<div class="controls">
						<input type="text" id="idcard" name="idcard" /><label
							id="idcard_msg"></label>
					</div>

					<label class="control-label" for="birthday">生日</label>
					<div class="controls">
						<input type="text" id="birthday" name="birthday" /><label
							id="birthday_msg"></label>
					</div>
					
					<div class="control-group col-xs-4">
						<div class="form-action">
							<button type="submit" id="regUser" class="btn btn-primary">注册</button>
							<label>${requestScope.rstMsg }</label>
						</div>
					</div>
				</div>
			</fieldset>
		</form>
	</div>


</div>