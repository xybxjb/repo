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
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"	href="static/css/default.css" type="text/css" />
<link rel="stylesheet"	href="static/css/all.css" type="text/css" />
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
 <!-- ECharts单文件引入 -->
<script type="text/javascript" src="static/build/dist/echarts.js"></script>
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
</style>
<body>
	<div class="headercss">
		<form id="form_search" action="" method="post">
			<fieldset class="form_fieldset">
				<div>
					<label>宿舍号：</label>
					<input  id="d_id" name="dId"  class="easyui-textbox"/>
					<label style="margin-left: 20px">检查时间：</label>
					<input type="text" id="cc_time"  name="checkDate" style="margin-top:5px" class="easyui-datebox"> 
					<input type="button" class="easyui-linkbutton" value="查询 " onclick="search();" style="width: 100px; height: 30px; margin-left: 30px;">
					<input type="button" class="easyui-linkbutton" value="查看卫生评比" onclick="check_sanitation();" style="width: 100px; height: 30px; margin-left: 100px;">
				</div>
			</fieldset>
		</form>
	</div>
	<table id="listData" ></table>
<!--编辑页面 -->
	<div id="w" class="easyui-window" title="信息修改" data-options="iconCls:'icon-save'" style="width:450px;height:500px;padding:10px;" closed="true">
		<form  id="fromSanitationDate">
			<div style="margin-left: 50px">
				<input style="margin-top:5px" type="hidden" name="id" id="dId" >
				<div style="margin-left: 55px">
					<input style="margin-top:5px" type="hidden" name="dormitory.id" id="ddId" >
					<div class="editwindow" style="padding-left:0px">宿舍号：
						<input  id="d_number" disabled="disabled"  class="easyui-textbox"/>
					</div>
					<div class="editwindow" style="padding-left:0px">检查时间:
						<input type="text" id="c_time"  name="checkDate" style="margin-top:5px" class="easyui-datebox"> 
					</div>
					<div class="editwindow" style="padding-left:0px">分数：&nbsp;&nbsp;&nbsp;
						<input type="text" id="grade2"  name="grade" style="margin-top:5px" class="easyui-textbox"> 
					</div>
					<div class="editwindow" style="padding-left:0px">检查人员:
						<input type="text" id="c_persan"  name="checkPerson" style="margin-top:5px" class="easyui-textbox"> 
					</div>
					<div class="editwindow" style="padding-left:0px;display: none;">照片名字:
						<input type="text" id="pic_id2"  name="pic" style="margin-top:5px" class="easyui-textbox"> 
					</div> 
				</div>
				<!-- 上传图片 -->
				<div class="row" style="width:80%;border:1px solid #95B8E7;margin-left:25px;margin-top:38px;height:200px" id="card2">	
					<div  style="width:100%;height:200px;float:left;position:relative" class="card_div2" id="card_div2">
							<h3  style="text-align: center;line-height: 70px;font-weight: 900;color:#6d88ac">宿舍卫生照片</h3>
							<div style="position: absolute;bottom: 1px;left: 60px">
								<input  style="width:180px;height:40px;" class="easyui-filebox"  name="pic" buttonIcon="icon-man"  buttonAlign="right"  id="files2"   buttonText="修改图片"    data-options="prompt:'请选择文件...'"/>	
								<img alt="图片" src=""  style="width:291px;height:200px;margin:0px -60px;display:none"  id="img2">
							</div>
							<!-- <img alt="" src="static/images/cuo3.png" class="ccd2" style="position:absolute;top:0px;right:0px;width:30px;display:none">  -->
					</div >
				</div>
				<div class="editwindow" style="padding-left:0px">
					<input type="button" value="修改" class="test" onclick="updateOrAddDate('fromSanitationDate','update');"style="text-align:center;position: absolute;left:38%;width:100px;margin-top:10px;height:30px">
				</div>
			</div>
			
		</form>
	</div>
	<div id="add_win" class="easyui-window" title="添加卫生表"  closed="true" style="width:450px;height:500px;padding:10px;">
		<form action="sanitation/addSanitation" id="sanitation_add_form" method="get">
			<div style="margin-left: 100px">
				<div class="editwindow" style="padding-left:0px">宿舍号:&nbsp;&nbsp;&nbsp;
					<input  id="dormitory_number" name="dormitory.id"  class="easyui-textbox"/>
				</div>
				<div class="editwindow" style="padding-left:0px">检查时间:
					<input type="text" id="check_time"  name="checkDate" style="margin-top:5px" class="easyui-datebox"> 
				</div>
				<div class="editwindow" style="padding-left:0px">分数：&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" id="grade"  name="grade" style="margin-top:5px" class="easyui-textbox"> 
				</div>
				<div class="editwindow" style="padding-left:0px">检查人员:
					<input type="text" id="check_persan"  name="checkPerson" style="margin-top:5px" class="easyui-textbox"> 
				</div>
				<div class="editwindow" style="padding-left:0px;display: none;">照片名字:
					<input type="text" id="pic_id"  name="pic" style="margin-top:5px" class="easyui-textbox"> 
				</div>
			</div>
			
			<!-- 上传图片 -->
			<div class="row" style="width:80%;border:1px solid #95B8E7;margin-left:52px;margin-top:38px;height:200px" id="card">	
				<div  style="width:100%;height:200px;float:left;position:relative" class="card_div" id="card_div1">
						<h3  style="text-align: center;line-height: 70px;font-weight: 900;color:#6d88ac">宿舍卫生照片</h3>
						<div style="position: absolute;bottom: 80px;left: 82px">
							<input  style="width:180px;height:40px;" class="easyui-filebox"  name="pic" buttonIcon="icon-man"  buttonAlign="right"  id="files"   buttonText="添加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "    data-options="prompt:'请选择文件...'"/>	
							<img alt="图片" src=""  style="width:332px;height:200px;margin:-80px -83px;display:none"  id="img1">
						</div>
					    <img alt="" src="static/images/cuo3.png" class="ccd" style="position:absolute;top:0px;right:0px;width:30px;display:none"> 
				</div >
			</div>
			<div class="editwindow" style="padding-left:40px;text-align: center;">
				<input type="button" value="保存" class="test" onclick="updateOrAddDate('sanitation_add_form','addSanitation');" style="margin-left: 120px">
			</div>
		</form>
	</div>
	 <!-- 展示详情 -->
	<div id="sanitation_pic" class="easyui-window" title="详情"  closed="true" style="width:600px;height:600px;padding:10px;">
			<p align="center" style="font-size: 25px"> 宿舍卫生详情</p>
				<div class="editwindow" style="padding-left:0px;width: 500px;">宿舍号:
					<span id="d_number3" ></span> <span style="margin-left: 80px">检查时间:</span><span id="c_time3" ></span>
				</div>
				<div class="editwindow" style="padding-left:0px;width: 500px;">分数：
					<span id="grade3" style="margin-right: 88px"></span> <span style="margin-left: 10px">检查人员:</span><span id="c_persan3" style="margin-right: 42px"></span>
				</div>
				<div class="editwindow" style="padding-left:0px;width: 500px;display: none;">照片名字:
					<span id="pic_id3" ></span>
				</div>
			<img alt="图片" src="" style="width: 400px;height: 300px;margin-left: 100px" id="ss_pic">
	</div>
	<!-- 卫生评比 -->
	<div id="sanitation_compare" class="easyui-window" title="卫生评比表"  closed="true" style="width:650px;height:400px;padding:10px;">
			<div class="headercss">
				<form id="form_search2" action="" method="post">
					<div>
						<label>日期查询(格式：201812)：</label>
						<input  id="s_checkDate" name="checkDate"  class="easyui-textbox"/>
						<input type="button" class="easyui-linkbutton" value="查询 " onclick="search2();" style="width: 100px; height: 30px; margin-left: 30px;">
						<input type="button" class="easyui-linkbutton" value="查看评比图表" class="test" onclick="to_echarts()" style="width: 100px; height: 30px; margin-left: 10px;">
					</div>
				</form>
			</div>
			<table id="listData2" style="height: 300px"></table>
			<div class="editwindow" style="padding-left:40px;text-align: center;">
				<input type="button" value="关闭" class="test" onclick="close_compare()" style="margin-left: 225px;margin-top: 10px">				
			</div>
	</div>
	
<!-- echarts -->	
	<div id="sanitation_echarts" class="easyui-window" title="图表"  closed="true" style="width:600px;height:600px;padding:10px;">
			<div id="main" style="height:400px"></div>
			<p align="center" style="font-size: 25px"> 宿舍卫生图标详情</p>
	</div>
</body>
<script type="text/javascript">
var imgurl = '<%= resource.getString("image_url")%>';
$(function () {
	init();
});

	function init(){
		$('#listData').datagrid({
	        url: 'sanitation/getAll',//请求方法的地址
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
	                field: 'dormitory', title: '宿舍号', width: 80, align: 'center',formatter: function(value, row, index){
						return value.number+"号宿舍";
	                }
	            },
	            {
	                field: 'checkDate', title: '检查时间', width: 80, align: 'center'
	            },
	            {
	                field: 'grade', title: '分数', width: 80, align: 'center'
	            },
	            {
	                field: 'checkPerson', title: '检查人员', width: 80, align: 'center'
	            },
	            {	
	            	field: 'opt', title:'操作',align: 'center',width: 100,formatter: function (value, row, index) {
	                    var str = "";
	                    str += '<a href="javascript:void(0);"  onclick="open_ed('+row.id+')" >修改 |</a>';
	                    str += '<a href="javascript:void(0);"  onclick="deletes('+row.id+')"> 删除 |</a>';
	                    str += '<a href="javascript:void(0);"  onclick="s_pic('+row.id+')"> 查看信息 |</a>';
	                  /*   str += '<a href="javascript:void(0);"  onclick="to_echarts('+row.dormitory.id+')">查看图表</a>'; */
	//	                    str += '<a href="javascript:void(0);"<input type="button" onclick="/* deletes('+row.id+') */">|删除</a>';
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
	}
	function init_win(){
		$('#add_win').window({
			title : '添加卫生表',
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

	// 下拉框获取宿舍号
	$("#d_number,#dormitory_number,#d_id,#D_id").combobox({          
		//后台返回的 json 数据方法地址         
		url:'Dormitory/listdormitorynumber',
		valueField:'id',//相当于option的value值
		textField:'number',//相当于<option></option>之间的显示值 value:1000    //默认显示值
		delay:500,
		/* filter: function(q, row){  
            var opts = $(this).combobox('options');  
            return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配  
            },formatter:function(row){ 
            	var opts=$(this).combobox("options"); 
            	return row[opts.textField]; 
            	} */ 
                
	});
	// 照片回显
	$('#files').filebox({
		onChange:function(value){
			if(value!='' && value != null){	
				
				var formData = new FormData();
	/* 			console.log(formData); */
				formData.append('pic',$('#filebox_file_id_4').get(0).files[0]);
				$.ajax({   	      
					 		url :"sanitation/savePice",  
					        type :'POST', 
						    data : formData,		
						    cache: false,
						    processData: false,
						    contentType: false ,	       
					        success:function(data) {  		
					    	  /*  alert(data) */
					       	   $('#pic_id').textbox("setValue",data);
					    	   $('#img1').attr('src',imgurl+data);
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
			url:"sanitation/getById",
			success:function(data){
				$("#dId").val(data.id);
				$("#ddId").val(data.dormitory.id);
				$("#d_number").textbox("setValue",data.dormitory.number);
				$("#c_time").datebox("setValue",data.checkDate);
				$("#grade2").textbox("setValue",data.grade);
				$("#c_persan").textbox("setValue",data.checkPerson);
				$("#pic_id2").textbox("setValue",data.pic);
				$("#img2").attr("src",data.pic);
				$('#card_div2 img').css('display','block');
			}
		}) 
		
		$("#w").window('open');
}	
	//更改+保存
	function updateOrAddDate(form,method){
		var data = $("#"+form).serialize();
		
		if(form=='fromSanitationDate'){
			if($('#d_number').textbox('getValue')==''){
				alert("宿舍号不能为空")
			}else if($("#c_time").textbox("getValue")==''){
				alert("检查时间不能为空")
			}else if($("#grade2").textbox("getValue")==''){
				alert("分数不能为空")
			}else if($("#c_persan").textbox("getValue")==''){
				alert("检查人员不能为空")
			}else{
				$.ajax({
					url: "sanitation/"+method,    //请求的url地址
					async: true, //请求是否异步，默认为异步，这也是ajax重要特性
					data: data,
					type: "post",   //请求方式
					success: function(req){
						$('#w').window('close');
						$('#add_win').window('close');
						$('#listData').datagrid('reload');
						$('#fromSanitationDate').form('clear');
						$('#sanitation_add_form').form('clear');
					}
			    	   
				});
			}
		}else{
			if($('#dormitory_number').textbox('getValue')==''){
				alert("宿舍号不能为空")
			}else if($("#check_time").datebox("getValue")==''){
				alert("检查时间不能为空")
			}else if($("#grade").textbox("getValue")==''){
				alert("分数不能为空")
			}else if($("#check_persan").textbox("getValue")==''){
				alert("检查人员不能为空")
			}else{
				if(confirm('录入成功后，不得修改。')==false){
					return false;
				}else {
					$.ajax({
						url: "sanitation/"+method,    //请求的url地址
						async: true, //请求是否异步，默认为异步，这也是ajax重要特性
						data: data,
						type: "post",   //请求方式
						success: function(req){
							$('#w').window('close');
							$('#add_win').window('close');
							$('#listData').datagrid('reload');
							$('#fromSanitationDate').form('clear');
							$('#sanitation_add_form').form('clear');
						}
				    	   
					});
					
					
				}
			}
		}
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
	
	
// 查看详情
	function s_pic(id){	
		$.ajax({
			type:"post",
			data:{"id":id},
			url:"sanitation/getById",
			success:function(data){
			/* 	alert(data.pic) */
				$("#ss_pic").attr("src",data.pic);
				$("#d_number3").html(data.dormitory.number);
				$("#c_time3").html(data.checkDate);
				$("#grade3").html(data.grade);
				$("#c_persan3").html(data.checkPerson);
				$("#pic_id3").html(data.pic);
			}
		}) 		
		$("#sanitation_pic").window('open');
}

//	删除
	function deletes(id){
		if(confirm('确认删除')==false){
			return false;
		}else{
			$.ajax({
				type:"post",
				url:"sanitation/del",
				data:{
					"id" : id
				},
				success : function(){
					$('#listData').datagrid('reload');
					
				}
			})
		}
		
	}	
// 卫生评比
	function check_sanitation(){
		 
		$("#sanitation_compare").window('open');
		$('#listData2').datagrid({
	        url: 'sanitation/getCount',//请求方法的地址
	        title: '卫生评比结果',
	        height: window.innerHeight-460,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	      /*   pagination:true,
	        pageSize : 20,
	        pageNumber:1,
	        pageList:[20,40,60],
	        singleSelect:true, */
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        queryParams:{},//往后台传递参数，json格式 */
	        columns: [[	        
	            {
	                field: 'A等级宿舍', title: 'A等级宿舍（/个）', width: 80, align: 'center',formatter: function(value, row, index){
	                	return value;
	                }
	            },
	            {
	                field: 'B等级宿舍', title: 'B等级宿舍(个)', width: 80, align: 'center',formatter: function(value, row, index){
	                	/* alert(value) */
						return value;
	                }
	            },
	            {
	                field: 'C等级宿舍', title: 'C等级宿舍(个)', width: 80, align: 'center'
	            },
	           
	        ]],
	     
		});
	}
	// 查询2
	function search2(){
		var fields =$('#form_search2').serializeArray();   
		
	    var params = $("#listData2").datagrid('options').queryParams;  
	    $.each( fields, function(i, field){  
	    /* 	alert(field.value); */
	        params[field.name] = field.value;   
	    });  
	    
	    $('#listData2').datagrid('reload')
	    $('#form_search2').form('clear');
	} 
	
//  关闭评比窗口
	function close_compare(){
		$("#sanitation_compare").window('close');
}

// to_echarts
	function to_echarts(){
		var checkDate = $.trim($('#s_checkDate').textbox('getValue'));
	 /* 	alert(checkDate);  */
		$("#sanitation_echarts").window('open');
		$.ajax({
			type:"post",
			url:"sanitation/getCount",
			data:{"checkDate" : checkDate},
			success : function(data){
				 var Data=data;
				 console.log(Data[0].A等级宿舍);
				 // 路径配置
		        require.config({
		            paths: {
		                echarts: 'static/build/dist'
		            }
		        });
		           // 使用
				require(
		    		[
		                'echarts',
		                'echarts/chart/pie',// 使用柱状图就加载bar模块，按需加载
		                'echarts/chart/funnel',
						
		        	],
		        	function(ec){
		        		 // 基于准备好的dom，初始化echarts图表
		       			var myChart = ec.init(document.getElementById('main')); 
		       			var option={
		       			 	 title : {
						        text: '深度数据访问来源',
						        subtext: '纯属虚构',
						        x:'center'   //  水平安放位置，默认为左侧，可选为：'center' | 'left' | 'right' | {number}
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"    //  内容格式器
						    },
						    legend: {
						        orient : 'vertical',     //orient:布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'   vertical:垂直的，垂直
						        x : 'left',    //  水平安放位置，默认为全图居中，可选为：'center' | 'left' | 'right' | {number}
						        data:['A等级宿舍(/个)','B等级宿舍(/个)','C等级宿舍(/个)']
						    },
						    toolbox: {
						        show : true,
						        feature : {
						            mark : {show: true},
						            dataView : {show: true, readOnly: false},
						            magicType : {
						                show: true, 
						                type: ['pie', 'funnel'],
						                option: {
						                    funnel: {
						                        x: '25%',
						                        width: '50%',
						                        funnelAlign: 'left',
						                        max: 1548
						                    }
						                }
						            },
						            restore : {show: true},
						            saveAsImage : {show: true}
						        }
						    },
						    calculable : true,
						    series : [
						        {
						            name:'访问来源',
						            type:'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:[
						               	{value:Data[0].A等级宿舍, name:'A等级宿舍(/个)'},
						               	{value:Data[0].B等级宿舍, name:'B等级宿舍(/个)'},
						                {value:Data[0].C等级宿舍, name:'C等级宿舍(/个)'},

						            ]
						        }
						    ]
		       			};
		       			// 为echarts对象加载数据 
		       			myChart.setOption(option); 
		        	}
				);
			}
		})
		
}
</script>
</html>