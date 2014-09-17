$j(document).ready(function() {
	$j("#main").scroll(function() {
		$j("#bottom_top").css("marginTop",$j('#main')
			.css("scrollTop")+ document.body.offsetHeight - 180);
	});
	updateHeight();
	$j("body").on("click",".cdel",function() {
		var data ={"id":$j(this).attr("oid")};
		$j.ajax({
    		url: base+"user/editCare.htm",
    		type:"POST",
    		data:data,
    		success: function(data, textStatus){
    			$j.toast("删除成功");
    		},
    		exception:function(data, textStatus){
    			$j.toast("删除失败");
    		}
    	});
		
	});
});

function current(d){ 
	var str=''; 
	str +=d.getFullYear()+'-'; //获当前年份 
	str +=d.getMonth()+1+'-'; //获取当前月份（0——11） 
	str +=d.getDate()+' '; 
	str +=d.getHours()+':'; 
	str +=d.getMinutes()+':'; 
	str +=d.getSeconds(); 
	return str;
} 

function updateHeight() {
	var height = document.getElementById("main").scrollHeight;
	$j("#center").css("height" , height);
	$j("#ct_left").css("height ",height - 10);
	$j("#ct_right").css("height" , height);
};