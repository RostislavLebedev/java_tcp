<?php
	if(isset($_POST['action'])) {
		switch($_POST['action']) {
			case "clickMe":
				execHelloWorld();
				break;
		}
	}

	function doStuff() {
		print shell_exec("systeminfo");
		exit;
	}

	function execHelloWorld() {
		chdir("java");
		echo shell_exec("javac helloworld.java && java helloworld");
	}

	function execServer()
	{
		chdir("java");
		shell_exec("javac ServerPC.java && java ServerPC");
	}

	function execClient()
	{
		chdir("java");
		shell_exec("javac ClientPC.java && java ClientPC");
	}
?>