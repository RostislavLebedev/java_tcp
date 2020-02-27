Das Programm dient der Ausführung von CMD-Befehlen auf entfernten Rechnern.
Aufruf am Server/Master:
	java ServerPC <port> -cmd <command>
Auruf am Client/Slave:
	java ClientPC <IPv4> <port>
Die vom Server empfangenen Daten (Client-CMD-Output) werden serverseitig in einer clientCmdOutput.txt gespeichert,
um anschließend in der Web-Oberfläche dargestellt werden zu können.
Die Anwendung funktioniert unter Windows.
