var pic ='';  var ci =1;
$(function(){
		init();
		getAllTrans();
		addActualAmount();
		$('#reimbMileage').combobox({
			onChange:function(){    zong(); }
		})
});		


	//查询全部
	function init(){
		$('#listData').datagrid({
	        url: 'visitRecords/getAll',//请求方法的地址
	        title: '查询结果',
	        height: window.innerHeight-160,
	        fitColumns: true, //列自适应 
	        nowrap: false,//禁止文字自动换行
	        loadMsg: '正在加载信息...',//当数据没有加载出来时显示的文字
	        pagination: true,//是否有分页/
	        singleSelect: true,//是否单行选择
	        pagePosition: 'bottom',//分页符在底部,可选参数为top，bottom，both
	        pageSize: 30,//页大小，一页多少条数据
	        pageNumber: 1,//默认当前的页码
	        pageList: [10,30,50],//一页可显示数据的条目 
	        queryParams: {},//往后台传递参数，json格式 */
	        columns: [[
	            { field: 'id', title: 'id', width: 40, align: 'center',hidden:true},
	            {
	            	field: 'proxyTeacher', title:'招生老师',align: 'center',width: 80,
	            	formatter: function(value,row,index){
	                    if (value.name){
	                        return value.name;
	                    } else {
	                        return '';
	                    }
	               }
	            },
	            {
	                field: 'dateTime', title: '来访时间', width: 80, align: 'center',
	            },
	            {
	                field: 'purpose', title: '来访目的', width: 80, align: 'center'
	            },
	            {
	            	field: 'reimAmount', title:'需报销总钱数',align: 'center',width: 80
	            },
	            {
	            	field: 'reimTime', title:'报销时间',align: 'center',width: 80,

	            },
	            {
	            	field: 'auditState', title:'审核状态',align: 'center',width: 80,
	            	formatter : function(value, row, index) {
	            		if (value == 0){
	            			return "待审核";
	            		}else if (value == 1) {
							return "审核未通过";
						} else if (value == 2) {
							return "待报销";
						} else if (value == 3) {
							return "已报销";
						}
					}
	            },
	            {	
	            	field: 'opt', title: '操作', align: 'center',width: 80,
	            	formatter:function(value,row,index){
	           		 var str = "";
	           		str += '<a href="javascript:void(0);" onclick="open_edit('+row.id+')">编辑</a>';
	     		    return str;
	            	}
	            }
	        ]]
	    });
	}
	
	//添加页面
	function add(){
		 //获取全部招生老师
		$('#button2 .l-btn-text').html('保存');
		$('#addForm').form('clear'); pic='';
		$('#img1').attr('src',pic); 
		$('#card_div1 img').siblings().css('display','block');   	   
		$('#files').css('display','none');
		$('#card_div1 img').css('display','none');
		if(ci==2){removethis("#button1");} 
		$('#button2').attr('onclick','save()');
		$("#proxyTeacher").combobox({disabled: false});
		var now= new Date();  var month=(now.getMonth()+1); if(month<10){month="0"+(month)}; 	
		var date =now.getFullYear()+"-"+month+"-"+now.getDate()+" "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
		$("#dateTime").textbox("setValue",date);
		$('#proxyTeacher').combobox({
			url : 'proxyTeacher/getAll',
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',	
			editable:false,
			onChange:function(value){
				$.ajax({
					url:"proxyTeacher/get",
					data:{id:value},
					success:function(data){
						$('#bankName').textbox('setValue',data.bankName);
						$('#bankCardNumber').textbox('setValue',data.bankCardNumber);
						$('#openingBank').textbox('setValue',data.openingBank);
						$('#listData').datagrid('reload');
					}
				})
			}
		});
				$('#add_win').window({
				title : '添加记录',
				width: "600px", 
				height:"480px",
				});
				$('#add_win').window('open');
}
	


	//	修改页面
	function open_edit(id){
		$('#button2').attr('onclick','update('+id+')');
		$('#button2 .l-btn-text').html('确认修改');
		$.ajax({
				type:"post",
				url:"visitRecords/getById",
				data:{id:id},
				success:function(data){
					$("#teacherId").val(id);
					$("#proxyTeacher").combobox({disabled: true});
					$("#proxyTeacher").textbox('setValue',data.proxyTeacher.name);
					$("#startFromAddress").textbox('setValue',data.startFromAddress);
					$("#purpose").textbox('setValue',data.purpose);
					$("#dateTime").textbox('setValue',data.dateTime);
					$("#reimAmount").textbox('setValue',data.reimAmount);
					$("#bankName").textbox('setValue',data.bankName);
					$("#bankCardNumber").textbox('setValue',data.bankCardNumber);
					$("#bankCardNumber").textbox('setValue',data.bankCardNumber);
					$("#openingBank").textbox('setValue',data.openingBank);
					var visitFees =	data.visitFee;
					$('#reimbMileage').textbox('setValue',visitFees[0].reimbMileage);
					$('#img1').attr('src',data.reimVoucher);
					$('#card_div1 img').siblings().css('display','none');   	   
				    $('#card_div1 img').css('display','block');
				   	pic = visitFees[0].reimbVoucher;	 
				  	if(data.visitFee.length==2){
						if(ci==1){addcontacts(); } ;fu(visitFees);	
					}else{
						if(ci==2){removethis("#button1");}; fu(visitFees);
					}
					$('#add_win').window({
						title : '修改',
						width: "600px", 
						height:"480px",
					});
					$('#add_win').window('open');
				}
			})
	}
	//赋值	
	function fu(visitFees){
		getAllTrans();
		console.log(visitFees[0].actualAmount);
		$('input[name="trans"]').each(function(index){
			$('.trans').eq(index).textbox('setValue',visitFees[index].trans.id)
			$('.ticketAmount').eq(index).textbox('setValue',visitFees[index].ticketAmount)
			$('.actualAmount').eq(index).textbox('setValue',visitFees[index].actualAmount)
		})	
	}



	//添加路程
	function addcontacts(){
		if(ci<=1){
			 $('#chepiao').after(
				'<div class="add_3 dd" id="chepiao" style="heighe:100px;">'+
			    '<div class="add_style_1" style="float:left">'+
				'<label>出&nbsp;&nbsp;行&nbsp;&nbsp;方&nbsp;&nbsp;式&nbsp;&nbsp;:&nbsp;</label>'+ 
				'<input id="trans" class="easyui-textbox trans" style="width:130px" type="text"  name="trans">'+
				'</div>'+
				'<div class="add_style_1" style="float:left;width:250px">'+
				'<label style="margin-left:19px">车&nbsp;&nbsp;票&nbsp;&nbsp;金&nbsp;&nbsp;额&nbsp;&nbsp;:&nbsp;<span></span></label> '+
				'<input  class="easyui-numberbox ticketAmount" style="width:130px"  name="ticketAmount">'+
				'</div>'+
				'<div class="add_style_1" style="float:left" >'+
				'<label>实&nbsp;&nbsp;报&nbsp;&nbsp;金&nbsp;&nbsp;额&nbsp;&nbsp;:<span></span></label> '+
				'<input  class="easyui-numberbox actualAmount" style="width:130px"  name="actualAmount">'+
				'</div>'+
				'<div class="add_style_1 .ee" style="float:left" >'+
				'<a href="script:void(0)" href="#" class="easyui-linkbutton" id="button1" iconCls="icon-cancel" onclick="removethis(this)"  style="margin-left:112px;width:100px"></a>'+
				'</div>'+
				'</div>')
			 var s =$("#trans").textbox('getValue');	
			 $.parser.parse('.dd'); //插入标签并进行渲染
			 getAllTrans();
			 $("#trans").textbox('setValue',s)
			 addActualAmount();
			 ci++; 
		 }else{
			 alert("最多添加2次车！");
		 }
	} 
	
	
	//  修改操作
	function update(){
		if(yan()==false){return;}
		var data =$('#addForm').serialize()+"&reimbVoucher="+pic;
		$.ajax({
			url:"visitRecords/update",
			data:data,
			success:function(date){
				$('#addForm').form('clear');
				$('#add_win').window('close')
				if($('.add_3').length>1){$('.add_3').eq(1).remove(); ci=1;}
				$('#listData').datagrid('reload');
			}
		})
	}


	//清空按钮
	function clea(){
		$('#search_form').form('clear');
	}


	//查询按钮
	function find(){
		var name =	$("#proxyTeacher1").textbox('getValue');
		var purpose =	$("#purpose1").textbox('getValue');
		var beginDateTime =	$("#beginDateTime").textbox('getValue');
		var endDateTime =	$("#endDateTime").textbox('getValue');
		var startFromAddress1 =	$("#startFromAddress1").textbox('getValue');
		var date="?name="+name+"&purpose="+purpose+"&beginDateTime="+beginDateTime+"&endDateTime="+endDateTime+"&startFromAddress1="+startFromAddress1;
		$('#listData').datagrid({url:"visitRecords/getVisitRecords"+date});
	}

	//删除路程
	function removethis(contact){
		  if(ci==1){
		  }else{
			  $(contact).parent().parent().remove(); ci--;
		  } 
	}


	


	//计算实际报销总金额
	function addActualAmount(){
		$('.actualAmount').textbox({
			onChange:function(){     zong(); }
		})
	}
	
	
	
	//计算实际报销总金额
	function zong(){
		var reimbMileageValue = $('#reimbMileage').textbox('getValue');
		var reimbMileage = 1;   var zong = 0;
		if(reimbMileageValue=='双程')reimbMileage=2;
		$('input[name="actualAmount"]').each(function(index,value){
			if(this.value!=''){
				zong=zong+(parseInt(this.value)*reimbMileage);
			}
		})
		$('#reimAmount').textbox('setValue',zong);
	}
	$('.ccd').click(function (){
				$('#files').textbox('setValue','');
				$('#card_div1').children().css('display','block');
				$('#card_div1 img').css('display','none');
				$('#files').css('display','none');
				pic='';	
	})


	//获取出行方式
	function getAllTrans(){
		$('.trans').combobox({
			url : 'transportation/getAll',
			valueField : 'id',
			selectOnNavigation : false,
			textField : 'name',	
			editable:false,
			onChange:function(val){
			}
		});
	}

	//保存按钮
	function save(){
		var data =$('#addForm').serialize()+"&reimbVoucher="+pic;
		if(yan()==false){return ;}
			$.ajax({
				url:"visitRecords/save",
				data:data,
				success:function(date){
					alert("添加成功！");
					$('#addForm').form('clear');
					$('#add_win').window('close')
					if($('.add_3').length>1){
						$('.add_3').eq(1).remove(); ci=1;
					}
					$('#listData').datagrid('reload');
				}
			})
		}	




	//验证			
	function yan(){
		if($("#proxyTeacher").textbox('getValue')==''){
			alert("老师姓名不能为空"); return false;
		}	
		else if($("#reimAmount").textbox('getValue')==''){
			alert("需报销总钱数不能为空"); return false;
		}else if($("#startFromAddress").textbox('getValue')==''){
			alert("出发地不能为空"); return false;
		}else if($("#bankName").textbox('getValue')==''){
			alert("银行卡姓名不能为空"); return false;
		}else if($("#bankCardNumber").textbox('getValue')==''){
			alert("银行卡号不能为空"); return false;
		}else if($("#openingBank").textbox('getValue')==''){
			alert("开户行不能为空"); return false;
		}else if($("#purpose").textbox('getValue')==''){
			alert("来访目的不能为空"); return false;
		}
			var dui = true;
		$('input[name="trans"]').each(function(){
			if(this.value==''){
				alert("出行方式不能为空");  dui=false;   return false;
			}
		})
		if(dui==false)return false;
		$('input[name="ticketAmount"]').each(function(){
			
			if(this.value==''){
				alert("发票金额不能为空"); dui=false; return false;
			}
		})
		if(dui==false)return false;
		$('input[name="actualAmount"]').each(function(){
			if(this.value==''){
				alert("实报金额不能为空"); dui=false; return false ;
			}
		})
		if(dui==false)return false;
		
		if(pic==''){
			alert("照片凭证不能为空"); return false;
		}
	}
