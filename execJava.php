<?php
	if(isset($_POST['action'])) {
		switch($_POST['action']) {
			case "clickMe":
				doStuff();
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
		shell_exec("javac server.java && java server");
	}

	function execClient()
	{
		chdir("java");
		shell_exec("javac client.java && java client");
	}
?>