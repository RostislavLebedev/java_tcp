Das Programm dient der Ausführung von CMD-Befehlen auf entfernten Rechnern.
Server/Master:
	java ServerPC <port> -cmd <command>
Client/Slave:
	java ClientPC <IPv4> <port>
	
TODO:
Die vom Server empfangenen Daten (Client-CMD-Output) werden in einer .txt gespeichert, um anschließend in der Web-Oberfläche dargestellt werden zu können.
