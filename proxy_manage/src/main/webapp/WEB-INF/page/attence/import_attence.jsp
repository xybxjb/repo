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
<link rel="stylesheet" type="text/css" href="static/css/styles.css">
</head>
<body>
   <div class="wrapper">
		<div class="container">
			<h1>更新学生考勤</h1>
			<form id="import_attence_from" class="form">
				<button type="submit" id="login-button" onclick='javascript:automatic();return false'>点击更新</button>
			</form>
			<span class ="stylea" style="width:200px">
				每天凌晨两点会自动更新数据，如果因为网络原因，或者服务器没开启请点击这个按钮手动更新，其实一般没必要，反正这个两点没更，下个两点也会更啊，
                             更新数据的时候会从没有更新的那天开始一直更新到昨天，
			</span>
		</div>
	</div>

     <script type="text/javascript">

	  function automatic() {
		  $.messager.confirm('确认框', '更新过程中请保持网络畅通，并不要开关机。', function(r){
				if (r){
					 $('#import_attence_from').form('submit', {    
						    url:'attence/importAttenceAutomatic',    
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
						    		}
						    	}else{
						    	$.messager.show({
						    		title:'我的消息',
				    				msg:'更新失败，请检查是否是网络，IP地址，权限问题,,消息将在5秒后关闭。',
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