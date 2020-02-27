// Der div mit id="textfeld0" wird mit dem Text gefüllt,
// den die php-Funktionen liefern.
$(document).ready(function(){
	$('.button').click(function(){
		var clickBtnValue = $(this).val();
		var ajaxurl =  "execJava.php",
		data = {'action': clickBtnValue};
		$.post(ajaxurl,data,function(response) {
			$("#textfeld0").text(response);
		});
	});
});