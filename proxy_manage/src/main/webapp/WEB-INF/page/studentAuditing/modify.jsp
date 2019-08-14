<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="static/css/all.css" type="text/css" />
<link rel="stylesheet" href="static/css/default.css" type="text/css" />
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"
	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"
	type="text/css" />
<link rel="stylesheet" href="static/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css"
	type="text/css" />


<title></title>

<style type="text/css">

	.fb{
		margin-left:30px;
		margin-top:10px;
		font-size:12px
	}
 	#ticketVoucherDiv .filebox{ 
		margin-left:110px
 		} 

</style>


</head>
<body>

<div>
	<form id="searchForm" action="" style="margin-top: 10px;margin-left:20px;">
		<div style="margin-bottom:20px;margin-left:20px">
			<label>学生姓名：</label><input type="text" name="studentName" style="margin-right:15px" class="easyui-textbox" >
			<label>班级：</label><input  name="className"  style="margin-right:15px" class="easyui-textbox">
			<label>来校时间：</label>
			<input  style="width: 126px; height: 22px" class="easyui-datebox" name="startTime">
			<label id="times_err">--</label>
			<input style="width: 126px; height: 22px" class="easyui-datebox" name="endTime">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input  value="查询" class="easyui-linkbutton"  type="button"   style="width:70px;height:25px"   onclick="search()" >
		<a href="javascript:void(0)"  style="margin-left:10px"   onclick="clean()"   class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  >条件清空</a>
		</div>
	</form>
</div>

<div style="margin-top: 20px">
<table id="listData"></table>
</div>

<div id="modifWin" class="easyui-window"  closed="true" style="width:600px;height:500px" >
	  <form id="modifyForm"  >
		<input  type="hidden"  name="student.id"  id="studentId"  >
		
		<div style="width:230px;height:200px;margin:10px 0px 0px 30px;float:left">
		
		<label class="fb">&nbsp;&nbsp;&nbsp;学生姓名：</label>  
	    <label id="studentName"  style="font-size:12px" ></label> <br>
		
		<label  class="fb"  style="margin-left:40px">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</label>
		<label id="classroom"  style="font-size:12px" ></label> <br>
		
		<label  class="fb" >&nbsp;&nbsp;&nbsp;应缴学费：</label>
		<label id="yingFee"  style="font-size:12px" ></label> <br>
		
		<label class="fb">报销人姓名:</label>&nbsp;&nbsp;
		<input  name="actualObject" id="actualObject" class="easyui-textbox" style="width:100px"> <br>
		
		<label class="fb">&nbsp;&nbsp;&nbsp;应报金额：</label>
		<input  id="yingAmount" name="billAmount" class="easyui-numberbox" style="width:100px">

		<label  class="fb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>&nbsp;
		<input id="remarks" name="remarks" class="easyui-textbox"  style="width:99px"> 


		</div>
<!--第二框 -->
		<div style="width:200px;height:200px;margin-top:10px;float:left">
		
		<label  class="fb" >&nbsp;&nbsp;&nbsp;联系电话：</label>
		<label id="studentTel"  style="font-size:12px" ></label>
		
		<label class="fb">&nbsp;&nbsp;&nbsp;招生老师：</label>
		<label id="proxyTeacher"  style="font-size:12px" ></label>
		
		
		
		<label class="fb">&nbsp;&nbsp;&nbsp;实缴学费：</label>
		<label id="shiFee"  style="font-size:12px" ></label> <br>
		
		
		<label  class="fb">报销人电话:</label>
		<input  name="actualObjectTel" id="actualObjectTel" class="easyui-numberbox"  style="width:99px"> <br>
		
		
		<label  class="fb">&nbsp;&nbsp;实报金额：</label>
		<input  id="shiAmount" name="amountReported"  class="easyui-numberbox" style="width:99px">  <br>
		</div>
		<div  style="clear:both"></div>
		<!--报销凭证 -->
		<div class="row" style="width:77%;border:1px solid #95B8E7;margin-left:50px;margin-top:10px;height:250px;margin-bottom:10px;position:relative" id="ticketVoucher" >	
			<div style="border:none;" class="ticketVoucherDiv" id="ticketVoucherDiv">
					<h3  style="text-align: center;line-height: 70px;font-weight: 900;color:#6d88ac">报销凭证</h3>
					<input    style="width:180px;height:40px;margin-right:20px"   class="easyui-filebox"   id="files"   buttonText="添加"    data-options="prompt:'请选择文件...'"/>	
					<img alt="图片"    style="width:100%;height:248px;margin:0 auto;display:none"  id="img1">
					<img alt="" src="static/images/cuo3.png" class="ccd" style="position:absolute;top:0px;right:0px;width:30px;display:none">
			</div >
		</div>
		<div style="text-align:center">
		<a href="script:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'"  style="width:100px;height:35px"  onclick="update()">确认修改</a>
		</div>
		
		<br><br>
		<div style="text-align: center;margin-bottom: 10px;margin-left: 28px">
		 	<table border="1" style="width: 90%">
		 		<tr id="tr2"><td>审核人</td><td>审核时间</td><td>审核状态</td><td>备注</td></tr>
		 	</table>
		</div>
		
	</form>
</div>


</body>
<script type="text/javascript">
	var pic='';
	$(function(){
		init();
	})
	
	function init(){
		$('#listData').datagrid({
			url:'unaudited/list?id=1',	//跳转地址
			titlr:"修改",		
			height : window.innerHeight - 100,
			fitColumns:true,			//列自适应
			pagination:true,			//是否有分页
			singleSelect:true,			//是否单行选择
			pageSize:10,					//页大小，一页多少条数据
			pageNumber:1,				//默认当前页码
			pageList:[5,10,20],			//一页可显示的数据条数
			nowrap:false,				//禁止文字自动换行
			loadMsg:'正在加载信息...',		//当数字没有加载出来时显示的文字
			queryParams:{},				//往后台传递数据，json格式
	        columns: [[
	   				{field:'student.id',title:'studentId',hidden:true,formatter:function(value, row, index){ return row.student.id}},    
	   				{field:'studentName',title:'学生姓名',width:80,align:'center',formatter:function(value, row, index){ return row.student.name}},    
	   				{field:'className',title:'班级',width:80,align:'center',formatter:function(value, row, index){ return row.student.classroom.name}},   
	   				{field:'tel',title:'联系方式',width:80,align:'center',formatter:function(value, row, index){ return row.student.tel}},    
	   				{field:'joinTime',title:'来校时间',width:80,align:'center',formatter:function(value, row, index){  return row.student.joinTime}},    
	   				{field:'tuition',title:'应缴学费(￥)',width:80,align:'center',formatter:function(value, row, index){ return row.student.tuition}},  
	   				{field:'amount',title:'实缴学费(￥)',width:80,align:'center',formatter:function(value, row, index){ if(row.amount==null)return 0 ; else return row.amount}},  
	   				{field:'billAmount',title:'应报金额(￥)',width:80,align:'center'},  
	   				{field:'amountReported',title:'实报金额(￥)',width:80,align:'center'},  
	   				{field:'auditState',title:'状态',width:80,align:'center',formatter : function(value, row, index) {
	   					if (value == 1) {return "待编辑";}
	   				}},  
	   				{field:'remarks',title:'备注',width:80,align:'center'},  
	   				{field:'opt',title:'操作',width:80,align:'center',formatter:opt},
	           ]]
		})
	}
	
	function opt(value,row,index){
		var str='<a href="javascript:void(0);" onclick="modify('+row.student.id+');">修改</a>';
		return str;
	}
	
	
	function update(){
		
		if($('#actualObject').textbox('getValue')==''){ alert("报销人姓名不能为空"); return;}
		if($('#actualObjectTel').textbox('getValue')==''){ alert("报销人电话不能为空"); return;}	
		else if($('#actualObjectTel').textbox('getValue').length!=11){alert("电话格式不正确"); return;}
		if($('#yingAmount').textbox('getValue')==''){ alert("应报金额不能为空"); return;}
		if($('#shiAmount').textbox('getValue')==''){ alert("实报金额不能为空"); return;}
		if(pic==''||pic==null){ alert("报销凭证不能为空"); return;}
		var date = $("#modifyForm").serialize()+"&ticketVoucher="+pic;	
		$.ajax({
				url:"unaudited/update",
				data:date,
				success:function(){
					$('#files').textbox('setValue',''); pic='';
					$('#ticketVoucherDiv').children().css('display','block');
					$('#ticketVoucherDiv img').css('display','none');
					$('#files').css('display','none');
					$('#modifWin').window('close');
					$('#listData').datagrid('reload');
				}
			})
	}
	
	
	
	function modify(studentId){
		$('#modifWin').window({
			title:'修改信息'
		});
		$.ajax({
			type:"post",
			url:"unaudited/getById?id="+studentId,
			data:{"studentId":studentId},
			success:function(data){
				$("#studentId").val(data.student.id);
				$("#studentName").html(data.student.name);
				$("#actualObject").textbox('setValue',data.actualObject);
				$("#classroom").html(data.student.classroom.name);
				$("#proxyTeacher").html(data.proxyTeacher.name);
				$("#studentTel").html(data.student.tel);
				$("#actualObjectTel").textbox('setValue',data.actualObjectTel);
				$("#yingFee").html(data.student.tuition);
				  if(data.amount==null){$("#shiFee").html(0);}
				  else{ $("#shiFee").html(data.amount); }
				$("#yingAmount").textbox('setValue',data.billAmount);
				if(data.amount==null){
					$("#shiAmount").textbox('setValue',0);
				}else{
					$("#shiAmount").textbox('setValue',data.amount);
				}
				$("#remarks").textbox('setValue',data.remarks);
				pic=data.ticketVoucher;
				if(pic!=null && pic!='' ){
					$('#img1').attr('src',data.ticketVoucher); pic=data.ticketVoucher;
		       		$('#ticketVoucherDiv img').siblings().css('display','none');   	   
		       		$('#ticketVoucherDiv img').css('display','block');
				}else{
					$('#files').textbox('setValue','');
					$('#ticketVoucherDiv').children().css('display','block');
					$('#ticketVoucherDiv img').css('display','none');
					$('#files').css('display','none');
				}
				$("#ticketVoucher").attr("src",data.ticketVoucher);
				$('#tr2').nextAll().remove();
				for(var x=0;x<data.studentAuditings.length;x++){
					var str;
					var status =data.studentAuditings[x].auditStatus
					if(status==0){
						str="未审核"
					}else if(status==1){
						str="审核未通过"
					}else if(status==2){
						str="待报销"
					}else if(status==3){
						str="已报销"
					}else if(status==4){
						str="作废"
					}
					$('#tr2').after('<tr><td>'+data.studentAuditings[x].auditor+'</td>'+
							'<td>'+data.studentAuditings[x].auditTime+'</td>'+
							'<td>'+str+'</td>'+
							'<td>'+data.studentAuditings[x].remarks+'</td></tr>')
				};
				$('#modifWin').window('open');
			}
		})
				
	}
	
	function search(){
		var date = $('#searchForm').serialize();	

		$('#listData').datagrid({"url":"unaudited/list?id=1&"+date});
	}
	
	//身份证照片回显
	$('#files').filebox({
		onChange:function(value){
			if(value!='' && value != null){	
				var formData = new FormData();
				formData.append('file',$('#filebox_file_id_2').get(0).files[0]);
				$.ajax({   	      
					 		url :"unaudited/savePic",  
					        type :'POST', 
						    data : formData,		
						    cache: false,
						    processData: false,
						    contentType: false ,	       
					        success:function(date) {  			  
					    	   $('#img1').attr('src',date); pic=date;
					       		$('#ticketVoucherDiv img').siblings().css('display','none');   	   
					       		$('#ticketVoucherDiv img').css('display','block');
								
					       },  
					        });
					}
				}
			})
	
	//删除图片
		$('.ccd').click(function (){
				pic='';
				$('#files').textbox('setValue','');
				$('#ticketVoucherDiv').children().css('display','block');
				$('#ticketVoucherDiv img').css('display','none');
				$('#files').css('display','none');
	})
	
	function clean(){
	$('#searchForm').form('clear');
	$('#listData').datagrid({'url':'unaudited/list?id=1'});
	}
	
</script>
</html>