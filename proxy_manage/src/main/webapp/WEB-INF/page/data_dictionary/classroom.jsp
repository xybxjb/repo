<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();%>
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

</style>
<body>
	<table id="listData"></table>
	
<!--编辑页面 -->
	<div id="w" class="easyui-window" title="信息修改" data-options="iconCls:'icon-save'" style="width:320px;height:300px;padding:10px;" closed="false">
		<form  id="fromClassroomDate">
			<input style="margin-top:5px" type="hidden" name="id" id="cId" >
			<div class="editwindow" style="padding-left:40px">班&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 级：<input style="margin-top:5px" class="easyui-textbox" name="name" id="cname"> </div>
			<div class="editwindow" style="padding-left:40px">创建时间：<input style="margin-top:5px" class="easyui-datebox" name="beginTime" id="beginTime"> </div>
			<div class="editwindow" style="padding-left:40px">结束时间：<input style="margin-top:5px" class="easyui-datebox" name="endTime" id="endTime"> </div>
			<div class="editwindow" style="padding-left:40px">老&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 师：<input type="text" id="teacher_name2"  name="teacherIds" style="margin-top:5px;width:133px" class="easyui-textbox"> </div>
			<div class="editwindow" style="padding-left:40px"><input type="button" value="保存" class="test" onclick="updateOrAddDate('fromClassroomDate','upd');"style="text-align:center;position: absolute;left:33%;width:100px;margin-top:15px;height:30px"></div>
		</form>
	</div>
	<div id="add_win" class="easyui-window" title="添加班级"  closed="true" style="width:364px;height:300px;padding:10px;">
		<form action="classroom/add" id="classroom_add_form">
			<div class="editwindow" style="padding-left:40px">班&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 级：<input type="text" id="classroom_name"  name="name" style="margin-top:5px" class="easyui-textbox"> </div>
			<div class="editwindow" style="padding-left:40px">老&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 师：<input type="text" id="teacher_name"  name="teacherId" style="margin-top:5px;width:133px" class="easyui-textbox"> </div>
			<div class="editwindow" style="padding-left:40px">创建时间：<input style="margin-top:5px" class="easyui-datebox" name="beginTime" id="beginTime2"> </div>
			<!-- <div class="editwindow" style="padding-left:40px">创建时间：<input style="margin-top:5px" class="easyui-datebox" name="endTime" id="beginTime2"> </div> -->
			<div class="editwindow" style="padding-left:40px"><input type="button" value="保存" class="test" onclick="updateOrAddDate('classroom_add_form','add');"></div>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function () {
	init();
	getTeacher();
});

	function init(){
		$('#listData').datagrid({
	        url: 'classroom/getAll',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-100,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	       /*  pagination:true,
	        pageSize : 20,
	        pageNumber:1,
	        pageList:[20,40,60], */
	        singleSelect:true,
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[	        
	            {
	                field: 'name', title: '班级名称', width: 80, align: 'center'
	            },
	            {
	                field: 'teacher', title: '老师名称', width: 80, align: 'center',formatter: function (value, row, index) {
	                	var teachers = [];
	                	$.each(value, function(index, item){
	                		teachers.push(item.name);
	                		
	        	        });
	                /* 	console.log(value); */
	                	return teachers;
	                }
	            },
	            {
	                field: 'beginTime', title: '创建时间', width: 80, align: 'center'
	            },
	            {
	                field: 'endTime', title: '结束时间', width: 80, align: 'center'
	            },
	            {	
	            	field: 'opt', title:'操作',align: 'center',width: 80,formatter: function (value, row, index) {
	                    var str = "";
	                    str += '<a href="javascript:void(0);" onclick="open_ed('+index+')">编辑</a>';
// 	                    str += '<a href="javascript:void(0);"<input type="button" onclick="/* deletes('+row.id+') */">|删除</a>';
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
			title : '添加班级',
			width : 500,
			height : 300,
			top:($(window).height() - 420) * 0.5,
	        left:($(window).width() - 450) * 0.4, 
			modal : true,
			shadow : true,
			closed:true,
			resizable : false
		}); 
	}
	//add给老师下拉框
	function getTeacher(){
		$('#teacher_name').combobox({
			required:true,    
		    multiple:true,//定义是否支持多选。
		    multivalue:true,
			url : 'teacher/getState',
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',	
			editable:false,			
			formatter: function (row) { //formatter方法就是实现了在每个下拉选项前面增加checkbox框的方法
               var opts = $(this).combobox('options');
               return '<input type="checkbox" class="combobox-checkbox" hidden="true">' + row[opts.textField]
            },  
        });
	}
	
	
// 	编辑
	function open_ed(index){
		var row = $('#listData').datagrid('getData').rows[index];
		$("#cId").val(row.id);
		$("#cname").textbox("setValue",row.name);
		$("#beginTime").datebox("setValue",row.beginTime);
		$("#endTime").datebox("setValue",row.endTime);
		
		$('#teacher_name2').combobox({
			required:true,    
		    multiple:true,//定义是否支持多选。
		    multivalue:true,
			url : 'teacher/getState',
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',	
			editable:false,			
			formatter: function (row) { //formatter方法就是实现了在每个下拉选项前面增加checkbox框的方法
               var opts = $(this).combobox('options');
               return '<input type="checkbox" class="combobox-checkbox" hidden="true">' + row[opts.textField]
            },  
        });
		
		 var teacherIds=[];
         $.each(row.teacher, function(index, item){
         	teacherIds.push(item.id);
         });
         $("#teacher_name2").combobox("setValues",teacherIds);
		$("#w").window('open');
}	
	//更改
	function updateOrAddDate(form,methods){
		var data = $("#"+form).serialize();
		if(form =='classroom_add_form'){
			if($('#classroom_name').textbox('getValue')==''){
				alert("班级不能为空")
			}else if($('#teacher_name').combobox('getValue')==null){
				
				alert("老师不能为空")
			}else if($('#beginTime2').datebox('getValue')==''){
				alert("创建时间不能为空")
			}else{
				$.ajax({
					url: "classroom/"+methods,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromClassroomDate').form('clear');
						$('#classroom_add_form').form('clear');
					}
			    	   
				});
			}
		}else{
			if($('#cname').textbox('getValue')==''){
				alert("班级不能为空")
			}else if($('#beginTime').datebox('getValue')==''){
				alert("创建时间不能为空")
			}else{
				$.ajax({
					url: "classroom/"+methods,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromClassroomDate').form('clear');
						$('#classroom_add_form').form('clear');
					}
			    });
			}
		}
		
	}
	
//	删除
	function deletes(id){
		if(confirm('确认删除')==false){
			return false;
		}else{
			$.ajax({
				type:"post",
				url:"classroom/del",
				data:{
					"id" : id
				},
				success : function(){
					$('#listData').datagrid('reload');
					
				}
			})
		}	
		
	}	
</script>
</html>