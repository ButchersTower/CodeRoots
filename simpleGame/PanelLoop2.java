package CodeRoots.simpleGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PanelLoop2 extends JPanel implements Runnable {

	// extends JPanel implements Runnable

	private int width = 160;
	private int height = 200;

	private Image[] imageAr;

	private Thread thread;
	private Image image;
	private Graphics g;

	// Vars for gLoop Below
	private int tps = 10;
	private int mpt = 1000 / tps;
	private int sleepTime = 0;
	private long lastSec = 0;
	private int ticks = 0;
	private long startTime;
	private long nextTick = 0;
	private boolean running = false;

	// Vars for gLoop Above

	public PanelLoop2() {
		super();

		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void run() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		this.setSize(new Dimension(width, height));

		startTime = System.currentTimeMillis();
		gStart();
	}

	/**
	 * Methods go below here.
	 * 
	 */

	public void gStart() {
		imageInit();

		running = true;
		gLoop();
	}

	public void gLoop() {
		while (running) {
			ticks++;

			nextTick = timer() + mpt;

			// Runs once a second and keeps track of ticks;
			// 1000 ms since last output
			if (timer() - lastSec > 1000) {
				if (ticks < tps - 1 || ticks > tps + 1) {
					if (timer() - startTime < 2000) {
						System.out.println("Ticks this second: " + ticks);
						System.out.println("timer(): " + timer());
						System.out.println("nextTick: " + nextTick);
					}
				}

				ticks = 0;
				lastSec = (System.currentTimeMillis() - startTime);
			}

			// Do the things you want the gLoop to do below here

			drwGm();

			// And above here.

			// Limits the ticks per second
			// if nextTick is later then timer then sleep till next tick
			if (nextTick > timer()) {
				sleepTime = (int) (nextTick - timer());
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	/**
	 * Methods go above here.
	 * 
	 */

	public long timer() {
		return System.currentTimeMillis() - startTime;

	}

	public void drwGm() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public void imageInit() {

		// imageAr = new Image[1];
		// ImageIcon ie = new ImageIcon(this.getClass().getResource(
		// "res/image.png"));
		// imageAr[0] = ie.getImage();

	}

}
