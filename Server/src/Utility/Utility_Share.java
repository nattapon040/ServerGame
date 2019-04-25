package Utility;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.LinkedHashMap;
import java.util.Random;

import Connection.Player;
import Connection.ServerProgram;
import PlayerBoss.ClientPlayer;

public class Utility_Share {
	
	public static ServerProgram program ;
	
	
	public static boolean sControlBoss = false;
	
	public static boolean sControlCloseServer = false;
	public static Dimension sSize = new Dimension(1366, 768);
	public static Random sRand = new Random();
	
	public static Image imgMaps = Toolkit.getDefaultToolkit().createImage(Utility_Share.class.getResource("/image/mapServer.jpg"));
	public static Image imgBoss = Toolkit.getDefaultToolkit().createImage(Utility_Share.class.getResource("/image/Boss.png"));
	public static Image imgPlayer = Toolkit.getDefaultToolkit().createImage(Utility_Share.class.getResource("/image/player.png"));
	
	public static LinkedHashMap<String, ClientPlayer> clientList = new LinkedHashMap<String, ClientPlayer>();
	//public static LinkedHashMap<String, MultiPlayer_Control> MultiPlayer = new LinkedHashMap<String, MultiPlayer_Control>();
	
	//public static Image imgPlayer = Toolkit.getDefaultToolkit().createImage(Utility_Share.class.getResource("/Image/player.png"));
	
	public static Object Deposit_info ;
	
	public static Point[][] grid = new Point[1366][768];
	

		
	public static boolean sControlBroadcast = false;

}
