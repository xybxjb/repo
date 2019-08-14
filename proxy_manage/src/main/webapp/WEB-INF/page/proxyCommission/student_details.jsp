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
<base href="<%=basePath+"/" %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="static/css/default.css" type="text/css" />
<link rel="stylesheet"	href="static/css/all.css" type="text/css" />
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css"/>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	html body{
		width: 100%;
		height: 100%;
	}
	.centers{
		/* text-align:center */
		margin-left: 100px
	}
	.centerss{
		/* text-align:center */
		margin-right: 70px
	} 
	.header{
		height: 70px;
		width: 100%;
		
	}
	.hfont{
		margin-left: 20px;
	}
	.aright{
		float: right;
	}
	.0.0
	.
	.inpt{
		height: 30px;
		width:150px;
		margin-left: 10px;
		margin-right: 20px;
		margin-top: 10px;
	}
	.ha{
		margin-left: 10px;
		margin-top:0px;
		margin-bottom: 0;
	}
	b{
		
		margin-left: 40px;
	}
	.submits{
		background-color: skyblue;
		height:40px;
		width:80px;
	}
	
	.haa{
		margin-top: 20px;
		margin-bottom:2	0px;
		margin-left: 10px;
	}
	.butt{
		margin-left:20px;
		margin-right:20px;
		height: 40px;
		width: 90px;
		background-color: #5CACEE;
	}
	.del{
		margin-left:20px;
		margin-right:20px;
		height: 40px;
		width: 90px;
		background-color: red;
	}

	
	.selectcss{
		height:40px;
		width:200px;
	}
	
	.easyui-window{
		height:350px;
		width:420px;
	}
	.newwindow{
		height: 40px;
		width:300px;
		margin-top: 20px;
	}
	.lobafont{
		font-size: 16px;
	}
	.sexcss{
		font-size: 18px;
		width:2em;
		height:2em;
		font-size:9px;
	}
	.headercss {
	    margin-top: 10px;
	    margin-left: 10px;
	    margin-right: 10px;
	    margin-bottom: 0px;
	    	/* background-color:yellow; */
}
	.cc{
		margin-left: 10px;
	    margin-right: 10px;
	    margin-top: -5px;
	  
	}
	.h1{
	font-size: 20px;
	}
	p.groove {
  
	padding-top:8px;	
    border-style:ridge; 
	width: 200px;
	height: 20px;	
	font-size: 12px;
	position: relative;
	top: 33px;

	
	
	}
	p.groove1 {
	padding-top:8px;	
    border-style:ridge; 
	width: 200px;
	height: 20px;	
	font-size: 12px;
 	position: relative; 
 	margin-left: 210px;  
	}
	
	.button {

    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 10px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px; 
    cursor: pointer;
   position :relative;
 		/* top:-90%;  */ 
/* 	left:80%; */
 
}

 
.button4 {background-color: #e7e7e7;
 color: black;
	border-radius: 10px;
	} /* Gray */ 
 
 .ipu{
 position: relative;
 margin-left: 60%;
 } 
 .searchdiv{
 	margin-bottom: 10px;
 	margin-top: 10px;
 	margin-left: 120px;
 }
 .my_li{
	list-style: none;
	width: 200px; 
	float: left;
	margin-top: 5px;
}
   
</style>

<script type="text/javascript">

$(function(){
	init();
});
function init(){
	$('#dg').datagrid({
        url: 'proxyteacherstudent/findPage',//请求方法的地址
        title: '名下学生详情',
        height: window.innerHeight-160, 
        fitColumns: true, //列自适应 
        nowrap: false,//禁止文字自动换行
        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
        pagination: true,//是否有分页
        singleSelect: true,//是否单行选择
        pagePosition: 'bottom',//分页符在底部,可选参数为top，bottom，
        pageSize:15,//页大小，一页多少条数据
        pageNumber: 1,//默认当前的页码
        pageList: [15, 20, 30],//一页可显示数据的条目 
        queryParams:{},//往后台传递参数，json格式 */        
        columns: [[
            /* { field: 'ck', checkbox: true, align: 'left', width: 50 }, */
            { field: 'studentId', title: 'id', width: 0, align: 'center', hidden: true },
            {
                field: 'studentName', title: '学生姓名', width: 80, align: 'center'
            },
            { 
            	field: 'proxyteacherName', title: '所属招生老师', width: 80, align: 'center'
            }, 
            {
                field: 'studentTuitionAmount', title: '缴纳学费金额', width: 80, align: 'center'
            },
            {
                field: 'commissionDate', title: '缴纳学费时间', width: 80, align: 'center',formatter: function(value,row,index){
                	var newTime = new Date(row.commissionDate);
                	return newTime.getFullYear()+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate();
                	
                }
            },
           /*  {
                field: 'ram', title: '未缴学费金额', width: 80, align: 'center'
            }, */
            {
                field: 'commissionAmount', title: '应获提成金额', width: 80, align: 'center'
            },
          
	     {
	                field: 'commissionPoint', title: '应获提成点位', width: 80, align: 'center'
	            },  
	            /* { 
	            	field: 'point', title: '当前点位', width: 80, align: 'center'
	            }, */
            { field: 'opt', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {
                var str = "";
                str += '<a href="javascript:void(0);" onclick="update_win('+row.studentId+')">详情</a>';
               
                return str;
            } },           
        ]],
        onLoadError : function(none) {
				alert("暂时没有招到学生！加油......")
		}
    });
}
function search(){
	var fields =$('#search_form').serializeArray();  
    var params = $("#dg").datagrid('options').queryParams;  
    $.each( fields, function(i, field){  
        params[field.name] = field.value;   
    });  
    $('#dg').datagrid('reload')
}
function add_win(){
	
	 $('#add_win').window('open');
	
}

function save(){
	var data =$('#com_add_from').serialize();  
	var flag=true;
	$('#com_add_from input[type="hidden"]').each(function(){
		
		
		console.log($(this).val())
		  if($(this).val()==null||$(this).val()==""){
			 alert($(this).parent().parent().children().eq("0").text()+"不能为空");
			 flag=false;
			 return flag;
		 }   
	})
	if(flag==true){
		 $.ajax({
				type:"post",
				data:data,
				url:"computer/save"			
			}) 
	}
		 
	
}
function close(){
	$('#add_win').window('close');
}

function update_win(studentId){
	 $.ajax({
		type:"post",
		data:{"id":studentId},
		url:"proxyteacherstudent/get", 
		success:function(data){			
			 /* $("#studentId").val(data.id); */
				$("#studentName").val(data.name);
				$("#studentSex").val(data.sexText);
				$("#studentAge").val(data.age);
				$("#studentTel").val(data.tel);
				$("#studentAddress").val(data.address);
				$("#studentClassroom").val(data.classroom.name);		
		}
			
		
		 
	})
	$('#update_win').window('open');
	
}
function updateclose(){
	$('#update_win').window('close');
}
function cdup(){
	$.ajax({
        url:"proxyCommissionDetails1/proxyCommissionSkip",
        type:"post",
        dataType : "json",
        success : function(data, textStatus, xhr) {
        		window.history.back(-1);
        },
        error : function(data, textStatus, xhr) {                
                /* setTimeout(function(){
                	alert("点击确定返回主页！");
                	window.location.href="commission/commissionSkip";}, 1); */
                window.history.back(-1);
                /* window.history.back(); */          
        }
	});
}
</script>
</head>
<body>

<div class="headercss">

	<fieldset class="form_fieldset">
		<p  class="h1" style="text-align: center">${name}老师${year}年${month}月提成详情</p	>  
		<div style="height: 80px">
	     	<fieldset class="form_fieldset"> 
		         <ul>
					<li class="my_li"><label style="font-size: 15px">当前月份提成：${money}元</label></li>
					<li class="my_li"><label style="font-size: 15px">未提成金额：${hasNotPaid}元</label></li>					
				 </ul>
				 <div class="searchbutton"> 
						<div class="ipu">
				 <input type="button" value="返回上一级 " class="easyui-linkbutton"  onclick="cdup()" style="width: 100px;height: 35px;margin-left: 30px; float: right;">
				<!--  <input type="button" value="导出 " class="easyui-linkbutton"  onclick="" style="width: 100px;height: 35px;margin-left: 30px; float: right;"> -->
							</div> 						
	            
			 	</div>
		  </fieldset>
		</div>
	</fieldset>							     		 


				
	     
		  
		   
		 	
		
		 

</div>
<div id="update_win" class="easyui-window" title="学生详情" closed="true"style="margin: 0 auto;">
		
		<input type="hidden" id="studentId" name="id">
		<div class="searchdiv"><label class="ui_font">姓名 : </label><input id="studentName"  name="name" type="text"  style="border: none"  readonly="readonly"  ></div>
		<div class="searchdiv"><label class="ui_font">性别 : </label><input id="studentSex"  type="text" name="sex" style="border: none" readonly="readonly"> </div>
		<div class="searchdiv"><label class="ui_font">年龄 : </label><input id="studentAge"  name="age" type="text" style="border: none" readonly="readonly"></div>
		<div class="searchdiv"><label class="ui_font">电话 : </label><input id="studentTel" type="text" name="tel" style="border: none" readonly="readonly"> </div>
		<div class="searchdiv"><label class="ui_font">地址 : </label><input id="studentAddress"  name="address" type="text" style="border: none" readonly="readonly"></div>
		<div class="searchdiv"><label class="ui_font">班级 : </label><input id="studentClassroom"  type="text" name="classroom" style="border: none" readonly="readonly"> </div>	
	</div>	


	<div id="dd" class="easyui-window" closed="true" ></div> 
	

		<div class="cc">
			<table id="dg">
			</table>
		
		</div>
</body>
</html>