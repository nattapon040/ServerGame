package PlayerBoss;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import Connection.Channel;
import Connection.Player;
import Connection.SendThread;
import Utility.Utility_Share;

public class ClientPlayer extends JLabel implements ActionListener{
	
	private int x,y;
	private int speedX=0,speedY=0;
	public Player player;
	
	private int blood;
	private Timer timer;
	private boolean addToFrame = false;
	private Channel channel;
	
	public ClientPlayer(Channel channel,Player player) {
		timer = new Timer(1, this);
		timer.start();
		this.channel = channel;
		this.player =player;
		System.out.println(player.getBlood());
		this.x = player.getX();
		this.y = player.getY();
		this.blood = player.getBlood();
		
		setIcon(new ImageIcon(Utility_Share.imgPlayer));
		setBounds(x, y, 82, 82);
		
	}
	
	public void updateSpeed () {
		setLocation(x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	public int getSpeedX() {
		return speedX;
	}

	
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public boolean isAddToFrame() {
		return addToFrame;
	}

	public void setAddToFrame(boolean addToFrame) {
		this.addToFrame = addToFrame;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 82, 82);
	}
	
	public int getBlood() {
		return player.getBlood();
	}

	public void setBlood(int blood) {
		this.player.setBlood(blood);
	}
	
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateSpeed();
		//System.out.println("Blood" + player.getBlood());
		//Utility_Share.program.server.broadcast(channel, player);
	}
	
	
}
