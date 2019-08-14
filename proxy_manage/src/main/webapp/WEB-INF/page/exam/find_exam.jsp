<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="static/css/default.css" type="text/css" />
<link rel="stylesheet"	href="static/css/all.css" type="text/css" />
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css"/>
<title>Insert title here</title>
<style type="text/css">

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
}
input[type="number"] {
    -moz-appearance: textfield;
}


	.myUi{
	list-style: none;
	
	}
	li{
		margin-top:17px;
	}
	li label{
	font-size: 15px
	}
	span{
	font-size: 15px
	}
	</style>
</head>
<body>
<form id="search_form" action="" method="post" >
		
		<fieldset class="form_fieldset"  >
			
			<label class="ui_font">时间筛选：</label>
			
			<input id="beginTime" class="easyui-datebox" style="width:130px" type="text" name="beginTime"> <span>-</span>
			
			<input id="endTime" class="easyui-datebox" style="width:130px" type="text" name="endTime">
			
			<label class="ui_font"  style="margin-left:40px">考试名称 :</label> 
					<input class="easyui-textbox" name="examName" id="examName" value=""/>
			
			
			<label class="ui_font"  style="margin-left:40px">班级：</label>
			
			<input id="className" class="easyui-textbox" style="width:130px" type="text" name="className">  
										
			<label class="ui_font"  style="margin-left:50px">学生姓名 : </label>
			
			<input name="findName"   onchange="find()"     value=""   style="color:red" class="easyui-textbox" id="findName">

			<a href="javascript:void(0)"  onclick="clean()"   class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  > 刷新清空</a>
			
			<a id="btn" href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-search'"  style="margin-left:50px"   >查找</a>  
		
		</fieldset>
	
	</form>
	
		<div class="cc">	<table id="dg"></table></div>
		
		<div id="w" class="easyui-window" title="信息修改" data-options="iconCls:'icon-save'" style="width:320px;height:550px;padding:10px;" closed="true">
				<form  id="fromDate">
				<ul  class="myUi"  id="myUl">
					<li></li>
				</ul>
				<input   type="button"  value="保存"   onclick="updateDate()"  style="text-align:center;position: absolute;left:33%;width:100px;margin-top:15px;height:30px" >
				</form>
		</div>
</body>
</html>

<script type="text/javascript">
var arr=[]; 
var array =[],columns=[],arr2=[]; 
var  index1=0,index2=0,hang=0; var bb=false; var fen=0;; 
$(function () {
	getClassroom();
	findExamName();
	findAll("course/getFilterCourse",{name:""},'exam/getStudentExam');
});

	function findAll(url,datas,datagridUrl){
		var examName = $('#examName').textbox('getValue');
		$.ajax({
		url:url,
		data:datas,
		success:function(data){    hang =data.length;  arr2=[]; index1=0; 
		 array.push({ field: 'index', title: '序号', width:'100px', align: 'center', formatter: function (value, row, index) {numbers=index+1;return index+1;}});	 
		 array.push({field:'id',title:'id',align:'center',hidden:true}); 
		 array.push({  field: 'student', title: '学生姓名', width:'100px', align: 'center',formatter:function (value, row, index) { return row.student.name}});
		 array.push({ field: 'classroom.name', title: '班级', width:'100px', align: 'center',formatter: function (value, row, index) {return row.classroom.name}}); 
		 array.push({  field: 'examDate', title: '考试时间', width:'100px', align: 'center'});
		 array.push({  field: 'name', title: '考试名称', width:'100px', align: 'center'});
		 for(var i=0;i<data.length;i++){   arr2[i]=data[i];    
			 array.push({field: 'kemu', title: data[i].name, width:'100px', align: 'center' ,formatter:function (value, row, index) {

				if(index1==hang)index1=0;
				 	if(row.scores[index1].score>=0){  return row.scores[index1++].score}
					else if(row.scores[index1].score==-1) {index1++; return null}
					else{  index1++; 
					return	'<div  style="width: 25px;height: 25px; background: #adb1b8; border-radius: 100%;position:relative;margin:0 auto">'+
					'<div id=""  style="width: 12px;height: 5px;background: white;position: absolute;left: 7px;top: 10px;"></div></div>' 
					}
			 	}
			 });}
			 array.push({  field: 'dd', title: '编辑操作', width:'100px', align: 'center',formatter: function (value, row, index) {
				 var str = '<a  href="javascript:void(0);" onclick="update('+row.id+')">修改</a>';
				 return str}});
			 columns.push(array);  
			 $('#dg').datagrid({url:datagridUrl,title: '查询结果', height: window.innerHeight-60, fitColumns: true,nowrap: false, loadMsg: '正在加载信息...',pagination: true,singleSelect: true, pagePosition: 'bottom',pageSize:10,pageNumber: 1,pageList: [10, 30, 50],queryParams:{},columns:columns,}); 
			 array=[]; columns=[];
		}
	})
	}
	
	$(function(){    
	    $('#btn').bind('click', function(){    
			$('#dg').datagrid({ url:'exam/getStudentExam'+getData(2)});
			findAll("course/getFilterCourse",getData(1));
	    });    
	});  
	
	function update(id){
		$("#myUl").empty();
		$.ajax({
			url:"exam/getById",
			data:{id:id},
			success:function(data){
				var strs ='<li><label>姓&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp名：</label><span   id="name"></span><br></li>'+
						  '<li><label>考试名称：</label>					 	    <span  id="examName1"></span><br> </li>'+
						  '<li><label>日&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp期：</label> <span  id="examDate"></span><br></li>'+
						  '<li><label>班&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp级：</label> <span  id="classroom"></span><br></li>'
				$("#myUl").append(strs);
				$("#name").html(data.student.name);
				$("#examDate").html(data.examDate);
				$("#examName1").html(data.name);
				$("#classroom").html(data.classroom.name);
				$.ajax({
					url:"course/getExamCourses",
					data:{id:data.id},
					success:function(datas){
						var fen ;
						
						$("li").eq(0).after('<li><input  type="hidden"   name="examId" value="'+data.id+'"></li>');
						
						for(var i=0;i<datas.length;i++){
							fen =null;
							$.ajax({
								url:"score/getScoreByCourseId",
								data:{courseId:datas[i].id,examId:id},	
								async:false,
								success:function(score){
								
									if(score!=null&&score!=''){fen = score;}
									else if(score===0){fen =0}
								}
							})
							var str = '<li><label>'+datas[i].name+':</label><input  type="number"   oninput="if(value>100)value=100"  style="position:absolute;right:118px;width:60px;font-size:15px;text-align:center"   value='+fen+'  name="'+datas[i].inputName+'"><br></li>'
						$("#myUl").append(str);  
						}
					}
				})
				$("#w").window('open');
			}
			
		})
	}
	function updateDate(){
		var data =$("#fromDate").serialize();
		$.ajax({
			url:"score/update?",
			data:data,
			success:function(){
				$("#myUl").empty();
				$("#w").window('close');
				$('#dg').datagrid('reload');
			}
		})
	}

	function findExamName(){
	$('#examName').combobox({
	url : 'exam/getExamName',
	/* valueField设置传过来对象的字段作为value值 */
	valueField : 'name',
	selectOnNavigation : false,
	/* textField设置传过来对象的字段作为文本 */
	textField : 'name',	
	 editable:false,
	 onChange:function(val){
			if(val){
				findAll("course/getFilterCourse",getData(1),'exam/getStudentExam'+getData(2));
			}
		}
});
}
	function clean(){ 
		$('#findName').textbox('setValue','');
		$('#beginTime').textbox('setValue','');
		$('#endTime').textbox('setValue','');
		$('#className').textbox('setValue','');
		$('#examName').textbox('setValue','');   
		findAll("course/getFilterCourse",getData(1),'exam/getStudentExam'+getData(2));
	}
	   
	$('#findName').textbox({ onChange: function () {
		findAll("course/getFilterCourse",getData(1),'exam/getStudentExam'+getData(2));
	} });
			
	
	function getData(val){
		var name =$('#findName').textbox('getValue');
		var beginTime=$('#beginTime').textbox('getValue');
		var endTime=$('#endTime').textbox('getValue');
		var classroomId = $('#className').textbox('getValue');
		var examName = $('#examName').textbox('getValue');
		var data1 ={classroomId:classroomId,name:name,beginTime:beginTime,endTime:endTime,examName:examName};
		var data2 ="?name="+name+"&beginTime="+beginTime+"&endTime="+endTime+"&classroomId="+classroomId+"&examName="+examName;
		if(val==1){return data1}
		else return data2;
	}
	
	function getClassroom(){
		$('#className').combobox({
		url : 'classroom/getAll',
		/* valueField设置传过来对象的字段作为value值 */
		valueField : 'id',
		selectOnNavigation : false,
		/* textField设置传过来对象的字段作为文本 */
		textField : 'name',	
		 editable:false,
		 onChange:function(val){
				if(val){
					findAll("course/getFilterCourse",getData(1),'exam/getStudentExam'+getData(2));
				}
			}
		});
	}	
</script>