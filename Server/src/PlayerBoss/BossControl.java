package PlayerBoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Connection.Player;
import Server.Laser;
import Utility.Utility_Share;

public class BossControl extends JLabel implements Runnable,ActionListener{
	
	private int x,y;
	private Player player;
	private ArrayList<Laser> listLaser = new ArrayList<Laser>();
	private JPanel map;
	
	private Timer timer;
	
	public BossControl(int x,int y,JPanel map) {
		this.x  = x;
		this.y = y;
		this.map = map;
		
		timer = new Timer(1, this);
		timer.start();
		player = new Player("Boss Player", x, y);
		player.setBlood(355);
		
		setLayout(null);
		setBounds(x, y, 350, 264);
		setIcon(new ImageIcon(Utility_Share.imgBoss));
		
	}
	
	public void updateToMap() {
		setLocation(x, y);
	}

	private void setLaserGun() {
		this.listLaser.add(new Laser(x+10, y+(264/2)));
		this.listLaser.add(new Laser(x+40, y+(264/2)));
		this.listLaser.add(new Laser(x+80, y+(264/2)));
		this.listLaser.add(new Laser(x+120, y+(264/2)));
		this.listLaser.add(new Laser(x+160, y+(264/2)));
		this.listLaser.add(new Laser(x+200, y+(264/2)));
		
		this.listLaser.add(new Laser(x+240, y+(264/2)));
		this.listLaser.add(new Laser(x+280, y+(264/2)));
		this.listLaser.add(new Laser(x+320, y+(264/2)));
	}
	
	private void setLocationLaser(int x,int y) {
		int l=10;
		for (Laser laser : listLaser) {
			
			laser.setX(x+l);
			laser.setY(y+(264/2));
			
			l+=40;
		}
	}
	private void runBoss(String status,int distanc) {
		if(status.equals("r")) {
			for (; x <= distanc ; x+=5) {
				
				if(x>=1000) {
					deleteLaser();
					createLaser();
					runBoss("l", x- Utility_Share.sRand.nextInt(600)+200);
					deleteLaser();
				}
				
				setLocation(x, y);
				setLocationLaser(x, y);
				player.setX(x);
				player.setY(y);
				Utility_Share.program.server.broadcast(null, player);
				
				try {Thread.sleep(30);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			
		}else {
			
			for (; distanc <= x; x-=5) {
				
				if(x<=20) {
					deleteLaser();
					createLaser();
					runBoss("r", x + Utility_Share.sRand.nextInt(500)+100);
					deleteLaser();
				}
				setLocation(x, y);
				setLocationLaser(x, y);
				player.setX(x);
				player.setY(y);
				Utility_Share.program.server.broadcast(null, player);
				
				try {Thread.sleep(30);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
	
	public ArrayList<Laser> getList() {
		return listLaser;
	}
	
	private void deleteLaser() {
		for (Laser laser : listLaser) {
			laser.setStatusShoot(false);
			map.remove(laser);
			//listLaser.remove(laser);
		}
		listLaser.clear();
	}
	
	private void createLaser() {
		setLaserGun();
		
		for (Laser laser : listLaser) {
			map.add(laser);
			laser.setStatusShoot(true);
		}
	}
	@Override
	public void run() {
		int rand = 0;
		while(player.getBlood() > 0) {
			
			rand = Utility_Share.sRand.nextInt(2);
			
			if(rand == 1) {
				deleteLaser();
				createLaser();
				runBoss("r", x+Utility_Share.sRand.nextInt(200));
				deleteLaser();
				runBoss("r", x+Utility_Share.sRand.nextInt(200));
				createLaser();
			}else {
				deleteLaser();
				createLaser();
				runBoss("l",x- Utility_Share.sRand.nextInt(200));
				deleteLaser();
				runBoss("l", x-Utility_Share.sRand.nextInt(200));
				createLaser();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	
}
