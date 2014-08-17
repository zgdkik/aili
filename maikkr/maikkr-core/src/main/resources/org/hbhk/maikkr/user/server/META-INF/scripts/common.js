$j(document).ready(function(){
	$j('.non-empty').focus(function(){
		$j(this).removeClass('notNull'); 
	});
	$j('.non-empty').blur(function(){
		var val= $j(this).val();
		if(val==null || val==""){
			$j(this).addClass('notNull'); 
		}
	});
});
function validate(){
	var  flag = true;
	$j('.non-empty').each(function(){
		var val= $j(this).val();
		if(val==null || val==""){
			$j(this).addClass('notNull'); 
			flag = false;
		}
	});
	return flag;
};