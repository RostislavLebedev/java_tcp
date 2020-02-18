function myFunc()
{
	$.ajax({url:"execJava.php",success:function(result)
	{
		$("div").text(result);
	}
})
}