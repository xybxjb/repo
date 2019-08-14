<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String basePath=request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
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
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css" type="text/css" />
<style type="text/css">
.suitionstate{
	appearance:none;
	-moz-appearance:none;
	-webkit-appearance:none;
	padding-right: 10px;
}
.outbox{
	margin-bottom:10px;
}
.headercss{	
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;    
	margin-bottom: 10px;
}
.div001{
	
    margin-left: 20px;
    margin-right: 20px;    
	
}
.jeiguo{
	margin-left: 10px;
	margin-right: 10px;
	margin-top: -5px;
}
.my_li02{
	list-style: none;
 	margin-left:40px;
 	margin-right:10px;
	float: right;
} 
#se{
color:#F00;
}
option{
color:#000;
}
.div001{
	
    margin-left: 20px;
    margin-right: 20px;    
}
.my_li01{
	list-style: none;
	width: 200px; 
	float: left;
	margin-top: 5px;
}
.my_li02{
	list-style: none;
 	margin-left:40px;
 	margin-right:10px;
	float: right;
	margin-top: 5px;
} 
</style>
<script type="text/javascript">
$(function () {
	
	init();
	 
});

function init(){
	$('#dg').datagrid({
        url: 'commission/commissionSkip1',//请求方法的地址
        title: '各招生老师月提成详情',
        height: window.innerHeight-130, 
        fitColumns: true, //列自适应 
        nowrap: false,//禁止文字自动换行
        /* idField: 'id',//主键列的列明 */
        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
        pagination: true,//是否有分页/
        singleSelect: true,//是否单行选择
        pagePosition: 'bottom',//分页符在底部,可选参数为top，bottom，both
        pageSize: 15,//页大小，一页多少条数据
        pageNumber: 1,//默认当前的页码
        pageList: [15, 30, 50],//一页可显示数据的条目 
        queryParams: {},//往后台传递参数，json格式 */
        
        columns: [[
            { field:'ck',checkbox:false,width:50,align:'center'},
            { field: 'proxyTeacherId', title: 'id', width: 200, align: 'center', hidden: true },
            {field: 'proxyTeacherName', title: '招生老师姓名', width: 200, align: 'center'},
             {field: 'commissionPoint', title: '提成点位', width: 200, align: 'center'}, 
            {field: 'commissionAmount', title: '提成金额', width: 200, align: 'center'},
            {
            	field: 'commissionDate', title: '结算日期', width: 200, align: 'center',formatter:function(value,row,index){
            		var newTime=new Date(row.commissionDate);
            		return newTime.getFullYear()+"-"+(newTime.getMonth()+1)+"-"+newTime.getDate();
            	}
           	
            	},
             {field: 'remarks', title: '备注', width: 200, align: 'center'

            },
            {field: 'wageState', title: '状态', width: 200, align: 'center' 
            } 
        ]],
        /* onDblClickCell: function(index,field,value){
    		$(this).datagrid('beginEdit', index);
    		var ed = $(this).datagrid('getEditor', {index:index,field:field});
    		$(ed.target).focus();
    	} */
    });	
}
function save(){
	var a=0;
	var rows = $('#dg').datagrid('getSelections');
	var b=rows.length;
	for(var i=0; i<rows.length; i++){
		var proxyteacherId=rows[i].proxyteacherId;//招生老师id
		var proxyteacherName=rows[i].proxyteacherName;//招生老师姓名
		var commissionAmount=rows[i].commissionAmount;//招生老师的提成
		var commissionDate=rows[i].commissionDate;//时间 毫秒
		var remarks1='.remarks'+i;
		var state1='#state'+i;
		var remarks=$(remarks1).val();//备注
		var state=$(state1).val();//状态
		$.ajax({
			url:"proxyteacherstudent/save",
			type:"post",
			async:false, //同步
			data:{"proxyteacherId":proxyteacherId,
				"proxyteacherName":proxyteacherName,
				"commissionAmount":commissionAmount,
				"commissionDate":commissionDate,
				"remarks":remarks,
				"state":state	
			} 
		})
		a++;
	 } 
	alert("总共选中"+b+"条数据,保存了"+a+"条数据")
}
function demand(){
	var fields =$('#search_form').serializeArray();  
    var params = $("#dg").datagrid('options').queryParams;  
    $.each( fields, function(i, field){  
        params[field.name] = field.value;   
    });  
    
    $('#dg').datagrid('reload')
}
</script>
</head>
<body>

	<div>
		<div class="headercss" style="height: 110px; border: 1px solid #95B8E7;"> 
			<div style="text-align: center;"><h1 style="font-size: 20px"> 招生老师历史提成详情</h1></div>	
			<!-- <a href="commission/commissionSkip"><button style="float: right; margin: 20px; border-radius :8px; background-color: #D2E0F2;font-size: 14px">返回</button></a> -->
			<!-- <button style="float: right; margin: 20px; border-radius :8px; background-color: #D2E0F2;font-size: 14px" onclick="save()">一键保存</button>	 -->	
				<div class="div001" style="height: 50px; border: 1px solid #95B8E7">
					<form id="search_form" action="proxyCommissionDetails1/proxyDeta" method="post" onsubmit="return check()">
						<div class="searchdiv" style="width: 262px"><label class="ui_font" >招生老师姓名: </label><input name="name" type="text" id="teachername" class="easyui-textbox"></div>
						<!-- <div class="searchdiv" style="width: 250px"><label class="ui_font">身份证号<span style="color: red">(必填)*</span> : </label><input type="text" name="cid" id="teachercid" class="easyui-textbox"> </div> -->
						<div class="searchdiv" style="width: 420px"><label class="ui_font">结算日期 ：</label>
						<input class="easyui-datebox" name="startTime"     
       						 data-options="required:true" style="width:150px"><label >&nbsp;&nbsp;至：</label> 
       						 <input class="easyui-datebox" name="endTime"     
       						 data-options="required:true" style="width:150px"> 
       					</div>
						<ul>
							<li class="my_li02"><a href="commission/commissionSkip"><input type="button" style="border-radius :8px; background-color: #D2E0F2;font-size: 14px" onclick="" value="返回"/></a></li>
							<!-- <li class="my_li02"><input type="button" style="border-radius :8px; background-color: #D2E0F2;font-size: 14px" value="打印"/></li> -->
							<li class="my_li02"><input type="button" style="border-radius :8px; background-color: #D2E0F2;font-size: 14px" value="查询" onclick="demand()"/></li>
						</ul>
					</form>
				</div>	
			</div>	
					
			
		</div>
		
		<div class="jeiguo" >
			<table id="dg">
			</table>
		</div>
	</div>
</body>
</html>