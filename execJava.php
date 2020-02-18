<?php
chdir("java");
echo shell_exec("javac helloworld.java && java helloworld");
?>