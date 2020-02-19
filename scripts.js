function myFunc()
{
	$.ajax({url:"execJava.php",success:function(result)
	{
		$("div").text(result);
	}
})
}

$(document).ready(function(){
	$('.button').click(function(){
		var clickBtnValue = $(this).val();
		var ajaxurl =  "execJava.php",
		data = {'action': clickBtnValue};
		$.post(ajaxurl,data,function(response) {
			$("div").text(response);
			//alert("action performed");
		});
	});
});