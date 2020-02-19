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
		InetAddress myIP;
		Socket s;
		chatUtils chat;
		String message;
		

		// 	Socket(InetAddress address, int port)
		myIP = InetAddress.getByName("localhost"); // the own IPv4-Address
		s = new Socket(myIP.getHostAddress(), 4999);
		chat = new chatUtils(s);

		while(true)
		{
			// Nutzereinagben einlesen
			// chat.scanInput();

			// Nachricht vom Server erhalten
			System.out.print("Server:\t");
			message = chat.receiveMessage();

			// Wenn nach Systeminfo gefragt wird
			if(message.equals("-systeminfo"))
			{
				runSysInfo();
			}
		}
	}

	// Am Ende wird eine cmdOutput.txt erstellt
	public static void runSysInfo()
	{
		String basedir;
		runCMD runCommand;

		basedir = "C:/xampp/htdocs/mttc/proto/java";
		runCommand = new runCMD(basedir);

		try
		{
			runCommand.execCMD("systeminfo");
		}
		catch(InterruptedException iex)
		{
			iex.printStackTrace();
		}
	}

	static void endConn()
	{
		System.exit(1);
	}






	
	/*
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
    */
}