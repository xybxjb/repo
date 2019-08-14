<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String basePath=request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath+"/" %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="static/css/default.css" type="text/css" />
<link rel="stylesheet"	href="static/css/all.css" type="text/css" />
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css" />
<style type="text/css">
.suitionstate{
	appearance:none;
	-moz-appearance:none;
	-webkit-appearance:none;
	padding-right: 10px;
}
.outbox{
	margin-bottom:10px;
}
.headercss{	
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;    
	margin-bottom: 10px;
}
.div001{
	
    margin-left: 20px;
    margin-right: 20px;    
	
}
.jeiguo{
	margin-left: 10px;
	margin-right: 10px;
	margin-top: -5px;
}
.my_li01{
	list-style: none;
	width: 200px; 
	float: left;
	margin-top: 5px;
}
.my_li02{
	list-style: none;
 	margin-left:40px;
 	margin-right:10px;
	float: right;
	margin-top: 5px;
} 
</style>
<script type="text/javascript">
$(function () {
	init();
	$('#exportWin').window('close');
});


function init(){
	$('#dg').treegrid({    
	    url:'proxyCommissionDetails1/proxyCommissionTree',  
	    title: '名下各招生老师详情',
	   	iconCls: 'icon-save',
	    singleSelect:true,
	    idField:'id',  //关键字段来标识树节点，不能重复  
	    treeField:'name', //树节点字段，也就是树节点的名称
	   	fitColumns:true,//列自适应
	    rownumbers:true,//显示一个行号列
	    collapsible:false,//收起表格的内容
	   
	    height: window.innerHeight-160,
	    loadMsg: '数据加载中...',
	    animate:true,//在节点展开或折叠的时候是否显示动画效果
	    lines:true,//显示treegrid行 
	    columns: [[
	               
	               {field: 'id', title: 'id', width: 10, align: 'center', hidden: true },
	               {field: 'name', title: '姓名', width: 30},
	               {field: 'amount', title: '提成金额(元)', width: 10, align: 'center'},
	               {field: 'address', title: '区域地址', width: 20, align: 'center'},
	               {field: 'tel', title: '联系方式', width: 10, align: 'center'},
	               {field: 'rank', title: '级别', width: 10, align: 'center',formatter: function (value, row,index) {
	            	/* var xs = [];for (var p in row) {xs.push(p + ":=" + row[p]);}alert(xs.join('\n')); */
	            	if(row.ranks){return  row.ranks.name;}return ""; 	        
	               }}, 
	               {field: 'point', title: '点位', width: 10, align: 'center',   },        	
	               { field: 'opt', title: '操作', width: 20, align: 'center', formatter: function (value, row, index) {
	               	
	                   var str = "";
	                   if(row.id=='${teacherId}'){ str += '&nbsp;&nbsp;<a href="proxyCommissionDetails1/jump4?id='+row.id+'">学生详情</a>';}else
	                   {str += '<a href="proxyCommissionDetails1/jump3?id='+row.id+'">详情</a>&nbsp;&nbsp;|';
	                   str += '&nbsp;&nbsp;<a href="proxyCommissionDetails1/jump4?id='+row.id+'">学生详情</a>';}
	                   
	                   return str;
	               } }
	 	]],
      	onLoadSuccess:function(data){
        	$('#dg').treegrid('expandAll');//全部展开树节点
      	},
       	onLoadError : function(none) {
			alert("没有下级了！")
		}
	});
}

function outport(){
	$('#exportWin').window('open');
	$('#exportWin').window({
		title:'导出文件',
		modal:true
	});
	$.ajax({
		url:"proxyCommissionDetails1/export", 	
		 success:function(data){
				var str='<%=basePath+"/"%>'+data;
				$("#export").attr("href",str);
					
		} 
	})	
}
function cdup(id){
	/* $.ajax({
        url:"proxyCommissionDetails1/proxyCommissionSkip",
        type:"post",
        dataType : "json",
         success : function(data, textStatus, xhr) { */
        		window.history.back(-1);
         $('#dg').datagrid('reload')
        /* }, */
        /*  error : function(data, textStatus, xhr) {                
                setTimeout(function(){
                	alert("点击确定返回主页！");
                	window.location.href="commission/commissionSkip";}, 1); */
  	                /*  window.history.back();   */
  	                
        /* }  
	}); */
}
</script>
</head>
<body>
	<div>
		<div class="headercss" style="height: 110px; border: 1px solid #95B8E7;"> 
			<div style="text-align: center;"><h1 style="font-size: 20px"> ${name}老师${year}年${month}月份提成详情</h1></div>
			<div class="div001" style="height: 50px; border: 1px solid #95B8E7;">
				<ul>
					<li class="my_li01"><label style="font-size: 15px">当前月份提成：${money}元</label></li>
					<li class="my_li01"><label style="font-size: 15px">未提成金额：${hasNotPaid}元</label></li>					
				</ul>
				<ul>
					<li class="my_li02"><a href="proxyCommissionDetails1/jump6?id=${id}"><input type="button" style="border-radius :8px; background-color: #D2E0F2;font-size: 14px" value="返回上一级" /></a></li>
					<li class="my_li02"><input type="button" style="border-radius :8px; background-color: #D2E0F2;font-size: 14px" onclick="outport()" value="导出"/></li>
					<li class="my_li02"><a href="proxyCommissionDetails1/jump4?id=${teacherId}"><input type="button" style="border-radius :8px; background-color: #D2E0F2;font-size: 14px" value="查看学生详情"/></a></li>
				</ul>
			</div>
		</div>
		<div class="jeiguo" >
			<table id="dg">
			</table>
		</div>
		<div id="exportWin" class="easyui-window" style="width: 200px;height: 150px">
		  	    <a id="export" href="javascript:void(0)">导出到本地</a>
		</div>
	</div>
</body>
</html>