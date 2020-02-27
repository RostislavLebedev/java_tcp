// ClientPC.java
/*
Aufruf asus dem Command Prompt:
	java Client PC <hostname> <portNummer>
Die serverseitige Anwendung muss zuerst gestartet werden.
Sobald die server- und die clientseitige Anwendung gestartet werden, wird ein CMD-Befehl am Client ausgeführt.
Der Output des Command Promt wird an den Server geschickt, wo es anschließend in einer clientCmdOutput.txt gespeichert wird.
*/

import java.io.*;
import java.net.*;

public class ClientPC
{
	public static void main(String [] args) throws IOException
	{
		/* Überprüfen, ob der Aufruf richtig stattgefunden hat. */
		if(args.length !=2)
		{
			System.err.println("Aufruf: java ClientPC <hostName> <portNummer>");
			System.exit(1); // Die Anwendung wird gestoppt, falls der Aufruf falsch
		}

		String hostName = args[0];
		int portNummer = Integer.parseInt(args[1]);
		//String basedir = "C:/xampp/htdocs/mttc/proto/java";
		//runCMD runCommand = new runCMD(basedir);

		/* Erstellen einer Verbindung: Initialisierung eines Sockets */
		try (
			Socket socket = new Socket(hostName, portNummer);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Daten an den Server
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Daten vom Server
		) {
			//BufferedReader br;
			//BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			//String fromUser = null;

			fromServer = in.readLine();
			System.out.println("Server: " + fromServer);
			execCMD(fromServer, out);
		}
		catch(InterruptedException iex)
		{
			System.err.println("A wild InterruptedException occured!");
		}
		catch(UnknownHostException e)
		{
			System.err.println("Kenne den Host nicht: " + hostName);
			System.exit(1);
		}
		catch(IOException ex)
		{
			System.err.println("Kein I/O für die Verbindung mit " + hostName);
			System.exit(1);
		}
	}

	/* Ausführung eines vom Server empfangenen CMD-Befehls */
	public static void execCMD(String command, PrintWriter out) throws InterruptedException
    {
    	//command += " > cmdOutput.txt"; // Output des CMD-Befehls wird in der Datei cmdOutput.txt gespeichert
        System.out.println("executing command: " + command);
        Runtime rt = null;
        Process proc = null;

        try
        {
            rt = Runtime.getRuntime();
            proc = rt.exec("cmd /c" + command);
        }
        catch(IOException e)
        {
            System.err.println("Etwas ging schief!");
        }

        System.out.println("Output wird zum Server gesendet!");
        sendStream(proc.getInputStream(), out); // CMD-Output des Clients wird an den Server weitergeleitet
        System.out.println("ERROR-OUTPUT:");
        sendStream(proc.getErrorStream(), out);
    }

    /* Weiterleitung des Client-CMD-Outputs an den Server */
    public static void sendStream(InputStream stream, PrintWriter out)
    {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(stream));
        String line = "";
        try
        {
           while ((line = in.readLine()) != null)
           {
                out.println(line); // zeilenweise an das PrintWriter-Objekt
           }
           in.close();
        }
        catch (IOException ex)
        {
        	System.err.println("IOException bei der Weiterleitung der CMD-Ausgabe!");
            ex.printStackTrace();
        }
    }
}