package Connection;




import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

import Utility.Utility_Share;

public class Broadcast implements Runnable{
	
	
	@Override
	public void run() {
		try {
		      //Keep a socket open to listen to all the UDP trafic that is destined for this port
			DatagramSocket   socket = new DatagramSocket(61533, InetAddress.getByName("0.0.0.0"));
		    socket.setBroadcast(true);
		      
		      
		      
		      
		      while (Utility_Share.sControlBroadcast == true) {
		        System.out.println(getClass().getName() + ">>>Ready to receive broadcast packets!");

		        //Receive a packet
		        byte[] recvBuf = new byte[15000];
		        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
		        socket.receive(packet);

		        //Packet received
		        System.out.println(getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
		        System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

		        //See if the packet holds the right command (message)
		        String message = new String(packet.getData()).trim();
		        if (message.equals("DISCOVER_FUIFSERVER_REQUEST")) {
		          byte[] sendData = "DISCOVER_FUIFSERVER_RESPONSE".getBytes();

		          //Send a response
		          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
		          socket.send(sendPacket);

		          System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
		        }
		      }
		     
		      socket.setBroadcast(false);
		      socket.close();
		    } catch (IOException ex) {
		    	
		      Logger.getLogger(Broadcast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		      Utility_Share.sControlBroadcast = false;
		    }
		
	}
	


	
}
