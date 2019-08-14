<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="static/css/default.css" type="text/css" />
<link rel="stylesheet" href="static/css/all.css" type="text/css" />
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="static/js/jquery.webcam.min.js"></script>
<link rel="stylesheet"
	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"
	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css"
	type="text/css" />
<link rel="stylesheet" href="static/css/bootstrap.min.css"
	type="text/css" />
<title>Insert title here</title>
</head>
<style type="text/css">
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
}

input[type="number"] {
	-moz-appearance: textfield;
}

.headercss {
	margin-top: 20px;
	margin-left: 10px;
	margin-right: 10px;
	margin-bottom: 20px;
}
#webcam {
	border: 1px solid #666666;
	width: 320px;
	height: 240px;
}

#photos {
	border: 1px solid #666666;
	width: 320px;
	height: 240px;
}

.btn {
	width: 320px;
	height: auto;
	margin: 5px 0px;
}

.btn input[type=button] {
	width: 150px;
	height: 50px;
	line-height: 50px;
	margin: 3px;
}
</style>
<body>
	<div class="headercss">
		<form id="form_search" action="" method="post">
			<fieldset class="form_fieldset">
				<div>
					<label>用户姓名：</label> <input id="users_id" name="username"
						class="easyui-textbox" /> <input type="button" value="查询 "
						class="easyui-linkbutton" onclick="search();"
						style="width: 100px; height: 30px; margin-left: 30px;">
				</div>
			</fieldset>
		</form>
	</div>
	<table id="listData"></table>
	<!--编辑页面 -->
	<div id="w" class="easyui-window" title="信息修改"
		data-options="iconCls:'icon-save'"
		style="width: 420px; height: 280px; padding: 10px;" closed="true">
		<form id="fromUsers">
			<div style="margin-left: 65px; margin-top: 26px;">
				<input style="margin-top: 5px" type="hidden" name="id" id="id">
				<div class="editwindow" style="padding-left: 0px">
					用户昵称： <input id="username2" name="name" class="easyui-textbox" />
				</div>
				<!-- <div class="editwindow" style="padding-left: 0px">
					用户密码： <input id="password2" name="password" class="easyui-textbox" />
				</div>
				<div class="editwindow" style="padding-left: 0px">
					账户状态： <select id="available2" class="easyui-combobox"
						name="available" style="margin-top: 5px; width: 145px">
						<option value="1">正常</option>
						<option value="2">禁用</option>
					</select>
				</div> -->
				<!-- <div class="editwindow" style="padding-left:0px;">角色名称:&nbsp;&nbsp;
						<input style="width: 145px;"  id="roles_id2" name="roles.id"  class="easyui-textbox"/>   
					</div> -->
			</div>
			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="修改" class="test"
					onclick="updateOrAddDate('fromUsers','update');"
					style="text-align: center; position: absolute; top: 180px; left: 38%; width: 100px; margin-top: 15px; height: 30px">
			</div>
		</form>
	</div>

	<!-- 添加页面 -->
	<div id="add_win" class="easyui-window" title="添加用户" closed="true"
		style="width: 420px; height: 280px; padding: 10px;">
		<form id="users_add_form" enctype="multipart/form-data" method="post">
			<div style="margin-top: 26px;">


				<div class="editwindow"
					style="padding-left: 10px; margin-top: 10px; width: 300px; height: 30px">

					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">用户名称：</span>
					</div>
					<div style="width: width: 200px;">
						<input id="username" name="username" class="easyui-textbox" />
					</div>
				</div>
				<div class="editwindow"
					style="padding-left: 10px; margin-top: 10px; width: 300px; height: 30px">

					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">用户昵称：</span>
					</div>
					<div style="width: width: 200px;">
						<input id="niname" name="name" class="easyui-textbox" />
					</div>
				</div>
				<div class="editwindow"
					style="padding-left: 10px; margin-top: 10px; width: 300px; height: 30px">
					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">账户状态：</span>
					</div>
					<div style="width: width: 200px;">
						<select id="available" class="easyui-combobox" name="available"
							style="width: 135px">
							<option value="1">正常</option>
							<option value="2">禁用</option>
						</select>
					</div>
				</div>
				<div class="editwindow"
					style="padding-left: 10px; margin-top: 10px; width: 300px; height: 30px">
					<div style="width: 80px; height: 30px; float: left;">
						<span style="float: right;">角色名称：</span>
					</div>
					<div style="width: width: 200px;">
						<input id="roles_id" name="roleId" class="easyui-textbox" />
					</div>
				</div>

			</div>
			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="提交" class="test"
					onclick="updateOrAddDate('users_add_form','AddUser');"
					style="text-align: center; position: absolute; top: 195px; left: 38%; width: 100px; margin-top: 15px; height: 30px">
			</div>
		</form>
	</div>
	<!-- 角色管理 -->
	<div id="role" class="easyui-window" title="角色管理"
		data-options="iconCls:'icon-save'"
		style="width: 620px; height: 380px; padding: 10px;" closed="true">
		<table id="listData2"></table>
	</div>
     <!-- 人脸管理页面 -->
	<div id="faceSystem" class="easyui-window" title="人脸识别管理"
		closed="true" style="width: 200px; height: 250px; text-align: center;"></div>
		
<!--人脸添加页面  -->
<div id="faceAdd" class="easyui-window" title="人脸添加"
		closed="true" style="width: 400px; height: 620px; text-align: center;">
		<div id="webcam"></div>
	<div class="btn">
		<input type="button" value="上传" id="delBtn" onclick="pullPhoto();" />
		<input type="button" value="拍照" id="saveBtn" onclick="savePhoto();" />
	</div>

	<div id="photos">
		<input type="text" style="display: none;" id="pic"> <img
			src="" id="img">
			<input type="text" style="display: none;" id="userid">
	</div>
		
		</div>
	<!-- 关联角色     添加页面 -->
	<div id="add_win3" class="easyui-window" title="添加关联角色" closed="true"
		style="width: 420px; height: 280px; padding: 10px;">
		<form id="users_add_form3" enctype="multipart/form-data" method="post">
			<div style="margin-left: 65px; margin-top: 80px;">
				<input style="margin-top: 5px" type="hidden" name="id" id="id3">
				<div class="editwindow" style="padding-left: 0px">
					角色名称:&nbsp;&nbsp; <input id="roles_id3" name="roleId"
						class="easyui-textbox" />
				</div>
			</div>
			<div class="editwindow" style="padding-left: 40px">
				<input type="button" value="提交" class="test"
					onclick="updateOrAddDate('users_add_form3','AddUser3');"
					style="text-align: center; position: absolute; top: 195px; left: 38%; width: 100px; margin-top: 15px; height: 30px">
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">

	$(function() {
		init();

	});
	function init() {
		$('#listData')
				.datagrid(
						{
							url : 'users/getAll',//请求方法的地址
							title : '查询结果',
							height : window.innerHeight - 135,
							fitColumns : true, //列自适应 
							nowrap : false,//禁止文字自动换行
							pagination : true,
							pageSize : 10,
							pageNumber : 1,
							pageList : [ 10, 20, 40, 60 ],
							singleSelect : true,
							loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
							queryParams : {},//往后台传递参数，json格式 */
							columns : [ [
									{
										field : 'username',
										title : '用户名称',
										width : 60,
										align : 'center'
									},
									{
										field : 'name',
										title : '用户昵称',
										width : 60,
										align : 'center'
									},
									/*  {
									     field: 'password', title: '用户密码', width: 100, align: 'center'
									 }, */
									{
										field : 'available',
										title : '用户状态',
										width : 40,
										align : 'center',
										formatter : function(value, row, index) {
											if (value == 1) {
												return "正常";
											} else if (value == 2) {
												return "禁用";
											} else {
												return "无状态";
											}
										}
									},
									{
										field : 'rolename',
										title : '角色名称',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
										console.log(row);
											var role = "";
											for (var i = 0; i < row.roles.length; i++) {
												var a = row.roles[i];
												role += a.rolename + ",";
											}
											return role;
										}
									},
									{
										field : 'opt',
										title : '操作',
										align : 'center',
										width : 200,
										formatter : function(value, row, index) {
											var str = "";
											str += '<a href="javascript:void(0);"  onclick="open_ed('
													+ row.id + ')">重置用户密码|</a>';
											if (row.username != "admin") {
												str += '<a href="javascript:void(0);"  onclick="deletes('
														+ row.id
														+ ')">删除用户|</a>';
											}
											str += '<a href="javascript:void(0);"  onclick="update('
													+ row.id + ')">更改用户昵称|</a>';
											str += '<a href="javascript:void(0);"  onclick="roles('
													+ row.id + ')">角色管理| </a>';
											str += '<a href="javascript:void(0);"  onclick="face('
														+ row.id + ')">人脸识别 </a>';
											//    str += '<a href="javascript:void(0);"  onclick="s_pic('+row.id+')" >查看详情</a>';	           
											return str;
										}
									} ] ],
							toolbar : [ {
								iconCls : 'icon-add',
								text : '添加',
								handler : function() {
									add_ed()
								}
							} ]
						});
		$("#w").window('close');
	}
//人脸识别
function face(id){
	//清空div
	$('#faceSystem').html("");
	//打开这个管理框
	$('#faceSystem').window('open');
	$("#userid").attr("value",id);
	var MyDiv = document.getElementById("faceSystem");

	var put = document.createElement("button"); //createElement生成button对象
	//add.setAttribute("style","border:1px; border-style: solid ; margin-left:20px; margin-top:20px; ")
	put.className = 'easyui-linkbutton l-btn l-btn-small'; //给生产的button添加class
	put.style.height = '30px';
	put.style.width = '150px';
	put.style.marginTop = '10px';
	put.innerHTML = '增加人脸';
	put.onclick = function() { //绑定点击事件
		faceAdd(id);
		$('#faceSystem').window('close');

	};
	var bye = document.createElement("button"); //createElement生成button对象
	bye.className = 'easyui-linkbutton l-btn l-btn-small';
	bye.style.height = '30px';
	bye.style.width = '150px';
	bye.style.marginTop = '10px'
	bye.innerHTML = '删除人脸';
	bye.onclick = function() { //绑定点击事件
		facedel(id);
		

	};
	//将三个按钮添加到div
	MyDiv.appendChild(put);
	MyDiv.appendChild(bye);
}
//人脸识别添加
function faceAdd(id) {
	$('#faceAdd').window('open');
	$("#img").attr("src", "");
	$("#pic").attr("value","");

}
//删除远程人脸识别
function facedel(id) {
	console.log(id)
	$.ajax({
		type : "post",
		url : "users/facedel",
		data : {
			id:id
		},
		success : function(data) {
			if(data=="SUCCESS"){
				$.messager.alert('警告', '删除成功');
			$('#faceSystem').window('close');
			}
			if(data=="face is not exist"){
				$.messager.alert('警告', '人脸库中无此用户信息，请录入');
			}
			
		}
	});

}

	// 打开添加框
	function add_ed() {
		$('#add_win').window('open');

		// 下拉框获取角色名称	
		$("#roles_id").combobox({
			//后台返回的 json 数据方法地址         
			url : 'roles/getAll',
			valueField : 'id',//相当于option的value值
			textField : 'rolename',//相当于<option></option>之间的显示值 value:1000    //默认显示值
		});
	}
	function init_win() {
		$('#add_win').window({
			title : '添加用户',
			width : 500,
			height : 300,
			top : ($(window).height() - 420) * 0.5,
			left : ($(window).width() - 450) * 0.4,
			modal : true,
			shadow : true,
			closed : true,
			resizable : false
		});
	}
	// 	编辑
	function open_ed(id) {
		if (window.confirm('是否将该账号的密码重置为初始密码“123456”')) {
			$.ajax({
				type : "post",
				data : {
					"id" : id
				},
				url : "users/resertPassword",
				success : function(data) {
					if (data) {
						alert("重置成功！")
					} else {
						alert("重置失败！")
					}
				},
			});
		}
	}

	/* // 下拉框获取角色名称	
	$("#roles_id2").combobox({          
		//后台返回的 json 数据方法地址         
		url:'roles/getAll',
		valueField:'id',//相当于option的value值
		textField:'rolename',//相当于<option></option>之间的显示值 value:1000    //默认显示值
	});
	$("#w").window('open'); */

	//更改用户昵称
	function update(id) {
			$.ajax({
				type : "post",
				data : {
					"id" : id
				},
				url : "users/getById",
				success : function(data) {
					$("#id").val(data.id);
					$("#username2").textbox("setValue", data.name);
					$('#w').window('open');
				},
			});
		}

	// 查询
	function search() {
		var fields = $('#form_search').serializeArray();

		var params = $("#listData").datagrid('options').queryParams;
		$.each(fields, function(i, field) {
			/* 	alert(field.value); */
			params[field.name] = field.value;
		});

		$('#listData').datagrid('reload')
		$('#form_search').form('clear');
	}

	//更改+保存
	function updateOrAddDate(form, method) {
		var data = $("#" + form).serialize();
		/* 	console.log(data) */
		if (form == 'fromUsers') {
			if ($("#username2").textbox("getValue") == '') {
				alert("昵称不能为空")
			}  else {
				$.ajax({
					url : "users/" + method, //请求的url地址
					async : true, //请求是否异步，默认为异步，这也是ajax重要特性
					data : data,
					type : "post", //请求方式
					success : function(req) {
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromUsers').form('clear');
						$('#users_add_form').form('clear');
					}

				});
			}
		} else if (form == 'users_add_form') {
			if ($('#username').textbox('getValue') == '') {
				alert("用户不能为空")
			} else if ($("#available").combobox("getValue") == '') {
				alert("状态不能为空")
			} else if ($("#roles_id").combobox("getValue") == '') {
				alert("请分配角色")
			} else if ($("#niname").textbox("getValue") == '') {
				alert("昵称不能为空")
			} else {

				$.ajax({
					url : "users/" + method, //请求的url地址
					async : true, //请求是否异步，默认为异步，这也是ajax重要特性
					data : data,
					type : "post", //请求方式
					success : function(req) {
						console.log(req)
						if (req == 0) {
							alert("已存在该用户");
						} else {
							$('#w').window('close');
							$('#add_win').window('close');
							$('#listData').datagrid('reload');
							$('#fromUsers').form('clear');
							$('#users_add_form').form('clear');
						}

					}

				});
			}
		} else if (form == 'users_add_form3') {

			$.ajax({
				url : "users/" + method, //请求的url地址
				async : true, //请求是否异步，默认为异步，这也是ajax重要特性
				data : data,
				type : "post", //请求方式
				success : function(req) {
					$('#w').window('close');
					$('#add_win').window('close');
					$('#add_win3').window('close');
					$('#listData').datagrid('reload');
					$('#listData2').datagrid('reload');
					$('#fromUsers').form('clear');

				}

			});

		}
	}
	//	删除
	function deletes(id) {
		if (confirm('确认删除') == false) {
			return false;
		} else {
			$.ajax({
				type : "post",
				url : "users/del",
				data : {
					"id" : id
				},
				success : function() {
					$('#listData').datagrid('reload');

				}
			})
		}
	}

	// 角色管理
	function roles(id) {
		$('#role').window('open');

		$('#listData2')
				.datagrid(
						{
							url : 'users/getById2',//请求方法的地址
							title : '查询结果',
							height : window.innerHeight - 135,
							fitColumns : true, //列自适应 
							nowrap : false,//禁止文字自动换行
							pagination : true,
							pageSize : 20,
							pageNumber : 1,
							pageList : [ 20, 40, 60 ],
							singleSelect : true,
							loadMsg : '正在加载信息...',//当数据没有加载出来时显示的文字
							queryParams : {
								"id" : id
							},//往后台传递参数，json格式 */
							columns : [ [
									{
										field : 'userId',
										title : '用户编号',
										//	width : 100,
										align : 'center',
										hidden : true,
										formatter : function(value, row, index) {
											return id;
										}
									},
									{
										field : 'id',
										title : '角色编号',
										//	width : 100,
										align : 'center',
										hidden : true,
										formatter : function(value, row, index) {
											return value;
										}
									},
									{
										field : 'rolename',
										title : '角色名称',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return value;
										}
									},
									{
										field : 'opt',
										title : '操作',
										align : 'center',
										width : 100,
										formatter : function(value, row, index) {
											var str = "";
											str += '<a href="javascript:void(0);"  onclick="deletes2('
													+ row.id
													+ ","
													+ id
													+ ')">删除关联角色 </a>';
											//    str += '<a href="javascript:void(0);"  onclick="s_pic('+row.id+')" >查看详情</a>';	           
											return str;
										}
									} ] ],
							toolbar : [ {
								iconCls : 'icon-add',
								text : '添加',
								handler : function() {
									add_ed2(id);
								}
							} ]
						});
	}

	// 给用户添加关联的角色
	// 打开添加框
	function add_ed2(id) {
		$('#add_win3').window('open');
		// 用户 id 赋值给 对象中的 id
		/* alert(id); */
		$("#id3").val(id);
		// 下拉框获取角色名称	
		$("#roles_id3").combobox({
			//后台返回的 json 数据方法地址         
			url : 'roles/getAll',
			valueField : 'id',//相当于option的value值
			textField : 'rolename',//相当于<option></option>之间的显示值 value:1000    //默认显示值
		});
	}

	//	删除 关联角色
	function deletes2(roleId, userId) {
		/* console.log(userId,roleId); */
		if (confirm('确认删除') == false) {
			return false;
		} else {
			$.ajax({
				type : "post",
				url : "users/del2",
				data : {
					"userId" : userId,
					"roleId" : roleId
				},
				success : function() {
					$('#listData').datagrid('reload');
					$('#add_win3').window('close');
					$('#listData2').datagrid('reload');

				}
			})
		}
	}
</script>
<script type="text/javascript">
$(function() {
	var w = 320, h = 240;
	var pos = 0, ctx = null, saveCB, image = [];
	var canvas = document.createElement("canvas");
	canvas.setAttribute('width', w);
	canvas.setAttribute('height', h);
	if (canvas.toDataURL) {

		ctx = canvas.getContext("2d");

		image = ctx.getImageData(0, 0, w, h);

		saveCB = function(data) {

			var col = data.split(";");
			var img = image;

			for (var i = 0; i < w; i++) {
				var tmp = parseInt(col[i]);
				img.data[pos + 0] = (tmp >> 16) & 0xff;
				img.data[pos + 1] = (tmp >> 8) & 0xff;
				img.data[pos + 2] = tmp & 0xff;
				img.data[pos + 3] = 0xff;
				pos += 4;
			}

			if (pos >= 4 * w * h) {
				ctx.putImageData(img, 0, 0);
				/* $.post("servlet/CatD", {type: "data", image: canvas.toDataURL("image/png")}, function(msg){
				    console.log("===="+eval(msg));
				    pos = 0;
				    $("#img").attr("src", msg+"");
				}); */
				$.ajax({
					type : "post",
					url : "users/cat?t=" + new Date().getTime(),
					data : {
						type : "pixel",
						image : canvas.toDataURL("image/png")
					},
					dataType : "html",
					success : function(data) {
						console.log("====" + data);
						pos = 0;
						
						$("#img").attr("src", data);
						$("#pic").attr("value",data);
					}
				});
			}
		};

	} else {

		saveCB = function(data) {
			image.push(data);

			pos += 4 * w;

			if (pos >= 4 * w * h) {
				/* $.post("servlet/CatD", {type: "pixel", image: image.join('|')}, function(msg){
				    console.log("+++++"+eval(msg));
				    pos = 0;
				    $("#img").attr("src", msg+"");
				}); */

				$.ajax({
					type : "post",
					url : "servlet/CatD",
					data : {
						type : "pixel",
						image : image.join('|')
					},
					dataType : "json",
					success : function(data) {
						console.log("+++++" + eval(msg));
						pos = 0;
						$("#img").attr("src", msg + "");
					}
				});
			}
		};
	}

	$("#webcam").webcam({
		width : w,
		height : h,
		mode : "callback",
		swffile : "static/js/jscam_canvas_only.swf",

		onSave : saveCB,

		onCapture : function() {
			webcam.save();
		},

		debug : function(type, string) {
			console.log(type + ": " + string);
		}
	});
});

//拍照
function savePhoto() {
	webcam.capture();
}
//上传
function pullPhoto() {
	var image=$("#pic").val();
	if(image==''){
		$.messager.alert('警告', '请先拍照!');
	}
	$.ajax({
		type : "post",
		url : "users/faceAdd",
		data : {
			image:$("#pic").val(),
			id:$("#userid").val()
			
		},
		success : function(data) {
			if(data=="success"){
				$('#faceAdd').window('close');
				$.messager.alert('恭喜', '录入成功');
			}else{
				$.messager.alert('警告', '录入失败！请重新拍照或联系管理员！');
			}
		}
	});
}
</script>

</html>