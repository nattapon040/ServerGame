package PlayerBoss;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Utility.Utility_Share;

public class ServerMap extends javax.swing.JFrame{

	public Maps maps;
	public ServerMap() {
		getContentPane().setLayout(null);
		setSize(Utility_Share.sSize);
		setTitle("WAR OF AIRSTRIKE(Server)");
		setResizable(false);
		maps = new Maps();
		getContentPane().add(maps);
		     
		setLocationRelativeTo(null);
	}
	
	public class Maps extends javax.swing.JPanel implements ActionListener{
		
		private Timer timer ;
		private BossControl bossControl;
		private JButton btnNewGame,btnStartGame,btnClose;
		public Maps() {
			
			try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {e.printStackTrace();}
			
			
			setSize(Utility_Share.sSize);
			setLayout(null);
			System.out.println(65645);
			setBackground(Color.black);
			this.timer = new  Timer(10, this);
			this.timer.start();
			
			bossControl = new BossControl(480, 20,this);
			add(bossControl);
			
			btnNewGame = new JButton("New Game");
			btnNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnStartGame.setEnabled(true);
				}
			});
			btnNewGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnNewGame.setBounds(10, 11, 89, 23);
			add(btnNewGame);
			
			btnStartGame = new JButton("Start Game");
			btnStartGame.setEnabled(false);
			btnStartGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnStartGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Utility_Share.sControlCloseServer == true) {
						Thread thread = new Thread(bossControl);
						thread.start();
					}else {
						JOptionPane.showMessageDialog(null, "Please Input PlayerName", "WARNING",  JOptionPane.WARNING_MESSAGE);
				          return;
					}
					
				}
			});
			btnStartGame.setBounds(109, 11, 89, 23);
			add(btnStartGame);
			
			btnClose = new JButton("Close");
			btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnClose.setBounds(208, 11, 89, 23);
			add(btnClose);
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2D = (Graphics2D)g;
			g2D.drawImage(Utility_Share.imgMaps, 0, 0, 1366, 768, 0, 0, 1382, 809, this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
			bossControl.updateToMap();
			
			for (Map.Entry<String, ClientPlayer > entry : Utility_Share.clientList.entrySet()) {
				String key = entry.getKey();
				ClientPlayer player = Utility_Share.clientList.get(key);
				if(player.isAddToFrame() == false) {
					add(player);
					player.setAddToFrame(true);
				}
			}
		}
		
		public Maps getInstance() {
			return getInstance();
		}
	}
}
