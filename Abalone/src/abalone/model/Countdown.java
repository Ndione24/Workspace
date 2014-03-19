package abalone.model;

import java.text.DecimalFormat;

public class Countdown extends Thread {

	protected long startTime;
	protected long remainingTime;
	protected boolean active;

	public Countdown(int minutes, int secondes) {
		this.remainingTime = minutes * 60 * 1000 + secondes * 1000;
		this.active = false;
	}

	public void run() {
		while (true) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.active) {
				this.remainingTime -= System.currentTimeMillis() - startTime;
				this.startTime = System.currentTimeMillis();
			}
		}
	}

	public void cStop() {
		this.active = false;
	}

	public void cStart() {
		this.startTime = System.currentTimeMillis();
		this.active = true;
	}

	public void cReset(int minutes, int secondes) {
		this.remainingTime = minutes * 60 * 1000 + secondes * 1000;
	}

	public boolean cTimeout() {
		return (remainingTime < 0);
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("00");
		return ((remainingTime >= 0) 
				? df.format((remainingTime / 1000) / 60) + " : " + df.format((remainingTime / 1000) % 60) 
				: "00 : 00");
	}
}
