package CodeRoots.simpleGame;

public class NewLoop {
	public void gLoop() {
		while (running) {
			// Do the things you want the gLoop to do below here

			// System.out.println("runTime: " + timer());

			// And above here.
			drwGm(g);

			ticks++;

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

			// Limits the ticks per second

			// if nextTick is later then timer then sleep till next tick
			// System.out.println("nextTick: " + nextTick);
			// System.out.println("timer: " + timer());
			if (nextTick > timer()) {
				sleepTime = (int) (nextTick - timer());
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
				}

				nextTick += mpt;
			}

			// if next tick is past the current time then don't sleep and add
			// time so it runs a tick after adding.
			if (nextTick < timer()) {
				nextTick += mpt;
			}

			// if the game is more than 2 seconds behind it updates the ticks.
			if (nextTick + 2000 < timer()) {
				nextTick = timer() + mpt;
			}
		}
	}
}
