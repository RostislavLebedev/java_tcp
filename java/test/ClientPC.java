import java.io.*;
import java.net.*;

public class ClientPC
{
	public static void main(String [] args) throws IOException
	{
		if(args.length !=2)
		{
			System.err.println("Aufruf: java ClientPC <hostName> <portNummer>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNummer = Integer.parseInt(args[1]);

		try (
			Socket socket = new Socket(hostName, portNummer);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;

			while ((fromServer = in.readLine()) != null)
			{
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("-q"))
                    break;
                
                fromUser = stdIn.readLine();
                if (fromUser != null)
                {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
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
}