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
<style type="text/css">
button{
	/*background-color:  #00a1cf; Blue  */
    border: none;
    color: white;
    padding: 6px 11px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    border-radius: 7px;
	float: right; 
	background-color: white;
    color: black;
    border: 1px solid #00a1cf; /* Blue */
    outline:none;
	}
	
button:HOVER {
	background-color:  #00a1cf; /*Blue  */
	color: white;
}
</style>
<script type="text/javascript">
$(function(){
	init();
});
function init(){
	$("#listData").datagrid({
		    url: 'studentProxyTeacher/getAll',//请求方法的地址
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
	                field: 'studentName', title: '学生姓名', width: 100, align: 'center'
	            },
	            {
	                field: 'studentPhone', title: '学生联系方式', width: 100, align: 'center'
	            },
	            {
	                field: 'proxyTeacherName', title: '招生老师', width: 100, align: 'center'
	            },
	            {
	                field: 'proxyTeacherPhone', title: '老师联系方式', width: 100, align: 'center'
	            },{
	                field: 'remark', title: '备注', width: 100, align: 'center'
	            }
	           
	            ]],
	})
}
function clean(){
	$("#form_search").form("clear");
	init();
}
function search(){
	var fields =$('#form_search').serializeArray();   
	
    var params = $("#listData").datagrid('options').queryParams;  
    $.each( fields, function(i, field){  
    /* 	alert(field.value); */
        params[field.name] = field.value;   
    });  
    
    $('#listData').datagrid('reload')
} 
</script>
<body>
<div class="headercss">
	<fieldset class="form_fieldset">
		<form id="form_search" action="" method="post">
			<div>
				<label>学生姓名：</label>
				<input  id="studentName" name="studentName"  class="easyui-textbox"/>
				<label>学生联系方式：</label>
				<input  id="studentPhone" name="studentPhone"  class="easyui-textbox"/>
				<label>招生老师姓名：</label>
				<input  id="proxyTeacherName" name="proxyTeacherName"  class="easyui-textbox"/>
			</div>
		</form>
		<button style="margin-left: 10px" onclick="clean()">清空</button>
		<button  style="margin-left: 10px" onclick="search();">点击搜索</button>
	</fieldset>
</div>
<table id="listData"></table>
</body>
</html>