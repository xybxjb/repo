<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
 		+ request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"	href="static/css/default.css" type="text/css" />
<link rel="stylesheet"	href="static/css/all.css" type="text/css" />
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css"/>
<link rel="stylesheet" href="static/css/bootstrap.min.css" type="text/css" />
<title>Insert title here</title>
</head>
<style type="text/css">

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
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
li{
	list-style: none;
}
.but {
	position: absolute;
	left: 378px;
	bottom: 360px;
}
</style>
<body>
	<div class="headercss">
		<form id="form_search" action="" method="post">
			<fieldset class="form_fieldset">
				<div>
					<label>角色名称：</label>
					<input  id="role_name" name="rolename"  class="easyui-textbox"/>
					<input type="button" value="查询 " class="easyui-linkbutton" onclick="search();" style="width: 100px; height: 30px; margin-left: 30px;">
				</div>
			</fieldset>
		</form>
	</div>
	<table id="listData" ></table>
	<!--编辑页面 -->
	<div id="w" class="easyui-window" title="信息修改" data-options="iconCls:'icon-save'" style="width:420px;height:280px;padding:10px;" closed="true">
		<form  id="fromRoles">
			<div style="margin-left: 65px;margin-top: 26px;">
				<input style="margin-top:5px" type="hidden" name="id" id="id" >
				<div class="editwindow" style="padding-left:0px">角色名称：
					<input  id="rolename2" name="rolename"  class="easyui-textbox"/>
				</div>
				<div class="editwindow" style="padding-left:0px">角色描述：
					<input  id="description2" name="description"  class="easyui-textbox"/>
				</div>
				<div class="editwindow" style="padding-left:0px">角色状态：
					<select id="available2" class="easyui-combobox" name="available" style="margin-top:5px;width: 145px">   
					    <option value="1">正常</option>   
					    <option value="2">禁用</option>    
					</select>
				</div>
			</div>
			<div class="editwindow" style="padding-left:40px">
				<input type="button" value="修改" class="test" onclick="updateOrAddDate('fromRoles','update');"style="text-align:center;position: absolute;top:180px;left:38%;width:100px;margin-top:15px;height:30px">
			</div>
		</form>
	</div>
		
	<!-- 添加页面 -->
	<div id="add_win" class="easyui-window" title="添加角色"  closed="true" style="width:420px;height:280px;padding:10px;" >
		<form id="roles_add_form" enctype="multipart/form-data" method="post">
			<div style="margin-left: 65px;margin-top: 26px">
				<div class="editwindow" style="padding-left:0px">角色名称：
					<input  id="rolename" name="rolename"  class="easyui-textbox"/>
				</div>
				<div class="editwindow" style="padding-left:0px">角色描述：
					<input  id="description" name="description"  class="easyui-textbox"/>
				</div>
				<div class="editwindow" style="padding-left:0px">角色状态：
					<select id="available" class="easyui-combobox" name="available" style="margin-top:5px;width: 145px">   
					    <option value="1">正常</option>   
					    <option value="2">禁用</option>    
					</select>
				</div>
			</div>
			
			<div class="editwindow" style="padding-left:40px">
				<input type="button" value="提交" class="test" onclick="updateOrAddDate('roles_add_form','AddRole');"style="text-align:center;position: absolute;top:180px;left:38%;width:100px;margin-top:15px;height:30px">
			</div>
		</form>
	</div>
	
<!--权限管理页面 -->
	<div id="permisssion" class="easyui-window" title="信息修改" data-options="iconCls:'icon-save'" style="width:720px;height:580px;padding:10px;" closed="true">
		<table id="listData2" ></table>
	</div>
	
	<!-- 关联添加权限 -->
	<div id="add_win3" class="easyui-window" title="添加权限"  closed="true" style="width:920px;height:680px;padding:10px;" >
	
		<div  style="border: 1px solid;width: 400px;float: left;">
			<ul id="permission1">
				
			</ul>
		</div>

		<div  style="border: 1px solid;width: 400px;float: left;margin-left: 68px;">
			<form id="roles_add_form3" enctype="multipart/form-data" method="post">
				<input style="margin-top:5px" type="hidden" name="id" id="id3" >
				<div style="margin-left: 65px;margin-top: 0px">
					<ul id="permission2">
						
					</ul>
					<!-- <input style="margin-top:5px" type="hidden" name="id" id="id3" >
					<div class="editwindow" style="padding-left:0px">权限名称：
						<input  id="permission2" name="permission"  class="easyui-textbox"/>
					</div> -->
			</div>
				
				<div class="editwindow" style="padding-left:40px">
					<input type="button" value="提交" class="test" onclick="updateOrAddDate('roles_add_form3','AddRole3');"style="text-align:center;position: absolute;top:528px;left:70%;width:100px;margin-top:15px;height:30px">
				</div>
			</form>
		</div>
		<div class="but">
			<ul>
				<li><button>移动到→</button></li>
				<li><button>移动到←</button></li>
			</ul>
		</div>
	</div>		
</body>
<script type="text/javascript">
$(function () {
	init();
	
});
	function init(){
		$('#listData').datagrid({
	        url: 'roles/getAll',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-135,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        pagination:true,
	        pageSize : 10,
	        pageNumber:1,
	        pageList:[10,20,40,60],
	        singleSelect:true,
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[	        
	            {
	                field: 'rolename', title: '角色名称', width: 100, align: 'center'
	            },
	            {
	                field: 'description', title: '角色描述', width: 100, align: 'center'
	            },
	            {
	                field: 'available', title: '角色状态', width: 100, align: 'center',formatter: function(value, row, index){
	                	if(value == 1){
	                		return "正常";
	                	}else if(value == 2){
	                		return "禁用";
	                	}else {
	                		return "无状态";
	                	}
	                }
	            },
	            {	
	            	field: 'opt', title:'操作',align: 'center',width: 100,formatter: function (value, row, index) {
	                    var str = "";
	                    str += '<a href="javascript:void(0);"  onclick="open_ed('+row.id+')">修改角色 |</a>';
	                    str += '<a href="javascript:void(0);"  onclick="deletes('+row.id+')">删除角色 |</a>';
	                    str += '<a href="javascript:void(0);"  onclick="permission('+row.id+')">权限管理</a>';	
	                //    str += '<a href="javascript:void(0);"  onclick="s_pic('+row.id+')" >查看详情</a>';	           
	                    return str;
	                }
	            }
	        ]],
	        toolbar: [{
	    		iconCls: 'icon-add',
	    		text:'添加',
	    		handler: function(){
	    			add_ed()
	    		}
	    	}]
		});
		$("#w").window('close');
	}
	function add_ed(){
		$('#add_win').window('open');
	}
	function init_win(){
		$('#add_win').window({
			title : '添加角色',
			width : 300,
			height : 200,
			top:($(window).height() - 420) * 0.5,
	        left:($(window).width() - 450) * 0.4, 
			modal : true,
			shadow : true,
			closed:true,
			resizable : false
		}); 
	}
// 	编辑
	function open_ed(id){
		$.ajax({
			type:"post",
			data:{"id":id},
			url:"roles/getById",
			success:function(data){
				$("#id").val(data.id);
				$("#rolename2").textbox("setValue",data.rolename);
				$("#description2").textbox("setValue",data.description);
				$("#available2").combobox("setValue",data.available);
			}
			 
		}) ;
		$("#w").window('open');
	}	
	
	// 查询
	function search(){
		var fields =$('#form_search').serializeArray();   
		
	    var params = $("#listData").datagrid('options').queryParams;  
	    $.each( fields, function(i, field){  
	    /* 	alert(field.value); */
	        params[field.name] = field.value;   
	    });  
	    
	    $('#listData').datagrid('reload')
	    $('#form_search').form('clear');
	} 
	
	//更改+保存
	function updateOrAddDate(form,method){
		var data = $("#"+form).serialize();
	/* 	console.log(data) */
		if(form =='fromRoles'){
				 if($("#rolename2").textbox("getValue")==''){
				alert("角色名称不能为空")
			}else if($("#description2").textbox("getValue")==''){
				alert("角色描述不能为空")
			}else if($("#available2").combobox("getValue")==''){
				alert("状态不能为空")
			}else {
				$.ajax({
					url: "roles/"+method,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromRoles').form('clear');
						$('#roles_add_form').form('clear');
					}
			    	   
				});
			}
		}else if(form =='roles_add_form'){
			if($('#rolename').textbox('getValue')==''){
				alert("用户不能为空")
			}else if($("#description").textbox("getValue")==''){
				alert("密码不能为空")
			}else if($("#available").combobox("getValue")==''){
				alert("状态不能为空")
			}else {
				$.ajax({
					url: "roles/"+method,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromRoles').form('clear');
						$('#roles_add_form').form('clear');
					}
			    	   
				});				
			}
		} else if(form =='roles_add_form3'){
			$.ajax({
				url: "roles/"+method,    //请求的url地址
				async: true, //请求是否异步，默认为异步，这也是ajax重要特性
				data: data,
				type: "post",   //请求方式
				success: function(req){
					$('#w').window('close');
					$('#add_win').window('close');
					$('#add_win3').window('close');
					$('#listData').datagrid('reload');
					$('#listData2').datagrid('reload');
					$('#fromRoles').form('clear');
					$('#roles_add_form3').form('clear');
				}
		    	   
			});		
		}
	}
//	删除
	function deletes(id){
		if(confirm('确认删除')==false){
			return false;
		}else{
			$.ajax({
				type:"post",
				url:"roles/del",
				data:{
					"id" : id
				},
				success : function(){
					$('#listData').datagrid('reload');
					
				}
			})
		}
	}	
	
// 权限管理页面
	function permission(id){
		$('#permisssion').window('open');
		
		$('#listData2').datagrid({
	        url: 'roles/getById2',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-135,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        pagination:true,
	        pageSize : 10,
	        pageNumber:1,
	        pageList:[10,20,40,60],
	        singleSelect:true,
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{"id" : id},//往后台传递参数，json格式 */
	        columns: [[	        
				/* {
				    field: 'roleId', title: '角色编号', width: 100, align: 'center',formatter: function(value, row, index){
				    	return id;
				    }
				},
	            {
	                field: 'id', title: '权限编号', width: 100, align: 'center'
	            }, */
	            {
	                field: 'permission', title: '权限名称', width: 100, align: 'center'
	                
	            },
	            {	
	            	field: 'opt', title:'操作',align: 'center',width: 100,formatter: function (value, row, index) {
	                    var str = "";
	                    str += '<a href="javascript:void(0);"  onclick="deletes2('+row.id+','+id+')">删除关联权限 </a>';
	                //    str += '<a href="javascript:void(0);"  onclick="s_pic('+row.id+')" >查看详情</a>';	           
	                    return str;
	                }
	            }
	        ]],
	        toolbar: [{
	    		iconCls: 'icon-add',
	    		text:'添加',
	    		handler: function(){
	    			add_ed2(id)
	    		}
	    	}]
		});
}

// 给用户添加关联的权限
	// 打开添加框
function add_ed2(id){
	$('#add_win3').window('open');
	// 用户 id 赋值给 对象中的 id
	/* alert(id); */
	$("#id3").val(id);
	
	// 获取所有权限
	$.ajax({
			type:"post",
			url:"roles/getAllPermission",
			success : function(data){
				 $("#permission1").empty();
				 $("#permission2").empty();
				 if(data != null){
					 for(var i=0;i<data.length;i++){
						 $("#permission1").append(
								 "<li >"+
									 "<input type='checkbox'  style='width: 15px;height: 15px;margin-top: 8px;'>&nbsp;&nbsp;:" +
								     "<input type='hidden'  value='"+data[i].id+"'  name='permissionId' style='width: 20px' />&nbsp;&nbsp;权限名称:"+
									 "<input type='text' style='color: red' value='"+data[i].permission+"'  name='permission'  class='easyui-textbox'/>"+
								 "</li >"
						 );
					 }
				 }
			}
		})
}

	$("button:eq(0)").click(function() {
		$("#permission1 li").each(function() {
			if($(this).children().prop("checked")) {
				$(this).children().removeAttr("checked")
				$(this).appendTo($("#permission2"));
			}
		})
	})
	$("button:eq(1)").click(function() {
			$("#permission2 li").each(function() {
				if($(this).children().prop("checked")) {
					$(this).children().removeAttr("checked")
					$(this).appendTo($("#permission1"));
				}
			})
		})
//	 根据角色 id 和 权限 id 删除  关联信息
	function deletes2(permissionId,roleId){
		/* console.log(userId,roleId); */
		if(confirm('确认删除')==false){
			return false;
		}else{
			$.ajax({
				type:"post",
				url:"roles/del2", 
				data:{
					"permissionId" : permissionId,
					"roleId" : roleId
				},
				success : function(){
					$('#listData').datagrid('reload');
					$('#add_win3').window('close');
					$('#listData2').datagrid('reload');
				}
			})
		}
	}	

</script>
</html>