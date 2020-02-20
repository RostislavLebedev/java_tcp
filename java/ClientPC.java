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
		String basedir = "C:/xampp/htdocs/mttc/proto/java";
		runCMD runCommand = new runCMD(basedir);

		try (
			Socket socket = new Socket(hostName, portNummer);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			BufferedReader br;
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser = null;

			while ((fromServer = in.readLine()) != null)
			{
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("-q"))
                    break;
                else
	            {
	            	runCommand.execCMD(fromServer);

	            	try
	            	{
	            		br = new BufferedReader(new FileReader("cmdOutput.txt"));

	            		while((fromUser = br.readLine()) != null)
		            	{
		            		out.println(fromUser);
		            	}
		            	System.exit(1);
	            	}
	            	catch(IOException ioex)
	            	{
	            		System.err.println("IOException beim Einlesen von cmdOutput.txt!");
	            		System.exit(1);
	            	}
	            }
            }
		}
		catch(FileNotFoundException fnex)
		{
			System.err.println("Datei nicht gefunden!");
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

	public static void datEinlesen()
	{
		
	}
}