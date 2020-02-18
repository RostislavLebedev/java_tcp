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

	public static void receiveMessage()
	{
		InputStreamReader inStream;
		BufferedReader bf;
		String message;

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
	}

	public static void scanInput()
	{
		Scanner scan;
		String message;

		System.out.print("Sie:\t");
		scan = new Scanner(System.in);
		message = scan.nextLine();
		if(message.equals("-q"))
			System.exit(1);
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