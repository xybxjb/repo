<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <table id="studentAttence_table"></table>
      
      <script type="text/javascript">
      $('#studentAttence_table').datagrid({ 
    	    url:"attence/ShowStudentAttence",
    	    striped: true,
    	    fitColumns: true,
    	    pagination:true,
    	    pageSize:20,
    	    pageList:[20,40,60],
    	    singleSelect:true,
    	    queryParams: {},
    	    columns:[[    
    	              
		    	        {field:'id',title:'id',hidden:true},    
		    	        {field:'userId',title:'班级',hidden:true},   
		    	        {field:'workDate',title:'工作日',width:220,align:'center',halign:'center'},    
		    	        {field:'checkType',title:'考勤类型',width:220,align:'center',halign:'center',formatter: function(value,row,index){
		    				if (value==0){
		    					return "上学";
		    				} else {
		    					return "放学";
		    				}
		    	        }		
		    	        },  
		    	        {field:'baseCheckTime',title:'规定打卡时间',width:220,align:'center',halign:'center'}, 
		    	        {field:'userCheckTime',title:'实际打卡时间',width:220,align:'center',halign:'center'}, 
		    	        {field:'timeResult',title:'打卡结果',width:220,align:'center',halign:'center',formatter: function(value,row,index){
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
		    	        }
		    	        }, 
		    	    ]],
    	    toolbar: [{
    	    	  text:'<form action="" id="studentAttence_form" method="post"> 输入查询日期<input  id="workDateFrom" name="workDateFrom"  type="text" ></input>     -- <input  id="workDateTo" name="workDateTo"  type="text" ></input> '+
    	    	    '学生姓名<input type="text" id="student_attence_studentName" name="studentName" onblur="selecttel()"></input>  手机号<input type="text" name="tel" id="student_attence_tel" class="easyui-combobox"/></form>',  
    	    },'-',{
    	    	 iconCls: 'icon-ok',
    	    	 text:'查询',    
    			 handler: function(){	
    				    var workDateFrom = $("input[name='workDateFrom']").val();
    	 			 	var workDateTo = $("input[name='workDateTo']").val();
    	 			 	var studentName = $("input[name='studentName']").val();
    	 			 	var tel = $("input[name='tel']").val();
    	 			 	var namespan = document.getElementById("sttence_apan");
    	 			 	if(workDateFrom!=null&&workDateFrom!==""&&workDateTo!=null&&workDateTo!==""&&studentName!=null&&studentName!==""&&tel!=null&&tel!==""){
    	 			 		 $.ajax({
    	 						type:"post",//请求方式
    	 						url:"attence/ShowStudentAttence",//请求url地址
    	 						data:{"workDateFrom":workDateFrom,"workDateTo":workDateTo,"studentName":studentName,"tel":tel},
    	 						async:false ,//请求是否异常，默认为异常，这也是ajax重要性
    	 						success:function(data){
    	 							var fields =$('#studentAttence_form').serializeArray(); 
    	 				    	    var params = $("#studentAttence_table").datagrid('options').queryParams;  
    	 				    	    $.each( fields, function(i, field){  	
    	 				    	        params[field.name] = field.value;   
    	 				    	    });  
    	 				    	    $('#studentAttence_table').datagrid('reload')
    	 						}
    	 					}); 
        		    	    $('#workDateFrom,#workDateTo').datebox({    
        		        	    required:true   
        		        	});
        		    	    namespan.innerHTML=""
    	 			 	}else{
    	 			 		
    	 			 		namespan.innerHTML="请输入所有信息"
    	 			 	}
    				     
    			}
    		},'-',{
     	    	text:'<span id="sttence_apan" style="color:red"><span>',
     		}]

    	});
        
    $('#workDateFrom,#workDateTo').datebox({    
  	   required:true
  	}); 
    $('#student_attence_studentName').validatebox({    
        required: true, 
        missingMessage:'姓名不能为空',
    });  
    $('#student_attence_tel').validatebox({    
        required: true,   
        missingMessage:'手机号不能为空',
    });
    function selecttel() {
    	var studentName = $("input[name='studentName']").val();
				$('#student_attence_tel').combobox({    
					url:"attence/selectTel?name="+studentName,
			        valueField: 'tel',    
			        textField: 'tel',
 				}); 
	}
     </script>
</body>
</html>