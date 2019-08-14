﻿$(function(){
	InitLeftMenu();
	tabClose();
	tabCloseEven();
})
function InitLeftMenu() {
	var _menus = {
		"menus" : [ 		
		
			{"menuid":"8","icon":"icon-sys","menuname":"招生代理管理",
				"menus":[	{"menuname":"代理老师","icon":"icon-nav","url":"proxyTeacher/index"},		      
				         	{"menuname":"来访记录","icon":"icon-nav","url":"visitRecords/visitRecords"},
						]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"学生信息录入",
				"menus":[	{"menuname":"学生基本信息","icon":"icon-nav","url":"student/index"},
				        ]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"学生信息管理",
				"menus":[	{"menuname":"学生信息修改/查询","icon":"icon-nav","url":"student/indexfind"},
				         	{"menuname":"学生报销信息修改","icon":"icon-nav","url":"studentAuditing/modify"},
				         	{"menuname":"学生招生信息","icon":"icon-nav","url":"studentProxyTeacher/index"},
				        ]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"学生缴费",
				"menus":[	{"menuname":"学生缴费","icon":"icon-nav","url":"fee/test2"},
				        ]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"学生考试信息",
				"menus":[	{"menuname":"成绩录入","icon":"icon-nav","url":"score/addExam"},
				         	{"menuname":"成绩查询","icon":"icon-nav","url":"score/findExam"},
				         	{"menuname":"分班操作","icon":"icon-nav","url":"score/brvBar"},
				        ]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"学生考勤",
				"menus":[	{"menuname":"查询班级考勤信息","icon":"icon-nav","url":"attence/classAttenceCount"},
				         	{"menuname":"查询个人考勤信息","icon":"icon-nav","url":"attence/studentAttence"},
				         	{"menuname":"考勤信息手动导入","icon":"icon-nav","url":"attence/importAttence"},
				         	{"menuname":"学生绑定考勤信息","icon":"icon-nav","url":"attence/bindingAttence"},
				        ]
			},	
			
			{"menuid" : "8","icon" : "icon-sys","menuname" : "基础数据",
				"menus":[	{"menuname":"专 业 表","icon":"icon-nav","url":"major/major"},
				           	{"menuname":"学习时长表","icon":"icon-role","url":"studyTime/studyTime"},
				           	{"menuname":"学 费 表","icon":"icon-nav","url":"tuition/tuition"},
				           	{"menuname":"老 师 表","icon":"icon-nav","url":"teacher/teacher"},
				           	{"menuname":"班 级 表","icon":"icon-nav","url":"classroom/classroom"},
				           	{"menuname":"课 程 表","icon":"icon-nav","url":"course/course"},
				           	{"menuname":"学 历 表","icon":"icon-nav","url":"education/education"},
				           	{"menuname":"考 试 表","icon":"icon-nav","url":"examName/examName"},
							{"menuname":"级 别 表","icon":"icon-nav","url":"rank/index"},
							{"menuname":"咨询老师","icon":"icon-nav","url":"advisoryTeacher/advisoryTeacher"},
							{"menuname":"出行方式表","icon":"icon-nav","url":"transportation/transportation"},
							{"menuname":"支付方式表","icon":"icon-nav","url":"payment/payment"},
							{"menuname":"推荐人信息表","icon":"icon-nav","url":"referrer/index"},
							{"menuname":"缴费类型设置","icon":"icon-nav","url":"feeType/index"}
						]
			},
				           
			{"menuid" : "8","icon" : "icon-sys","menuname" : "财务管理",
				"menus":[	{"menuname":"学生来校报销：未审核","icon":"icon-role","url":"studentAuditing/unaudited"},
							{"menuname":"学生来校报销：待报销","icon":"icon-nav","url":"studentAuditing/reimburse"},
							{"menuname":"学生来校报销：已报销","icon":"icon-nav","url":"studentAuditing/reimbursed"},  	
							{"menuname":"学生来校报销：作废","icon":"icon-nav","url":"studentAuditing/die"},  	
							{"menuname":"招生老师报销:未审核","icon":"icon-nav","url":"proxyTeacherAuditing/proxyTeacherAuditing"},
							{"menuname":"招生老师报销:待报销","icon":"icon-nav","url":"proxyTeacherAuditing/proxyTeacherAuditing1"},
							{"menuname":"招生老师报销:已报销","icon":"icon-nav","url":"proxyTeacherAuditing/proxyTeacherAuditing2"},
							{"menuname" : "招生老师提成管理","icon":"icon-nav","url":"commission/commissionSkip"},
							{"menuname" : "招生老师工资历史","icon":"icon-nav","url":"commission/commissionSkip11"},
						]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"学生住宿管理",
				"menus":[	{"menuname":"学生住宿","icon":"icon-nav","url":"Dormitory/indexfind"},
							{"menuname":"学生查寝","icon":"icon-nav","url":"CheckDormitory/indexfind"},
							{"menuname":"宿舍卫生表","icon":"icon-nav","url":"sanitation/sanitation"},			          	
						]
			},
							
			{"menuid":"8","icon":"icon-sys","menuname":"谈话记录",
				"menus":[	{"menuname":"学生谈话记录","icon":"icon-nav","url":"studentConversation/studentConversation"},
				         	{"menuname":"家长谈话记录","icon":"icon-nav","url":"parentConversation/parentConversation"},
				         	{"menuname":"谈话导入汇总","icon":"icon-nav","url":"conversationIntroduction/conversationIntroduction"},
				        ]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"就业喜报",
				"menus":[	{"menuname":"就业管理","icon":"icon-nav","url":"graduated/index"},
				        ]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"APP首页轮播图",
				"menus":[	{"menuname":"APP首页轮播图管理","icon":"icon-nav","url":"appIndexCycleImage/index"},
				        ]
			},
			
			{"menuid":"8","icon":"icon-sys","menuname":"权限管理",
				"menus":[	{"menuname":"用户管理","icon":"icon-nav","url":"users/index"},
//				         	{"menuname":"角色管理","icon":"icon-nav","url":"roles/index"},
				         	{"menuname":"角色管理","icon":"icon-nav","url":"roles/index2"},
				         	{"menuname":"权限管理","icon":"icon-nav","url":"permissions/index"},
				        ]
			},
		]
}; 		
	


    $(".west").empty();
    var menulist = '<div class="easyui-accordion" border="false" style="height:auto">';
   
    $.each(_menus.menus, function(i, n) {
    	menulist += '<div title="'+n.menuname+'" style="overflow:auto; border:0">'
		menulist += '<ul>'
        $.each(n.menus, function(j, o) {
			menulist += '<li><div><a target="mainFrame" href="' + o.url + '" ><span class="icon '+o.icon+'" ></span>' + o.menuname + '</a></div></li> ';
        })
        menulist += '</ul></div>';
    })
    menulist += '</div>'
	$(".west").append(menulist);
	
	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).text();
		var url = $(this).attr("href");
		addTab(tabTitle,url);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	$(".easyui-accordion").accordion();
 }

function addTab(subtitle,url){
	if(!$('#tabs').tabs('exists',subtitle)){
		
		var currTab = $('#tabs').tabs('getSelected');
	    if(currTab){
	        $('#tabs').tabs('close',currTab.panel('options').title);
	    }
		
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			width:$('#mainPanle').width()-10,
			height:$('#mainPanle').height()-26
		});
	}else{
		$('#tabs').tabs('select',subtitle);
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children("span").text();
		$('#tabs').tabs('close',subtitle);
	});

	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY,
		});
		
		var subtitle =$(this).children("span").text();
		$('#mm').data("currtab",subtitle);
		
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!=currtab_title)
				$('#tabs').tabs('close',t);
		});	
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

function clockon() {
    var now = new Date();
    var year = now.getFullYear(); //getFullYear getYear
    var month = now.getMonth();
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    week = arr_week[day];
    var time = "";
    time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;

    $("#bgclock").html(time);

    var timer = setTimeout("clockon()", 200);
}
