$(function() {
	getat();
	init();
	getmajor();
	getstudyTime();
	geteducation();
	getclassroom();
	getproxyTeacher();
	getTransportation();
	// 获取当前时间
	var now = new Date();
	var month = (now.getMonth() + 1);
	if (month < 10) {
		month = "0" + (month)
	}
	;
	var date = now.getFullYear() + "-" + month + "-" + now.getDate();
	$("#joinTime").textbox("setValue", date);
	testBoth("#sheng", "#shi", "#xian");

});
$('#name').textbox({
	onChange : function(value) {
		$('#name2').textbox('setValue', value);
	}
})

// 身份证号
function init() {
	$('#idCard').textbox({
		onChange : function(idCard) {
			var a = false;
			if (idCard.length == 18) {
				$.ajax({
					type : "post",
					url : "student/IdcardisNo",
					data : {
						idCard : $("#idCard").textbox("getValue")
					},
					async : false,
					success : function(data) {
						if (data) {
							a = true;
						}
					}
				})
				if (a) {
					$.messager.alert('警告', '该学生信息已录入');
					return false;
				}
				var age = idCard.substring(6, 14);
				var agenian = age.substring(0, 4);// 年
				var ageyue = age.substring(4, 6);// 月
				var ageri = age.substring(6, 8);// 日
				$("#birthday").val(agenian + "-" + ageyue + "-" + ageri)
				console.log(agenian + "-" + ageyue + "-" + ageri)
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
	})
}

// 专业
function getmajor() {
	$('#major').combobox({
		url : 'major/getAll',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',
		editable : false,
		onLoadError : function(){
			if(confirm("刷新")){
				$('#major').combobox('reload')
			}
		},
		onChange : function(val) {
			if (val) {
				findtuition();
			} else {
			}
		}
	});
}

// 学习时长
function getstudyTime() {
	$('#studyTime').combobox({
		url : 'studyTime/getAll',
		valueField : 'studyTime',
		selectOnNavigation : false,
		textField : 'studyTime',
		editable : false,
		formatter : function(row) {
			var opts = $(this).combobox('options');
			return row[opts.textField] + "个月";
		},
		onLoadError : function(){
			if(confirm("刷新")){
				$('#studyTime').combobox('reload')
			}
		},
		onChange : function(val) {
			findtuition();
		}
	});
}

// 通过时长和专业获取学费
function findtuition() {
	var studyTime = $("#studyTime").textbox("getValue");
	var majorId = $("#major").textbox("getValue");
	if (majorId != null && majorId != "" && studyTime != null
			&& studyTime != '') {
		$.ajax({
			url : "tuition/getTuitionId",
			data : {
				studyTime : studyTime,
				majorId : majorId
			},
			success : function(tution) {
				$("#tuition").textbox('setValue', tution.tuition);
				$("#zhuNumber").textbox('setValue', tution.quarterage);
				$("#zaNumber").textbox('setValue', tution.incidentals);
			}
		})
	}
}

// 班级
function getclassroom() {
	$('#classroom').combobox({
		url : 'classroom/getAllBe',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',
		editable : false,
		onLoadSuccess : function(data) {
			$('#classroom').combobox('select', data[0].id);
			
		},
		onLoadError : function(){
			if(confirm("刷新")){
				$('#classroom').combobox('reload')
			}
		},
		onChange : function(val) {
			findteachers();
		}
	});
}

// 招生老师
function getproxyTeacher() {
	$('#proxyTeacher').combobox({
		url : 'proxyTeacher/getAll',
		valueField : 'id',
		selectOnNavigation : false,
		textField : 'name',
		editable : false,
		onLoadError : function(){
			if(confirm("刷新")){
				$('#proxyTeacher').combobox('reload')
			}
		},
		onChange : function(val) {
			if (val) {
				findtuition();
			} else {
			}
		}
	});
}

// 通过班级获取老师的姓名
function findteachers() {
	var classroomId = $("#classroom").textbox("getValue");
	if (classroomId != null && classroomId != "") {
		$("#teacher").combobox({
			url : 'teacher/findTeachers?classroomId=' + classroomId,
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',
			editable : false,
			onLoadSuccess : function(data) {
				if (data.length != 0) {
					$('#teacher').combobox('select', data[0].id);
				}

			},
			onLoadError : function(){
				if(confirm("刷新")){
					$("#teacher").combobox('reload')
				}
			},
		})
	}
}

// 学历
function geteducation() {
	$('#education').combobox({
		url : 'education/getAll',
		valueField : 'name',
		selectOnNavigation : false,
		textField : 'name',
		editable : false,
		onChange : function(val) {
			if (val) {
				$("#education").combobox('setValue', val);
			} else {
			}
		},
		onLoadError : function(){
			if(confirm("刷新")){
				$('#education').combobox('reload')
			}
		},
	});
}
// 出行方式
function getTransportation() {
	$('#transportation').combobox({
		url : 'transportation/getAll',
		valueField : 'name',
		selectOnNavigation : false,
		textField : 'name',
		editable : false,
		onLoadError : function(){
			if(confirm("刷新")){
				$('#transportation').combobox('reload')
			}
		},
	}
	);

}

// 咨询老师
function getat() {
	$('#advisoryTeacher').combobox({
		url : 'advisoryTeacher/teacherName',
		/* valueField设置传过来对象的字段作为value值 */
		valueField : 'id',
		selectOnNavigation : false,
		/* textField设置传过来对象的字段作为文本 */
		textField : 'name',
		editable : false,
		onLoadError : function(){
			if(confirm("刷新")){
				$('#advisoryTeacher').combobox('reload')
			}
		},
	});
}
var ci = 1;

// 点击添加紧急联系人
function addcontacts() {
	if (ci <= 2) {
		$('#jinji')
				.after(
						'<div  class="jinji" id="jinji" >'
								+ '<label style="font-size: 9px;margin-left: 20px;float:left">姓名:<span></span></label>'
								+ '<input class="jinName" style="width:130px" type="text" name="cname">'
								+ '<label style="font-size: 9px;margin-left: 45px;float:left ">联系电话:<span></span></label>'
								+ '<input class="jinTel" style="width:130px" type="text" name="ctel">'
								+ '<label style="font-size: 9px;margin-left: 45px;float:left ">关系:<span></span></label>'
								+ '<input class="jinGuan" style="width:130px" type="text" name="crelation">'
								+ '<a href="script:void(0)" href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onclick="removethis(this)"  style="width:100px;float:left;margin-top:9px;margin-left:30px"></a></div>')
		$.parser.parse('.jinji'); // 插入标签并进行渲染
		ci++;
	} else {
		alert("最多添加3个紧急联系人！");
	}
}

function removethis(contact) {
	if (ci == 1) {
		alert("至少需要一位紧急联系人！")
	} else {
		$(contact).parent().remove();
		ci--;
	}
}

// 判断是否为空
$('#studentsave').submit(

function() {
	if ($("#idCard").textbox("getValue") == '') {
		alert("学生身份证号不能为空！");
		return false;
	}
	var a = false;
	$.ajax({
		type : "post",
		url : "student/IdcardisNo",
		data : {
			idCard : $("#idCard").textbox("getValue")
		},
		async : true,
		success : function(data) {
			if (data) {
				a = true;
			}
		}
	})
	if (a) {
		alert("该学生信息已录入");
		return false;
	}

	if ($("#name").textbox("getValue") == '') {
		alert("学生姓名不能为空！");
		return false;
	}

	if ($("#tel").textbox("getValue") == '') {
		alert("电话号码不能为空！");
		return false;
	} else if ($("#tel").textbox("getValue").length != 11) {
		alert("请输入正确的手机号！")
		return false;
	}
	if ($("#studyTime").textbox("getValue") == '') {
		alert("学生学习时长不能为空！");
		return false;
	}
	if ($("#major").textbox("getValue") == '') {
		alert("学生专业不能为空！");
		return false;
	}
	if ($("#tuition").textbox("getValue") == '') {
		alert("学生学费不能为空！");
		return false;
	}
	if ($("#proxyTeacher").textbox("getValue") == '') {
		alert("学生招生老师不能为空！");
		return false;
	}
	if ($("#advisoryTeacher").textbox("getValue") == '') {
		alert("学生咨询老师不能为空！");
		return false;
	}
	if ($("#sheng").val() == '') {
		alert("地址不能为空！");
		return false;
	}
	if ($("#joinTime").textbox("getValue") == '') {
		alert("入学日期不能为空！");
		return false;
	}
	if ($("#initialResideDate").textbox("getValue") == '') {
		alert("住宿日期不能为空！");
		return false;
	}
	//正则判断录入日期格式是否正确
	var reg=/^[1-2]\d{3}\-(0?[1-9]|1[0-2])\-(0?[1-9]|[1-2][0-9]|3[0-1])$/gi;
	if (!reg.test($("#joinTime").textbox("getValue"))) {
	   alert("请输入正确的入学日期格式");
	 return false;
	}
	 reg=/^[1-2]\d{3}\-(0[1-9]|1[0-2])\-(0[1-9]|[1-2][0-9]|3[0-1])$/gi;
	if (!reg.test($("#initialResideDate").textbox("getValue"))) {
		   alert("请输入正确的住宿日期格式");
		 return false;
		}
	/*
	 * if($('#files').textbox('getValue')==''){ alert("身份证正面不能为空"); return
	 * false; } if($('#files2').textbox('getValue')==''){ alert("身份证反面不能为空");
	 * return false; }
	 */
	for (var i = 0; i < $('.jinName').length; i++) {
		if ($(".jinName").eq(i).val() == '') {
			alert("紧急联人" + (i + 1) + ":姓名不能为空！");
			return false;
		}
		if ($(".jinTel").eq(i).val() == '') {
			alert("紧急联人" + (i + 1) + ":电话不能为空！");
			return false;
		}
		if($(".jinTel").eq(i).val().length!=11){
			alert("紧急联人" + (i + 1) + ":电话输入错误！");
			return false;
		}
		if ($(".jinGuan").eq(i).val() == '') {
			alert("紧急联人" + (i + 1) + ":关系不能为空！");
			return false;
		}
	}
	if ($("#transportation").combobox('getValue') == '') {
		alert("出行方式不能为空！");
		return false;
	}

	alert("保存成功！");
})

// 地址三级联动
function testBoth(sheng, shi, xian, pro, city, country) {
	$
			.ajax({
				type : "post",
				url : "area/check",
				async : true,
				success : function(data) {
					var arr = [];
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
					$(shi).children().remove()
					$(xian).children().remove()
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
					$(xian).children().remove()
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
									});
				}
			});

};
