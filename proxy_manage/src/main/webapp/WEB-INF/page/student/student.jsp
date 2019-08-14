<%@ page language="java" import="java.util.ResourceBundle"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="static/css/all.css" type="text/css" />
<link rel="stylesheet" href="static/css/default.css" type="text/css" />
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="static/js/jquery.i18n.properties.min.js"></script>
<link rel="stylesheet"
	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"
	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css"
	type="text/css" />
<link rel="stylesheet" href="static/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="static/css/student.css" type="text/css" />

<title>Insert title here</title>

</head>
<body>
	<form id="studentsave" action="student/save" method="post"
		class="form-horizontal" style="width: 1136px;"
		enctype="multipart/form-data">
		<div class="student">
			<div style="margin-left: 15px; margin-right: 15px;">
				<div style="border-bottom: 1px solid #99BBE8; margin-bottom: 23px;">
					<p style="font-size: 22px">基本信息</p>
				</div>
			</div>

			<div class="row" style="width: 50%; float: left;">
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">姓名:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="name" class="easyui-textbox" style="width: 130px"
						type="text" name="name">
				</div>

				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">性别:
						<h6 class="bitian">*</h6> <span></span>
					</label>
					<p style="margin-top: 4px;" id="sex">
						<span>男</span> <input type="radio" checked="checked" value="male"
							name="sex"> <span>/女</span> <input type="radio"
							value="female" name="sex">
					</p>
				</div>


				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">身份证号:
						<h6 class="bitian"right:278px;top:4px;>*</h6> <span></span>
					</label> <input id="idCard" class="easyui-textbox" style="width: 130px"
						type="text" name="idCard" >
				</div>

				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">出生日期:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="birthday"
						style="width: 120px; height: 23px; border: 0; margin: 4px"
						readonly="readonly" type="text" name="birthday">
				</div>

				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">年龄:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="age"
						style="width: 120px; height: 23px; border: 0; margin: 4px"
						readonly="readonly" name="age">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">电话号码:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="tel" class="easyui-textbox" style="width: 130px"
						type="text" name="tel">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">学习时长:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="studyTime" class="easyui-textbox" style="width: 130px"
						name="studyTime">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">入学时间:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="joinTime" class="easyui-datebox" style="width: 130px"
						name="joinTime">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">住宿费(元):<span></span></label>
					<input class="easyui-textbox" style="width: 130px" type="text"
						name="quarterage" id="zhuNumber">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">开始住宿时间:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="initialResideDate" class="easyui-datebox"
						style="width: 130px" name="initialResideDate">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">班级:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input class="easyui-textbox" style="width: 130px" type="text"
						id="classroom" name="classroom.id">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">原专业:<span></span></label>
					<input class="easyui-textbox" style="width: 130px" type="text"
						name="originalMajor">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">毕业证编号:<span></span></label>
					<input class="easyui-textbox" style="width: 130px" type="text"
						name="number" placeholder="学信网可查">
				</div>
			</div>

			<div class="row" style="width: 50%; float: left;">

				<div
					style=" height: 214px; border: 1px solid #95B8E7; position: relative"
					class="col-md-3 picture_div" id="picture">
					<label  style=" height: 214px;line-height:214px;font-size: 9px; margin-right: 5px;">学生照片:<span></span></label>
						<div style="margin-top:86px;position: absolute;bottom: 100px;left: 150px " id="fileblock">
						<input style="width: 180px; height: 40px;" class="easyui-filebox"
							name="file3" buttonIcon="icon-man" buttonAlign="right" id="files3"
							buttonText="添加" data-options="prompt:'请选择文件...'" /> 
							
					<img alt="图片"
							style="width: 100%; height: 212px; margin:-100px -10px; display: none"
							id="img3"> 
				   <img alt="" src="static/images/cuo3.png"
						class="ccd"
						style="position: absolute; top: -105px; right: 0px; width: 30px; display: none">
						</div>
				</div>




				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">专业:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="major" class="easyui-textbox" style="width: 130px"
						type="text" name="major.id">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">学费(元):
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="tuition" class="easyui-textbox" style="width: 130px"
						type="text" name="tuition">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">杂费(元):<span></span></label>
					<input class="easyui-textbox" style="width: 130px" type="text"
						name="incidentals" id="zaNumber">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">老师:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="teacher" class="easyui-textbox" style="width: 130px"
						type="text" name="teacher.id">
				</div>
			</div>
			<div class="row" style="width: 50%; float: left;">
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">学历:<span></span></label>
					<input id="education" class="easyui-textbox" style="width: 130px"
						name="education">
				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">咨询老师:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="advisoryTeacher" name="advisoryTeacher.id"
						class="easyui-textbox" style="width: 130px" type="text">

				</div>
				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">招生老师:
						<h6 class="bitian">*</h6> <span></span>
					</label> <input id="proxyTeacher" class="easyui-textbox"
						style="width: 130px" type="text" name="proxyTeacher.id">
				</div>

				<div class="col-md-3">
					<label style="font-size: 9px; margin-right: 5px;">与招生老师关系:<span></span></label>
					<input class="easyui-textbox" style="width: 130px" type="text">

				</div>
			</div>
			<div class="row"
				style="width: 77%; border: none; margin-left: 105px;">
				<div class="col-md-12">
					<label id="address"
						style="padding-right: 10px; font-size: 9px; float: left; margin-right: 3px; margin-left: 5px; width: 76px; border-right: 1px solid #95B8E7; height: 34px; margin-bottom: 0; line-height: 32px; text-align: 0;"><h6
							class="bitia">*</h6>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：<span></span></label>
					<div style="font-size: 9px; float: left; margin-top: 7px;">
						<label style="text-align: right">省:</label> <select id="sheng"
							name="address">
							<option value="">请选择省份</option>
						</select> <label style="text-align: right">市:</label><select name="address"
							id="shi"></select> <label style="text-align: right">县:</label><select
							name="address" id="xian"></select>
					</div>
				</div>
			</div>
			<!-- 图片名称  -->
			<div style="display: none;">
				<input class="easyui-textbox" style="width: 130px;" type="text"
					name="idCardPic" id="id_CardPic"> 
					
					
					<input
					class="easyui-textbox" style="width: 130px;" type="text"
					name="idCardPic2" id="id_CardPic2">
					
					<input
					class="easyui-textbox" style="width: 130px;" type="text"
					name="picture" id="student_Pic">
			</div>

			<!--上传身份证 -->
			<div class="row"
				style="width: 77%; border: 1px solid #95B8E7; margin-left: 105px; margin-top: 30px; height: 250px"
				id="card">
				<div
					style="width: 50%; height: 248px; border-right: 1px solid #95B8E7; float: left; position: relative"
					class="card_div" id="card_div1">
					<h3
						style="text-align: center; line-height: 70px; font-weight: 900; color: #6d88ac">身份证正面</h3>
					<input style="width: 180px; height: 40px" class="easyui-filebox"
						name="file" buttonIcon="icon-man" buttonAlign="right" id="files"
						buttonText="添加" data-options="prompt:'请选择文件...'" /> <img alt="图片"
						style="width: 100%; height: 248px; margin: 0 auto; display: none"
						id="img1"> 
				 <img alt="" src="static/images/cuo3.png"
						class="ccd"
						style="position: absolute; top: 0px; right: 0px; width: 30px; display: none">

				</div>

				<div style="width: 50%; float: left; position: relative;"
					class="card_div" id="card_div2">
					<h3
						style="text-align: center; line-height: 70px; font-weight: 900; color: #6d88ac">身份证反面</h3>
					<input style="width: 180px; height: 40px" class="easyui-filebox"
						name="file2" buttonIcon="icon-man" buttonAlign="right" id="files2"
						buttonText="添加" data-options="prompt:'请选择文件...'" />
						
			 <img alt="图片"
						src=""
						style="width: 100%; height: 248px; display: block; margin: 0 auto; display: none"
						id="img2"> <img alt="" src="static/images/cuo3.png"
						class="ccd"
						style="position: absolute; top: 0px; right: 0px; width: 30px; display: none">

				</div>
			</div>
			<!-- 提成选择 -->
			<br> <br> <br>
			<div style="border-bottom: 1px solid #99BBE8; margin-bottom: 23px;">
				<p style="font-size: 22px; margin: 1px 13px 10px;">提成选择</p>
			</div>
			<div class="jinji">
				<label
					style="font-size: 9px; margin-left: 20px; float: left; margin-top: 20px">添加提成:<span></span></label>
				<input
					style="font-size: 9px; margin-left: 20px; float: left; margin-top: 20px"
					type="radio" name="commission" value="1" checked="checked">
				<span
					style="font-size: 9px; margin-left: 5px; float: left; margin-top: 18px">返佣</span>
				<input
					style="font-size: 9px; margin-left: 20px; float: left; margin-top: 20px"
					type="radio" name="commission" value="0"> <span
					style="font-size: 9px; margin-left: 5px; float: left; margin-top: 18px">不返佣</span>

			</div>
			<!-- 紧急联系人 -->
			<br> <br> <br>
			<div style="border-bottom: 1px solid #99BBE8; margin-bottom: 23px;">
				<p style="font-size: 22px; margin: 1px 13px 10px;">紧急联系人</p>
			</div>
			<div class="jinji" id="jinji">
				<label style="font-size: 9px; margin-left: 20px; float: left">姓名:<span></span></label>
				<input class="jinName" style="width: 130px" type="text" name="cname">
				<label style="font-size: 9px; margin-left: 45px; float: left">联系电话:<span></span></label>
				<input class="jinTel" style="width: 130px" type="text" name="ctel">
				<label style="font-size: 9px; margin-left: 45px; float: left">关系:<span></span></label>
				<input class="jinGuan" style="width: 130px" type="text"
					name="crelation"> <a href="script:void(0)"
					class="easyui-linkbutton" iconCls="icon-add"
					onclick="addcontacts()"
					style="width: 100px; margin-top: 9px; margin-left: 30px"></a>
			</div>

			<br> <br>
			<div style="clean: both"></div>


			<!-- 学生来校方式 -->
			<div>
				<div style="border-bottom: 1px solid #99BBE8; margin-bottom: 23px;">
					<p style="font-size: 22px; margin: 1px 13px 10px;">学生来校方式</p>
				</div>

				<div class="jinji come" id="jinji">
					<label style="font-size: 9px; margin-left: 20px;">姓名:<span></span></label>
					<input class="easyui-textbox" style="margin-top: -11px" type="text"
						name="name2" id="name2"> <label
						style="font-size: 9px; margin-left: 45px;">出发地:<span></span></label>
					<input class="easyui-textbox" style="width: 130px" type="text"
						name="beforeAddress"> <label
						style="font-size: 9px; margin-left: 45px;">来校方式:<span></span></label>
					<input class="easyui-textbox" style="width: 130px" type="text"
						name="transportation" id="transportation">
				</div>
			</div>
			<div>
				<div style="border-bottom: 1px solid #99BBE8; margin-bottom: 23px;">
					<p style="font-size: 22px; margin: 1px 13px 10px;">备注</p>
				</div>
				<input class="form-control" type="text" id="remarks" 
						name="remarks" placeholder="备注">
			</div>
			<div class="col-md-3"
				style="padding-left: 120px; border: none; margin-left: 318px; margin-bottom: 50px;">
				<input type="submit" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" style="width: 30%; height: 50px"
					onclick="savemessages()" value="保存" />
			</div>
		</div>
	</form>
</body>

<script type="text/javascript" src="static/js/student.js"></script>
<script type="text/javascript">
var imgurl = '<%=resource.getString("image_url")%>';

//身份证照片回显
$('#files').filebox({
    onChange:function(value){
		if(value!='' && value != null){	
			var formData = new FormData();
			
			formData.append('file',$('#filebox_file_id_5').get(0).files[0]);
			
			$.ajax({   	      
				 		url :"student/savePic",  
				        type :'POST', 
					    data : formData,		
					    cache: false,
					    processData: false,
					    contentType: false ,	       
				        success:function(data) {  	
			<%-- alert('<%=resource.getString("image_url") %>'); --%>
							
				    	    $('#img1').attr('src',imgurl+data);
				    	    $('#id_CardPic').textbox("setValue",data);
				       		$('#card_div1 img').siblings().css('display','none');   	   
				       		$('#card_div1 img').css('display','block');

						},  
				        });
				}
			}
		})

$('#files2').filebox({  
		onChange: function(value) {	
				
			if(value!='' && value != null){
				var formData = new FormData();
				formData.append('file',$('#filebox_file_id_6').get(0).files[0]);
				$.ajax({   	      
					 		url :"student/savePic",  
					        type :'POST', 
						    data : formData,		
						    cache: false,
						    processData: false,
						    contentType: false ,	       
					        success:function(data) {  			
					    	$('#img2').attr('src',imgurl+data);
					        $('#id_CardPic2').textbox("setValue",data);
				       		$('#card_div2 img').siblings().css('display','none');   	   
				       		$('#card_div2 img').css('display','block');
							},  
					        });
			}	
			}
		})
	$('#files3').filebox({  
		onChange: function(value) {	
				
			if(value!='' && value != null){
				var formData = new FormData();
				formData.append('file',$('#filebox_file_id_4').get(0).files[0]);
				$.ajax({   	      
					 		url :"student/savePic",  
					        type :'POST', 
						    data : formData,		
						    cache: false,
						    processData: false,
						    contentType: false ,	       
					        success:function(data) {  			
					    	$('#img3').attr('src',imgurl+data);
					        $('#student_Pic').textbox("setValue",data);
				       		$('#picture img').siblings().css('display','none');   	   
				       		$('#picture img').css('display','block');
							},  
					        });
			}	
			}
		}) 
		
	 
	
		//删除图片
		$('.ccd').click(function (){
		var	index =$(this).parent().index('.card_div');
		var	 index1=$(this).parent().index('.col-md-3 picture_div'); 
		var	 index1=$(this).parent().index('#fileblock'); 
			if(index==0){
				$('#files').textbox('setValue','');
				$('#card_div1').children().css('display','block');
				 $('#id_CardPic').textbox("setValue","");
				$('#card_div1 img').css('display','none');
				$('#files').css('display','none');
			}else{
				$('#files2').textbox('setValue','');
				$('#card_div2').children().css('display','block');
				 $('#id_CardPic2').textbox("setValue","");
				$('#card_div2 img').css('display','none');
				$('#files2').css('display','none');
			}    
			
			 if(index1==0){
				$('#files3').textbox('setValue','');
				$('#fileblock').children().css('display','block');
				$('#student_Pic').textbox("setValue","");
				$('#fileblock img').css('display','none');
				$('#files3').css('display','none');
			} 
	})
	
	
</script>
</html>

