// server_pc.java
/*

Aufruf aus dem Command Prompt:
	java ServerPC <portNummer> <commandType> <command>
Unterschtütze <commandType>:
	-cmd

Nach dem Aufruf wird im Dateisystem des Servers eine Datei clientCmdOutput.txt erstellt.
Die Datei kann anschließend in der Web-Oberfläche dargestellt werden.

*/
import java.io.*;
import java.net.*;

public class ServerPC
{
	public static void main(String [] args) throws IOException
	{
		// Überprüfen, ob der Aufruf richtig stattgefunden hat.
		// <commandType> "-cmd"
		if(args.length != 3)
		{
			System.err.println("Aufruf: java ServerPC <portNummer> <commandType> <command>");
			System.exit(1);
		}


		int portNummer = Integer.parseInt(args[0]);

		//Erstellen einer Verbindung: Instanziierung eines ServerSocket und eines Sockets.
		try (
			ServerSocket serverSocket = new ServerSocket(portNummer);
			Socket clientSocket = serverSocket.accept(); // The accept method waits until a client starts up and requests a connection on the host and port of this server
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Daten an den Client
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Daten vom Client
		) {
			String inputLine, outputLine;
            
            // Wenn <commandType> == "-cmd", dann soll der Client den übergebenen Befehl <command> ausführen.
            if(args[1].equals("-cmd"))
            	out.println(args[2]);

            // FileWriter und BufferedWriter für die Speicherung des CMD-Outputs des Clients.
            FileWriter fw = new FileWriter("clientCmdOutput.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            while((inputLine = in.readLine()) != null)
            {
            	System.out.println(inputLine); // Der CMD-Output des Clients wird in der CMD des Servers ausgegeben...
            	bw.write(inputLine + "\n"); // ...und zeilenweise in eine .txt geschrieben.
            }
            
            bw.close();
		}
		catch(IOException e)
		{
			System.out.println("Exception caught, when trying to listen to port " + portNummer + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}