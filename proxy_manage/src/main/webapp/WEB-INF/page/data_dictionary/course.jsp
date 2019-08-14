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
	<div id="w" class="easyui-window" title="信息修改" data-options="iconCls:'icon-save'" style="width:320px;height:230px;padding:10px;" closed="false">
		<form  id="fromCourseDate">
			<input style="margin-top:5px" type="hidden" name="id" id="cId" >
			<div class="editwindow" style="padding-left:20px">课&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 程：<input style="margin-top:5px" class="easyui-textbox" name="name" id="cname"> </div>
			<div class="editwindow" style="padding-left:20px">隶属专业：<input type="text" id="major_name2"  name="major.id" style="margin-top:5px" class="easyui-combobox"> </div>
			<div class="editwindow" style="padding-left:20px">译&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 名：<input id="cinputName" name="inputName" style="margin-top:5px" class="easyui-textbox"> </div>
			<div class="editwindow" style="padding-left:40px"><input type="button" value="保存" class="test" onclick="updateOrAddDate('fromCourseDate','update');"style="text-align:center;position: absolute;left:33%;width:100px;margin-top:15px;height:30px"></div>
		</form>
	</div>
<!-- 	添加页面 -->
	<div id="add_win" class="easyui-window" title="添加专业"  closed="true" style="width:364px;height:200px;padding:10px;">
		<form action="course/add" id="course_add_form">
			<div class="editwindow" style="padding-left:40px">课&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 程：<input type="text" id="course_name"  name="name" style="margin-top:5px" class="easyui-textbox"> </div>
			<div class="editwindow" style="padding-left:40px">隶属专业：<input type="text" id="major_name"  name="major.id" style="margin-top:5px" class="easyui-combobox"> </div>
			<div class="editwindow" style="padding-left:40px">译&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 名：<input type="text" id="inputName" name="inputName" style="margin-top:5px" class="easyui-textbox"> </div>
			<div class="editwindow" style="padding-left:60px"><input type="button" value="保存" class="test" onclick="updateOrAddDate('course_add_form','add');"></div>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function () {
	init();
	getMajor();
	
});

	function init(){
		$('#listData').datagrid({
	        url: 'course/getAll',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-100,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        /* pagination:true,
	        pageSize : 20,
	        pageNumber:1,
	        pageList:[20,40,60], */
	        singleSelect:true,
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[	        
	            {
	                field: 'name', title: '课程名称', width: 80, align: 'center'
	            },
	            {
	                field: 'major', title: '隶属专业', width: 80, align: 'center',formatter: function (value, row, index) {
	                	
	                	return value.name;
	                }
	            },
	            {
	                field: 'inputName', title: '译文', width: 80, align: 'center'
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
			title : '添加课程',
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
	//给add 页面 专业下拉列表
	function getMajor(){
		$.ajax({
			type:"post",
			url:"major/getAll",
			success:function(data){
				$('#major_name').combobox({
					data: data,
					valueField:"id",
					textField:"name"
				})
				/* $('#major_name2').combobox({
					data: data,
					valueField:"id",
					textField:"name"
				}) */
				
			}
			 
		}) 
		
	}
// 	编辑
	function open_ed(index){
		var row = $('#listData').datagrid('getData').rows[index];
		$("#cId").val(row.id);
		$("#cname").textbox("setValue",row.name);
		$("#cinputName").textbox("setValue",row.inputName);
		$('#major_name2').combobox({
			url : 'major/getAll',
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',	
			editable:false,			
        });
		$("#major_name2").combobox("setValue",row.major.id);
		$("#w").window('open');
}	
	//更改+保存
	function updateOrAddDate(form,method){
		//var classroom_name = $("#fromClassroomDate").textbox('getValue');
		var data = $("#"+form).serialize();
		if(form =='course_add_form'){
			if($('#course_name').textbox('getValue')==''){
				alert("课程不能为空")
			}else if($('#major_name').combobox('getValue')==''){
				alert("隶属专业不能为空")
			}else if($('#inputName').textbox('getValue')==''){
				alert("译名不能为空")
			}else{
				$.ajax({
					url: "course/"+method,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromCourseDate').form('clear');
						$('#course_add_form').form('clear');
					}
			    	   
				});
			}
		}else{
			if($('#cname').textbox('getValue')==''){
				alert("课程不能为空")
			}else if($('#cinputName').textbox('getValue')==''){
				alert("译名不能为空")
			}else{
				$.ajax({
					url: "course/"+method,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromCourseDate').form('clear');
						$('#course_add_form').form('clear');
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
				url:"course/del",
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