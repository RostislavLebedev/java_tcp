// server_pc.java

import java.io.*;
import java.net.*;

public class ServerPC
{
	public static void main(String [] args) throws IOException
	{
		if(args.length != 1)
		{
			System.err.println("Aufruf: java ServerPC <portNummer>");
			System.exit(1);
		}


		int portNummer = Integer.parseInt(args[0]);

		try (
			ServerSocket serverSocket = new ServerSocket(portNummer);
			Socket clientSocket = serverSocket.accept(); // The accept method waits until a client starts up and requests a connection on the host and port of this server
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {
			String inputLine, outputLine;
            
            // Initiate conversation with client
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("-q"))
                    break;
            }
		}
		catch(IOException e)
		{
			System.out.println("Exception caught, when trying to listen to port " + portNummer + "or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}