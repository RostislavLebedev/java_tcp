// ServerPC.java
/*
Aufruf aus dem Command Prompt:
	java ServerPC <clientIP> <commandType> <command>
Unterschtütze <commandType>:
	-cmd
Die serverseitige Anwendung muss zuerst gestartet werden.
Nach dem Aufruf wird im Dateisystem des Servers eine Datei clientCmdOutput.txt erstellt.
Die Datei kann anschließend in der Web-Oberfläche dargestellt werden.
*/

import java.io.*;
import java.net.*;

public class ServerPC
{
	public static void main(String [] args) throws IOException
	{
		/*	Überprüfen, ob der Aufruf richtig stattgefunden hat.
			<commandType> "-cmd" */
		if(args.length != 3)
		{
			System.err.println("Aufruf: java ServerPC <clientIP> <commandType> <command>");
			System.exit(1);
		}

		int port = 4999;
		int backLog = 50;
		InetAddress bindAddr = InetAddress.getByName(args[0]);
		

		/* Erstellen einer Verbindung: Instanziierung eines ServerSockets und eines Sockets. */
		try
		{
			ServerSocket serverSocket = new ServerSocket(port, backLog, bindAddr);
			Socket clientSocket = serverSocket.accept(); // The accept method waits until a client starts up and requests a connection on the host and port of this server
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Daten an den Client
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8")); // Daten vom Client

			//System.out.println("try beginnt");

			String inputLine, outputLine;
            
            /* Wenn <commandType> == "-cmd", dann soll der Client den übergebenen Befehl <command> ausführen. */
            if(args[1].equals("-cmd"))
            	out.println(args[2]);

            //System.out.println("Command \"" + args[2] + "\" wird uebermittelt\n");

            /* FileWriter und BufferedWriter für die Speicherung des CMD-Outputs des Clients in einer .txt. */
            FileWriter fw = new FileWriter("clientCmdOutput.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            while((inputLine = in.readLine()) != null)
            {
            	System.out.println(inputLine); // Der CMD-Output des Clients wird in der CMD des Servers ausgegeben...
            	bw.write(inputLine + "\n"); // ...und zeilenweise in eine .txt geschrieben.
            }
            
            bw.close();
		}
		catch(UnknownHostException uhe)
		{
			System.err.println("Kenne den host " + args[0] + " nicht!");
		}
		catch(UnsupportedEncodingException ueex)
		{
			System.err.println("Nicht unterstützte Codierung!");
		}
		catch(IOException e)
		{
			System.out.println("Exception caught, when trying to listen to port " + port + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}