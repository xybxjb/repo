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
}
	.cc{
		margin-left: 10px;
	    margin-right: 10px;
	    margin-top: -5px;
	  
	}
	
</style>
<script type="text/javascript">
$(function(){
	init();
	init2();//查询当月学费总金额
	init3();//查询未缴纳学费总额
	
}); 
function init2(){
	$.ajax({
		url:"commission/commissionTatal",
		type:"post",
		dataType:"json",
		success:function(data){
			$("#totalamount").html("当月学费总金额："+data+"元")
		}
	})
}	
function init3(){
	$.ajax({
		url:"commission/commissionNo",
		type:"post",
		dataType:"json",
		success:function(data){
			$("#commissionno").html("未缴纳学费总金额："+data+"元")
		}
	})
}	
function init(){
	$('#dg').datagrid({
        url: 'commission/commiss',//请求方法的地址
        title: '各团队详情',
        height: window.innerHeight-160, 
        fitColumns: true, //列自适应 
        nowrap: true,//禁止文字自动换行
        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
        pagination: true,//是否有分页
        singleSelect: true,//是否单行选择
        pagePosition: 'bottom',//分页符在底部,可选参数为top，bottom，
        pageSize:10,//页大小，一页多少条数据
        pageNumber: 1,//默认当前的页码
        pageList: [10, 30, 50],//一页可显示数据的条目 
        queryParams:{},//往后台传递参数，json格式 */
        columns: [[
            /* { field: 'ck', checkbox:true, align: 'center', width: 50 },  */
            { field: 'id', title: 'ID', width: 10, align: 'center',hidden:true}, 
            {
                field: 'name', title: '团队长姓名', width: 50, align: 'center'
            },
            { 
            	field: 'amount', title: '团队提成总额(元)', width: 50, align: 'center'
            }, 
            {
                field: 'noPaid', title: '团队未提成金额(元)', width: 50, align: 'center'
            },
            {
                field: 'address', title: '家庭住址', width: 50, align: 'center'
            },
            {
                field: 'tel', title: '联系方式', width: 50, align: 'center'
            },
            {
                field: 'rankName', title: '级别', width: 60, fixed:true, align: 'center',formatter: function (value, row, index) {
                	return row.ranks.name;
                }
            },
            /* {
                field: 'deposit', title: '可提成人数', width: 90, align: 'center'
            }, */
            { field: 'opt', title: '操作', width: 80, align: 'center', formatter: function (value, row, index) {
                var str = "";
                str += '<a href="proxyCommissionDetails1/jump2?id='+row.id+'">详情</a>';
                //str += '<a href="commission/aa?id='+row.id+'" onclick="if(confirm(\'确认删除\')==false)return false;">删除</a>';
                return str;
            } }
        ]]
    });
	
	$('#exportWin').window('close');
}
 function check(){
	var name=document.getElementById("teachername").value;
	var teachercid=document.getElementById("teachercid").value;
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 	
	if (name==""||name==null) {
        
        alert("请输入招生老师姓名");return false;
    }else{
    	
    }
 	if (teachercid==""||teachercid==null||reg.test(teachercid)===false) {
        
        alert("请输入正确的身份证号码");return false;
    } 
 	
} 


 function inputExcel(){  //导出excel
	 
	$('#exportWin').window('open');
	$('#exportWin').window({
		title:'导出文件',
		modal:true
	});
	 $.ajax({
			url:"commission/print1",//请求url地址
			success:function(data){
				var str='<%=basePath+"/" %>'+data;
				$("#xiazai").attr("href",str);
			}
	    })
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
		$.ajax({
			type:"post",//请求方式
			url:"computer/getstateAll",//请求url地址
			async:true ,//请求是否异常，默认为异常，这也是ajax重要性
			success:function(data){
				$('#computerStateSelect').html('');
				var str = '<option>---请选择---</option>';
				for(var i = 0;i<data.length;i++){
					str += '<option value="'+data[i].id+'">'+data[i].state+'</option>'
				}
				$('#computerStateSelect').append(str);
			}
		});
	 $('#add_win').window('open');
	
} 					

function save(name,cid,yearmonth){
	var data ={"name":name,"cid":cid,"yearmonth":yearmonth}  
	var flag=true;
/* 	$('#search_form .easyui-textbox').each(function(){
		
		
		console.log($(this).val())
		  if($(this).val()==null||$(this).val()==""){
			 alert($(this).parent().parent().children().eq("0").text()+"不能为空");
			 flag=false;
			 return flag;
		 }   
	}) */
	if(flag==true){
		 $.ajax({
				type:"post",
				data:data,
				dataType:"json",
				url:""			
			}) 
	}
		 
	
}
function close(){
	$('#add_win').window('close');
}

function update_win(id){
	 $.ajax({
		type:"post",
		data:{"id":id},
		url:"computer/get", 
		success:function(data){
			
					console.log(id)
					 $("#eid").val(data.id);
						$("#emodel").textbox("setValue",data.model);
						$("#edescription").textbox("setValue",data.description);
						$("#ecpu").textbox("setValue",data.cpu);
						$("#edisk").textbox("setValue",data.disk);
						$("#eram").textbox("setValue",data.ram);
						$("#edeposit").textbox("setValue",data.deposit);
						$("#erent").textbox("setValue",data.rent);		
		}
			
		
		 
	})
	$('#update_win').window('open');
	
}
function updateclose(){
	$('#update_win').window('close');
}

</script >  
</head>
<body>
<div class="headercss">
		
	<form id="search_form" action="proxyCommissionDetails1/proxyDeta" method="post" onsubmit="return check()">
		<fieldset class="form_fieldset">
			<div class="searchdiv" style="width: 300px"><label class="ui_font" >招生老师姓名 : </label><input name="name" type="text" id="teachername" class="easyui-textbox"></div>
			<div class="searchdiv" style="width: 250px"><label class="ui_font">身份证号 : </label><input type="text" name="cid" id="teachercid" class="easyui-textbox"> </div>
			<div class="searchdiv" style=""><label class="ui_font">月   份 : </label><input id="attYearMonth" editable="false" name="yearmonth" class="easyui-datebox"  style="width: 172px" /> </div>
			<div class="button_size" style="width: 250px">

		   <input type="button" class="inputstyle"  onclick="inputExcel()"value="导出" style="height: 30px;width: 60px;margin-right: 10px;border-radius:5px " >

			<a href="proxyCommissionDetails1/jump5"><input type="button" class="inputstyle" onclick="" value="生成工资单" style="height: 30px;width: 80px;margin-right: 10px;border-radius:5px"></a>
			<input type="submit" class="inputsyle" onclick="check()" value="查询" style="height: 30px;width: 60px;margin-right: 10px;border-radius:5px">
			</div>
		
		</fieldset>
	</form>


</div> 


		<!-- 导出excel 浏览器自动下载 -->
	<div id="exportWin" class="easyui-window">
		  	    <%-- <a href="<%=basePath+"/" %>static/xls/xls.xls">导出到本地</a> --%>
		  	    <a id="xiazai" href="javascript:void(0)">导出到本地</a>
		</div>




<div class="headercss" style="height: 50px;border: 1px solid #95B8E7;position: relative;margin-top: -10px;margin-bottom: 10px;">
	<ul style="list-style: none;">
	<li id="totalamount" style="float: left;border: 1px solid #95B8E7;margin-right: 180px;margin-top:12px;font-size: 20px;margin-left: 10px;font-family: 宋体 ">当月学费总金额：xxx元</li>
	<li  style="float: left;border: 1px solid #95B8E7;margin-right: 180px;margin-top:12px;font-size: 20px;margin-left: 10px;font-family: 宋体 ">当月提成总金额：${money2}元</li>
	<li id="commissionno" style="float: left;border: 1px solid #95B8E7;margin-top:12px;font-size: 20px;font-family: 宋体 ">未缴纳学费总金额：xxx元</li>
	</ul>

</div>
 <div class="cc">
			<table id="dg">
			</table>
		
		</div>
<script type="text/javascript">
$(function() {

   $('#attYearMonth').datebox({
       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
       onShowPanel: function () {
          //触发click事件弹出月份层
          span.trigger('click'); 
          if (!tds)
            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
            setTimeout(function() { 
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function(e) {
                   //禁止冒泡执行easyui给月份绑定的事件
                   e.stopPropagation(); 
                   //得到年份
                   var year = /\d{4}/.exec(span.html())[0] ,
                   //月份
                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
                   month = parseInt($(this).attr('abbr'), 10);  

         //隐藏日期对象                     
         $('#attYearMonth').datebox('hidePanel') 
           //设置日期的值
           .datebox('setValue', year + '-' + month); 
                        });
                    }, 0);
            },
            //配置parser，返回选择的日期
            parser: function (s) {
                if (!s) return new Date();
                var arr = s.split('-');
                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
            },
            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
            formatter: function (d) { 
                var currentMonth = (d.getMonth()+1);
                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
                return d.getFullYear() + '-' + currentMonthStr; 
            }
        });

        //日期选择对象
        var p = $('#attYearMonth').datebox('panel'), 
        //日期选择对象中月份
        tds = false, 
        //显示月份层的触发控件
        span = p.find('span.calendar-text'); 
        var curr_time = new Date();

        //设置前当月
       /*  $("#attYearMonth").datebox("setValue", myformatter(curr_time)); */
});
		//格式化日期
		function myformatter(date) {
		    //获取年份-9
		    var y = date.getFullYear();
		    //获取月份
		    var m = date.getMonth() + 1;
		    return y + '-' + m;
		}
	</script>
</body>
