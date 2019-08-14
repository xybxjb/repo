<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
%>
<%@page import="cn.deepcoding.model.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="static/css/all.css"	type="text/css" />
<link rel="stylesheet"	href="static/css/default.css"	type="text/css" />
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"	src="static/js/student.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css" />
<link rel="stylesheet" href="static/css/bootstrap.min.css" type="text/css" />
<title>Insert title here</title>


<script type="text/javascript">
$(function(){
	init();
});
	function init(){
		$('#listData').datagrid({
	        url: 'proxyTeacherAuditing/ListAll',//请求方法的地址
	        title: '查询结果',
	         height: window.innerHeight-160, 
	        /* height:350, */
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        pagination:true,
	        pageNumber:1,
	        singleSelect: true,//是否单行选择
	        pageSize:10,//页大小，一页多少条数据
	        pageList:[10,30,50],
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[
	            { field: 'id', title: 'id', width: 40, align: 'center',hidden:true},
	            {
	                field: 'name', title: '姓名', width: 80, align: 'center'
	            },
	            {
	                field: 'address', title: '地址', width: 80, align: 'center'
	            },
	            {
	                field: 'tel', title: '电话', width: 80, align: 'center'
	            },
	            {
	                field: 'datetime', title: '来访时间', width: 130, align: 'center'/* ,formatter:function(value,row,index){
	                	if(row.datetime!=null){
	                		var date=new Date(row.datetime);
	                		return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+
	                		date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	                	}else
	                		return '';
	                }  */
	            },
	            {
	                field: 'reimAmount', title: '需报销总金额(元)', width: 80, align: 'center'
	            },
	            {
	                field: 'purpose', title: '来访目的', width: 80, align: 'center'
	            },
	            {
	            	field: 'auditState', title:'状态',align: 'center',width: 80 /* ,formatter:function(value,row,index){
	            		if(row.auditState==0){return "待审核"}if(row.auditState==1){return "审核未通过"}if(row.auditState==2){return "待报销"}
	            		if(row.auditState==3){return "已报销"}
	            	} */
	            },
	            {	
	            	field: 'opt', title: '详情', align: 'center',width: 80,formatter:opt
	            }
	        ]]
	    });
	}
	 
	function getdatetime(value, row, index){
		if(row.visitRecords){
			 for(var i=0;i<row.visitRecords.length;i++){
				return row.visitRecords[i].datetime;
			} 
		}else{
			return '';
		}
	}
	function getpurpose(value, row, index){
		if(row.visitRecords){
			 for(var i=0;i<row.visitRecords.length;i++){
				return row.visitRecords[i].purpose;
			} 
		}else{
			return '';
		}
	}
	function gettrans(value, row, index){
		if(row.visitRecords){
			 for(var i=0;i<row.visitRecords.length;i++){
				 for(var j=0;j<row.visitRecords[i].visitFee.length;j++){
					return row.visitRecords[i].visitFee[j].trans.name;
				 }
				} 
		}else{
			return '';
		}
	}
	function getactualAmount(value, row, index){
		if(row.visitRecords){
			 for(var i=0;i<row.visitRecords.length;i++){
				 for(var j=0;j<row.visitRecords[i].visitFee.length;j++){
					return row.visitRecords[i].visitFee[j].actualAmount;
				 }
				} 
		}else{
			return '';
		}
	}
	function getticketAmount(value, row, index){
		if(row.visitRecords){
			 for(var i=0;i<row.visitRecords.length;i++){
				 for(var j=0;j<row.visitRecords[i].visitFee.length;j++){
					return row.visitRecords[i].visitFee[j].ticketAmount;
				 }
				} 
		}else{
			return '';
		}
	}
	 
	function show (value,row,index){
		if(value){
			return value.name;
		}else{
			return '';
		}
    	
    }
	
	function opt(value,row,index){
		 var str = "";
		    str += '<a href="javascript:void(0);" onclick="xiangqing('+row.id+')">审&nbsp;核</a>';
		    return str;
	}
	function search(){
		var fields =$('#search_form').serializeArray();  
	    var params = $("#listData").datagrid('options').queryParams;  
	    $.each( fields, function(i, field){  
	        params[field.name] = field.value;   
	    });  
	    
	    $('#listData').datagrid('reload')
	}
	function xiangqing(id){
		$.ajax({
			type : "post",//请求方式
			data:{"id":id},
			url : "proxyTeacherAuditing/getById",
			async : true,
			success : function(data) {
					 $('#xiangqing_win').window({
						title : '详情',
						width: "500px", 
						
					}); 
					$('#xiangqing_win').window('open');
					$('#id').val(data.id);
					$('#name').val(data.name);
					$('#sex').val(data.sex);
					$('#address').val(data.address);
					$('#tel').val(data.tel);
					$('#idcard').val(data.idCard);
					$('#datetime').val(data.datetime);
					$('#purpose').val(data.purpose);
					$('#tr1').nextAll().remove();
					 for(var i=0;i<data.visitFees.length;i++){
					$('#tr1').after('<tr><td>'+data.visitFees[i].trans.name+'</td>'+
							'<td>'+data.visitFees[i].ticketAmount+'</td>'+
							'<td>'+data.visitFees[i].actualAmount+'</td>'+
							'<td><a onclick="picture('+i+')"  href="javascript:void()">查看凭证</a>'+
							'<input type="hidden" id="picture'+i+'" value="'+data.visitFees[i].reimbVoucher+'"/>'+
							'</td></tr>');
					
					} ;
					$('#tr2').nextAll().remove();
					for(var x=0;x<data.teacherAuditing.length;x++){
						$('#tr2').after('<tr><td>'+data.teacherAuditing[x].auditor+'</td>'+
								'<td>'+data.teacherAuditing[x].auditTime+'</td>'+
								'<td>'+data.teacherAuditing[x].auditStatus+'</td></tr>')
					};
					$('#auditTime').datetimebox({    
					    required: true,    
					    showSeconds: true   
					}); 
				 	
					  

			}
		});
	}
	function picture(i){
		var str="picture"+i;
		console.log(str);
		var a = document.getElementById("picture"+i).value;
	    if(a!=null&&a!=""){
	    	var u = '<%=basePath + "/"%>'+a;
	    	$("#picture_win").children().remove();
	    	$("#picture_win").append("<img src='"+u+"' style='width:400px;height:300px;'/>")
	    	$("#picture_win").window("open");
	    } else{
	    	alert("暂无报销凭证   ==！")
	    }
	}
</script>
<style>
	

</style>
</head>
<body>
	
	<div class="headercss">
			<form id="search_form" action="" method="post" >
				<fieldset class="form_fieldset">
					<div class="searchdiv" style="width:200px;margin-top: 3px;height: 10px">
						<label class="ui_font">姓名：</label>
						<input  type="text" name="name" class="easyui-textbox" style="width: 120px">
					</div>
					<div class="searchdiv" style="width:200px;margin-top: 3px;height: 10px">
						<label class="ui_font">联系方式 ：</label>
						<input  type="text" name="tel" class="easyui-textbox"style="width: 120px">
					</div>
					<div class="searchdiv" style="width:200px;margin-top: 3px;height: 10px">
						<label class="ui_font">身份证号 ：</label>
						<input  type="text" name="idCard" class="easyui-textbox"style="width: 120px">
					</div>
					<div class="searchdiv" style="width:420px;margin-top: 0px;height: 10px">
						<label class="ui_font">来访时间 ：</label>
						<input class="easyui-datetimebox" name="startTime"     
       						 data-options="required:true" style="width:150px"><label >&nbsp;&nbsp;至：</label> 
       						 <input class="easyui-datetimebox" name="endTime"     
       						 data-options="required:true" style="width:150px"> 
					</div>
					
					<div class="searchbutton" style="float: right">
						
						<input type="button" value="查询 " onclick="search();">
					</div>
				</fieldset>
			</form>
	</div>
	<table id="listData"></table>
	<div id="picture_win" class="easyui-window" title="凭证图片" closed="true" style="width: 500px;height: 400px;"></div>
	<div id="xiangqing_win" class="easyui-window" closed="true" style="position: relative; top: 10px;overflow: hidden " >
		<form id="xiangqingForm" action="proxyTeacherAuditing/save1">
			<input id="id" type="hidden"  name="id" />
			<div style="text-align: left;position:relative;left:27px; margin-bottom: 10px">
				<label>姓名：</label><input id="name" type="text" style="border: none;"readonly="readonly"/>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：</label><input id="sex" type="text" style="border: none"readonly="readonly"/>
			</div>
			<div style="text-align: left;position:relative;left:27px;margin-bottom: 10px">
				<label>地址：</label><input id="address" type="text" style="border: none" readonly="readonly"/>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：</label><input id="tel" type="text" style="border: none"readonly="readonly"/>
			</div>
			<div style="text-align: center;margin-bottom: 10px">
				<label>身份证号：</label><input id="idcard" type="text" style="border: none" readonly="readonly"/>
				<label>来访时间：</label><input id="datetime" type="text" style="border: none" readonly="readonly"/>
			</div>
			<div style="text-align: left;margin-bottom: 10px;margin-left: 28px;">
				<label>来访目的：</label><input id="purpose" type="text" style="border: none" readonly="readonly"/>
				
			</div>
			 <div id="tableone" style="text-align: center;margin-bottom: 10px;margin-left: 28px">
			 	
					<table border='1' style="width: 90%">
						<tr id="tr1"><td>来访方式</td><td>车票金额</td><td>实报金额</td><td>报销凭证</td></tr>
					
					</table>
				
			</div>
			<div style="text-align: center;margin-bottom: 10px;margin-left: 28px">
			 	<table border="1" style="width: 90%">
			 		<tr id="tr2"><td>审核人</td><td>审核时间</td><td>审核状态</td></tr>
			 	</table>
				
			</div> 
			  <%--  <div style="text-align: center;margin-bottom: 10px">
			  <%User user=(User)request.getSession().getAttribute("user"); %>
				<label>&nbsp;&nbsp;&nbsp;审核人：</label><input id="auditor" type="text"  name="auditor" style="border: none" value="<%=user.getName()%>" readonly="readonly"/>
			</div>   --%>
			<div style="text-align: center;margin-bottom: 10px">
				<label>审核时间：</label>
				<input class="easyui-datetimebox" name="auditTime" id="auditTime"  style="width:150px">
			</div> 
			<input class="inp" type="submit" onclick="" value="同意" style="position: relative;left: 200px" />
		</form>
	</div>
</body>
</html>