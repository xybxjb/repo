<%@ page language="java" import="java.util.ResourceBundle" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro" %>
<%
    ResourceBundle resource = ResourceBundle.getBundle("const");
%>
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
</head>
<style type="text/css">

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
}
input[type="number"] {
    -moz-appearance: textfield;
}
.headercss {
	margin-top: 20px;
	margin-left: 10px;
	margin-right: 10px;
	margin-bottom: 20px;
}
.easyui-filebox buttontext{
	margin-right: 25px;
    font-size: 23px;
    font-weight: 400;
}
</style>
<body>
	<div class="headercss">
		<form id="form_search" action="" method="post">
			<fieldset class="form_fieldset">
				<div>
					<label>学生：</label>
					<input  id="st_id" name="student.name"  class="easyui-textbox"/>
					<input type="button" value="查询 "  class="easyui-linkbutton" onclick="search();" style="width: 100px; height: 30px; margin-left: 30px;">
				</div>
			</fieldset>
		</form>
	</div>
	<table id="listData" ></table>
<!--编辑页面 -->
	<div id="w" class="easyui-window" title="信息修改" data-options="iconCls:'icon-save'" style="width:660px;height:540px;padding:10px;" closed="true">
		<form  id="fromStudentConversation">
			<input style="margin-top:5px" type="hidden" name="id" id="cId" >
			<div style="width:260px;height:150px;margin-top: 10px;margin-left:20px;float: left;">
				<input style="margin-top:5px" type="hidden" name="student.id" id="s_id" >
				<div class="editwindow"  style="padding-left:0px;">谈话学生：
					<input  id="student_Name" disabled="disabled"  class="easyui-textbox" />
				</div>
				<div class="editwindow" style="padding-left:0px;width: -2px">谈话老师：
					<input  id="t_name" name="teacherName"  class="easyui-textbox" style="width: 145px"/>
				</div>
				<div class="editwindow" style="padding-left:0px">谈话原因：
					<select id="reason2" class="easyui-combobox" name="reason" style="margin-top:5px;width: 145px">   
					    <option value=""></option>   
					    <option value="出勤">出勤</option>   
					    <option value="纪律违反">纪律违反</option>    
					    <option value="学习困难">学习困难</option>    
					    <option value="作业完成情况">作业完成情况</option>    
					    <option value="思想问题">思想问题</option>    
					</select>
				</div>
				<div class="editwindow" style="padding-left:0px;display: none;">照片名字:&nbsp;&nbsp;&nbsp;
					<input type="text" id="pic_id2"  name="pic" style="margin-top:5px" class="easyui-textbox"> 
				</div>
			</div>
			<div style="width:260px;height:150px;margin-top: 10px;margin-left:20px;float: left;">
				
				<div class="editwindow" style="padding-left:0px;">谈话时间:&nbsp;&nbsp;&nbsp;
					<input type="text" id="c_time"  name="conversationTime" style="margin-top:5px;" class="easyui-datebox"> 
				</div>
				<div class="editwindow" style="padding-left:0px">是否达到预期效果:
					<select id="expected2" class="easyui-combobox" name="expected" style="margin-top:5px;width:110px">   
					    <option value=""></option>   
					    <option value="1">是</option>   
					    <option value="2">否</option>    
					</select>
				</div>
				<div class="editwindow" style="padding-left:0px">学生态度：&nbsp;
					<select id="s_attitude" class="easyui-combobox" name="studentAttitude" style="margin-top:5px;width:140px">   
					    <option value=""></option>   
					    <option value="好">好</option>   
					    <option value="差">差</option>    
					</select>
				</div>
			</div>
			<!-- 上传谈话记录图片 -->
			<div class="row" style="width:80%;border:1px solid #95B8E7;margin-left:50px;height:220px;position: relative;top: 160px" id="card2">	
				<div  style="width:100%;height:180px;text-align: center;" class="card_div2" id="card_div2">
						<h3 style="font-weight: 900;color:#6d88ac;position:absolute;left: 170px" id="tan2">谈话记录照片</h3>
						<div style="position: absolute;bottom: 0px;left: 100px">
							<input  style="width:180px;height:40px;" class="easyui-filebox"  name="pic" buttonIcon="icon-man"  buttonAlign="right"  id="files2"   buttonText="修改图片"    data-options="prompt:'请选择文件...'"/>	
							<img alt="图片"  style="width:498px;height:220px;margin:0px -100px;display:none"  id="img2">
						</div>
						<!-- <img alt="" src="static/images/cuo3.png" class="ccd2" style="position:absolute;top:0px;right:0px;width:30px;display:none">  -->
				</div >
			</div> 
				<div class="editwindow" style="padding-left:40px">
					<input type="button" value="修改" class="test" onclick="updateOrAddDate('fromStudentConversation','update');"style="text-align:center;position: absolute;top:420px;left:38%;width:100px;margin-top:15px;height:30px">
				</div>
			
		</form>
	</div>
	<div id="add_win" class="easyui-window" title="添加谈话记录"  closed="true" style="width:660px;height:540px;padding:10px;" >
	
		<form action="studentConversation/AddStudentConversation" id="studentConversation_add_form" enctype="multipart/form-data" method="post">
			<div style="width:260px;height:150px;margin-top: 10px;margin-left:20px;float: left;">
				<div class="editwindow" style="padding-left:0px">谈话学生：&nbsp;&nbsp;&nbsp;
					<input  id="student_id" name="student.id"  class="easyui-textbox"/>
				</div>
				<div class="editwindow" style="padding-left:0px">谈话老师：&nbsp;&nbsp;&nbsp;
					<input  id="teacher_name" name="teacherName"  class="easyui-textbox"/>
				</div>
				<div class="editwindow" style="padding-left:0px">谈话原因：&nbsp;&nbsp;&nbsp;
					<select id="reason" class="easyui-combobox" name="reason" style="margin-top:5px;width: 145px">   
					    <option value=""></option>   
					    <option value="出勤">出勤</option>   
					    <option value="纪律违反">纪律违反</option>    
					    <option value="学习困难">学习困难</option>    
					    <option value="作业完成情况">作业完成情况</option>    
					    <option value="思想问题">思想问题</option>    
					</select>
				</div>
				<div class="editwindow" style="padding-left:0px;display: none;">照片名字:&nbsp;&nbsp;&nbsp;
					<input type="text" id="pic_id"  name="pic" style="margin-top:5px" class="easyui-textbox"> 
				</div>
			</div>
			<div style="width:260px;height:150px;margin-top: 10px;margin-left:20px;float: left;">
				<div class="editwindow" style="padding-left:0px">谈话时间:&nbsp;&nbsp;&nbsp;
					<input type="text" id="conversation_time"  name="conversationTime" style="margin-top:5px" class="easyui-datebox"> 
				</div>
				<div class="editwindow" style="padding-left:0px">是否达到预期效果:
					<select id="expected" class="easyui-combobox" name="expected" style="margin-top:5px;width:105px">   
					    <option value=""></option>   
					    <option value="1">是</option>   
					    <option value="2">否</option>    
					</select>
				</div>
				<div class="editwindow" style="padding-left:0px">学生态度：&nbsp;
					<select id="student_attitude" class="easyui-combobox" name="studentAttitude" style="margin-top:5px;width:140px">   
					    <option value=""></option>   
					    <option value="好">好</option>   
					    <option value="差">差</option>    
					</select>
				</div>
				
			</div>		
			<!-- 上传谈话记录图片 -->
			<div class="row" style="width:80%;border:1px solid #95B8E7;margin-left:50px;height:220px;position: relative;top: 160px" id="card">	
				<div  style="width:100%;height:180px;text-align: center;" class="card_div" id="card_div1">
						<h3 style="font-weight: 900;color:#6d88ac;position:absolute;left: 170px" id="tan1">谈话记录照片</h3>
						<div style="position: absolute;bottom: 100px;left: 150px">
							<input  style="width:180px;height:40px;" class="easyui-filebox"  name="pic" buttonIcon="icon-man"  buttonAlign="right"  id="files"   buttonText='添加 &nbsp;&nbsp&nbsp&nbsp'    data-options="prompt:'请选择文件...'"/>	
							<img alt="图片"  style="width:498px;height:220px;margin:-100px -150px;display:none"  id="img1">
						</div>
						<img alt="" src="static/images/cuo3.png" class="ccd" style="position:absolute;top:0px;right:0px;width:30px;display:none"> 
				</div >
			</div> 
			<div class="editwindow" style="padding-left:40px">
				<input type="button" value="提交" class="test" onclick="updateOrAddDate('studentConversation_add_form','AddStudentConversation');"style="text-align:center;position: absolute;top:420px;left:38%;width:100px;margin-top:15px;height:30px">
			</div>
		</form>
	</div>
	 <!-- 展示详情 -->
	<div id="studentConversation_pic" class="easyui-window" title="详情"  closed="true" style="width:600px;height:600px;padding:10px;">
			<p align="center" style="font-size: 25px"> 谈话记录详情表</p>
			<div style="width:260px;height:150px;margin-top: 10px;margin-left:20px;float: left;">
				<input style="margin-top:5px" type="hidden" name="student.id" id="s_id" >
				<div class="editwindow"  style="padding-left:0px">谈话学生：
					<span id="s_name3" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px">谈话老师：
					<span id="t_name3" ></span>
				</div>
				<div class="editwindow" style="padding-left:12px">谈话原因：
					<span id="reason3" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px;display: none;">照片名字:&nbsp;&nbsp;&nbsp;
					<span id="pic_id3" ></span>
				</div>
			</div>
			<div style="width:260px;height:150px;margin-top: 10px;margin-left:20px;float: left;">
				
				<div class="editwindow" style="padding-left:0px;">谈话时间:&nbsp;&nbsp;&nbsp;
					<span id="c_time3" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px">是否达到预期效果:
					<span id="expected3" style="margin-right: 17px"></span>
				</div>
				<div class="editwindow" style="padding-left:0px">学生态度：
					<span id="s_attitude3" style="margin-right: 42px"></span>
				</div>
			</div>
			<img alt="图片" src="" style="width: 400px;height: 300px;margin-left: 100px" id="ss_pic">
	</div>
</body>
<script type="text/javascript">
var imgurl = '<%= resource.getString("image_url")%>';
$(function () {
	init();
});

	function init(){
		$('#listData').datagrid({
	        url: 'studentConversation/getAll',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-135,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        pagination:true,
	        pageSize : 20,
	        pageNumber:1,
	        pageList:[20,40,60], 
	        singleSelect:true,
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[	        
	            {
	                field: 'student', title: '谈话学生', width: 60, align: 'center',formatter: function(value, row, index){
						return value.name;
	                }
	            },
	            {
	                field: 'teacherName', title: '谈话老师', width: 60, align: 'center'
	            },
	            {
	                field: 'reason', title: '谈话原因', width: 60, align: 'center'
	            },
	            {
	                field: 'studentAttitude', title: '学生态度', width: 60, align: 'center'
	            },
	            {
	                field: 'conversationTime', title: '谈话时间', width: 60, align: 'center'
	            },
	            {
	                field: 'expected', title: '是否达到预期效果', width: 60, align: 'center',formatter: function(value, row, index){
	                	if(value == 1){
	                		return "是";
	                	}
	                	if(value == 2){
	                		return "否";
	                	}
	                }
	            },
	            {	
	            	field: 'opt', title:'操作',align: 'center',width: 200,formatter: function (value, row, index) {
	                    var str = "";
	 
	                    str += '<a href="javascript:void(0);"  onclick="open_ed('+row.id+')"> 修改谈话记录 |</a>';	              
	                    str += '<a href="javascript:void(0);"  onclick="deletes('+row.id+')" >删除谈话记录 |</a>';
	                    str += '<a href="javascript:void(0);"  onclick="s_pic('+row.id+')">查看详情</a>';
	//	                	str += '<a href="javascript:void(0);"<input type="button" onclick="/* deletes('+row.id+') */">|删除</a>';
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
		// 下拉框获取老师姓名	
		$("#teacher_name").combobox({          
			//后台返回的 json 数据方法地址         
			url:'studentConversation/getAllName',
			valueField:'teacherName',//相当于option的value值
			textField:'teacherName',//相当于<option></option>之间的显示值 value:1000    //默认显示值
		});
		$("#student_id").combobox({          
			//后台返回的 json 数据方法地址         
			url:'student/getStudent',
			valueField:'id',//相当于option的value值
			textField:'name',//相当于<option></option>之间的显示值 value:1000    //默认显示值
			
	                
		});
	}
	function init_win(){
		$('#add_win').window({
			title : '添加谈话记录',
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
	
	// 下拉框获取学生姓名	
	/*  $("#st_id").combobox({          
		//后台返回的 json 数据方法地址         
		url:'student/getStudent',
		valueField:'id',//相当于option的value值
		textField:'name',//相当于<option></option>之间的显示值 value:1000    //默认显示值
		
                
	});  */
	// 下拉框获取老师姓名	
	/* $("#teacher_name,#t_name").combobox({          
		//后台返回的 json 数据方法地址         
		url:'studentConversation/getAllName',
		valueField:'teacherName',//相当于option的value值
		textField:'teacherName',//相当于<option></option>之间的显示值 value:1000    //默认显示值
		
                
	}); */
	
	// 照片回显
	$('#files').filebox({
		onChange:function(value){
			if(value!='' && value != null){	
				
				var formData = new FormData();
	/* 			console.log(formData); */
				formData.append('file',$('#filebox_file_id_4').get(0).files[0]);
				$.ajax({   	      
					 		url :"studentConversation/savePice",  
					        type :'POST', 
						    data : formData,		
						    cache: false,
						    processData: false,
						    contentType: false ,	       
					        success:function(data) {  		
					    	  /*  alert(data) */
					       	   $('#pic_id').textbox("setValue",data);
					    	   $('#img1').attr('src',imgurl+data);
					    	   $('#tan1').css('display','none');   
					    	   $('#img1').siblings().css('display','none');
					    	   $('#card_div1 img').css('display','block');
							},  
					   });
					}
				}
	});
	//删除图片
	$('.ccd').click(function (){

	var	index =$(this).parent().index('.card_div');
		if(index==0){
			$('#pic_id').textbox('setValue','');
			$('#card_div1').children().css('display','block');
			$('#img1').siblings().css('display','block');
			$('#img1').attr('src','');
			$('#card_div1 img').css('display','none');
			$('#files').css('display','none');
		}  		
});
	
//  修改图片
	$('#files2').filebox({
		onChange:function(value){
			if(value!='' && value != null){	
				
				var formData = new FormData();
	/* 			console.log(formData); */
				formData.append('file',$('#filebox_file_id_3').get(0).files[0]);
				$.ajax({   	      
					 		url :"studentConversation/savePice",  
					        type :'POST', 
						    data : formData,		
						    cache: false,
						    processData: false,
						    contentType: false ,	       
					        success:function(data) {  		
					    	  /*  alert(data) */
					       	   $('#pic_id2').textbox("setValue",data);
					    	   $('#img2').attr('src',imgurl+data);
					    	   $('#tan2').css('display','none');   
					    	   $('#card_div2 img').css('display','block');
							},  
					   });
					}
				}
	});
	$('.ccd2').click(function (){

		var	index =$(this).parent().index('.card_div2');
			if(index==0){
				$('#pic_id2').textbox('setValue','');
				$('#card_div2').children().css('display','block');
				$('#card_div2 img').css('display','none');
				$('#files2').css('display','none');
			}  		
	});
// 	编辑
	function open_ed(id){
		$.ajax({
			type:"post",
			data:{"id":id},
			url:"studentConversation/getById",
			success:function(data){
				$("#cId").val(data.id);
				$("#s_id").val(data.student.id);
				$("#student_Name").textbox("setValue",data.student.name);
				$("#t_name").textbox("setValue",data.teacherName);
				$("#reason2").combobox("setValue",data.reason);
				$("#s_attitude").textbox("setValue",data.studentAttitude);
				$("#c_time").datebox("setValue",data.conversationTime);
				$("#expected2").combobox("setValue",data.expected);
				$("#pic_id2").textbox("setValue",data.pic);
				$("#img2").attr("src",data.pic);
				$('#card_div2 img').css('display','block');
			}
		}) ;
		 $("#t_name").combobox({          
			//后台返回的 json 数据方法地址         
			url:'studentConversation/getAllName',
			valueField:'teacherName',//相当于option的value值
			textField:'teacherName',//相当于<option></option>之间的显示值 value:1000    //默认显示值
		}); 
		
		
		$("#w").window('open');
}	

// 查询
	function search(){
		var fields =$('#form_search').serializeArray();   
		
	    var params = $("#listData").datagrid('options').queryParams;  
	    $.each( fields, function(i, field){  
	    	/* alert(field.value); */
	        params[field.name] = field.value;   
	    });  
	    
	    $('#listData').datagrid('reload')
	    $('#form_search').form('clear');
	} 
	

	//更改+保存
	function updateOrAddDate(form,method){
		var data = $("#"+form).serialize();
	/* 	console.log(data) */
		if(form =='fromStudentConversation'){
				 if($("#t_name").textbox("getValue")==''){
				alert("谈话老师不能为空")
			}else if($("#reason2").combobox("getValue")==''){
				alert("谈话原因不能为空")
			}else if($("#s_attitude").textbox("getValue")==''){
				alert("学生态度不能为空")
			}else if($("#expected2").combobox("getValue")==''){
				alert("预期效果不能为空")
			}else {
				$.ajax({
					url: "studentConversation/"+method,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromStudentConversationDate').form('clear');
						$('#studentConversation_add_form').form('clear');
					}
			    	   
				});
			}
		}else{
			if($('#student_id').textbox('getValue')==''){
				alert("谈话学生不能为空")
			}else if($("#teacher_name").textbox("getValue")==''){
				alert("谈话老师不能为空")
			}else if($("#reason").combobox("getValue")==''){
				alert("谈话原因不能为空")
			}else if($("#student_attitude").textbox("getValue")==''){
				alert("学生态度不能为空")
			}else if($("#expected").combobox("getValue")==''){
				alert("预期效果不能为空")
			}else {
					$.ajax({
						url: "studentConversation/"+method,    //请求的url地址
						async: true, //请求是否异步，默认为异步，这也是ajax重要特性
						data: data,
						type: "post",   //请求方式
						success: function(req){
							$('#w').window('close');
							$('#add_win').window('close');
							$('#listData').datagrid('reload');
							$('#fromStudentConversationDate').form('clear');
							$('#studentConversation_add_form').form('clear');
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
				url:"studentConversation/del",
				data:{
					"id" : id
				},
				success : function(){
					$('#listData').datagrid('reload');
					
				}
			})
		}
	}	
// 查看图片详情
	function s_pic(id){	
		$.ajax({
			type:"post",
			data:{"id":id},
			url:"studentConversation/getById",
			success:function(data){
			/* 	alert(data.pic) */
				if(data.expected == 1){
					data.expected="是";
				}
				if(data.expected == 2){
					data.expected="否";
				}
				$("#ss_pic").attr("src",data.pic);
				$("#s_name3").html(data.student.name);
				$("#t_name3").html(data.teacherName);
				$("#reason3").html(data.reason);
				$("#s_attitude3").html(data.studentAttitude);
				$("#c_time3").html(data.conversationTime);
				$("#expected3").html(data.expected );
				$("#pic_id3").html(data.pic);
			}
		}) 		
		$("#studentConversation_pic").window('open');
}
</script>
</html>