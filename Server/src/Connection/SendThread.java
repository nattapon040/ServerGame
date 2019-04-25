package Connection;

public class SendThread extends Thread{

	private Channel channel;
	private Player player;
	
	public SendThread(Channel channel,Player player) {
		this.channel = channel;
		this.player = player;
	}
	
	@Override
	public void run() {
		channel.send(channel, player);
	}

}
