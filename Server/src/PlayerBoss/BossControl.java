package PlayerBoss;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Connection.ObjectDataLaser;
import Connection.Player;
import Server.Laser;
import Utility.Utility_Share;

public class BossControl extends JLabel implements Runnable,ActionListener{
	
	private int x,y;
	private Player player;
	private ArrayList<Laser> listLaser = new ArrayList<Laser>();
	private JPanel map;
	
	private ObjectDataLaser dataLaser;
	private Timer timer;
	
	
	
	public BossControl(int x,int y,JPanel map) {
		this.x  = x;
		this.y = y;
		this.map = map;
		
		timer = new Timer(1, this);
		timer.start();
		
		dataLaser = new ObjectDataLaser(0,false, x, y);
		
		player = new Player("Boss Player", x, y,dataLaser);
		player.setBlood(355);
		
		setLayout(null);
		setBounds(x, y, 350, 264);
		setIcon(new ImageIcon(Utility_Share.imgBoss));
		
	}
	
	public void updateToMap() {
		setLocation(x, y);
	}

	private void setLaserGun() {
		this.listLaser.clear();
		
		this.listLaser.add(new Laser(0,x+10, y+(264/2)));
		player.getDataLaser().setKey(0);
		player.getDataLaser().setX(listLaser.get(0).getX());
		player.getDataLaser().setY(listLaser.get(0).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(1,x+40, y+(264/2)));
		player.getDataLaser().setKey(1);
		player.getDataLaser().setX(listLaser.get(1).getX());
		player.getDataLaser().setY(listLaser.get(1).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(2,x+80, y+(264/2)));
		player.getDataLaser().setKey(2);
		player.getDataLaser().setX(listLaser.get(2).getX());
		player.getDataLaser().setY(listLaser.get(2).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(3,x+120, y+(264/2)));
		player.getDataLaser().setKey(3);
		player.getDataLaser().setX(listLaser.get(3).getX());
		player.getDataLaser().setY(listLaser.get(3).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(4,x+160, y+(264/2)));
		player.getDataLaser().setKey(4);
		player.getDataLaser().setX(listLaser.get(4).getX());
		player.getDataLaser().setY(listLaser.get(4).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(5,x+200, y+(264/2)));
		player.getDataLaser().setKey(5);
		player.getDataLaser().setX(listLaser.get(5).getX());
		player.getDataLaser().setY(listLaser.get(5).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(6,x+240, y+(264/2)));
		player.getDataLaser().setKey(6);
		player.getDataLaser().setX(listLaser.get(6).getX());
		player.getDataLaser().setY(listLaser.get(6).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(7,x+280, y+(264/2)));
		player.getDataLaser().setKey(7);
		player.getDataLaser().setX(listLaser.get(7).getX());
		player.getDataLaser().setY(listLaser.get(7).getY());
		Utility_Share.program.server.broadcast(null, player);
		
		this.listLaser.add(new Laser(8,x+320, y+(264/2)));
		player.getDataLaser().setKey(8);
		player.getDataLaser().setX(listLaser.get(8).getX());
		player.getDataLaser().setY(listLaser.get(8).getY());
		Utility_Share.program.server.broadcast(null, player);
	}
	
	private void setLocationLaser(int x,int y) {
		int l=10,key=0;
		for (Laser laser : listLaser) {
			
			laser.setX(x+l);
			laser.setY(y+(264/2));
			laser.setKey(key);
			player.getDataLaser().setKey(key);
			player.getDataLaser().setX(laser.getX());
			player.getDataLaser().setY(laser.getY());
			
			Utility_Share.program.server.broadcast(null, player);
			l+=40;
			key++;
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
		int key = 0;
		for (Laser laser : listLaser) {
			laser.setStatusShoot(false);
			player.getDataLaser().setStatusShoot(laser.isStatusShoot());
			player.getDataLaser().setKey(key);
			Utility_Share.program.server.broadcast(null, player);
			map.remove(laser);
			//listLaser.remove(laser);
			key++;
		}
		listLaser.clear();
	}
	
	private void createLaser() {
		setLaserGun();
		int key =0;
		for (Laser laser : listLaser) {
			map.add(laser);
			
			laser.setStatusShoot(true);
			player.getDataLaser().setStatusShoot(laser.isStatusShoot());
			player.getDataLaser().setKey(key);
			Utility_Share.program.server.broadcast(null, player);
			key++;
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
