<%@ page language="java" import="java.util.ResourceBundle"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ResourceBundle resource = ResourceBundle.getBundle("const");
%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath + "/"%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="static/css/default.css" type="text/css" />
<link rel="stylesheet" href="static/css/all.css" type="text/css" />
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"
	href="static/jquery-easyui-1.4.1/themes/default/easyui.css"
	type="text/css" />
<link rel="stylesheet" href="static/jquery-easyui-1.4.1/themes/icon.css"
	type="text/css" />
<style type="text/css">
.headercss {
	margin-top: 20px;
	margin-left: 10px;
	margin-right: 10px;
	margin-bottom: 20px;
}

.form_filed1 {
	float: left;
	display: block;
	margin-left: 10px;
	margin-top: 5px;
}

.col-md-3 {
	text-align: justify;
	margin-bottom: 10px;
	margin-left: 30px;
}

.filebox {
	position: absolute;
	left: 10%;
	margin-top: 55px;
}

.textbox-button {
	width: 50px;
}
</style>

</head>
<body>
	<div>

		<!-- 模糊查询 -->
		<div class="headercss">
			<form id="search_form" action="" method="post">
				<fieldset class="form_fieldset">
					<div class="searchdiv"
						style="width: 200px; margin-top: 3px; height: 10px;">
						<label class="ui_font">姓名：</label> <input type="text" id="getname"
							name="name" class="easyui-textbox">
					</div>

					<div class="form_filed1" style="width: 400px">
						<label class="ui_font">加入时间：</label> <input class="easyui-datebox"
							id="getbegindate" name="begindate" /> <label>-</label> <input
							id="getenddate" class="easyui-datebox" name="enddate" />
					</div>

					<div class="searchdiv"
						style="width: 400px; height: 40px; margin-top: 5px; margin-left: 10px;">
						<label>&nbsp&nbsp地址：</label> <select name="address"
							id="shengSearch">
							<option value="">请选择省份</option>
						</select> <select name="address" id="shiSearch" style="width: 130px;">
							<option value="">请选择城市</option>
						</select>
					</div>


					<div class="searchdiv"
						style="width: 200px; height: 0px; margin-top: 5px;">
						<label class="ui_font" style="margin-left: -11px;">身份证：</label><input
							id="getidcard" type="text" name="idcard" class="easyui-textbox">
					</div>

					<div class="searchdiv" style="margin-top: 5px; margin-left: 32px">
						<label class="ui_font">级别：</label> <select id="parantIdselect"
							class="easyui-combobox" style="width: 130px" name="ranks.id">
						</select>
					</div>



					<div class="searchdiv" style="margin-top: 5px; margin-left: 32px">
						<label class="ui_font">上级姓名：</label> <select id="parantSJselect"
							class="easyui-combobox" style="width: 130px" name="parentProxyTeacherId.id">
						</select>
					</div>

					<br>
					<!-- <div class="searchdiv" style="margin-top: 5px; margin-left: 32px">
						<label class="ui_font">数据来源：</label> <select id="sourceSelect"
							class="easyui-combobox" style="width: 130px" name="source.id">
						</select>
					</div> -->


					<div class="searchbutton" style="float: right">

						<input class="easyui-linkbutton" type="button" value="清空"
							onclick="cleans(); " style="width: 50px"> <input
							type="button" class="easyui-linkbutton" style="width: 50px"
							value="查询 " onclick="search();"> <input type="button"
							class="easyui-linkbutton" style="width: 50px" value="添加"
							onclick="add_win();">
					</div>
				</fieldset>
			</form>
		</div>



	</div>


	<!-- 添加点位页面   -->
	<div id="addpoint" closed="true" class="easyui-window">
		<form id="addFormpoint" method="post">

			<div class="container">
				<div style="margin-left: 15px; margin-right: 15px;">
					<div style="margin-bottom: 23px;"></div>
				</div>

				<div class="row" style="width: 33%; float: left; height: 280px">
					<div class="col-md-3" style="margin-left: 43px">
						<label>点位:</label> <input class="easyui-textbox"
							style="width: 130px" type="text" id="point" name="point">
					</div>
					<div class="col-md-5" style="margin-left: 43px">
						<label>开始时间:<span></span></label> <input id="addstartdate"
							class="easyui-datebox " style="width: 130px" name="startDate"
							id="startdate" data-options="required:true,validType:'date'">
					</div>
					<div class="col-md-5" style="margin-left: 43px">
						<label>结束时间:<span></span></label> <input class="easyui-datebox"
							style="width: 130px" name="endDate" id="enddate"
							data-options="required:true,validType:'date'">
					</div>


					<div class="col-md-3">
						<div style="margin-top: 20px">
							<input type="button" class="easyui-linkbutton" id="add_submit"
								style="width: 170px; height: 40px; margin: 18px 40px 40px 0px;"
								onclick="add_addpoint('addFormpoint','addpoint')" value="保存">
						</div>
					</div>

				</div>
			</div>
		</form>
	</div>


	<!-- 修改点位页面   action="proxyTeacher/updatepoint"   -->
	<div id="editpoint" closed="true" class="easyui-window">
		<form id="editFormpoint" enctype="multipart/form-data" method="post">

			<div class="container" style="text-align: center;">
				<div style="margin-left: 15px; margin-right: 15px;">
					<div style="margin-bottom: 23px;"></div>
				</div>
				<div class="row" style="width: 33%; float: left; height: 280px">
					<div class="col-md-3" style="margin-left: 40px">
						<label>点位:</label> <input class="easyui-textbox" name="point"
							id="edit_point">
					</div>

					<div class="col-md-20" style="margin-left: 40px">
						<label style="width: 200px;">开始时间:<span></span></label> <input
							class="easyui-datebox" style="width: 130px" name="startDate"
							id="edit_startdate" data-options="required:true,validType:'date'">
					</div>
					<div class="col-md-20" style="margin-left: 40px">
						<label>结束时间:<span></span></label> <input class="easyui-datebox"
							style="width: 130px" name="endDate" id="edit_enddate"
							data-options="required:true,validType:'date'">
					</div>

					<div class="col-md-3">
						<div style="margin-top: 20px">
							<input type="button" class="easyui-linkbutton"
								style="width: 170px; height: 40px; margin: -13px 40px 40px 0px;"
								onclick="add_editpoint('editFormpoint','updatepoint')"
								value="保存">
						</div>
					</div>

				</div>


			</div>
			<input type="hidden" name="id" id="edit_id"> <input
				type="hidden" name="proxyTeacher.id" id="edit_proxyteacherid">
		</form>
	</div>

	<!-- 点位详情 -->
	<div id="xiangqing_point" class="easyui-window" closed="true">
		<form id="xiangqingpointFrom">
			<div
				style="width: 721px; height: 296px; float: left; margin-top: -4px;">
				<table id="tableid1" class="easyui-datagrid"
					style="width: 100%; height: 263px;">
					<thead>
						<tr>
							<th data-options="field:'point',width: 150,align:'center'">点位</th>
							<th data-options="field:'startDate',width: 150,align:'center'">开始日期</th>
							<th data-options="field:'endDate',width: 150,align:'center'">结束日期</th>
							<th
								data-options="field: 'opt',width: 150,  align: 'center', formatter: opt">操作</th>

							<input type="button"
								style="width: 100px; color: blue; height: 20px; margin: 10px 5px 5px 5px;"
								onclick="addpoint()" value="添加">

						</tr>

					</thead>
				</table>
			</div>
		</form>

	</div>






	<!--添加页面  -->
	<div id="add_win" closed="true" class="easyui-window"">
		<form id="addForm" action="proxyTeacher/add"
			enctype="multipart/form-data" method="post">
			<div class="container">
				<div style="margin-left: 15px; margin-right: 15px;">
					<div style="margin-bottom: 23px;"></div>
				</div>

				<div class="row"
					style="width: 33%; float: left; border-right: 1px solid #cad8e9; height: 305px">
					<div class="col-md-3" style="margin-left: 53px">
						<label>姓名:</label> <input class="easyui-textbox"
							style="width: 130px" type="text" name="name">
					</div>

					<div class="col-md-3">
						<label>身份证号:</label> <input id="idcard" class="easyui-textbox"
							style="width: 130px" type="text" name="idcard"
							data-options="required:true,validType:'idcard'">

					</div>
					<div class="col-md-3">
						<label>加入时间:<span></span></label> <input class="easyui-datebox"
							style="width: 130px" name="joinDate" id="startTime"
							data-options="required:true,validType:'date'">
					</div>
					<div class="col-md-3" style="margin-left: 53px">
						<label>级别:</label> <input class="easyui-textbox"
							style="width: 130px" id="rankselect" type="text" name="ranks.id">
					</div>
					<div class="col-md-3">
						<label>介绍类别:</label> <span>是</span> <input type="radio"
							checked="checked" value="是" name="introducerType"> <span>/否</span>
						<input type="radio" value="否" name="introducerType">
					</div>

					<div class="col-md-3" style="margin-left: 18px">

						<label>介绍人姓名:</label> <input class="easyui-textbox"
							style="width: 130px;" type="text" name="introducerName">
					</div>
					<div class="col-md-3">
						<label>上级姓名:<span></span></label> <input class="easyui-textbox"
							style="width: 130px" id="parantselect"
							name="parentProxyTeacherId.id">
					</div>
					<div style="margin-left: 53px">
						<label>地址:<span></span></label> <select name="address" id="sheng"
							style="width: 130px; margin-bottom: 10px;">
							<option value="">请选择省份</option>
						</select><br> <select name="address" id="shi"
							style="width: 130px; margin-left: 30px; margin-bottom: 10px;">
							<option value="">请选择城市</option>
						</select><br> <select name="address" id="xian"
							style="width: 130px; margin-left: 30px; margin-bottom: 10px;">
							<option value="">请选择区(县)</option>
						</select><br>
					</div>


				</div>
				<div class="row"
					style="width: 33%; float: left; border-right: 1px solid #cad8e9; height: 305px">
					<div class="col-md-3" style="margin-left: 53px">
						<label>性别:</label> <span>男</span> <input type="radio"
							checked="checked" value="男" name="sex"> <span>/女</span> <input
							type="radio" value="女" name="sex">
					</div>

					<div class="col-md-3" style="margin-left: 53px">
						<label>年龄:</label> <input id="age"
							style="width: 120px; height: 23px; border: 0" readonly="readonly"
							type="text" name="age">
					</div>
					<div class="col-md-3" style="margin-left: 53px">
						<label>状态:</label> <span>在职</span> <input type="radio"
							checked="checked" value="0" name="state"> <span>/离职</span>
						<input type="radio" value="1" name="state">
					</div>
					<div class="col-md-3" style="margin-left: 53px">
						<label>电话:</label> <input id="idcard" class="easyui-textbox"
							style="width: 130px" type="text" name="tel"
							data-options="required:true,validType:'tel'">
					</div>
					<div class="col-md-3" style="margin-left: 60px">
						<label>qq:</label> <input id="qq" class="easyui-textbox"
							style="width: 130px" type="text" name="qq"
							data-options="validType:'qq'">
					</div>
					<div class="col-md-3" style="margin-left: 53px">
						<label>微信:<span></span></label> <input class="easyui-textbox"
							style="width: 130px" type="text" name="wechat">
					</div>


					<div class="col-md-3" style="margin-left: 53px">
						<label>点位:<span></span></label> <input class="easyui-textbox"
							style="width: 130px" type="text" name="point">
					</div>
					<div class="col-md-3" style="margin-left: 18px">
						<label>银行卡账户:</label> <input class="easyui-textbox"
							style="width: 130px" type="text" name="bankCardNumber">
					</div>

					<div class="col-md-3">
						<label>点开户行:</label> <input class="easyui-textbox"
							style="width: 130px" type="text" name="openingBank">

					</div>

					<div class="col-md-3">
						<label>开户姓名:<span></span></label> <input class="easyui-textbox"
							style="width: 130px" type="text" name="bankName">
					</div>

					<div class="col-md-3">
						<div style="margin-top: 20px">
							<input type="submit" class="easyui-linkbutton"
								style="width: 263px; height: 40px; margin: 23px 40px 40px -34px;"
								value="保存">
						</div>
					</div>


				</div>

				<div style="width: 33%; float: right;">
					<label>备注:</label>
					<div class="col-md-3" style="margin-top: 10px">

						<div
							style="margin: 0 auto; border: 1px solid #95B8E7; height: 95px; width: 219px; margin-left: -12px">
							<textarea rows="5" cols="31" name="remark"
								style="border-color: #6d88ac; outline: none; border: none"></textarea>
						</div>


					</div>
					<label>银行卡图片:</label> <br />
					<div
						style="border: 1px solid #95B8E7; height: 150px; width: 220px; margin: 0 auto; position: relative; margin-top: 10px"
						class="card_div" id="card_div1">

						<input style="width: 180px; height: 40px; margin-top: 30px"
							class="easyui-filebox" name="file" buttonAlign="right" id="files"
							buttonText="添加" data-options="prompt:'请选择文件...'" /> <img
							alt="图片"
							style="width: 100%; height: 150px; margin: 0 auto; display: none"
							id="img1"> <img alt="" src="static/images/cuo3.png"
							class="ccd"
							style="position: absolute; top: 0px; right: 0px; width: 30px; display: none">
					</div>
					<div style="display: none;">
						<input class="easyui-textbox" style="width: 130px" type="text"
							name="bankcard" id="bankcard1">
					</div>
				</div>


			</div>
		</form>
	</div>
	<!--更新页面  -->
	<div id="edit_win" class="easyui-window" closed="true">

		<form id="editForm" action="proxyTeacher/update"
			enctype="multipart/form-data" method="post">

			<div style="margin-left: 15px; margin-right: 15px;">
				<div style="margin-bottom: 23px;"></div>
			</div>
			<div>
				<input type="hidden" class="easyui-textbox" name="id" id="eid">
			</div>
			<div class="row"
				style="width: 33%; float: left; border-right: 1px solid #cad8e9; height: 305px">
				<div class="col-md-3" style="margin-left: 53px">
					<label>姓名:</label> <input id="ename" class="easyui-textbox"
						style="width: 130px" type="text" name="name">
				</div>
				<div class="col-md-3">
					<label>身份证号:</label> <input id="eidcard" class="easyui-textbox"
						style="width: 130px" type="text" name="idcard"
						data-options="required:true,validType:'idcard'">
				</div>

				<div class="col-md-3">
					<label>加入时间:<span></span></label> <input id="ejoinDate"
						class="easyui-datebox" style="width: 130px" name="joinDate"
						data-options="required:true,validType:'date'">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>级别:<span></span></label> <select id="erankselect"
						class="easyui-combobox" style="width: 130px" type="text"
						name="ranks.id">
					</select>
				</div>
				<div class="col-md-3">
					<label>介绍类别:</label> <span>是</span> <input type="radio"
						checked="checked" value="是" name="introducerType"> <span>/否</span>
					<input type="radio" value="否" name="introducerType">
				</div>
				<div class="col-md-3" style="margin-left: 18px">
					<label>介绍人姓名:</label> <input id="eintroducerName"
						class="easyui-textbox" style="width: 130px;" type="text"
						name="introducerName">
				</div>

				<div class="col-md-3">
					<label>上级姓名:<span></span></label> <select id="eparantselect"
						class="easyui-combobox" style="width: 130px" type="text"
						name="parentProxyTeacherId.id">
					</select>
				</div>
				<div style="margin-left: 53px">
					<label>地址：<span></span></label> <select name="address" id="shengup"
						style="width: 130px; margin-bottom: 10px; margin-left: -10px">
						<option value="">请选择省份</option>
					</select><br> <select name="address" id="shiup"
						style="width: 130px; margin-left: 30px; margin-bottom: 10px;">
						<option value="">请选择城市</option>
					</select><br> <select name="address" id="xianup"
						style="width: 130px; margin-left: 30px; margin-bottom: 10px;">
						<option value="">请选择区(县)</option>
					</select><br>
				</div>



			</div>
			<div class="row"
				style="width: 33%; float: left; border-right: 1px solid #cad8e9; height: 305px">
				<div class="col-md-3" style="margin-left: 53px">
					<label>性别:</label> <span>男</span> <input type="radio"
						checked="checked" value="男" name="sex"> <span>/女</span> <input
						type="radio" value="女" name="sex">
				</div>

				<div class="col-md-3" style="margin-left: 53px">
					<label>年龄:</label> <input id="eage"
						style="width: 120px; height: 23px; border: 0" readonly="readonly"
						type="text" name="age">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>状态:</label> <span>在职</span> <input type="radio"
						checked="checked" value="0" name="state"> <span>/离职</span>
					<input type="radio" value="1" name="state">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>电话:</label> <input id="etel" class="easyui-textbox"
						style="width: 130px" type="text" name="tel"
						data-options="required:true,validType:'tel'">
				</div>
				<div class="col-md-3" style="margin-left: 60px">
					<label>qq:</label> <input id="eqq" class="easyui-textbox"
						style="width: 130px" type="text" name="qq"
						data-options="validType:'qq'">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>微信:<span></span></label> <input id="ewechat"
						class="easyui-textbox" style="width: 130px" type="text"
						name="wechat">
				</div>

				<div class="col-md-3" style="margin-left: 53px">
					<label>点位:<span></span></label> <input id="epoint"
						class="easyui-textbox" style="width: 130px" type="text"
						name="point">
				</div>
				<div class="col-md-3" style="margin-left: 18px">
					<label>银行卡账户:</label> <input id="ebankCardNumber"
						class="easyui-textbox" style="width: 130px" type="text"
						name="bankCardNumber">
				</div>

				<div class="col-md-3">
					<label>点开户行:</label> <input id="eopeningBank"
						class="easyui-textbox" style="width: 130px" type="text"
						name="openingBank">

				</div>

				<div class="col-md-3">
					<label>开户姓名:<span></span></label> <input id="ebankName"
						class="easyui-textbox" style="width: 130px" type="text"
						name="bankName">
				</div>

				<div class="col-md-3">
					<div style="margin-top: 20px">
						<input type="submit" class="easyui-linkbutton"
							style="width: 263px; height: 40px; margin: 23px 40px 40px -34px;"
							value="保存">
					</div>
				</div>
			</div>
			<div style="float: right; width: 33%;">

				<label>备注:</label>
				<div class="col-md-3" style="margin-top: 10px">

					<div
						style="margin: 0 auto; border: 1px solid #95B8E7; height: 95px; width: 219px; margin-left: -12px">
						<textarea rows="5" cols="31" name="remark" id="eremark"
							style="border-color: #6d88ac; outline: none; border: none"></textarea>
					</div>


				</div>
				<label>银行卡图片:</label> <br />
				<div
					style="border: 1px solid #95B8E7; height: 150px; width: 220px; margin: 0 auto; position: relative"
					class="card_div" id="card_div2">


					<input style="width: 180px; height: 40px;" class="easyui-filebox"
						name="file" buttonIcon="icon-man" buttonAlign="right" id="files2"
						buttonText="添加" data-options="prompt:'请选择文件...'" /> <img alt="图片"
						style="width: 100%; height: 150px; margin: 0 auto; display: none"
						id="ebankcard" onerror="javascript:this.src=''"> <img alt=""
						src="static/images/cuo3.png" class="cdd"
						style="position: absolute; top: 0px; right: 0px; width: 30px; display: none">
				</div>
				<div style="display: none;">
					<input class="easyui-textbox" style="width: 130px" type="text"
						name="bankcard" id="bankcard2">
				</div>
			</div>

		</form>
	</div>

	<!-- 详情 -->
	<div id="xiangqing_win" class="easyui-window" closed="true">
		<form id="xiangqingFrom" action="">

			<div class="row"
				style="width: 50%; height: 300px; float: left; margin-top: 20px; border-right: 1px solid #cad8e9;">
				<input type="hidden" name="id" id="sid">
				<div class="col-md-3" style="margin-left: 53px">
					<label>姓名：</label><input type="text" id="sname" name="name"
						style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>性别：</label><input type="text" id="ssex" name="sex"
						style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>年龄：</label><input type="text" id="sage" name="age"
						style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3">
					<label>身份证号：</label><input type="text" id="sidcard" name="idcard"
						style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3">
					<label>加入时间：</label><input type="text" id="sjoinDate"
						name="joinDate" style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>状态：</label><input type="text" id="sstate" name="state"
						style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3">
					<label>介绍类别：</label><input type="text" id="sintroducerType"
						name="introducerType" style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3" style="margin-left: 18px">
					<label>介绍人姓名：</label><input type="text" id="sintroducerName"
						name="introducerName" style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3">
					<label>上级姓名：</label><input type="text" id="sparentProxyTeacherId"
						name="parentProxyTeacherId.id" style="border: none;"
						readonly="readonly">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>QQ：</label><input type="text" id="sqq" name="qq"
						style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>微信：</label><input type="text" id="swechat" name="wechat"
						style="border: none;" readonly="readonly">
				</div>
				<div style="margin-left: 53px">
					<label>地址：</label><input type="text" id="saddress" name="address"
						style="border: none; width: 180px" readonly="readonly">
				</div>

			</div>
			<div class="row"
				style="width: 49%; height: 300px; float: left; margin-top: 20px;">

				<div class="col-md-3" style="margin-left: 53px">
					<label>级别：</label><input type="text" id="sranks" name="ranks.id"
						style="border: none;" readonly="readonly">
				</div>

				<div class="col-md-3" style="margin-left: 53px">
					<label>数据来源：</label><input type="text" id="sources"
						name="source.id" style="border: none;" readonly="readonly">
				</div>

				<div class="col-md-3" style="margin-left: 53px">
					<label>点位：</label><input type="text" id="spoint" name="point"
						style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3">
					<label>点开户行：</label><input type="text" id="sopeningBank"
						name="openingBank" style="border: none;" readonly="readonly">
				</div>

				<div class="col-md-3">
					<label>开户姓名：</label><input type="text" id="sbankName"
						name="bankName" style="border: none;" readonly="readonly">
				</div>
				<div class="col-md-3" style="margin-left: 53px">
					<label>备注:</label>
					<div
						style="margin: 0 auto; /* border: 1px solid #95B8E7; */ height: 95px; width: 170px; margin-left: 23px">
						<!--title="${msgContent}"   可实现鼠标悬停显示全部文字效果 
							overflow: hidden; /*内容超出后隐藏*/
							text-overflow: ellipsis;/*文字隐藏后添加省略号*/
							width: 16em;/*不允许出现半汉字截断*/
						-->
						<%-- <textarea rows="5" cols="20" name="remark" id="sremark"
						    maxlength="20" 
						    onchange="this.value=this.value.substring(0, 20);" 
						    onkeydown="this.value=this.value.substring(0, 20);" 
						    onkeyup="this.value=this.value.substring(0, 20);"  
							style="border-color: #6d88ac; outline: none; border: none;"
							readonly="readonly"; 
							title="${msgContent}"></textarea>  --%>
						<input name="remark" id="sremark"
							style=" overflow: hidden; text-overflow: ellipsis; 
							border: 0px;outline:none;
							cursor: pointer;"
							readonly="readonly";
						>
					</div>


					<div class="col-md-3" style="margin-left: -42px">
						<label>银行卡图片：</label>
						<div
							style="/* border: 1px solid #95B8E7; */ height: 120px; width: 172px; margin-top: 20px; margin: 0 auto; margin-left: 63px">
							<img alt="图片加载失败" style="width: 100%; height: 120px;"
								id="sbankcard"
								onerror="javascript:this.src='static/images/ImagLoad.jpg' ">

						</div>
						<div style="display: none;">
							<input class="easyui-textbox" style="width: 130px" type="text"
								name="bankcard" id="bankcard3">
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
	<table id="listData"></table>
</body>

<script type="text/javascript" src="static/js/proxy_teacher.js">
</script>
<script type="text/javascript">function cleans(){ 
	$('#getname').textbox('setValue','');
	$('#getbegindate').textbox('setValue','');
	$('#getenddate').textbox('setValue','');
	$('#shengSearch').val('');
	$('#shiSearch').val('');
	$('#getidcard').textbox('setValue','');
	$('#parantIdselect').textbox('setValue','');
	$('#parantSJselect').textbox('setValue','')
	/* $('#sourceSelect').combobox('setValue',''); */
	init();
}

var imgurl = '<%=resource.getString("image_url")%>';

	// 添加的身份证照片回显
	$('#files').filebox(
			{
				onChange : function(value) {
					if (value != '' && value != null) {
						var formData = new FormData();
						formData.append('file',
								$('#filebox_file_id_5').get(0).files[0]);
						$.ajax({
							url : "proxyTeacher/savePic",
							type : 'POST',
							data : formData,
							cache : false,
							processData : false,
							contentType : false,
							success : function(date) {
								$('#img1').attr('src', imgurl + date);
								$('#card_div1 img').siblings().css('display',
										'none');
								$("#bankcard1").textbox('setValue', date);
								$('#card_div1 img').css('display', 'block');

							},
						});
					}
				}
			})

	//删除图片
	$('.ccd').click(function() {

		$('#files').textbox('setValue', '');
		$('#card_div1').children().css('display', 'block');
		$('#card_div1 img').css('display', 'none');
		$('#files').css('display', 'none');

	})
	// 修改图片回显
	$('#files2').filebox(
			{
				onChange : function(value) {

					if (value != '' && value != null) {
						var formData = new FormData();
						formData.append('file',
								$('#filebox_file_id_6').get(0).files[0]);
						$.ajax({
							url : "proxyTeacher/savePic",
							type : 'POST',
							data : formData,
							cache : false,
							processData : false,
							contentType : false,
							success : function(date) {
								$('#ebankcard').attr('src', imgurl + date);
								$("#bankcard2").textbox('setValue', date)
								$('#card_div2 img').siblings().css('display',
										'none');
								$('#card_div2 img').css('display', 'block');
							},
						});
					}
				}
			})

	// 删除图片
	$('.cdd').click(function() {

		$('#files2').textbox('setValue', '');
		$('#card_div2').children().css('display', 'block');
		$('#card_div2 img').css('display', 'none');
		$('#files2').css('display', 'none');

	})

	$('#file').filebox({
		onChange : function(value) {
			var formData = new FormData();
			formData.append('file', $('#filebox_file_id_2').get(0).files[0]);
			$.ajax({
				url : "proxyTeacher/savePic",
				type : 'POST',
				data : formData,
				cache : false,
				processData : false,
				contentType : false,
				success : function(date) {
					if (date != 'err')
						$('#img').attr('src', imgurl + date);
				},
			});

		}
	})
</script>


</html>