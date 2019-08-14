<%@ page language="java" import="java.util.ResourceBundle" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ResourceBundle resource = ResourceBundle.getBundle("const");
%>
 <%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
 		+ request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<base href="<%=basePath + "/"%>"></base>

<link rel="stylesheet"	href="static/css/default.css" type="text/css" />
<link rel="stylesheet"	href="static/css/all.css" type="text/css" />

<link rel="stylesheet" href="static/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css"/>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>

<title>APP首页轮播图</title>

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
</style>

<body>
	<div class="headercss">
		<form id="form_search" action="" method="post">
			<fieldset class="form_fieldset">
				<div>
					<label>图片名称：</label>
					<input  id="search_id" name="imageName"  class="easyui-textbox"/>
					<input type="button" value="查询 / 查询所有"  class="easyui-linkbutton" onclick="search();" style="width: 100px; height: 30px; margin-left: 30px;">
					<input type="button" value="一键清除使用图片"  class="easyui-linkbutton" onclick="clearAllUse();" style="width: 100px; height: 30px; margin-left: 30px;">
				</div>
			</fieldset>
		</form>
	</div>
	<table id="listData" ></table>
	
	<!--编辑图片信息弹框 -->
	<div id="w" class="easyui-window" title="图片信息修改" data-options="iconCls:'icon-save'" style="width:600px;height:540px;padding:10px;" closed="true">
		<form  id="formAppIndexCycleImage">
			<input style="margin-top:5px" type="hidden" name="id" id="cId" >
			<div class="editwindow" style="margin-top:30px;margin-left:50px">图片名称：
				<input  id="image_name2" name="imageName"  class="easyui-textbox"/>
			</div>
			<div class="editwindow" style="margin-left:50px">是否使用当前图片：
				<select id="image_use2" class="easyui-combobox" name="imageUse" style="width:95px">   
				    <option value="1">是</option>   
				    <option value="0">否</option>    
				</select>
			</div>
			<div class="editwindow" style="margin-left:50px;display:none">照片名字：
				<input type="text" id="pic_id2"  name="imageSrc" style="margin-top:5px" class="easyui-textbox"> 
			</div>
			<div class="editwindow" style="margin-left:56px;width:208px;height:25px">
				<input style="width:100%;height:100%;" class="easyui-filebox" name="imageSrc" buttonIcon="icon-man"  buttonAlign="right" id="files2" buttonText="替换图片&nbsp;&nbsp;" data-options="prompt:'请选择文件...'"/>
			</div>
			
			<!-- 上传轮播图图片 -->
			<div class="row" style="width:80%;border:1px solid #95B8E7;margin-left:56px;height:220px;position: relative;top: 30px" id="card2">	
				<div style="width:100%;height:180px;text-align: center;" class="card_div2" id="card_div2">
					<h3 style="font-weight: 900;color:#6d88ac;position:absolute;left: 165px" id="tan2">轮播图图片</h3>
					<div style="position: absolute;bottom: 80px;left: 135px">
						<img alt="图片"  style="width:453px;height:220px;margin:-81px -136px;display:none"  id="img2">
					</div>
				</div >
			</div> 
			<div class="editwindow" style="padding-left:40px">
				<input type="button" value="确认修改" class="test" onclick="updateOrAddDate('formAppIndexCycleImage','update');" style="text-align:center;position: absolute;top:400px;left:41%;width:100px;margin-top:35px;height:30px">
			</div>
		</form>
	</div>
	
	<!-- 添加图片信息弹框 -->
	<div id="add_win" class="easyui-window" title="添加轮播图图片信息"  closed="true" style="width:600px;height:540px;padding:10px;" >
		<form action="appIndexCycleImage/addAppIndexCycleImage" id="appIndexCycleImage_add_form" enctype="multipart/form-data" method="post">
			<div class="editwindow" style="margin-top:50px;margin-left:50px">图片名称：
				<input  id="image_name1" name="imageName"  class="easyui-textbox"/>
			</div>
			<div class="editwindow" style="margin-left:50px">是否使用当前图片：
				<select id="image_use1" class="easyui-combobox" name="imageUse" style="width:95px">   
				    <option value="1">是</option>   
				    <option value="0">否</option>    
				</select>
			</div>
			<div class="editwindow" style="margin-left:50px;display:none">照片名字：
				<input type="text" id="pic_id"  name="imageSrc" style="margin-top:5px" class="easyui-textbox"> 
			</div>
			
			<!-- 上传轮播图图片 -->
			<div class="row" style="width:80%;border:1px solid #95B8E7;margin-left:56px;height:220px;position: relative;top: 30px" id="card">	
				<div  style="width:100%;height:180px;text-align: center;" class="card_div" id="card_div1">
					<h3 style="font-weight: 900;color:#6d88ac;position:absolute;left: 165px" id="tan1">轮播图图片</h3>
					<div style="position: absolute;bottom: 80px;left: 135px">
						<input  style="width:180px;height:40px;" class="easyui-filebox"  name="imageSrc" buttonIcon="icon-man"  buttonAlign="right"  id="files"   buttonText="添加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"    data-options="prompt:'请选择文件...'"/>	
						<img alt="图片"  style="width:453px;height:220px;margin:-81px -136px;display:none"  id="img1">
					</div>
					<img alt="" src="static/images/cuo3.png" class="ccd" style="position:absolute;top:0px;right:0px;width:30px;display:none"> 
				</div >
			</div> 
			<div class="editwindow" style="padding-left:40px">
				<input type="button" value="提交" class="test" onclick="updateOrAddDate('appIndexCycleImage_add_form','addAppIndexCycleImage');" style="text-align:center;position: absolute;top:400px;left:41%;width:100px;margin-top:25px;height:30px">
			</div>
		</form>
	</div>
	
	 <!-- 查看图片详情弹框 -->
	<div id="parentConversation_pic" class="easyui-window" title="图片信息详情"  closed="true" style="width:600px;height:600px;padding:10px;">
		<p align="center" style="font-size: 25px;margin-top:40px">图片信息</p>
		<input style="margin:10px 60px 10px;" type="hidden" name="student.id" id="s_id" >
		<div style="margin:50px 60px 10px;">图片名称：
			<span id="image_name3" ></span>
		</div>
		<div style="margin:10px 60px 10px;">是否使用当前图片：
			<span id="image_use3" ></span>
		</div>							
		<div style="margin:10px 60px 10px;;display: none;">图片地址：
			<span id="pic_id3" ></span>
		</div>
		<img alt="图片" src="" style="width: 450px;height: 250px;margin:30px 58px 10px;" id="ss_pic">
	</div>
</body>

<script type="text/javascript">
var imgurl = '<%= resource.getString("image_url")%>';

$(function () {
	init();
});
	
function init(){
	$('#listData').datagrid({
		url: 'appIndexCycleImage/getAll',//请求方法的地址
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
				field: 'imageName', title: '图片名称', width: 100, align: 'center',formatter: function(value, row, index){
					return value;
				}
			},           
			{
				field: 'imageUse', title: '是否使用当前图片', width: 100, align: 'center',formatter: function(value, row, index){
					if(value == 1){
						return "是";
					}
					if(value == 0){
						return "否";
					}
				}
			},
			{	
				field: 'opt', title:'操作',align: 'center',width: 120,formatter: function (value, row, index) {
					var str = "";
					str += '<a href="javascript:void(0);"  onclick="open_ed('+row.id+')"> 编辑图片 |</a>';
					str += '<a href="javascript:void(0);"  onclick="deletes('+row.id+')"> 删除图片 |</a>';
					str += '<a href="javascript:void(0);"  onclick="s_pic('+row.id+')"> 查看图片详情</a>';
					return str;
				}
			}
			]],
               
		toolbar: [{
			iconCls: 'icon-add',
			text:'添加',
			handler: function(){
				
				$('#pic_id').textbox('setValue','');
				$('#card_div1').children().css('display','block');
				$('#img1').siblings().css('display','block');
				$('#img1').attr('src','');
				$('#card_div1 img').css('display','none');
				$('#files').css('display','none');
				
				$('#add_win').window('open');
			}
		}]
	});
	$("#w").window('close');
}

	
/**
 * 根据图片名称进行查询
 */
function search() {
	var fields =$('#form_search').serializeArray();   
//	console.log(fields);
    var params = $("#listData").datagrid('options').queryParams;  
//  console.log(params);
    $.each( fields, function(i, field){  
        params[field.name] = field.value;   
    });  
    
    $('#listData').datagrid('reload');
    $('#form_search').form('clear');
}	

/**
 * 清除所有正在用于轮播图的图片
 */
function clearAllUse() {
	$.ajax({   	      
 		url :"appIndexCycleImage/clearAllUse",  
        type :'POST', 
	    data :{},
	    cache: false,
	    processData: false,
	    contentType: false ,	       
        success:function(data) {  		
    	   
		},  
	});
	init();
}
 
function init_win(){
	$('#add_win').window({
		title : '添加轮播图图片',
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
	
/**
 * 图片回显
 */
$('#files').filebox({
	onChange:function(value){
		if(value!='' && value != null){	
			
			var formData = new FormData();
	 		//console.log(formData); 
			formData.append('file',$('#filebox_file_id_4').get(0).files[0]);
			$.ajax({   	      
		 		url :"appIndexCycleImage/saveCycleImage",  
		        type :'POST', 
			    data : formData,		
			    cache: false,
			    processData: false,
			    contentType: false ,	       
		        success:function(data) {  		
					//alert(data);
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

/**
 * 【添加】弹出窗口中点击【圆圈叉号】时的删除图片功能
 */
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
	
/**
 * 修改图片
 */
$('#files2').filebox({
	onChange:function(value){
		if(value!='' && value != null){	
			var formData = new FormData();
			//console.log(formData);
			formData.append('file',$('#filebox_file_id_3').get(0).files[0]);
			$.ajax({   	      
		 		url :"appIndexCycleImage/saveCycleImage",  
		        type :'POST', 
			    data : formData,		
			    cache: false,
			    processData: false,
			    contentType: false ,	       
		        success:function(data) {  		
					//alert(data);
					$('#pic_id2').textbox("setValue",data);
					$('#img2').attr('src',imgurl+data);
					$('#tan2').css('display','none');   
					$('#card_div2 img').css('display','block');
				},  
			});
		}
	}
});

/**
 * 点击【编辑图片】时图片信息回显功能
 */
function open_ed(id){
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"appIndexCycleImage/getCycleImageById",
		success:function(data){
			$("#cId").val(data.id);
			$("#image_name2").textbox("setValue",data.imageName);
			$("#image_use2").combobox("setValue",data.imageUse);
			$("#pic_id2").textbox("setValue",data.imageSrc);
			$("#img2").attr("src",data.imageSrc);
			$('#card_div2 img').css('display','block');
		}
		 
	}) ;
	$("#w").window('open');
}	

/**
 * 编辑图片(if) + 添加图片(else)
 */
function updateOrAddDate(form,method){
	var data = $("#"+form).serialize();
	//console.log(data);
	if(form =='formAppIndexCycleImage'){
		if($("#image_name2").textbox("getValue")==''){
			alert("请输入图片名称")
		}else if($("#image_use2").combobox("getValue")==''){
			alert("请选择是否使用当前图片")
		}else {
			$.ajax({
				url: "appIndexCycleImage/"+method,// 请求的url地址
				async: true,// 请求是否异步，默认为异步，这也是ajax重要特性
				data: data,
				type: "post",// 请求方式
				success: function(req){
					$('#w').window('close');
					$('#add_win').window('close');
					$('#listData').datagrid('reload');
					$('#formAppIndexCycleImage').form('clear');
					$('#appIndexCycleImage_add_form').form('clear');
				}
		    	   
			});
		}
	}else{
		if($("#image_name1").textbox("getValue")==''){
			alert("请输入图片名称")
		}
		else  if($("#image_use1").combobox("getValue")==''){
			alert("请选择是否使用当前图片")
		}else {
			$.ajax({
				url: "appIndexCycleImage/"+method,    //请求的url地址
				async: true, //请求是否异步，默认为异步，这也是ajax重要特性
				data: data,
				type: "post",   //请求方式
				success: function(req){
					$('#w').window('close');
					$('#add_win').window('close');
					$('#listData').datagrid('reload');
					$('#formAppIndexCycleImage').form('clear');
					$('#appIndexCycleImage_add_form').form('clear');
				}
		    	   
			});				
		}
	}
}
	
/**
 * 删除图片
 */
function deletes(id){
	if(confirm('确认删除')==false){
		return false;
	}else{
		$.ajax({
			type:"post",
			url:"appIndexCycleImage/delete",
			data:{
				"id" : id
			},
			success : function(){
				$('#listData').datagrid('reload');
			}
		})
	}
}	
	
/**
 * 查看图片详情
 */
function s_pic(id){	
	$.ajax({
		type:"post",
		data:{"id":id},
		url:"appIndexCycleImage/getCycleImageById",
		success:function(data){
			if(data.imageUse == 1){
				data.imageUse="是";
			}
			if(data.imageUse == 0){
				data.imageUse="否";
			}	
			$("#ss_pic").attr("src",data.imageSrc);
			$("#image_name3").html(data.imageName);
			$("#image_use3").html(data.imageUse);
			$("#pic_id3").html(data.imageSrc);
		}
	}) 		
	$("#parentConversation_pic").window('open');
}
	
</script>
</html>