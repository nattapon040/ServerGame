package Connection;


import java.io.Serializable;

public class ObjectDataLaser implements Serializable{
	
	private static final long serialVersionUID = 1112;
	
	private boolean statusShoot;
	private final int width = 3;
	private int height;
	private int x,y;
	private int key;
	
	
	public ObjectDataLaser(int key,boolean statusShoot, int x, int y) {
		this.statusShoot = statusShoot;
		this.x = x;
		this.y = y;
		this.key = key;
	}
	
	
	public int getKey() {
		return key;
	}


	public void setKey(int key) {
		this.key = key;
	}


	public boolean isStatusShoot() {
		return statusShoot;
	}
	public void setStatusShoot(boolean statusShoot) {
		this.statusShoot = statusShoot;
	}
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
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
	
	
}
