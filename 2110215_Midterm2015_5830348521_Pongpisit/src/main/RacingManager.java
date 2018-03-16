package main;

import java.util.*;

import character.JumperChocobo;
import character.Pikachu;
import character.RunnerChocobo;
import simInterface.ICharacter;
import simInterface.IObstructable;

public class RacingManager {
	
	public static final double GOAL = 100.0;
	public static ICharacter characters[];
	public static boolean hasWinner = false;

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to CHOCOBO RACING.\nThe goal at distance 100 m.\n");
		System.out.println("Press 'any key' to START.");
		kb.nextLine();
		kb.close();

		System.out.printf("Initialized Characters\n");
		initializeCharacter();
		
		int roundCount = 1;
		String winnerName = null;
		
		while(true) {
			System.out.println(roundCount);
			sortCharacter();
			decreaseObstructedDuration();
			randomObstructedCharacters();
			for (ICharacter c : characters) {
				c.run();
				printCharacterDistance(c.getClass().getName().replace("character.", ""), c.getDistance());
				if (checkWinner(c)) {
					winnerName = c.getClass().getName().replace("character.", "");
				}
			}
			if (hasWinner)break;
			roundCount += 1;
		}
		printTheWinner(winnerName);
	}
	
	public static double getGoal() {
		return GOAL;
	}
	
	public static ICharacter[] getCharacters() {
		return characters;
	}

	public static boolean hasWinner() {
		return hasWinner;
	}
	
	
	
	public static void initializeCharacter() {
		characters = new ICharacter[3];
		
		ICharacter rc = new RunnerChocobo();
		ICharacter jc = new JumperChocobo();
		ICharacter pk = new Pikachu();
		
		rc.start();
		jc.start();
		pk.start();
		
		characters[0] = rc;
		characters[1] = jc;
		characters[2] = pk;
	}
	
	public static void sortCharacter() {
		Arrays.sort(characters);
	}
	
	public static boolean checkWinner(ICharacter character) {
		if (character.getDistance() >= GOAL) {
			hasWinner = true;
			return true;
		}else {
			return false;
		}
	}
	
	public static void randomObstructedCharacters() {
		int rnd = Provide.Library.randomChance();
		if (rnd >=1 && rnd <= 20) {
			for (ICharacter c : characters) {
				if (c instanceof JumperChocobo) {
					((JumperChocobo) c).obstructed();
					return;
				}
			}
		}
		if (rnd >=21 && rnd <= 40) {
			for (ICharacter c : characters) {
				if (c instanceof Pikachu) {
					((Pikachu) c).obstructed();
					return;
				}
			}
		}
		if (rnd >=41 && rnd <= 60) {
			for (ICharacter c : characters) {
				if (c instanceof JumperChocobo) {
					((JumperChocobo) c).obstructed();
				}
				if (c instanceof Pikachu) {
					((Pikachu) c).obstructed();
				}
			}
		}
		if (rnd >=61 && rnd <= 100) {
			return;
		}
	}
	
	public static void decreaseObstructedDuration() {
		for (ICharacter c : characters) {
			if (c instanceof IObstructable) {
				IObstructable obc = (IObstructable)(c);
				
				if (obc.getObstructedDuration() > 0) {
					obc.setObstructedDuration(obc.getObstructedDuration() -1);
				}
				if (obc.getObstructedDuration() == 0) {
					if (obc instanceof Pikachu) {
						((Pikachu)(obc)).setSpeed(4);
					}
					if (obc instanceof JumperChocobo) {
						((JumperChocobo)(obc)).setSpeed(7.5);
					}
				}		
			}
		}
	}
	
	public static void printRound(int round) {
		System.out.printf("\nRound %d\n", round);
	}

	public static void printCharacterDistance(String characterName, double characterDistance) {
		System.out.printf("- %s distance : %.2f\n", characterName, characterDistance);
	}

	public static void printJumperChocoboPreparingToJump(double jumperChocoboDistance) {
		System.out.printf("- JumperChocobo distance : %.2f and preparing to jump\n", jumperChocoboDistance);
	}

	public static void printJumperChocoboObstructed() {
		System.out.printf("+ JumperChocobo has obstructed > speed down for 2 seconds\n");
	}

	public static void printPikachuObstructed() {
		System.out.printf("+ Pikachu has obstructed > stop running for 1 second\n");
	}

	public static void printTheWinner(String characterName) {
		System.out.printf("\n%s is the WINNER!", characterName);
	}
}
