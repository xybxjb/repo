<%@ page language="java" import="java.util.ResourceBundle" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ResourceBundle resource = ResourceBundle.getBundle("const");
%>
  <%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ request.getContextPath();
%>
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
<link rel="stylesheet" href="static/css/visit.css" type="text/css" />

</head>
<body>
		<div class="headercss">
			<form id="search_form" action="" method="post" >
				<fieldset class="form_fieldset">
					<div class="name">
						<label class="ui_font">姓名：</label>
						<input  type="text" name="name" class="easyui-textbox" name="proxyTeacher"  id="proxyTeacher1">
					</div>
					
					<div class="name">	
						<label class="ui_font">来访目的：</label>
					<input class="easyui-combobox"  style="width:130px;"  id="purpose1"  name="purpose"   data-options="
							valueFiled:'label',
							textField:'value',
							data:[{label:'送生',value:'送生'},{label:'考察',value:'考察'}]">
					</div>
					
					 <div  class="shijian">
						<label class="ui_font">来访时间：</label> 
							<input class="easyui-datetimebox" name="beginDateTime"  id="beginDateTime"/>
							<label>-</label> 
							<input class="easyui-datetimebox" name="endDateTime"   id="endDateTime"/>
					</div>
					<br>
					<br>
					<br>
					<div class="chufa">
						<label class="ui_font">出发地：</label>
						<input   name="startFromAddress" id="startFromAddress1"   class="easyui-textbox">
					</div>
					<br>
					<br>
					<br>
					<div class="chaxun">
						<input type="button" class="easyui-linkbutton" style="width: 50px"value="查询" onclick="find();">
						<input type="button" class="easyui-linkbutton" style="width: 50px"value="添加" onclick="add();">
						<input type="button" class="easyui-linkbutton" style="width: 50px"value="清空刷新" onclick="clea();">
					</div>
				</fieldset>
			</form>
		</div>
<table id="listData"></table>
<!--添加菜单  -->
<div id="add_win"  closed="true"  class="easyui-window"  style="top:38px">
	<form id="addForm" action="visitRecords/save"  enctype="multipart/form-data" method="post">
			<div style="margin-left:15px;margin-right:15px;">
				<div style="margin-bottom: 23px;"></div>
			</div>
			<div class="add">
				<div class="add_1">
					<div class="add_style_1">
						<label>老&nbsp;&nbsp;师&nbsp;&nbsp;姓&nbsp;&nbsp;名&nbsp;&nbsp;:<span></span></label>
						<input  name="id"  id="teacherId"  type="hidden"/>
						<input id="proxyTeacher"  name="proxyTeacher.id" class="easyui-textbox" style="width:130px" type="text">
					</div>
					
					<div class="add_style_1">
						<label >来&nbsp;&nbsp;访&nbsp;&nbsp;目&nbsp;&nbsp;的&nbsp;&nbsp;:<span></span></label>
						<select name="purpose"  id="purpose"   class="easyui-combobox" style="width:130px;">
							<option value="送生">送生</option>
							<option value="考察">考察</option>
						</select>
					</div>
					<div class="add_style_1">
						<label >来&nbsp;&nbsp;访&nbsp;&nbsp;时&nbsp;&nbsp;间&nbsp;&nbsp;:<span></span></label>
						<input class="easyui-datetimebox" style="width:130px" type="text"  id="dateTime"  name="dateTime">
					</div>
					<div class="add_style_1"  >
					<label>出&nbsp;&nbsp;&nbsp;&nbsp;发&nbsp;&nbsp;&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;:</label> 
					<input class="easyui-textbox" style="width:130px" type="text" id="startFromAddress"   name="startFromAddress">
				</div>
				</div>
				<div class="add_2">
					<div class="add_style_1">
						<label>银&nbsp;行&nbsp;卡&nbsp;姓&nbsp;名&nbsp;:</label> 
						<input class="easyui-textbox" style="width:130px" type="text"  name="bankName" id="bankName" >
					</div>
					<div class="add_style_1">
						<label>银&nbsp;&nbsp;行&nbsp;&nbsp;卡&nbsp;&nbsp;号&nbsp;&nbsp;:</label> 
						<input class="easyui-numberbox" style="width:130px" type="text"  name="bankCardNumber"  id="bankCardNumber">
					</div>
					<div class="add_style_1">
						<label>开&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;行&nbsp;&nbsp;&nbsp;:<span></span></label>
						<input class="easyui-textbox" style="width:130px" type="text"  name="openingBank"   id="openingBank">
					</div>
				</div>
			</div>
			<div class="add_3" id="chepiao"  style="margin-top:10px">
				
				<div class="add_style_1" style="float:left">
						<label>出&nbsp;&nbsp;行&nbsp;&nbsp;方&nbsp;&nbsp;式&nbsp;&nbsp;:</label> 
						<input id="trans" class="easyui-textbox trans" style="width:130px" type="text"  name="trans">
				</div>
				<div class="add_style_1" style="float:left;width:250px">
						<label style="margin-left:19px">车&nbsp;&nbsp;票&nbsp;&nbsp;金&nbsp;&nbsp;额&nbsp;&nbsp;:<span></span></label> 
						<input id="ticketAmount" class="easyui-numberbox ticketAmount" style="width:130px"  name="ticketAmount">
				</div>
				<div class="add_style_1" style="float:left" >
						<label>实&nbsp;&nbsp;报&nbsp;&nbsp;金&nbsp;&nbsp;额&nbsp;&nbsp;:<span></span></label> 
						<input id="actualAmount" class="easyui-numberbox actualAmount" style="width:130px"     name="actualAmount">
				</div>
				
				<div class="add_style_1" style="float:left;margin-left: 58px;" >
						<label>实&nbsp;&nbsp;报&nbsp;&nbsp;金&nbsp;&nbsp;额&nbsp;&nbsp;:<span></span></label> 
						<input id="actualAmount" class="easyui-numberbox actualAmount" style="width:130px"     name="actualAmount">
				</div>
				<div class="add_style_1" style="float:left" >
						<a href="script:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addcontacts()"  style="margin-left:112px;width:100px"></a>
				</div>
			</div>
			<div style="clean:both"></div>
			
			<div   style="width:560px;height50px" >
				<div class="add_style_1" style="float:left;margin-left: 58px;">
						<label>需报销总钱数&nbsp;:</label> 
						<input id="reimAmount" class="easyui-numberbox" style="width:130px" type="text"  name="reimAmount">
					</div>
				<div class="add_style_1" style="float:left;margin-left:39px">
						<label>报&nbsp;&nbsp;销&nbsp;&nbsp;里&nbsp;&nbsp;程&nbsp;&nbsp;:<span></span></label>
		<input class="easyui-combobox"  style="width:130px;"  id="reimbMileage"  name="reimbMileage"   data-options="
			valueFiled:'label',
			textField:'value',
			data:[{label:'单程',value:'单程'},{label:'双程',value:'双程'}]">
		</div>
		
			</div>
			<div  style="width:50%;height:200px;border: 1px solid #95B8E7;margin:20px 30px;float:left;position:relative" class="card_div" id="card_div1">
				<h3  style="text-align: center;line-height: 70px;font-weight: 900;color:#6d88ac">车票照片</h3>
				<input    style="width:180px;height:40px"    class="easyui-filebox"   buttonIcon="icon-man"  buttonAlign="right"  id="files"   buttonText="添加"    data-options="prompt:'请选择文件...'"/>	
				<img alt="图片"    style="width:100%;height:100%;margin:0 auto;display:none"  id="img1">
				<img alt="" src="static/images/cuo3.png" class="ccd" style="position:absolute;top:0px;right:0px;width:30px;display:none">
			</div >
			<div style="display: none;">
				<input id="reimVoucher" class="easyui-textbox" style="width:130px" type="text"  name="reimVoucher">
			</div>
		 <div style="float:left" >
			<a href="script:void(0)" id="button2"   class="easyui-linkbutton" iconCls="icon-save" onclick="save()"  style="margin:89px 0px 0px 28px;width:180px;height:40px">保存</a>
		 </div>
	</form>
</div>


</body>
<script type="text/javascript"	src="static/js/visit.js"></script>
<script type="text/javascript">
var imgurl = '<%= resource.getString("image_url")%>';

//身份证照片回显
$('#files').filebox({
	onChange:function(value){
		if(value!='' && value != null){	
			var formData = new FormData();
			formData.append('file',$('#filebox_file_id_3').get(0).files[0]);
			$.ajax({   	      
				url :"visitRecords/savePic",  
			    type :'POST', 
			    data : formData,		
				cache: false,
			    processData: false,
				contentType: false ,	       
				success:function(date) {  			  
				   if(date!="err"){
				     $('#img1').attr('src',imgurl+date);
				     $('#reimVoucher').textbox("setValue",date);
					 $('#card_div1 img').siblings().css('display','none');   	   
					 $('#card_div1 img').css('display','block');
					       pic = date;	 
				     }else{
				    		alert("上传错误");
				     }
				},  
			});
		}
	}
})

</script>
</html>