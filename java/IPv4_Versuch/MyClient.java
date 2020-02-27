// MyClient.java

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient
{
	private Socket socket;
	private Scanner scanner;

	private MyClient(InetAddress serverAddress, int serverPort) throws Exception
	{
		this.socket = new Socket(serverAddress, serverPort);
	}

	private void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
    }

	private static void main(String [] args) throws Exception
	{
		MyClient client = new MyClient(
			InetAddress.getByName(args[0]),
			Integer.parseInt(args[1]));

		System.out.println("Mit dem Server verbunden: " + client.socket.getInetAddress());
	}
}