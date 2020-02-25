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
		shell_exec("javac ServerPC.java && java ServerPC 4444 -cmd ipconfig");
		showOutput();
	}

	// Die Zeilenumbrüche werden nicht erkannt!
	function showOutput() {
		$datei = file("clientCmdOutput.txt");
		for ($i = 0; $i < count($datei); $i++) {
			echo nl2br($datei[$i]);
		}
	}
?>