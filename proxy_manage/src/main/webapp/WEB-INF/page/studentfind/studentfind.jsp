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
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="static/css/default.css"	type="text/css" />
<script type="text/javascript"	src="static/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"	type="text/css" />
<title>Insert title here</title>
<style type="text/css">
.easyui-window{
		height:350px;
		width:420px;
	}
	.col-md-3{
		text-align:justify;
		margin-bottom:10px;
		margin-left:30px;
	}
	
</style>

<script type="text/javascript">
var isOpen = 0;
$(function(){
	init();
	close();
});
	function init(){
		$('#listData').datagrid({
	        url: 'student/listAll',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-100, 
	        fitColumns: true, //列自适应 
	        nowrap: true,//禁止文字自动换行
	        pagination:true,
	        singleSelect: true,
	        pageNumber:1,
	        pageSize:10,
	        pageList:[10,15,30],
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[
	            { field: 'id', title: 'id',  align: 'center',hidden:true},
	            {field: 'name', title: '姓名', width: 40, align: 'center'},
	            {field: 'joinTime', title: '入学时间', width: 40, align: 'center'},
	            {field: 'classroom', title: '班级', width: 40, align: 'center',formatter:function (value,row,index){
	            	return value.name;
	            	}
	            },
	            {field: 'sexText', title: '性别', width: 20, align: 'center'},
	            {field: 'age', title: '年龄', width: 20, align: 'center'},
	            {field: 'dormitorynote', title: '住宿情况', width: 25, align: 'center',formatter:function (value,row,index){
	            	var str="";
	            	if(row.dormitorynote==1){ 
	            		str='<input  type="button" style="width: 100%" value="'+'是'+ '" onclick="xiangqing_boarder('+ row.id + ')" >'
	            		}else{ str="否"}
	            	return str;
            	}},
	            {field: 'idCard', title: '身份证号', width: 60, align: 'center'},
	            {field: 'tel', title: '电话', width: 65, align: 'center'},
	            {field: 'findJobText', title: '是否就业', width: 25, align: 'center'},
	            {field: 'persons', title: '家长电话', width: 60, align: 'center',formatter:function (person,row,index){
	            	
	            	return person[0].tel;}
	            },
	            {field: 'address', title: '地址', width: 60, align: 'center'},
	            {field: 'remarks', title: '备注', width: 60, align: 'center'},
	            {field: 'opt', title: '操作', align: 'center',width: 70,formatter:function (value,row,index){
	/*             	console.log(value);
	            	console.log(row);
	            	console.log(index); */
	            	var str = '<a href="javascript:void(0);" onclick="xiangqing('+index+')">详情</a>|';
	            	 str+='<a href="javascript:void(0);" onclick="edit('+index+');">修改</a>|';
	            	 str+='<a href="javascript:void(0);" onclick="del('+row.id+');">删除</a>|';
	            	 str+='<a href="javascript:void(0);" onclick="inputExcel1('+row.id+');">导出</a>';
	            	 
	            	 return str;
	            	}
	           
	            }
	        ]]
	    });
		$('#exportWin').window('close');
		$('#exportWin1').window('close');
	}
	
	function close(){
		$('#exportWin').window('close');
	}
	function del(id){
		$.ajax({
			type:"post",
			url:"student/delete",
			async:true,
			data:{
				id:id
			},
			success:function(data){
				if(data=="error"){
					alert("删除错误，该学生有缴费信息");
				}
				if(data=="success"){
					alert("删除成功");
				}
				
				init();
				
			}})
		
	}
	
	//模糊查询
	function search() {
		var fields = $('#search_form').serializeArray();
		var params = $("#listData").datagrid('options').queryParams;
		$.each(fields, function(i, field) {
			params[field.name] = field.value;
		});
		$('#listData').datagrid('reload')
	}
	
	function clean(){ 
		$('#search_form').form('clear')
		init();
		
	}
	//详情
	function xiangqing(index){
		
		var array =[],columns=[],arr2=[]; 
		var row = $('#listData').datagrid('getData').rows[index];
		/* console.log(row) */
			$('#xname').html(row.name);
			$('#xidcard').html(row.idCard);
			$('#xage').html(row.age);
			$('#xsex').html(row.sexText);
			$('#xtel').html(row.tel);
			$('#xaddress').html(row.address);
			$('#xclassroom').html(row.classroom.name);
			$('#xbirthday').html(row.birthday);
			$('#xjoinTime').html(row.joinTime);
			$('#xmajor').html(row.major.name);
			$('#xstudyTime').html(row.studyTime+"个月");
			

			$('#xjiazhang').html(row.persons[0].name);
			$('#xdianhuas').html(row.persons[0].tel);
			$('#xlaoshi').html(row.proxyTeacher.name);
			$('#xlaoshidianhua').html(row.proxyTeacher.tel);
			
// 			console.log(row);
// 			console.log(row.remarks);
			$('#remarksl').html(row.remarks);
			console.log($('#remarks'));
			
				$('#xiangqing_win').window({
					
					
					title:"详细信息",
					width: "1000px", 
					height:"600px",
					 left:"15%",
					 top:"10%" 
				
				
					
				});
				//缴费情况
				$('#feeData').datagrid({
			        url: 'fee/select',//请求方法的地址
			        title: '缴费情况',
			        height: 200, 
			        fitColumns: true, //列自适应 
			        nowrap: true,//禁止文字自动换行
			        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
			        queryParams:{
			        	id:row.id
			        },//往后台传递参数，json格式 
			        columns : [ [
									{
										field : 'type',
										title : '缴费类型',
										width : 10,
										align : 'center',
									 
									},

									{
										field : 'payment',
										title : '缴费方式',
										width : 10,
										align : 'center',
										formatter:function(val){return val.name;}
									},
									{
										field : 'paymentTime',
										title : '缴费时间',
										width : 10,
										align : 'center',

									},
									{
										field : 'amount',
										title : '缴费金额',
										width : 10,
										align : 'center',
								 
									},
									{
										field : 'remarks',
										title : '备注',
										width : 10,
										align : 'center',
									}
									
									 ] ]
			    }); 
				//学生各科考试成绩
				 $.ajax({
					url:"course/getCourses",
					data:{id:row.id} ,
					success:function(data){ hang =data.length;  arr2=[]; index1=0; 
					 array.push({  field: 'student', title: '学生姓名', width:'80px', align: 'center',formatter:function (value, row, index) { 
						 return row.student.name}}); 
					 array.push({  field: 'examDate', title: '考试时间', width:'80px', align: 'center'});
					 array.push({  field: 'name', title: '考试名称', width:'100px', align: 'center'});
					 for(var i=0;i<data.length;i++){   arr2[i]=data[i];    
						 array.push({field: 'kemu', title: data[i].name, width:'80px', align: 'center' ,formatter:function (value, row, index) {

							if(index1==hang)index1=0;
							 	if(row.scores[index1].score>=0){  return row.scores[index1++].score}
								else if(row.scores[index1].score==-1) {index1++; return null}
								else{  index1++; 
								return	'<div  style="width: 25px;height: 25px; background: #adb1b8; border-radius: 100%;position:relative;margin:0 auto">'+
								'<div id=""  style="width: 12px;height: 5px;background: white;position: absolute;left: 7px;top: 10px;"></div></div>' 
								}
						 	}
						 });}
					 columns.push(array); 
					 $('#examData').datagrid({url:'exam/getStudentExam',title: '各科考试成绩', height: 200, fitColumns: true,nowrap: false, loadMsg: '正在加载信息...',pagination: true,singleSelect: true, pagePosition: 'bottom',pageSize:10,pageNumber: 1,pageList: [10, 30, 50],queryParams:{name:row.name},columns:columns,}); 
					 array=[]; columns=[];
					}
				}) 
				
				$('#xiangqing_win').window('open');
				
		
	}
	
	//修改
	function edit(index){
		
		var row = $('#listData').datagrid('getData').rows[index];
		console.log(row)
		$('#id').val(row.id);
		$('#name').textbox('setValue',row.name);
		$("#remarks").textbox("setValue",row.remarks);
		$('#xdianhua').textbox('setValue',row.persons[0].tel);
		$('#tuition').textbox('setValue',row.tuition);
		$('#quarterage').textbox('setValue',row.quarterage);
		$('#incidentals').textbox('setValue',row.incidentals);
		$('#initialResideDate').textbox('setValue',row.initialResideDate);
		$('#studyTime').combobox({
			url : 'studyTime/getAll',
			valueField : 'studyTime',
			selectOnNavigation : false,
			textField : 'studyTime',	
			editable:false,
		})
		$("#studyTime").combobox("select",row.studyTime);
		$('#proxyTeacher').combobox({
			url : 'proxyTeacher/getAll',
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',	
			editable:false,
		})
		$("#proxyTeacher").combobox("select",row.proxyTeacher.id);		
		$('#classroomId').combobox({
				url : 'classroom/getAll',
				valueField : 'id',
				selectOnNavigation : false,
				textField : 'name',	
				editable:false,
		})
		$("#classroomId").combobox("select",row.classroom.id);
		
		$('#majorId').combobox({
				url : 'major/getAll',
				valueField : 'id',
				selectOnNavigation : false,
				textField : 'name',	
				editable:false,
		})
		$("#majorId").combobox("select",row.major.id);
		
		$('#idCard').textbox('setValue',row.idCard);
			$('#age').textbox('setValue',row.age);
			$('#tel').textbox('setValue',row.tel);
			$('#birthday').textbox('setValue',row.birthday);
		
			var areastr=row.address;
			var areaarr=areastr.split("-");
			testBoth("#sheng","#shi","#xian",areaarr[0],areaarr[1],areaarr[2]);	
		$('#editWin').window({
			title: '修改用户',
			width: 530,
			height:380,
		});
		$('#editWin').window('open');	
		idCard();
		center($("#editWin"));
	}
	function center(obj) {
        //obj这个参数是弹出框的整个对象
        var screenWidth = $(window).width(), screenHeigth = $(window).height();
        //获取屏幕宽高
         var scollTop = $(document).scrollTop();
        //当前窗口距离页面顶部的距离
        var objLeft = (screenWidth - obj.width()) / 2;
        ///弹出框距离左侧距离
        var objTop = (screenHeigth - obj.height()) / 2 + scollTop;
        ///弹出框距离顶部的距离
        obj.css({
            left:objLeft + "px",
            top:objTop + "px"
        });
     //   obj.fadeIn(500);
        //弹出框淡入
        isOpen = 1;
        //弹出框打开后这个变量置1 说明弹出框是打开装填
        //当窗口大小发生改变时
        $(window).resize(function() {
            //只有isOpen状态下才执行
            if (isOpen == 1) {
                //重新获取数据
                screenWidth = $(window).width();
                screenHeigth = $(window).height();
                var scollTop = $(document).scrollTop();
                objLeft = (screenWidth - obj.width()) / 2;
                var objTop = (screenHeigth - obj.height()) / 2 + scollTop;
                obj.css({
                    left:objLeft + "px",
                    top:objTop + "px"
                });
                obj.fadeIn(500);
            }
        });
        //当滚动条发生改变的时候
        $(window).scroll(function() {
            if (isOpen == 1) {
                //重新获取数据
                screenWidth = $(window).width();
                screenHeigth = $(window).height();
                var scollTop = $(document).scrollTop();
                objLeft = (screenWidth - obj.width()) / 2;
                var objTop = (screenHeigth - obj.height()) / 2 + scollTop;
                obj.css({
                    left:objLeft + "px",
                    top:objTop + "px"
                });
                obj.fadeIn(500);
            }
        });
    }
	
	//身份证号
	function idCard(){
		$('#idCard').textbox({    
		   onChange:function(idCard){
			if(idCard.length==18){
				var age=idCard.substring(6,14);
				var agenian=age.substring(0,4);//年
				 var ageyue=age.substring(4,6);//月
				 var ageri=age.substring(6,8);//日
				 $('#birthday').textbox('setValue',agenian+"-"+ageyue+"-"+ageri);
				 var mydate=new Date();
				var tages=mydate.getFullYear()-agenian;				 
				if((mydate.getMonth()+1)-ageyue>0){
					$('#age').textbox('setValue',tages);
				}
				if((mydate.getMonth()+1)-ageyue<0){
					$('#age').textbox('setValue',tages-1);
				}
				if((mydate.getMonth()+1)-ageyue==0){
					if(mydate.getDate()-ageri<0){
						$('#age').textbox('setValue',tages-1);
					}else{
						$('#age').textbox('setValue',tages);
					}
				}
		} else{
				   alert("请输入合法的身份证号");
				   $('#idCard').next('span').find('input').focus();
			   }
		   }
		})
	}
	
//三级联动
	
	function testBoth(sheng,shi,xian,pro,city,country){
		$.ajax({
			type:"post",
			url:"area/check",
			async:true,
			success:function(data){
					for (var i=0;i<data.length;i++) {
					var str='<option  value="'+data[i].name+'"';
					if(data[i].name==pro){
						str+=" selected";
						}
					str+=">"+data[i].name+"</option>"
					$(sheng).append(str)
					for (var j=0;j<data[i].children.length;j++) {
						var shistr = '<option  value="'+data[i].children[j].name+'"';
						 if(data[i].children[j].name==city){
							shistr+=' selected';
							} 
						shistr+=">"+data[i].children[j].name+"</option>";
						$(shi).append(shistr);
						for(var k=0;k<data[i].children[j].children.length;k++){
							var countrystr = '<option  value="'+data[i].children[j].children[k].name+'"';
							 if(data[i].children[j].children[k].name==country){
								countrystr+=' selected';
								} 
							countrystr+=">"+data[i].children[j].children[k].name+"</option>";
							$(xian).append(countrystr);
							}
					}
				}
				$(sheng).change(function() {
				var shengname = $(this).val();
				for(var s = 0; s < data.length; s++) {
					var xhname = data[s].name;
					if(shengname == xhname) {
						$(shi).children().remove()
						$(xian).children().remove()
						for(var c = 0; c < data[s].children.length; c++) {
							$(shi).append('<option  value="'+data[s].children[c].name+'" >' + data[s].children[c].name + "</option>")
							if(c == 0) {
								$(xian).children().remove()
								for(var nn = 0; nn < data[s].children[c].children.length; nn++) {
									$(xian).append('<option  value="'+data[s].children[c].children[nn].name+'" >' + data[s].children[c].children[nn].name + "</option>")
								}
							}
						}
					}
			}
		})
			$(shi).change(function() {
			var shinames = $(this).val();
			for(var she = 0; she < data.length; she++) {
				for(var mm = 0; mm < data[she].children.length; mm++) {
					if(shinames == data[she].children[mm].name) {
						$(xian).children().remove()
						for(var nns = 0; nns < data[she].children[mm].children.length; nns++) {
							$(xian).append('<option  value="'+data[she].children[mm].children[nns].name+'" >' + data[she].children[mm].children[nns].name + "</option>")
						}
					}
				}
			}
		})	
			}
		});
		
	};
	
	function update(){
		var data = $("#editForm").serialize(); 
			$.ajax({
				url: "student/update",    //请求的url地址
				async: true, //请求是否异步，默认为异步，这也是ajax重要特性
				data: data,
				type: "post",   //请求方式
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				success: function(req){
					
					$('#editWin').window('close');
					$('#listData').datagrid('reload')
					$('#editForm').form('clear');
				}
		    	   
			});
	}
//导出excel
function inputExcel(){ 
	 
	$('#exportWin').window('open');
	$('#exportWin').window({
		title:'导出文件',
		modal:true
	});
	 $.ajax({
			url:"student/print1",//请求url地址
			success:function(data){
				var str='<%=basePath+"/" %>'+data;
				$("#xiazai").attr("href",str);
			}
	    })
} 
//导出详情excel
function inputExcel1(id){ 
	$('#exportWin1').window('open');
	$('#exportWin1').window({
		title:'导出文件',
		modal:true
	});
	 $.ajax({
			url:"student/print2",//请求url地址
			data:{"id":id},
			success:function(data){
				var str='<%=basePath+"/" %>'+data;
				$("#xiazai1").attr("href",str);
			}
	    })
} 
 	function xiangqing_boarder(id) {
		
		$('#xiangqing_boarder').window('open');

		$.ajax({
				url: "studentDormitory/getStudentId?",   
				async: true, //请求是否异步，默认为异步，这也是ajax重要特性
				data: {"id":id},
				type: "post",   //请求方式
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				success: function(data){
					$("#number").textbox("setValue",data.dormitroy.number);
					$("#adress").textbox("setValue",data.dormitroy.adress);
					
				}
		    	   
			});

		
	}	
	
	
	
	function que(){
		$("#boarderform").form("clear");
		$("#xiangqing_boarder").window("close");
	}
</script>
</head>
<body>



<div id="xiangqing_boarder" class="easyui-window" title="住宿详情" style="width:300px;height:200px"   
        data-options="iconCls:'icon-save',modal:true,closed:true">
        <form id="boarderform">
    			 <div style="margin-top:5px" >		
					<label >宿舍号：</label><input id="number"   class="easyui-textbox" /><br>
				</div> 
				<div style="margin-top:5px" >		
					<label >地       址：</label><input id="adress"   class="easyui-textbox" /><br>
				</div> 
     	<div style="margin-left: 130px;margin-top: 10px"><input type="button" value="确认" onclick="que()" ></div>
     	</form>
</div> 
<form id="search_form" action="student/select" method="post"
			style="margin: 0px; padding: 0px">
			<fieldset class="form_fieldset">
				<div class="form_filed" style="margin-left: 55px;">
					<label class="ui_fon  t">姓名：</label> 
					<input type="text" class="easyui-textbox" name="name">
					 <label  class="ui_font">身份证：</label>
					<input type="text" class="easyui-textbox" name="idCard">
				</div>

				<div class="searchbutton">
					<input type="button" value="查询 " class="easyui-linkbutton"
						onclick="search();"
						style="width: 70px; height: 30px;margin-left: 30px;margin-top:-3px">
					<input type="button" value="清空条件 " class="easyui-linkbutton"
						onclick="clean();"
						style="width: 70px; height: 30px;margin-left: 30px;margin-top:-3px">
						<!--导出数据  -->
					<input type="button" class="easyui-linkbutton"  onclick="inputExcel()"value="导出" 
						style="height: 30px;width: 60px;margin-right: 10px;margin-left:30px;border-radius:5px " >
				</div>
				<div style="text-align: right;"><input type="button" class="inputstyle"  onclick="inputExcel()"value="导出Excle表" style="height: 30px;width: 100px;margin-right: 10px;border-radius:5px;margin-top: -20px " >
				</div>
			</fieldset>
		</form>	
		
			
	<!--修改  -->
	<div id="editWin" class="easyui-window" closed="true" style="text-align: center;width:500px;">
		<form id="editForm" action="" >
			<input id="id" type="hidden"  name="id" />
			<div style="float: left;margin-left: 20px;margin-top: 10px;">
				<div style="margin-top:5px" >		
					<label >姓&nbsp; &nbsp;&nbsp; &nbsp;名：</label><input id="name"  name="name" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >	
					<label >身份证号：</label><input id="idCard"  name="idCard" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >	
					<label >年&nbsp; &nbsp;&nbsp; &nbsp;龄：</label><input id="age"  name="age" readonly="readonly" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >	
				<label >生&nbsp; &nbsp;&nbsp; &nbsp;日：</label><input id="birthday"  name="birthday" readonly="readonly" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >	
				<label >电&nbsp; &nbsp;&nbsp; &nbsp;话：</label><input id="tel"  name="tel" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >	
					<label >班&nbsp; &nbsp;&nbsp; &nbsp;级：</label><input id="classroomId"  name="classroom.id" class="easyui-textbox" style="width: 140px;margin-top:5px"/><br>
				</div>
				<div style="margin-top:5px" >	
					<label >专&nbsp; &nbsp;&nbsp; &nbsp;业：</label><input id="majorId"  name="major.id" class="easyui-textbox" style="width: 140px;margin-top:5px"/><br>
				</div>
				<label id="address" style="margin-left: -7px; ">地&nbsp; &nbsp;&nbsp; &nbsp;址：</label>
					<select  id="sheng" name="address"style="margin-top:5px ;width: 137px"></select><br>
					<select  id="shi" name="address" style="margin-left: 55px;width: 137px;margin-top:5px"></select><br>
					<select  id="xian" name="address" style="margin-left: 55px;width: 137px;margin-top:5px" ></select><br>
			</div>
			<div style="float: left;margin-left: 20px;margin-top: 10px">
				<div style="margin-top:5px" >
					<label >学生&nbsp;&nbsp;&nbsp;学费：</label><input id="tuition"  name="tuition" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >
					<label >学生住宿费：</label><input id="quarterage"  name="quarterage" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >
					<label >开始住宿时间：</label><input id="initialResideDate"  name="initialResideDate" class="easyui-textbox" /><br>
				</div>
				
				<div style="margin-top:5px" >
					<label >学生&nbsp;&nbsp;&nbsp;杂费：</label><input id="incidentals"  name="incidentals" class="easyui-textbox" /><br>
				</div>
				<div style="margin-top:5px" >
					<label >招生&nbsp;&nbsp;&nbsp;老师：</label><input id="proxyTeacher"  name="proxyTeacher.id" class="easyui-textbox" style="width: 140px;margin-top:5px" /><br>
				</div>
				<div style="margin-top:5px" >
					<label >学习&nbsp;&nbsp;&nbsp;时长：</label><input id="studyTime"  name="studyTime" class="easyui-textbox" style="width: 140px;margin-top:5px"/><br>
				</div>
				<div style="margin-top:5px" >
					<label >家长&nbsp;&nbsp;&nbsp;电话：</label><input id="xdianhua"  name="xdianhua" class="easyui-textbox" style="width: 140px;margin-top:5px"/><br>
				</div>
				<div style="margin-top:5px" >		
					<label >备&nbsp; &nbsp;&nbsp; &nbsp;注：</label><input id="remarks"  name="remarks"  class="easyui-textbox" /><br>
				</div>
			</div>
			<input  type="button" onclick="update();" value="保存" style="margin-top:140px;margin-right: 215px;"  />
		</form>
	</div>
	<table id="listData"></table>
	
	<!-- 详情 -->
		<div id="xiangqing_win" class="easyui-window" closed="true" style="text-align: center; width:100%;height: 270px; ">
		<form id="xiangqingFrom" style="margin-top: 10px;">

			<!-- <table class="easyui-datagrid" style="height: 100px;">
				<thead>
					<tr>
						<th data-options="field:'name',width:80,align:'center'">姓名</th>
						<th data-options="field:'sexText',width:50,align:'center'">性别</th>
						<th data-options="field:'idcard',width:100,align:'center'">身份证号</th>
						<th data-options="field:'age',width:50,align:'center'">年龄</th>
						<th data-options="field:'birthday',width:100,align:'center'">出生日期</th>
						<th data-options="field:'tel',width:100,align:'center'">电话</th>
						<th data-options="field:'joinTime',width:100,align:'center'">入学时间</th>
						<th data-options="field:'classroom',width:100,align:'center'">班级</th>
						<th data-options="field:'major',width:100,align:'center'">专业</th>
						<th data-options="field:'studyTime',width:100,align:'center'">学习时长</th>
						<th data-options="field:'address',width:100,align:'center'">地址</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input id="xname" readonly="readonly"
							style="width: 80px; text-align: center;"></td>
						<td><input id="xsex" style="width: 50px; text-align: center;"
							readonly="readonly"></td>
						<td><input id="xidcard" readonly="readonly"
							style="width: 100px; text-align: center;"></td>
						<td><input id="xage" readonly="readonly"
							style="width: 50px; text-align: center;text-align: center;"></td>
						<td><input id="xbirthday" style="width: 100px; text-align: center;"
							readonly="readonly"></td>
						<td><input id="xtel" readonly="readonly"
							style="width: 100px; text-align: center;"></td>
						<td><input id="xjoinTime" style="width: 100px; text-align: center;"
							readonly="readonly"></td>
						<td><input id="xclassroom" readonly="readonly"
							style="width: 100px; text-align: center;"></td>
						<td><input id="xmajor" style="width: 100px; text-align: center;"
							readonly="readonly"></td>
						<td><input id="xstudyTime" readonly="readonly"
							style="width: 100px; text-align: center;"></td>
                        <td><input id="xaddress" style="width: 100px;"
							readonly="readonly"></td>
					</tr>
				</tbody>
			</table> --> 
			<div style="width: 100%;height:100px;overflow: hidden;padding-left:80px">
			
			<div style="width:260px;margin-top: 10px;margin-left:20px;float: left;text-align: left;">
				
				<div class="editwindow"  style="padding-left:0px">姓　　名：
					<span id="xname"></span>
				</div>
				<div class="editwindow" style="padding-left:0px">性　　别：
					<span id="xsex" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px">身份证号：
					<span id="xidcard" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px;">年　　龄:
					<span id="xage" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px;">出生日期:
					<span id="xbirthday" ></span>
				</div>
				
			</div>
			<div style="width:260px;margin-top: 10px;margin-left:20px;float: left;text-align: left;">
				
				<div class="editwindow" style="padding-left:0px">电　　话:
					<span id="xtel" style="margin-right: 17px"></span>
				</div>	
				<div class="editwindow" style="padding-left:0px">入学时间：
					<span id="xjoinTime" style="margin-right: 42px"></span>
				</div>
				<div class="editwindow" style="padding-left:0px;">班　　级:
					<span id="xclassroom" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px">专　　业:
					<span id="xmajor" style="margin-right: 17px"></span>
				</div>
				<div class="editwindow" style="padding-left:0px">地　　址:
					<span id="xaddress" style="margin-right: 17px"></span>
				</div>
			</div>
			<div style="width:260px;margin-top: 5px;margin-left:20px;float: left;text-align: left;">
				
			
				<div class="editwindow" style="">学习时长：
					<span id="xstudyTime" style="margin-right: 42px"></span>
				</div>
					<div class="editwindow" style="padding-left:0px;">家　　长:
					<span id="xjiazhang" ></span>
				</div>
				
				<div class="editwindow" style="padding-left:0px">家长电话:
					<span id="xdianhuas" style="margin-right: 17px"></span>
				</div>
				<div class="editwindow" style="padding-left:0px">招生老师:
					<span id="xlaoshi" style="margin-right: 17px"></span>
				</div>
				<div class="editwindow" style="padding-left:0px">老师电话:
					<span id="xlaoshidianhua" style="margin-right: 17px"></span>
				</div>
				<div class="editwindow" style="padding-left:0px">备注:
					<span id="remarksl" style="margin-right: 17px"></span>
				</div>
			</div>
			
			<input type="button" class="inputstyle"  onclick="inputExcel()"value="导出Excle表" style="height: 30px;width: 100px;margin-right: 45px;border-radius:5px; " >
				
			</div>
			<table id="feeData" ></table>
			<table id="examData"></table>
		</form>
	</div>
	<!-- 导出excel 浏览器自动下载 -->
	<div id="exportWin" class="easyui-window">
		  	    <%-- <a href="<%=basePath+"/" %>static/xls/xls.xls">导出到本地</a> --%>
		 <a id="xiazai" href="javascript:void(0)">导出到本地</a>
	</div>
	<!-- 导出excel 浏览器自动下载 -->
	<div id="exportWin1" class="easyui-window">
		  	    <%-- <a href="<%=basePath+"/" %>static/xls/xls.xls">导出到本地</a> --%>
		 <a id="xiazai1" href="javascript:void(0)">导出到本地</a>
	</div>
</body>
</html>