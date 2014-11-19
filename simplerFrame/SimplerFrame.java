package CodeRoots.simplerFrame;

import javax.swing.JFrame;

public class SimplerFrame {
	// extends JFrame
	public SimplerFrame() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("SimplerFrame");
	}

	public static void main(String[] args) {
		new SimplerFrame();
	}
}
