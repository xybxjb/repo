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
	<div id="add_win" class="easyui-window" title="添加缴费类型"  closed="true" style="width:300px;height:180px;padding:10px;">
		<form  id="add_form">
			<div class="editwindow" style="padding-left:0px;padding-left: 10px">&nbsp;&nbsp;&nbsp;&nbsp;缴费类型：<input type="text" id="name"  name="name" style="margin-top:5px" class="easyui-textbox"> </div>
			<div class="editwindow" style="padding-left:0px;text-align: left;">需要到期时间：
			<select id="addDate" class="easyui-combobox" name="addDate" >  
				<option value='0' selected="selected">&nbsp;&nbsp;否&nbsp;&nbsp;</option>
				<option value='1'>&nbsp;&nbsp;是&nbsp;&nbsp;</option>
			</select>
			
			<!-- <input type="text" id="name"  name="addDate" style="margin-top:5px" class="easyui-combobox">  --></div>
			<div class="editwindow" style="padding-left:33px"><input type="button" value="保存" class="test" onclick="updateOrAddDate('add_form','save');"></div>
		</form>
	</div>
</body>
<script type="text/javascript">
$(function () {
	init();
});

	function init(){
		$('#listData').datagrid({
	        url: 'feeType/find',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-100,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        /* pagination:true,
	        pageNumber:1,
	        pageList:[10,30,50], */
	        singleSelect:true,
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[	        
	            {
	                field: 'name', title: '缴费类型', width: 80, align: 'center'
	            },
	            {
	                field: 'addDate', title: '是需要到期时间', width: 80, align: 'center',formatter: function (value, row, index) {
	                	if(value){
	                		return "是";
	                	}else{
	                		return "否";
	                	}
	                }
	            },
	            {	
	            	field: 'opt', title:'操作',align: 'center',width: 80,formatter: function (value, row, index) {
	                    var str = "";
	                    str += '<a href="javascript:void(0);" onclick="deletes('+row.id+')">删除</a>';
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

	//更改+保存
	function updateOrAddDate(form,method){
		//var classroom_name = $("#fromClassroomDate").textbox('getValue');
			if($('#name').textbox('getValue')==''){
				$.messager.alert("警告","缴费类型不能为空")
			}else{
				var data = $("#"+form).serialize();
				$.ajax({
					url: "feeType/"+method,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#add_form').form('clear');
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
				url:"feeType/delete",
				data:{"id" : id},
				success : function(data){
					if(data.code==1){
						alert('此数据已被使用，不能删除');
					}else{
						$('#listData').datagrid('reload');
					}
					
				}
			})
		}
		
	}	
</script>
</html>