// server_pc.java

import java.io.*;
import java.net.*;

public class ServerPC
{
	public static void main(String [] args) throws IOException
	{
		if(args.length != 3)
		{
			System.err.println("Aufruf: java ServerPC <portNummer> <commandType> <command>");
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
            
            if(args[1].equals("-cmd"))
            	out.println(args[2]);

            
            while((inputLine = in.readLine()) != null)
            {
            	System.out.println(inputLine);
            }
            
		}
		catch(IOException e)
		{
			System.out.println("Exception caught, when trying to listen to port " + portNummer + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}