package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import Utility.Utility_Share;

public class Server implements Runnable{
	
	public boolean running;
	
	private ServerSocket serverSocket;

	private ArrayList<Channel> channels;
	
	public OnSocketListener onSocketListener;
	
	public Server(OnSocketListener onSocketListener){
		this.onSocketListener = onSocketListener;
	}
	
	public void bind(int port) throws IOException{
		this.serverSocket = new ServerSocket(port);
		Utility_Share.sControlCloseServer = true;
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}

	public void stop() throws IOException{
		Utility_Share.sControlBroadcast = false;
		running = false;
		serverSocket.close(); 
		
	}
	
	@Override
	public void run(){
		channels = new ArrayList<>();
		
		running = true;
		while(running){
			try{
				Socket socket = serverSocket.accept();
				
				Channel channel = new Channel(socket, onSocketListener);
				channel.start();
				
				channels.add(channel);
				
			}catch (SocketException e){
				break;
			}catch (IOException e){
				break;
			}
		}
		
		try{
			for(Channel channel : channels){
				channel.stop();
			}
			channels.clear();
		}catch(IOException e){e.printStackTrace();}
	}

	public void broadcast(Channel c,Player msg){
		if(!running)
			return;
		
		for(Channel channel : channels){
			//if(c != channel) {
				Thread thread = new Thread(new SendThread(channel, msg));
				thread.start();
			//}
		}
	}
	
	public void sendClient(Channel c,Player msg) {
		Thread thread = new Thread(new SendThread(c, msg));
		thread.start();
	}
	
	public void remove(Channel channel){
		if(!running)
			return;
		
		channels.remove(channel);
	}
	
	public ArrayList<Channel> getChannels(){
		return channels;
	}
}
