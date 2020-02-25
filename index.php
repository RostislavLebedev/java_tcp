<!DOCTYPE html>
<html>
<head>
	<title>Web-Interface Proto</title>
	<meta http-equiv="Content-Type"  content="text/html" charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="scripts.js"></script>
	<link rel="stylesheet" type="text/css" href="styles.css" media="screen"/>
</head>

<body>
	<button class="button" name="clickMe" value="clickMe" id="theButton">Click Me!</button>
	<div id="textfeld0">
		<?php
		chdir("java");
		$datei = file('clientCmdOutput.txt');
		# header('Content-type: text/plain');
		for ($zeile = 0; $zeile < count($datei); $zeile++) {
			$line = $datei[$zeile];
			// $line = "<p>" . $datei[$zeile] . "<p/>";
			// echo str_replace(array("\r\n","\r","\n"), '<br>', $line);
			// echo $line;
			echo "<br>" . $line;	
		}
		?>
	</div>

	<!-- FOOTER BEGINNT -->
	<div class="footer">
		<p align="center">
			<br>
			Lebedev Rostislav | 2020
		</p>
	</div>
	<!-- FOOTER ENDET -->
	
</body>
</html>