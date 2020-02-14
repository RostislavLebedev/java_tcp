/*
 * class server
 *
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class server
{
	public static void main(String [] args) throws IOException
	{
		// ServerSocket und Socket erstellen
		ServerSocket servSock = new ServerSocket(4999);
		Socket s = servSock.accept();

		System.out.println("client connected");
		System.out.println("ENTER = Abschicken\n\"-q\" zum Beenden");
		while(true)
		{
			// Nachricht vom Client empfangen
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			String str = bf.readLine();
			System.out.println("\tclient: " + str);

			// Nutzereingaben einlesen
			Scanner scan = new Scanner(System.in);
			System.out.print("\tSie (Enter = abschicken): ");
			String eingabeServer = scan.nextLine();

			if (eingabeServer.equals("-q"))
				break;

			// Nutzereingaben auslesen
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println(eingabeServer);
			pr.flush();
		}
	}
}