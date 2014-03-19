package abalone.model;

import java.awt.Color;

public class Option {

	public String name1, name2;
	public boolean ia;
	public Color color1, color2;
	
	/** Default options */
	public Option() {
		this.name1 = "Player1";
		this.name2 = "Player2";
		this.color1 = Color.BLACK;
		this.color2 = Color.WHITE;
		this.ia = false;
	}
	
	public String getName1() {
		return name1;
	}
	
	public String getName2() {
		return name2;
	}
	
	public void setName1(String name1) {
		this.name1 = name1;
	}
	
	public void setName2(String name2) {
		this.name2 = name2;
	}
	
	public void setColor1(Color color1) {
		this.color1 = color1;
	}
	
	public void setColor2(Color color2) {
		this.color2 = color2;
	}
	
	public void setIA(boolean isActived) {
		this.ia = isActived;
	}
	
	public boolean isIA() {
		return this.ia;
	}
}
