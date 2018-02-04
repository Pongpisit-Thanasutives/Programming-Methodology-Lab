package model;

import javafx.scene.paint.Color;
import utility.GameScreen;

public class Main {

	public static void main(String[] args) {
		Field f = new Field();
		Tank blueTank = new Tank(f, 1, 1, Entity.EAST, 3, 3, 0, 5, Color.BLUE);
		Tank redTank = new Tank(f, 3, 4, Entity.WEST, 3, 3, 1, 5, Color.RED);
		GameScreen.startGame(f);
	}

}
