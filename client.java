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
		// runCMD Instanz, um CMD-Befehle auszuführen
		String basedir = "C:/Users/Rostislav Lebedev/repos/firstProject";
		runCMD runCommand = new runCMD(basedir);

		// 	Socket(InetAddress address, int port)
		InetAddress myIP = InetAddress.getByName("localhost"); // the own IPv4-Address
		Socket s = new Socket(myIP.getHostAddress(), 4999);

		System.out.println("\nENTER = Abschicken\n\"-q\" zum Beenden");
		while(true)
		{
			// Nutzereinagben einlesen
			Scanner scan = new Scanner(System.in);
			System.out.print("\tSie: ");
			String eingabeClient = scan.nextLine();
			if (eingabeClient.equals("-q"))
				break; // Abbruch, wenn "-q" eingegeben
			else
				nachrichtAnServer(s, eingabeClient); // Nutzereingaben an den Server schicken
			
			// Nachricht vom Server erhalten
			String serverSagt = nachrichtVomServer(s);

			// CMD systeminfo am Client aufsühren und in der Datei outputCMD.txt speichern
			if (serverSagt.equals("-systeminfo"))
			{
				try
				{
					runCommand.execCMD("systeminfo");
					outputCMDAnServer(s, runCommand);
				}
				catch(InterruptedException iex)
				{
					iex.printStackTrace();
				}
			}
		}
	}

	// nachrichtAnServer(...) übermittelt die clientseitigen Nutzereingaben an den Server
	public static void nachrichtAnServer(Socket s, String eingabeClient)
	{
		try
		{
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println(eingabeClient);
			pr.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	// nachrichtVomServer(...) gibt die vom Server empfangenen Nachrichten aus
	public static String nachrichtVomServer(Socket s)
	{
		String str = "";

		try
		{
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			str = bf.readLine();
			System.out.println("\tserver: " + str);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return str;
	}

	// outputCMDAnServer()
	public static void outputCMDAnServer(Socket s, runCMD runCommand) throws IOException
	{
		runCommand.sendOutput(s.getOutputStream());
	}
}