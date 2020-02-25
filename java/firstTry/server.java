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
		String message;

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
			// System.out.print("Client:\t");
			// message = chat.receiveMessage();
		}
	}

    static void printOutput()
    {
        String datName = null;;
        File datei = null;
        BufferedReader in = null;
        String zeile = null;

        try
        {
            datName = "cmdOutput.txt";
            datei = new File(datName);
            in = new BufferedReader(new FileReader(datName));
            while((zeile = in.readLine()) != null)
            {
                System.out.println(zeile);
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Datei " + datName + " nicht gefunden!");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }        
        finally
        {
            try
            {
                if(in !=null)
                    in.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

	static void endConn()
	{
		System.exit(1);
	}
}