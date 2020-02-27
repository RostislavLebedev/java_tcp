import java.io.*;
import java.net.*;

public class server
{
	public static void main(String [] args) throws IOException
	{
		InetAddress bindAddr = InetAddress.getByName(args[0]);
		int backLog = 50;
		int port = 4999;

		try
		{
			ServerSocket serverSocket = new ServerSocket(port, backLog, bindAddr);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));

			out.println("Der Server sendet etwas an den Client");
			String inputLine;

			while((inputLine = in.readLine()) != null)
			{
				System.out.println(inputLine);
			}

			System.exit(1);
		}
		catch(UnknownHostException uhe)
		{
			System.err.println("UnknownHostException");
		}
		catch(IOException ioe)
		{
			System.err.println("IOException");
		}
	}
}