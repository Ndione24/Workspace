package abalone.model;

import java.awt.Point;
import java.util.ArrayList;

public class Action {

	/** Les billes selectionnees */
	private ArrayList<Point> balls;
	/** La direction du coups */
	private byte direction;
	/** Le joueur qui a joue le coups */
	private Player player;

	public Action() {
		balls = new ArrayList<Point>();
	}
	
	/**
	 * Construction d'une action
	 * @param balls		billes selectionnees
	 * @param player	joueur qui joue le coup
	 * @param direction	direction du coup
	 */
	public Action(ArrayList<Ball> balls, Player player, byte direction) {
		this.balls = new ArrayList<Point>();
		this.player = player;
		this.direction = direction;
		for (Ball ball : balls) {
			this.balls.add(new Point(ball.getLine(), ball.getColumn()));
		}
	}
	
	/** Renvoie les billes jouées dans le coup */
	public ArrayList<Point> getBalls() {
		return balls;
	}
	
	/** Renvoie la direction du coup */
	public int getDirection() {
		return direction;
	}
	
	/** Renvoie le joueur du coup */
	public Player getPlayer() {
		return player;
	}
	
	/** Renvoie le nombre de billes joue dans l'action */
	public int getNbBalls() {
		return this.balls.size();
	}
	
	/** Compare deux actions */
	public boolean equals(Action coup) {
		return (this.direction == coup.getDirection() && this.balls == coup.getBalls());
	}
	
	/** Affiche la position et la direction initial des billes */
	public String toString() {
		String str = new String();
		for (Point point : this.balls) {
			str += point.getX() + ',' + point.getY() + "  ";
		}
		return str += ": " + this.direction;
	}
}
