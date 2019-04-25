package Connection;

import java.io.Serializable;


public class Player  implements Serializable {
	
	private int x,y;
	private static final long serialVersionUID = 10004;
	private int speedX=0,speedY=0;
	private String Key_User;
	private String namePlyer;
	private int blood ;
	
	public Player(String key,int x, int y) {
		this.Key_User = key;
		this.x = x;
		this.y = y;
	
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

	public String getKey_User() {
		return Key_User;
	}

	public void setKey_User(String key_User) {
		Key_User = key_User;
	}

	public String getNamePlyer() {
		return namePlyer;
	}

	public void setNamePlyer(String namePlyer) {
		this.namePlyer = namePlyer;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}
}
