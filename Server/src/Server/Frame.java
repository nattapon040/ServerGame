package Server;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultCaret;

import Connection.ServerProgram;
import PlayerBoss.ServerMap;
import Utility.Utility_Share;

import javax.swing.JScrollPane;

public class Frame extends javax.swing.JFrame{
	
	public JTextArea textArea;
	public JScrollPane scrollPane;
	
	private JButton btn_OpenServer,btnCreateBoss;
	
	public Frame() {
		
		Utility_Share.program = new ServerProgram();
		
		setResizable(false);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {e.printStackTrace();}
		
		getContentPane().setLayout(null);
		setTitle("Server");
		setSize(new Dimension(500, 350));
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if(false==btn_OpenServer.isEnabled()) {
						Utility_Share.program.server.stop();
					}else {
						System.exit(0);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 484, 311);
		getContentPane().add(panel);
		
		textArea = new JTextArea();
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setDragEnabled(true);
		textArea.setDoubleBuffered(true);
		textArea.setEditable(false);
		textArea.setBounds(20, 40, 442, 218);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setIgnoreRepaint(true);
		scrollPane.setFocusTraversalPolicyProvider(true);
		scrollPane.setFocusCycleRoot(true);
		scrollPane.setDoubleBuffered(true);
		scrollPane.setFocusable(true);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(20, 40, 442, 218);
		
		DefaultCaret crList = (DefaultCaret) textArea.getCaret();
        crList.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
		getContentPane().add(scrollPane);
		
		JLabel lblStatusClientOnserver = new JLabel("Status OnServer Client");
		lblStatusClientOnserver.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblStatusClientOnserver.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusClientOnserver.setBounds(10, 7, 464, 23);
		panel.add(lblStatusClientOnserver);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 264, 464, 36);
		panel.add(panel_1);
		
		btn_OpenServer = new JButton("Open Server");
		btn_OpenServer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btn_OpenServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							btn_OpenServer.setEnabled(false);
							btnCreateBoss.setEnabled(true);
							Utility_Share.program.start();
						} catch (IOException e1) {e1.printStackTrace();}
					}
				});
				
				thread.start();
			}
		});
		btn_OpenServer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(btn_OpenServer);
		

		JButton btnExitProgram = new JButton("Exit Program");
		btnExitProgram.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExitProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Utility_Share.sControlCloseServer == false) {
						System.exit(0);
						return;
					}
					Utility_Share.sControlBroadcast = false;
					Utility_Share.program.getServer().stop();
					System.exit(0);
				} catch (IOException e1) {
					System.exit(0);
					
				}
			
			}
		});
		
		btnCreateBoss = new JButton("Open Maps");
		btnCreateBoss.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreateBoss.setEnabled(true);
		btnCreateBoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerMap map = new ServerMap();
				map.setVisible(true);
			}
		});
		panel_1.add(btnCreateBoss);
		btnCreateBoss.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExitProgram.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(btnExitProgram);
		
		Utility_Share.program.setServerFrame(this);
		
	}
}
