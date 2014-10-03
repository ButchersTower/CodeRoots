package CodeRoots.simpleGame;

import javax.swing.JFrame;

public class SimpleGame extends JFrame{
	// extends JFrame
		public SimpleGame() {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(new Panel());
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setTitle("SimpleGame");
		}

		public static void main(String[] args) {
			new SimpleGame();
		}
}
