import java.io.*;
import java.net.*;

public class client
{
	public static void main(String [] args) throws IOException
	{
		int port = 4999;
		InetAddress address = InetAddress.getByName("localhost");

		try
		{
			Socket socket = new Socket(address, port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Daten an den Server
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Daten vom Server

			String fromServer;

			fromServer = in.readLine();
			System.out.println(fromServer);

			String toServer;
			out.println("Der Client sendet etwas an den Server");
		}
		catch(IOException ioe)
		{
			System.out.println("IOException");
		}
	}
}