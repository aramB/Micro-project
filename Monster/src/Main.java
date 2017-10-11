import java.util.Random;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import java.nio.charset.Charset;

public class Main
{
	public char mon = 'c';//(char) 0b01010101001010110011000101000110001101000011011101000101;
	private static final int RANGE_FROM_PLAYER = 5;
	static Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF32"));
	static Player playah = new Player(49, 14, '\u263A');
	static Monstah[] monsterArray = new Monstah[100];

	public static void main(String[] args) throws InterruptedException {
		int score = 0;
		Key key;
		terminal.setCursorVisible(false);
		terminal.enterPrivateMode();
		initMonster(monsterArray);
		printGameOver("******** Mad Code ********", 37, 5);
		draw();
		while (checkAlive()) {
			//Wait for a key to be pressed
			do {
				Thread.sleep(5);
				key = terminal.readInput();
			} while (key == null);

			switch(key.getKind())
			{
			 case ArrowDown:
			 	if (playah.getPosY() < 28) {
					playah.setNextPosY(playah.getPosY() + 2);
					playah.setNextPosX(playah.getPosX());
				}
			 	break;
			 case ArrowUp:
				 if (playah.getPosY() > 1) {
					 playah.setNextPosY(playah.getPosY() - 2);
					 playah.setNextPosX(playah.getPosX());
				 }
			 	break;
			 case ArrowLeft:
				 if (playah.getPosX() > 1) {
					 playah.setNextPosX(playah.getPosX() - 2);
					 playah.setNextPosY(playah.getPosY());
				 }
			 	break;
			 case ArrowRight:
			 	if (playah.getPosX() < 98) {
					playah.setNextPosX(playah.getPosX() + 2);
					playah.setNextPosY(playah.getPosY());
				}
			 	break;
			}
			System.out.println(key.getCharacter() + " " + key.getKind());
			move();
			terminal.clearScreen();
			check();
			draw();
			score++;
		}
		terminal.clearScreen();
		printGameOver("Game Over!", 44, 14);
		printGameOver("======================", 38, 15);
		printGameOver("Your score: " + score, 43, 16);
	}

	private static void initMonster(Monstah[] arr) {
		char mon = "\u1F47D".toCharArray()[0];
		arr[0] = new Monstah(playah.getPosX() + RANGE_FROM_PLAYER, playah.getPosY() + RANGE_FROM_PLAYER, mon, 1, 1);
		arr[1] = new Monstah(playah.getPosX() - RANGE_FROM_PLAYER, playah.getPosY() + RANGE_FROM_PLAYER, mon, 1, 1);
		arr[2] = new Monstah(playah.getPosX() + RANGE_FROM_PLAYER, playah.getPosY() - RANGE_FROM_PLAYER, mon, 1, 1);
		arr[3] = new Monstah(playah.getPosX() - RANGE_FROM_PLAYER, playah.getPosY() - RANGE_FROM_PLAYER, mon, 1, 1);
		arr[4] = new Monstah(50, 64, '#', 1, 2);
		arr[5] = new Monstah(50, -64, '#', 1, 2);
		arr[6] = new Monstah(150, 14, '#', 2,1);
		arr[7] = new Monstah(-150, 14, '#', 2,1);
		for (int i = 7; i < arr.length; i++) {
			int relocate = randomGenerator(4)+1;
			switch (relocate) {
				case 1:
					arr[i] = new Monstah(randomGenerator(50) + 105, randomGenerator(70) - 30, mon, 1,1);
					break;
				case 2:
					arr[i] = new Monstah(randomGenerator(40) - 50, randomGenerator(70) - 30, mon,1,1);
					break;
				case 3:
					arr[i] = new Monstah(randomGenerator(150)  - 200, randomGenerator(50) + 30, mon,1,1);
					break;
				case 4:
					arr[i] = new Monstah(randomGenerator(150) - 200, randomGenerator(25) - 40, mon,1,1);
					break;
			}
		}
	}

	public static int randomGenerator(int n) {
		Random rand = new Random();
		return rand.nextInt(n);
	}

	public static void draw() {
		playah.drawPlayer(terminal);
		for (Monstah elem : monsterArray) {
			elem.drawMonster(terminal);
		}
	}

	public static void move() {
		playah.movePlayer();
		for (Monstah elem : monsterArray) {
			elem.moveMonster(playah);
		}
	}

	public static boolean checkAlive() {
		for (Monstah elem : monsterArray) {
			if (playah.getPosX() == elem.getPosX() && playah.getPosY() == elem.getPosY()) {
				return false;
			}
		}
		return true;
	}



	public static void check() {
		for (Monstah elem : monsterArray) {
			elem.monsterKillMonster(monsterArray);
		}
	}

	public static void printGameOver(String s, int x, int y) {
		for (int i = 0; i < s.length(); i++) {
			terminal.moveCursor(x+i, y);
			terminal.applyForegroundColor(Terminal.Color.WHITE);
			terminal.putCharacter(s.charAt(i));
		}
	}

}
