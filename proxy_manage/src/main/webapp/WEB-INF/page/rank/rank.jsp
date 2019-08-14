<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String basePath=request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath+"/" %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css" />

<script type="text/javascript">
	$(function(){
		init();
		
	});
	
	function init(){
		$('#listData').datagrid({
	        url: 'rank/getAll',//请求方法的地址
	        title: '查询结果',
	        height : window.innerHeight-100, //去除滚动条
	        singleSelect: true,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[
	            { field: 'id', title: 'id', width: 80, align: 'center',hidden:true},
	            {
	                field: 'name', title: '级别', width: 80, align: 'center'
	            },
	            {	field: 'opt', title: '操作', align: 'center',width: 80,formatter:function (value,row,index){
	            	//console.log(row)
	            	var str = "";
	            	str += '<a href="javascript:void(0);" onclick="edit('+row.id+')">编辑</a>';
// 	                str += '<a href="javascript:void(0);"<input type="button" onclick="/* del('+row.id+') */">|删除</a>';
	            	return str;
	            }
	            }
	        ]],
	        toolbar: [{
	    		iconCls: 'icon-add',
	    		text:'添加',
	    		handler: function(){
	    			add()
	    		}
	    	}]
	    });
	}
	
	function del(id){
		$.ajax({
			type:"post", //请求方法
			url:"rank/delete", //请求的url地址
			data:{"id":id},
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			success:function(){
				$('#listData').datagrid('reload');
			}
		});
	}
	
	function edit(id){
		 $.ajax({
			type:"post", //请求方法
			url:"rank/get", //请求的url地址
			data:{"id":id},
			async:true,//请求是否异步，默认为异步，这也是ajax重要特性
			success:function(data){
				$("#id").val(data.id);
				$("#rankName").textbox("setValue",data.name);
			}
		}); 
		 
		 $('#editWin').window({
				title:'修改级别'
			});
			$('#editWin').window('open');
	}
	
	function add(){
		
		$('#addWin').window({
			title:'添加级别'
		});
		
		$('#addWin').window('open');
	}  
	
	function save(){
		var fields = $('#addForm').serialize();
		if($('#name').textbox('getValue')==''){
			alert("级别不能为空")
		}else{
			$.ajax({
				type:"post", //请求方法
				url:"rank/add", //请求的url地址
				data:fields,
				async:true,//请求是否异步，默认为异步，这也是ajax重要特性
				success:function(data){
					$('#listData').datagrid('reload');
					$('#addWin').window('close');
					$('#addForm').form('clear'); 
				}
			});
		}
	}
	
	function update(){
		var fields = $('#editForm').serialize();
		if($('#rankName').textbox('getValue')==''){
			alert("级别不能为空")
		}else{
			$.ajax({
				type:"post", //请求方法
				url:"rank/update", //请求的url地址
				data:fields,
				async:true,//请求是否异步，默认为异步，这也是ajax重要特性
				success:function(data){
					$('#listData').datagrid('reload');
					$('#editWin').window('close');
				}
			});
		}
	}

</script>
<style type="text/css">
	
	#searchForm div{
		display: inline-block;
	}
	
	#addForm div{
		margin-top: 3px;
		margin-left: 30px;
	}
	.inp{
		margin-left: 66px;
		margin-top: 20px;
		width:100px;
		height:30px;
	}
	#updateForm div{
		margin-top: 20px;
		margin-left: 80px;
	}
</style>
<title>chapter</title>
</head>
<body>
	<table id="listData"></table>
	
	<div id="addWin" class="easyui-window" closed="true" style="width:270px;height:150px;padding:10px;">
		<form id="addForm" action="">
			<div>
				<label>级别：</label><input style="margin-left:20px; width:135px;" id="name" name ="name" class="easyui-textbox">
			</div>
			<input class="inp" type="button" onclick="save();" value="保存"  />
		</form>
	</div>
 
	<div id="editWin" class="easyui-window" closed="true" >
		<form id="editForm" action="" style="width:87%">
			<input id="id" type="hidden"  name="id" />
			<div style="margin-top:20px; margin-left:30px;width:207px;">
				<label>级别：</label>&nbsp;<input style="margin-left:20px; width:135px;" id="rankName" name="name" class="easyui-textbox"/>
			</div>
			<input class="inp" type="button" onclick="update();" value="更改"  />
		</form>
	</div>

</body>
</html>