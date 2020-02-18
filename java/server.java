/*
 * class server
 * Client und Server können nacheinander Nachrichten austauschen
 * Enter zum Abschicken
 * "-q" + Enter zum Abbrechen
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
		chatUtils chat = new chatUtils(s);

		System.out.println("client connected");
		while(true)
		{
			// Nutzereingaben einlesen
			chat.scanInput();

			// Nachricht vom Client empfangen
			System.out.print("Client:\t");
			chat.receiveMessage();
		}
	}
}