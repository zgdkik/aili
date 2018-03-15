var wait = 60;
function time(o) {
	$('.popover').remove();
	if (wait == 0) {
		o.removeAttr("disabled");
		o.html("获取验证码");
		wait = 60;
	} else {
		o.attr("disabled", true);
		o.html("重新发送(" + wait + ")");
		wait--;
		setTimeout(function() {
			time(o);
		}, 1000);
	}
}
$(document).ready(function() {
	 $("body").find('.popover').each(function(){
	        $(this).click(function(e){
	            $('.popover').remove();
	            e.preventDefault();
	            return false;
	        });
	        $(this).popover({
	            trigger : 'click'
	        });
	    });
	$('#forget-form').bootstrapValidator({
		message : '输入值无效',
		fields : {
			userName : {
				validators : {
					notEmpty : {
						message : '账号不能为空'
					}
				}
			},
			captcha : {
				validators : {
					notEmpty : {
						message : '验证码不能为空'
					}
				}
			},
			newPassword : {
				validators : {
					notEmpty : {
						message : '新密码不能为空'
					},
					stringLength : {
						min : 6,
						max : 20,
						message : '密码6位到20之间'
					},
				}
			},
			confirmPassword : {
				validators : {
					notEmpty : {
						message : '确认输入不能为空'
					},
					identical : {
						field : 'newPassword',
						message : '确认密码与新密码不一致'
					},
					stringLength : {
						min : 6,
						max : 20,
						message : '密码6位到20之间'
					},
				}
			}
		}
	});
	
	$("[data-toggle='popover']").popover();
	$('.userName').bind('input propertychange', function() {
		var validate = $('.userName').val();
		if (validate!=null && validate.trim().length>=5) {
			$('.get-captcha').removeAttr("disabled");
		}else{
			$('.get-captcha').attr("disabled", "disabled");
		}
	}); 
	$('.get-captcha').on("click", function() {
		var validate = $('.userName').val();
		if (validate==null || validate.trim().length<5) {
			return;
		}
		var bb = $(this);
		bb.attr("disabled", true);
		var param = $(".forget-form").getFormObj();
		sds.sendPost(base + '/user/getCaptcha', param, {
			successHandler : function(data, textStatus, jqXHR) {
				bootbox.alert(data.msg);
				if(data.success){
					time(bb);
				}
			}
		});
	});

	$('.btn-block').on("click", function() {
		$('#forget-form').data('bootstrapValidator').validate();
		var validate = $('#forget-form').data('bootstrapValidator').isValid();
		if (!validate) {
			return;
		}
		var me = $(this);
		me.attr("disabled", "disabled");
		var param = $(".forget-form").getFormObj();
		sds.sendPost(base + '/user/retrievePassword', param, {
			successHandler : function(data, textStatus, jqXHR) {
				me.removeAttr("disabled");
				if(data.success==false){
					bootbox.alert(data.msg);
				}else{
					bootbox.alert(data.msg,function(){
						window.location.href=base+"/user/login";
					});
				}
				
			}
		});
	});
});
