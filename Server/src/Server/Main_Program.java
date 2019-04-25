package Server;


public class Main_Program {

	public static void main(String[] args) {
		
		Thread ServerFrame = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Frame frame = new Frame();
				frame.setVisible(true);
			}
		});
		ServerFrame.start();
	}

}
