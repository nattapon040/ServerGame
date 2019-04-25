package Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Server.Frame;
import Utility.Utility_Share;

public class ServerProgram implements OnSocketListener
{
	public Server server;
	
	private Frame serverFrame;
	
	private String ls = System.getProperty("line.separator");
	
	
	public Frame getServerFrame() {
		return serverFrame;
	}

	public void setServerFrame(Frame serverFrame) {
		this.serverFrame = serverFrame;
	}

	@Override
	public void onConnected(Channel channel)
	{
		Socket socket = channel.getSocket();
		String hostName = socket.getInetAddress().getHostName();
		int port = socket.getPort();
		
		String msg = "Client connected from " + hostName + ":" + port;
		serverFrame.textArea.append(msg+ls);
	}
	
	@Override
	public void onDisconnected(Channel channel)
	{
		server.remove(channel);
		
		Socket socket = channel.getSocket();
		String hostName = socket.getInetAddress().getHostName();
		int port = socket.getPort();
		
		String msg = "Client disconnected from " + hostName + ":" + port;
		serverFrame.textArea.append(msg+ls);
		
		//server.broadcast(msg);
	}
	
	@Override
	public void onReceived(Channel channel, Player msg)
	{
		server.broadcast(channel,msg);
	}
	
	public void start() throws IOException
	{
		

		int port = 61533;
		
		server = new Server(this);
		server.bind(port); // Open Server
		server.start(); // Start Accept Thread
		
		Utility_Share.sControlBroadcast = true;
		Thread thread = new Thread(new Broadcast());
		thread.start();
		
		
		
		serverFrame.textArea.append("Server has started."+ls);
	
	}

	public Server getServer() {
		return server;
	}

	
	

}
