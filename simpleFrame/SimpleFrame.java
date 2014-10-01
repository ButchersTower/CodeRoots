package simpleFrame;

import javax.swing.JFrame;

public class SimpleFrame extends JFrame {
	// extends JFrame
	public SimpleFrame() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("SimpleFrame");
	}

	public static void main(String[] args) {
		new SimpleFrame();
	}
}
