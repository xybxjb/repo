<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" deferredSyntaxAllowedAsLiteral="true"%>
<%

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="icon" sizes="any" mask="" href="static/images/icon.png">
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css" />
<link rel="stylesheet"	href="static/css/default.css"	type="text/css" />
</head>
<body>
    <a href="attence/bindingAttence">如果查询不到该学生可能是未绑定该学生钉钉用户唯一标识请先绑定</a>
    <table id="attence_table"></table>  
     <div id="attence_window">
        <table id="attence_window_table"></table>
     </div>
    <script type="text/javascript">
 	$('#attence_table').datagrid({    
 	    url:'attence/showClassAttenceCount',  
 	    striped: true,
 	    fitColumns: true,
 	    pagination:true,
 	    pageSize:20,
 	    pageList:[20,40,60],
 	    singleSelect:true,
 	    queryParams: {},
 	    columns:[[    
            {field:'userId',title:'学生姓名',hidden:true},   
 	        {field:'studentName',title:'学生姓名',width:200,align:'center',halign:'center'},    
 	        {field:'normal',title:'正常打卡次数',width:200,align:'center',halign:'center'},    
 	        {field:'early',title:'早退次数',width:200,align:'center',halign:'center'},   
 	        {field:'late',title:'迟到次数',width:200,align:'center',halign:'center'},    
 	        {field:'seriousLate',title:'严重迟到次数',width:200,align:'center',halign:'center'},    
 	        {field:'absenteeism',title:'矿工迟到次数',width:200,align:'center',halign:'center'},  
 	        {field:'notSigned',title:'未打卡次数',width:200,align:'center',halign:'center'},  
 	        {field:'asd',title:'操作',width:100,align:'right',
   	        	formatter: function(value,row,index){
   					return "<a  href='#' onclick='javascript:add_tab(this);return false' >查看详情</a>";
   				}
   	        }
 	    ]],
 	    toolbar: [{
 	    	  text:'<form action="" id="class_attence_count_form" method="post"> 输入查询日期<input id="workDateFrom"  name="workDateFrom"  type= "text""> </input>    -- <input id="workDateTo"  name="workDateTo"  type= "text"> </input>'
 	    	         +'  选择班级<input id="classname" class="easyui-textbox" style="width:130px" type="text" name="classname"></form>',  
 	    },'-',{
 	    	iconCls: 'icon-ok',
 	    	text:'查询',
 			handler: function(){
 				var workDateFrom = $("input[name='workDateFrom']").val();
 			 	var workDateTo = $("input[name='workDateTo']").val();
 			 	var classname = $("input[name='classname']").val();
 			 	var namespan = document.getElementById("sttence_apan");
 			 	if(workDateFrom!=null&&workDateFrom!==""&&workDateTo!=null&&workDateTo!==""&&classname!=null&&classname!==""){
 			 		  $.ajax({
 						type:"post",//请求方式
 						url:"attence/showClassAttenceCount",//请求url地址
 						data:{"workDateFrom":workDateFrom,"workDateTo":workDateTo,"classname":classname},
 						async:false ,//请求是否异常，默认为异常，这也是ajax重要性
 						success:function(data){
 							var fields =$('#class_attence_count_form').serializeArray(); 
 				    	    var params = $("#attence_table").datagrid('options').queryParams;  
 				    	    $.each( fields, function(i, field){  	
 				    	        params[field.name] = field.value;   
 				    	    });  
 				    	    $('#attence_table').datagrid('reload')
 							
 						}
 					});  
		    	    $('#workDateFrom,#workDateTo').datebox({    
		        	    required:true   
		        	}); 
		    	    namespan.innerHTML="";
 			 	}else{
 			 		
 			 		namespan.innerHTML="请输入所有信息";
 			 	}
 				    
 			}	 
 		},'-',{
 	    	text:'<span id="sttence_apan" style="color:red"><span>',
 		}]

 	});
 	
 	$('#workDateFrom,#workDateTo').datebox({    
 	    required:true   
 	});  
  
 	$('#classname').combobox({    
 	    url:'classroom/getAllBe',    
 	    valueField:'id',    
 	    textField:'name'   
 	});
 	$('#classname').validatebox({    
 		    required: true,    
 		    validType: 'email'   
   });


 function add_tab(obj) {
 	var userId = $(obj).parent().parent().siblings("[field='userId']").children().eq(0).html();
 	var workDateFrom = $("input[name='workDateFrom']").val();
 	var workDateTo = $("input[name='workDateTo']").val();
 	 $('#attence_window').window({    
 		    width:500,    
 		    height:345,
 		    draggable:false,
 		    resizable:false,
 		    shadow:true,
 		    title:'个人详情',
 		    openAnimation:'slide'
 		});
 	 $('#attence_window_table').datagrid({ 
 		    queryParams: {
 			   userId: userId,
		       workDateFrom: workDateFrom,
		       workDateTo: workDateTo,
 	     	},
 	 	    url:"attence/ShowWindowStudentAttence",
 	 	    fitColumns:true,
 	 	    striped: true,
 	 	    fitColumns: true,
 	 	    pagination:true,
 	 	    pageSize:10,
 	 	    pageList:[10,20,30],
 	 	    columns:[[    
 	    	        {field:'workDate',title:'工作日',width:102},    
 	    	        {field:'checkType',title:'考勤类型',width:102,formatter: function(value,row,index){
	    				if (value==0){
	    					return "上学";
	    				} else {
	    					return "放学";
	    				}
	    	        }},  
 	    	        {field:'baseCheckTime',title:'规定打卡时间',width:102}, 
 	    	        {field:'userCheckTime',title:'实际打卡时间',width:102}, 
 	    	        {field:'timeResult',title:'打卡结果',width:102,formatter: function(value,row,index){
	    				if (value==0){
	    					return "正常";
	    				} else if(value==1) {
	    					return "早退";
	    				} else if(value==2) {
	    					return "迟到";
	    				} else if(value==3) {
	    					return "严重迟到";
	    				} else if(value==4) {
	    					return "逃课迟到";
	    				} else if(value==5) {
	    					return "未打卡";
	    				}
	    	        }}, 
 		    	    ]],
 	 	});

      }
     </script>
       
</body>
</html>