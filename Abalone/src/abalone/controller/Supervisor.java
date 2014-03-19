package abalone.controller;

/**
 * Le superviseur est une partie du controleur : Il s'agit d'un charge de
 * surveiller si tout se passe bien dans le programme et de mettre a jour
 * regulierement la fenetre
 */
public class Supervisor extends Thread {

	private GameController gController;
	private boolean isRunning;

	public Supervisor(GameController gController) {
		super();
		this.gController = gController;
		isRunning = true;
	}

	@Override
	public void run() {
		super.run();

		while (isRunning) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();	
			}
			//TODO

		}
	}

}
