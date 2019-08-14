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
    <div>
    <form action="" id="binding_attence_from">
         <a href="#"  onclick='javascript:binding_attence();return false' >请先点击自动添加学生钉钉用户唯一标识</a>
    </form>
  
    </div>
    <div  style="float: left;  width:520px ;margin:0px 100px 0px 0px" >
        <table id="binding_student"></table> 
    </div>
    <div  style="float: left;  width:700px" >
        <table id="binding_attence"></table> 
    </div>
    
      
     <script type="text/javascript">
     function add_tab(obj) {
    	 
    	 	var tel = $(obj).parent().parent().siblings("[field='tel']").children().eq(0).html();
    	 	var name = $(obj).parent().parent().siblings("[field='name']").children().eq(0).html();
    	 	/* var userId = $("input[name='userId']").val(); */
    	 	var userId = $(obj).parent().parent().siblings("[field='price']").children().children().val();
    	 	/* alert(userId2); */
    	 	/* if(userId.length==18){ */
    	 		$.ajax({
						type:"post",//请求方式
						url:"attence/addBindingStudentUserId",//请求url地址
						data:{"userId":userId,"name":name,"tel":tel},
				});		
    	 		$('#binding_attence').datagrid('reload'); 
    	 		$('#binding_student').datagrid('reload'); 
    	 	/* }else{
    	 		alert("请输入正确的userId");
    	 	} */
     }	 	
     
     $('#binding_attence').datagrid({    
		    url:'attence/ShowBindingAttence',    
		    singleSelect:true,
		    title: '学生钉钉标识列表',
		    columns:[[    
		        {field:'name',title:'姓名',width:100,align:'center',halign:'center'},
		        {field:'userId',title:'钉钉用户唯一标识',width:200,align:'center',halign:'center'},
		        {field:'departmentList',title:'部门',width:399,align:'center',halign:'center',
		        	formatter: function(value,row,index){
   					return value;
   				}},    
		    ]]    
		});
    	 $('#binding_student').datagrid({    
    		    url:'attence/ShowBindingStudent',    
    		    singleSelect:true,
    		    title: '手动添加重复学生唯一标识',
    		    columns:[[    
    		        {field:'name',title:'姓名',width:100,align:'center',halign:'center'},    
    		        {field:'tel',title:'电话',width:120,align:'center',halign:'center'},    
    		        {field:'price',title:'钉钉用户唯一标识',width:199,align:'center',halign:'center',
    		        	formatter: function(value,row,index){
    	   					return '<input  name="userId"  value="" />';
    	   				}
    		        },  
    		        {field:'asd',title:'操作',width:100,align:'right',align:'center',halign:'center',
    	   	        	formatter: function(value,row,index){
    	   					return "<a  href='#' onclick='javascript:add_tab(this);return false' >添加</a>";
    	   				}
    	   	        }
    		    ]]    
    		}); 
	  function binding_attence() {
		  $.messager.confirm('确认框', '绑定过程中请保持网络畅通，并不要开关机。', function(r){
				if (r){
					 $('#binding_attence_from').form('submit', {    
						    url:'attence/bindingAttenceAutomatic',    
						    success:function(data){  
						    	if(!isNaN(data)){
						    		if(data==0){
						    			$.messager.show({
						    				title:'我的消息',
						    				msg:'数据库已经是最新版本无需更新,消息将在5秒后关闭。',
						    				timeout:5000,
						    				showType:'slide'
						    			});
						    		}else{
						    			$.messager.show({
								    		title:'我的消息',
								    		msg:"数据库数据已经更新至最新版本，此次更新一共更新了"+data+"条数据，此消息将在5秒后关闭。",
								    		timeout:5000,
								    		showType:'show',
								    	});
						    			$('#binding_attence').datagrid('reload'); 
						    	 		$('#binding_student').datagrid('reload');
						    		}
						    	}else{
						    	$.messager.show({
						    		title:'我的消息',
				    				msg:'更新失败，请检查是否是网络，IP地址，权限问题,消息将在5秒后关闭。',
				    				timeout:5000,
				    				showType:'show'
						    	});
						    	}
						    }    
					});
				}
			});


		  
		  
	  }
</script> 
     
</body>
</html>