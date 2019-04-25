package Server;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import PlayerBoss.ClientPlayer;
import Utility.Utility_Share;

public class Laser extends JPanel implements ActionListener{
	
	private int x,y;
	private int damage;
	private Timer timer ;
	private boolean statusShoot = false;
	public Laser(int x, int y) {
		this.x = x;
		this.y = y;
		timer = new Timer(100, this);
		timer.start();
		setBounds(x, y, 2, 500);
		setBackground(new Color(255, 0, 7));
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
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	private void consiltion() {
		for (Map.Entry<String, ClientPlayer > entry : Utility_Share.clientList.entrySet()) {
			String key = entry.getKey();
			ClientPlayer playerData = Utility_Share.clientList.get(key);
			
			if(playerData.getBounds().intersects(new Rectangle(x+82, y+82, 3, getHeight()))) {
				if(playerData.getBlood() <= 0) {
					playerData.setBlood(0);
				}else {
					playerData.player.setBlood(playerData.player.getBlood()-20);
					
				}
				
				Utility_Share.program.server.sendClient(playerData.getChannel(), playerData.player);
				System.out.println("Consil");
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setSize(3, Utility_Share.sRand.nextInt(600));
		setLocation(x, y);
		if(statusShoot == true)consiltion();
	}
	public boolean isStatusShoot() {
		return statusShoot;
	}
	public void setStatusShoot(boolean statusShoot) {
		this.statusShoot = statusShoot;
	}
	
	
}
