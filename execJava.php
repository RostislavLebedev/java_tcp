<?php
	if(isset($_POST['action'])) {
		switch($_POST['action']) {
			case "clickMe":
				execServer();
				break;
		}
	}

	// zum Testen
	function execHelloWorld() {
		chdir("java");
		echo shell_exec("javac helloworld.java && java helloworld");
	}

	// hier wird die serverseitige java-Anwendung gestartet
	// die clientseitige Anwendung muss manuell aus dem Command Prompt des Clients gestartet werden
	function execServer()
	{
		chdir("java");
		//shell_exec("javac ServerPC.java && java ServerPC 4444 -cmd ipconfig");
		showOutput();
	}

	// Die Zeilenumbrüche werden nicht erkannt!
	function showOutput() {
		$datei = file('clientCmdOutput.txt');
		# header('Content-type: text/plain');
		for ($zeile = 0; $zeile < count($datei); $zeile++) {
			$line = $datei[$zeile];
			// $line = "<p>" . $datei[$zeile] . "<p/>";
			// echo str_replace(array("\r\n","\r","\n"), '<br>', $line);
			// echo $line;
			// echo '<br />' . $line;
			// echo $line . '<br />';
			// echo $line . '<pre>';
			// echo $line . "<table>";
			// echo 'first line' . PHP_EOL;
			echo nl2br($line);
		}
	}
?>