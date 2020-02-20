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
	            	execCMD(fromServer, out);

	            	break;

	            	
	            	/*
	            	try
	            	{
	            		// ????????????????????????????????????????????????
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
	            	*/
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

        // Ausgabe in der CMD des Clients
        System.out.println("OUTPUT wird zum Server gesendet!");
        printStream(proc.getInputStream(), out);
        System.out.println("ERROR-OUTPUT");
        printStream(proc.getErrorStream(), out);
    }

    // Ausgabe in der CMD des Clients
    public static void printStream(InputStream stream, PrintWriter out)
    {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(stream));
        String line = "";
        try
        {
           while ((line = in.readLine()) != null)
           {
                out.println(line);
           }
           in.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}