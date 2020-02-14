/*
 * class client
 * 
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class client
{
	public static void main(String [] args) throws IOException
	{
		// 	Socket(InetAddress address, int port)
		Socket s = new Socket("localhost", 4999);

		System.out.println("ENTER = Abschicken\n\"-q\" zum Beenden");
		while(true)
		{
			// Nutzereinagben einlesen
			Scanner scan = new Scanner(System.in);
			System.out.print("\tSie (ENTER = abschicken): ");
			String eingabeClient = scan.nextLine();

			if (eingabeClient.equals("-q"))
				break;

			// Nutzereingaben an den Server schicken
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println(eingabeClient);
			pr.flush();

			// Nachricht vom Server erhalten
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			String str = bf.readLine();
			System.out.println("\tserver: " + str);
		}
	}
}