$(function() {
	init();
	parent();
	parent1();
	testBoth2();
	//source();
});
$.extend($.fn.validatebox.defaults.rules, {    
    tel: {    //第1步,选中校验谁
           validator: function(value){    //第2步, 具体编写校验规则
            var reg = /^1[3,5,7,8][0-9]{9}$/;
            
               return reg.test(value);
               
           },    
           message: '请输入正确的手机号!'   //第3步,如果输入内容不符合校验规则,出现的提示语.
       }, 
	idcard: {
		validator: function(value){
			var reg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
			return reg.test(value);
		},
		message:'请输入正确的身份证号!'
	},
	 date: {    
	        validator: function(value){
	        	var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
	        	return reg.test(value);   
	        },    
	        message: '日期格式不正确，正确格式为：2014-01-01'   
	    },   
	    qq: {    
	        validator: function(value){
	        	var reg = /^[1-9][0-9]{4,9}$/;
	        	return reg.test(value);   
	        },    
	        message: '请输入正确的QQ号'   
	    },   
});  
function init() {
	$('#listData')
			.datagrid(
					{
						url : 'proxyTeacher/list',// 请求方法的地址
						title : '查询结果',
						height : window.innerHeight - 160,
						fitColumns : true, // 列自适应
						nowrap : false,// 禁止文字自动换行
						/* idField: 'id',//主键列的列明 */
						loadMsg : '正在加载信息...',// 当数据没有加载出来时显示的文字
						pagination : true,// 是否有分页/
						singleSelect : true,// 是否单行选择
						pagePosition : 'bottom',// 分页符在底部,可选参数为top，bottom，both
						pageSize : 10,// 页大小，一页多少条数据
						pageNumber : 1,// 默认当前的页码
						pageList : [ 10, 30, 50, 100 ],// 一页可显示数据的条目
						queryParams : {},// 往后台传递参数，json格式 */
						columns : [ [
								{
									field : 'id',
									title : 'id',
									width : 20,
									align : 'center',
									hidden : true
								},
								{
									field : 'name',
									title : '姓名',
									width : 20,
									align : 'center'
								},
								{
									field : 'sex',
									title : '性别',
									width : 10,
									align : 'center'
								},
								{
									field : 'age',
									title : '年龄',
									width : 10,
									align : 'center'
								},
								{
									field : 'point',
									title : '点位',
									width : 10,
									align : 'center',
									formatter : function(value, row, index) {
										/*console.log(row)
										console.log(row.proxyTeacherPoint!=null)
										console.log(row.proxyTeacherPoint=='null')*/
										/*proxyTeacherPoint ==null 表示该点位过期 点位显示无*/
										if(row.proxyTeacherPoint == null){
											return '<input  type="button" style="width: 100%" value="'+ '无'+ '" onclick="xiangqing_point('+ index + ')" >';
										}else{
											return '<input  type="button" style="width: 100%" value="'+ row.proxyTeacherPoint.point+ '" onclick="xiangqing_point('+ index + ')" >';
										}
									}
								
								},
								{
									field : 'idcard',
									title : '身份证号',
									width : 35,
									align : 'center',
								},
								{
									field : 'address',
									title : '地址',
									width : 40,
									align : 'center',
								},
								{
									field : 'ranks',
									title : '级别',
									width : 25,
									align : 'center',
									formatter : getRanksName
								},
								{
									field : 'sourceText',
									title : '数据来源',
									width : 25,
									align : 'center' //,
									//formatter : getSourceName
								},
								{
									field : 'leaderName',
									title : '上级姓名',
									width : 40,
									align : 'center',
									formatter : getTeacherName
								},
								{
									field : 'joinDate',
									title : '加入时间',
									width : 40,
									align : 'center',
								},
								{
									field : 'tel',
									title : '联系电话',
									width : 30,
									align : 'center',
								},
								{
									field : 'state',
									title : '状态',
									width : 20,
									align : 'center',
									formatter : function(value, row, index) {
										if (row.state != null) {
											if (value == '1') {
												return '<span style="color: red;">已离职</span>';
											} else if (value == '0') {
												return "在职";
											}
										}
									}
								}, {
									field : 'opt',
									title : '操作',
									width : 30,
									align : 'center',
									formatter : optt
								}, ] ]
					});
}
function optt(value, row, index) {
	var str = "";
	str += '<a href="javascript:void(0);" onclick="xiangqing(' + row.id
			+ ')">详情</a>|';
	str += '<a href="javascript:void(0);" onclick="edit(' + row.id
			+ ')">编辑</a>';
	/*
	 * str += '<a href="proxyTeacher/delState?state=1&id='+row.id+'"
	 * onclick="if(confirm(\'确认删除\')==false)return false;">删除</a>';
	 */
	return str;
}

function opt(value, row, index) {
	var str = "";
	str += '<a href="javascript:void(0);" onclick="editpoint(' + row.id
			+ ')">编辑</a>|';
	// str += '<a href="proxyTeacher/deletepoint?state=1&id='+row.id+'"
	// onclick="if(confirm(\'确认删除\')==false)return false;">删除</a>';
	str += '<a href="javascript:void(0);" onclick="deletepoint(' + row.id
			+ ')">删除</a>';
	return str;
}
// 删除
function deletepoint(id) {
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.ajax({
				type : "post",
				url : "proxyTeacher/deletepoint", // 请求的url地址
				data : {
					"id" : id
				},
				success : function() {
					$('#tableid1').datagrid('reload');

				}
			}) 
	    }    
	});
	
	
}

// 添加point点位
function addpoint() {

	$('#addpoint').window({
		title : '点位添加',
		width : 320,
		height : 280,
	});
	$('#addFormpoint').form('clear');
	$('#addpoint').window('open');
}

function cleans() {
	$('#getname').textbox('setValue', '');
	$('#getbegindate').textbox('setValue', '');
	$('#getenddate').textbox('setValue', '');
	$('#shengSearch').val('');
	$('#shiSearch').val('');
	$('#getidcard').textbox('setValue', '');
	$('#parantIdselect').textbox('setValue', '');
	$('#parantSJselect').textbox('setValue', '');
	/* init(); */
}

//$("#add_submit").on('click', function() {
//	var point = $("#point").val();
//	if (point == ""){
////		alert("点位不能为空");
//		return false;
//	} else {
//		return true;
//	}
//});

// 提交修改的点位数据
function add_editpoint(form, method) {
	var data = $("#" + form).serialize();
	if ($("#edit_point").textbox("getValue") == '') {
		alert("点位不能为空");
	}else if(isNaN($("#edit_point").textbox("getValue"))){
		 $.messager.alert('警告','请输入正确类型');
	 } else if ($("#edit_startdate").textbox("getValue") == '') {
		alert("开始时间不能为空");
	} else

	if ($("#edit_enddate").textbox("getValue") == '') {
		alert("结束时间不能为空");

	} else
		$.ajax({
			url : "proxyTeacher/" + method, // 请求的url地址
			async : true, // 请求是否异步，默认为异步，这也是ajax重要特性
			data : data,
			type : "post", // 请求方式
			success : function(data) {
				console.log(data)
				if (data == 'StartTimeError') {
					$.messager.alert('警告', '初始日期已有点位，请重新添加');
				}
				if (data == 'EndTimeError') {
					$.messager.alert('警告', '结束日期已有点位，请重新添加');
				}
				if (data == 'ContoinsTimeError') {
					$.messager.alert('警告', '添加得日期之间已有点位，请重新添加');
				}
				if (data == "SUCCESS") {
					$('#editpoint').window('close');
					$('#tableid1').datagrid('reload');
					$('#editFormpoint').form('clear');
				}

			}

		});
}

// 保存添加的点位数据
function add_addpoint(form, method) {
	var data = $("#" + form).serialize();
	// console.log(data);

	if ($("#point").textbox("getValue") == '') {
		$.messager.alert('警告', '点位不能为空');
	 }else if(isNaN($("#point").textbox("getValue"))){
		 $.messager.alert('警告','请输入正确点位');
	 }else if ($("#addstartdate").textbox("getValue") == '') {
		$.messager.alert('警告', '开始时间不能为空');
	} else if ($("#enddate").textbox("getValue") == '') {
		$.messager.alert('警告', '结束时间不能为空');
	} else

		$.ajax({
			url : "proxyTeacher/addpoint", // 请求的url地址
			async : true, // 请求是否异步，默认为异步，这也是ajax重要特性
			data : data,
			type : "post", // 请求方式
			success : function(data) {
				console.log(data)
				if (data == 'StartTimeError') {
					$.messager.alert('警告', '初始日期已有点位，请重新添加');
				}
				if (data == 'EndTimeError') {
					$.messager.alert('警告', '结束日期已有点位，请重新添加');
				}
				if (data == 'ContoinsTimeError') {
					$.messager.alert('警告', '添加得日期之间已有点位，请重新添加');
				}

				if (data == "SUCCESS") {
					$('#addpoint').window('close');
					$('#tableid1').datagrid('reload');
					$('#addFormpoint').form('clear');
				}

			}

		});
}

// 打开修改点位窗口回显数据
function editpoint(id) {
	$.ajax({
		type : "post",
		url : "proxyTeacher/selectpointbyid",
		async : true,
		data : {
			"id" : id
		},
		success : function(data) {
			/*极其想吐槽，你一个input框竟然用data这个class*/
			$("#edit_id").val(data.id);
			$("#edit_point").textbox("setValue", data.point);
			$("#edit_startdate").textbox("setValue", data.startDate);
			$("#edit_enddate").textbox("setValue", data.endDate);
			$("#edit_proxyteacherid").val(data.proxyTeacherId); 
		}

	})
	$('#editpoint').window({
		title : '点位修改',
		width : 320,
		height : 280,

	});
	$('#editpoint').window('open');

}

// 点位详情
function xiangqing_point(index) {
	var row = $('#listData').datagrid('getData').rows[index];
	var proxyteacherId = row.id;
	$('#xiangqing_point').window({
		title : '点位详情',
		width : 734,
		height : 300,

	});
	$("#tableid1").datagrid({
		url : 'proxyTeacher/getpoint?id=' + proxyteacherId,
		singleSelect : true,

	});

	$('#xiangqing_point').window('open');
}

// 根据身份证填写年龄
function init1() {
	$('#idcard').textbox({
		onChange : function(idcard) {
			if (idcard.length == 18) {
				var age = idcard.substring(6, 14);
				var agenian = age.substring(0, 4);// 年
				var ageyue = age.substring(4, 6);// 月
				var ageri = age.substring(6, 8);// 日

				var mydate = new Date();
				var tages = mydate.getFullYear() - agenian;
				if ((mydate.getMonth() + 1) - ageyue > 0) {

					$("#age").val(tages);
				}
				if ((mydate.getMonth() + 1) - ageyue < 0) {
					$("#age").val(tages - 1);
				}
				if ((mydate.getMonth() + 1) - ageyue == 0) {
					if (mydate.getDate() - ageri < 0) {
						$("#age").val(tages - 1);
					} else {
						$("#age").val(tages);
					}
				}

			} else {
				alert("请输入合法的身份证号");
			}
		}
	});
}
// 页面获取级别名字
function getRanksName(value, row, index) {
	if (row.ranks) {
		return row.ranks.name
	} else {
		return '';
	}
}
function getTeacherName(value, row, index) {
	if (row.leaderName != null) {
		return row.leaderName
	} else {
		return '<span style="color: red">无上级人</span>'
	}
}
//数据来源
function getSourceName(value, row, index) {
	if (row.source) {
		return row.source.text;
	} else {
		return '';
	}
}
function search() {
	var fields = $('#search_form').serializeArray();
	var params = $("#listData").datagrid('options').queryParams;
	$.each(fields, function(i, field) {
		params[field.name] = field.value;
	});

	$('#listData').datagrid('reload')
}
// 三级联动

function testBoth(sheng, shi, xian, pro, city, country) {
	$
			.ajax({
				type : "post",
				url : "area/check",
				async : true,
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var str = '<option  value="' + data[i].name + '"';
						if (data[i].name == pro) {
							str += " selected";
						}
						str += ">" + data[i].name + "</option>"
						$(sheng).append(str)
						for (var j = 0; j < data[i].children.length; j++) {
							var shistr = '<option  value="'
									+ data[i].children[j].name + '"';
							if (data[i].children[j].name == city) {
								shistr += ' selected';
							}
							shistr += ">" + data[i].children[j].name
									+ "</option>";
							$(shi).append(shistr);
							for (var k = 0; k < data[i].children[j].children.length; k++) {
								var countrystr = '<option  value="'
										+ data[i].children[j].children[k].name
										+ '"';
								if (data[i].children[j].children[k].name == country) {
									countrystr += ' selected';
								}
								countrystr += ">"
										+ data[i].children[j].children[k].name
										+ "</option>";
								$(xian).append(countrystr);
							}
						}
					}
					$(sheng)
							.change(
									function() {
										var shengname = $(this).val();
										for (var s = 0; s < data.length; s++) {
											var xhname = data[s].name;
											if (shengname == xhname) {
												$(shi).children().remove()
												$(xian).children().remove()
												for (var c = 0; c < data[s].children.length; c++) {
													$(shi)
															.append(
																	'<option  value="'
																			+ data[s].children[c].name
																			+ '" >'
																			+ data[s].children[c].name
																			+ "</option>")
													if (c == 0) {
														$(xian).children()
																.remove()
														for (var nn = 0; nn < data[s].children[c].children.length; nn++) {
															$(xian)
																	.append(
																			'<option  value="'
																					+ data[s].children[c].children[nn].name
																					+ '" >'
																					+ data[s].children[c].children[nn].name
																					+ "</option>")
														}
													}
												}
											}
										}
									})
					$(shi)
							.change(
									function() {
										var shinames = $(this).val();
										for (var she = 0; she < data.length; she++) {
											for (var mm = 0; mm < data[she].children.length; mm++) {
												if (shinames == data[she].children[mm].name) {
													$(xian).children().remove()
													for (var nns = 0; nns < data[she].children[mm].children.length; nns++) {
														$(xian)
																.append(
																		'<option  value="'
																				+ data[she].children[mm].children[nns].name
																				+ '" >'
																				+ data[she].children[mm].children[nns].name
																				+ "</option>")
													}
												}
											}
										}
									})
				}
			});

};

function testBoth2(SHENG, SHI, pro, city, country) {
	$
			.ajax({
				type : "post",
				url : "area/check",
				async : true,
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var str = '<option  value="' + data[i].name + '"';
						if (data[i].name == pro) {
							str += " selected";
						}
						str += ">" + data[i].name + "</option>"
						$(shengSearch).append(str)
						for (var j = 0; j < data[i].children.length; j++) {
							var shistr = '<option  value="'
									+ data[i].children[j].name + '"';
							if (data[i].children[j].name == city) {
								shistr += ' selected';
							}
							shistr += ">" + data[i].children[j].name
									+ "</option>";
							$(shiSearch).append(shistr);
						}
					}
					$(shengSearch)
							.change(
									function() {
										var shengname = $(this).val();
										for (var s = 0; s < data.length; s++) {
											var xhname = data[s].name;
											if (shengname == xhname) {
												$(shiSearch).children()
														.remove()
												for (var c = 0; c < data[s].children.length; c++) {
													$(shiSearch)
															.append(
																	'<option  value="'
																			+ data[s].children[c].name
																			+ '" >'
																			+ data[s].children[c].name
																			+ "</option>")
												}
											}
										}
									})
				}
			})
};

function add_win() {
	$('#add_win').window({
		title : '添加用户',
		width : "800px",
		height : "500px",

	});
	$('#rankselect').combobox({
		url : 'rank/getAll',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',
		editable : false,
	// onChange:function(val){
	// getRankParent();
	// }

	});
	$('#parantselect').combobox({

		url : 'proxyTeacher/getAll',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',
		editable : false,

	});

	testBoth("#sheng", "#shi", "#xian");
	init1();
	$('#add_win').window('open');
}

function parent() {
	$('#parantIdselect').combobox({
		url : 'rank/getAll',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',

	});
}
function parent1() {
	$('#parantSJselect').combobox({
		url : 'proxyTeacher/getAll',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',

	});
}
//数据来源
function source() {
	$('#sourceSelect').combobox({
		url : 'source/findSource',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',

	});
}
function xiangqing(id) {

	$.ajax({
				type : "post",
				url : "proxyTeacher/get",
				data : {
					"id" : id
				},
				async : true,
				success : function(data) {
					$('#xiangqing_win').window({
						title : "详细信息",
						width : "520px",
						height : "500px",
					});

					$("#sid").val(data.id);
					$("#sname").val(data.name);
					$("#ssex").val(data.sex);
					$("#sage").val(data.age);
					$("#sidcard").val(data.idcard);
					$("#saddress").val(data.address);
					$("#sjoinDate").val(data.joinDate);
					$("#sintroducerType").val(data.introducerType);
					$("#sintroducerName").val(data.introducerName);
					if (data.parentProxyTeacherId) {
						$("#sparentProxyTeacherId").val(
								data.parentProxyTeacherId.name);
					}
					if (data.ranks) {
						$("#sranks").val(data.ranks.name);
					}
					/*if (data.source) {
						$("#sources").val(data.source.name);
					}*/
					/*$("#spoint").val(data.point);*/
					$("#sopeningBank").val(data.openingBank);
					$("#sbankName").val(data.bankName);
					$("#sbankCardNumber").val(data.bankCardNumber);
					$("#sqq").val(data.qq);
					$("#swechat").val(data.wechat);
					$("#stel").val(data.tel);
					if (data.state == 0) {
						$("#sstate").val("在职");
					} else {
						$("#sstate").val("离职");
					}
					/*console.log("文本域的长度");*/
					$("#sremark").val(data.remark);
					$("#sremark").attr("title",data.remark);				
					/*console.log(data.remark.length)*/
					
				
					$("#sbankcard").attr("src", data.bankcard);

					$('#xiangqing_win').window('open');

				}
			});
}

function edit(id) {
	$.ajax({
		type : "post",
		url : "proxyTeacher/get",
		data : {
			"id" : id
		},
		async : true,
		success : function(data1) {

			$('#erankselect').combobox({
				url : 'rank/getAll',
				valueField : 'id',
				selectOnNavigation : false,
				textField : 'name',
				editable : false,

			});

			$("#eid").textbox("setValue", data1.id);

			var rankId = $('#eid').textbox("getValue")
			$('#eparantselect').combobox({

				url : 'proxyTeacher/getRankParent?rankId=' + rankId,
				valueField : 'id',
				selectOnNavigation : false,
				textField : 'name',
				editable : false,

			});

			$("#ename").textbox("setValue", data1.name);
			$("#editForm input[name='sex']").each(function() {
				if (this.value == data1.sex) {
					this.checked = 'checked';
				}
			});
			$("#eage").val(data1.age);

			$("#erankselect").combobox("select", data1.ranks.id);
			if (data1.parentProxyTeacherId == null) {
				$("#eparantselect").combobox("select",
						data1.parentProxyTeacherId);
			} else {

				$("#eparantselect").combobox("select",
						data1.parentProxyTeacherId.id);
			}

			$("#eidcard").textbox("setValue", data1.idcard);
			var areastr = data1.address;
			var areaarr = areastr.split("-");
			testBoth("#shengup", "#shiup", "#xianup", areaarr[0], areaarr[1],
					areaarr[2]);
			$("#ejoinDate").datebox({
				value : data1.joinDate
			});
			$("#eintroducerType input[name='introducerType']").each(function() {
				if (this.value == data1.introducerType) {
					this.checked = 'checked';
				}
			});

			$("#eintroducerName").textbox("setValue", data1.introducerName);
			$("#epoint").textbox("setValue", data1.point);
			$("#eopeningBank").textbox("setValue", data1.openingBank);
			$("#ebankName").textbox("setValue", data1.bankName);
			$("#ebankCardNumber").textbox("setValue", data1.bankCardNumber);
			$("#eqq").textbox("setValue", data1.qq);
			$("#ewechat").textbox("setValue", data1.wechat);
			$("#etel").textbox("setValue", data1.tel);
			/* $("#eremark").textbox("setValue",data1.remark); */
			$("#eremark").val(data1.remark);

			$('#ebankcard').attr('src', data1.bankcard);
			$('#card_div2 img').siblings().css('display', 'none');
			$('#card_div2 img').css('display', 'block');

			$("#editForm input[name='state']").each(function() {
				if (this.value == data1.state) {
					this.checked = 'checked';
				}
			});
			$('#edit_win').window({
				title : "更改信息",
				width : "800px",
				height : "500px",
			});

			$('#edit_win').window('open');
			testBoth("#sheng", "#shi", "#xian");
			init1();

		}
	});
}

formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
			+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

window.onload = function() {
	$('#startTime').datebox('setValue', formatterDate(new Date()));
}
