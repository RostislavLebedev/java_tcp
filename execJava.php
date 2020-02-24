<?php
	if(isset($_POST['action'])) {
		switch($_POST['action']) {
			case "clickMe":
				execServer();
				break;
		}
	}

	function execHelloWorld() {
		chdir("java");
		echo shell_exec("javac helloworld.java && java helloworld");
	}

	function execServer()
	{
		chdir("java");
		//shell_exec("javac ServerPC.java && java ServerPC 4444 -cmd ipconfig");
		showOutput();
	}

	function execClient()
	{
		chdir("java");
		shell_exec("javac ClientPC.java && java ClientPC");
	}

	// Die Zeilenumbrüche werden nicht erkannt!
	function showOutput() {
		$datei = file("clientCmdOutput.txt");
		for ($i = 0; $i < count($datei); $i++) {
			echo nl2br($datei[$i]);
		}
	}
?>