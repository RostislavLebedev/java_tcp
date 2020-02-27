/*
 * class chatUtils
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class chatUtils
{
	private static Socket s;

	public chatUtils(Socket s)
	{
		this.s = s;
	}

	public static String receiveMessage()
	{
		InputStreamReader inStream;
		BufferedReader bf;
		String message = null;

		try
		{
			inStream = new InputStreamReader(s.getInputStream());
			bf = new BufferedReader(inStream);
			message = bf.readLine();
			System.out.println(message);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			return message;
		}
	}

	// Einlesen der Nutzereingaben
	public static void scanInput()
	{
		Scanner scan;
		String message;

		System.out.print("Sie:\t");
		scan = new Scanner(System.in);
		message = scan.nextLine();
		if(message.equals("-q"))
		{
			//System.exit(1);
			server.endConn();
			client.endConn();
		}
		else if(message.equals("-systeminfo"))
			server.printOutput(); // das ist eine sehr haessliche Lösung, das muss überarbeitet werden
		else
			sendMessage(message);
	}

	// wird nur in dieser Klasse aufgerufen
	public static void sendMessage(String message)
	{
		PrintWriter pr;

		try
		{
			pr = new PrintWriter(s.getOutputStream());
			pr.println(message);
			pr.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}