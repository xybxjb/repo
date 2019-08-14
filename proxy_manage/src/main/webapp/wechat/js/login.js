$(function(){
	/*手机号正则验证*/
	$('.user input').blur(function(){
		 $('.gain').off("click");
		var regex = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
		var cont = $(this).val();
		if(cont == ''){
            $('.err').html('手机号不能为空');
        }else if(!regex.test(cont)){
            $('.err').html('格式不正确');
        }else{
            $('.err').html('');
            $('.gain').on('click',fun);
        }
	})
	
	/*验证码*/
    //$('.gain').on('click',fun);
	function fun(){
		$.ajax({
			type:"POST",
			url:"/proxy_manage/weixin/getmessige",
			xhrFields: {
            	withCredentials: true // 这里设置了withCredentials
			},
			data : {
				tel : $('.phone').val()
			},
			success : function(response){
				console.log(response);
				if(response['status'] == 0){
					var num = 59;
			        $('.gain').text(num+'秒后重新发送');
			        var timer = setInterval(function(){
			            $('.gain').off("click");
			            num--;
			            $('.gain').text(num+'秒后重新发送');
			            if(num==0){
					        clearInterval(timer);
					        $('.gain').on('click',fun);
					        num = 59;
					        $('.gain').text("重新发送");
					    }
			        },1000)
				} 
				if(response['status'] ==1){
					$('.err').html('该用户不存在!');
				}
			},
			error : function(error){
				console.log(error);
			}
		});
    }
	
	$('.logo_put').click(function(){
		$.ajax({
			type:"GET",
			url:"/proxy_manage/weixin/getcode",
			xhrFields: {
            	withCredentials: true // 这里设置了withCredentials
			},
			data : {
				code :$('#gain').val()
			},
			success : function(response){
				console.log(response);
				if(response['status'] == 0){
					window.location = "welcome.html";
					window.location.href = "/proxy_manage/wechat/welcome.html";

					$('.yzm').html('');
				} 
				if(response['status'] ==1){
					//$('.err').html('该用户不存在!');
					$('.yzm').html('验证码错误！请重新获取');
				}
			},
			error : function(error){
				console.log(error);
			}
		});
	})
	
})
