/*
 * class client
 * Client und Server können nacheinander Nachrichten austauschen
 * Enter zum Abschicken
 * "-q" + Enter zum Abbrechen
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class client
{
	public static void main(String [] args) throws IOException
	{
		// 	Socket(InetAddress address, int port)
		InetAddress myIP = InetAddress.getByName("localhost"); // the own IPv4-Address
		Socket s = new Socket(myIP.getHostAddress(), 4999);

		System.out.println("\nENTER = Abschicken\n\"-q\" zum Beenden");

		while(true)
		{
			// Nutzereinagben einlesen
			sendeNachricht(s);

			// Nachricht vom Server erhalten
			nachrichtVomServer(s);
		}
	}

	public static void sendeNachricht(Socket s)
    {
    	Scanner scan = new Scanner(System.in);
		System.out.print("\tSie: ");
		String eingabeClient = scan.nextLine();
		if (eingabeClient.equals("-q"))
			System.exit(1);
		else
			nachrichtAnServer(s, eingabeClient); // Nutzereingaben an den Server schicken
    }

	// nachrichtAnServer(...) übermittelt die clientseitigen Nutzereingaben an den Server
	public static void nachrichtAnServer(Socket s, String anServer)
	{
		try
		{
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println(anServer);
			pr.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	// nachrichtVomServer(...) gibt die vom Server empfangenen Nachrichten aus
	public static void nachrichtVomServer(Socket s)
	{
		String str = "";

		// runCMD Instanz, um CMD-Befehle auszuführen
		String basedir = "C:/Users/Rostislav Lebedev/repos/firstProject";
		runCMD runCommand = new runCMD(basedir);

		try
		{
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			str = bf.readLine();
			System.out.println("\tserver: " + str);

			if (str.equals("-systeminfo"))
			{
				try
				{
					runCommand.execCMD("systeminfo"); // CMD "systeminfo" ausführen
					sendOutput(s); // die Systeminformationen am Server darstellen
				}
				catch(InterruptedException iex)
				{
					iex.printStackTrace();
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	// Datei cmdOutput.txt einlesen und den inhalt zeilenweise an den Server schicken
    // WIP
    public static void sendOutput(Socket s)
    {
        String datName = "cmdOutput.txt";
        File datei = new File(datName);
        BufferedReader in = null;
        String zeile;

        try
        {
            in = new BufferedReader(new FileReader(datName));
            zeile = null;

            while ((zeile = in.readLine()) != null)
            {
            	System.out.println(zeile);
                //nachrichtAnServer(s,zeile); // funktioniert nicht, wie es soll
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Datei nicht gefunden");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(in != null)
        			in.close();
        	}
        	catch(IOException ioex)
        	{
        		ioex.printStackTrace();
        	}
        }
    }
}