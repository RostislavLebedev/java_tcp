// MyServer.java

import java.io.*;
import java.net.*;

public class MyServer
{
	private ServerSocket server;

	public MyServer(String bindAddr) throws Exception
	{
		this.server = new ServerSocket(0,1,InetAddress.getByName(bindAddr));
	}

	public InetAddress getServerSocketAddress()
	{
		return this.server.getInetAddress();
	}

	public int getPort()
	{
		return this.server.getLocalPort();
	}

	private void listen() throws Exception
	{
		String data = null;
		Socket client = this.server.accept();
		String clientAddress = client.getInetAddress().getHostAddress();
		System.out.println("Neue Verbindung mit " + clientAddress);

		BufferedReader in = new BufferedReader(
			new InputStreamReader(client.getInputStream()));

		while((data = in.readLine()) != null)
		{
			System.out.println(clientAddress + ": " + data);
		}
	}

	public static void main(String [] args) throws Exception
	{
		MyServer app = new MyServer(args[0]);

		System.out.println("Server läuft: " + app.getServerSocketAddress().getHostAddress() + " Port = " + app.getPort());

		app.listen();
	}
}