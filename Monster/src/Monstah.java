import com.googlecode.lanterna.terminal.Terminal;

public class Monstah
{
	private int posX;
	private int posY;
	private char symbol;
	private int speedX;
	private int speedY;


	public Monstah(final int posX, final int posY, final char symbol, final int speedX, final int speedY) {
		this.posX = posX;
		this.posY = posY;
		this.symbol = symbol;
		this.speedX = speedX;
		this.speedY = speedY;

	}

	public void drawMonster(Terminal terminal) {
		if (getPosX() < 100 && getPosY() < 30 && getPosX() >= 0 && getPosY() >= 0) {
			terminal.moveCursor(getPosX(), getPosY());
			terminal.putCharacter(getSymbol());
		}
	}

	public void monsterKillMonster(Monstah[] arr) {
		int count = 0;
		Monstah[] killedMonster = new Monstah[3];
		for (int i = 0; i < arr.length; i++) {
			if (getPosX() == arr[i].getPosX() && getPosY() == arr[i].getPosY()){
				killedMonster[count] = arr[i];
				count++;
			}
		}
		if(count>=2) {
			for (Monstah elem : killedMonster) {
				if (elem != null) {
					elem.setRandomPos();
				}
			}
		}
	}

	private void setRandomPos() {
		int relocate = Main.randomGenerator(4)+1;
		switch (relocate) {
			case 1:
				setPosX(Main.randomGenerator(50) + 105);
				setPosY(Main.randomGenerator(70) - 30);
				break;
			case 2:
				setPosX(Main.randomGenerator(40) - 50);
				setPosY(Main.randomGenerator(70) - 30);
				break;
			case 3:
				setPosX(Main.randomGenerator(150)  - 200);
				setPosY(Main.randomGenerator(50) + 30);
				break;
			case 4:
				setPosX(Main.randomGenerator(150) - 200);
				setPosY(Main.randomGenerator(25) - 40);
				break;
		}
	}

	public void moveMonster(Player p) {
		if (getPosX() < p.getPosX()) {
			setPosX(getPosX() + getSpeedX());
		}
		else if (getPosX() > p.getPosX()) {
			setPosX(getPosX() - getSpeedX());
		}
		else {
			setPosX(getPosX());
		}

		if (getPosY() < p.getPosY()) {
			setPosY(getPosY() + getSpeedY());
		}
		else if (getPosY() > p.getPosY()) {
			setPosY(getPosY() - getSpeedY());
		}
		else {
			setPosY(getPosY());
		}
	}

	//region getters and setters
	public int getPosX() {
		return posX;
	}

	public void setPosX(final int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(final int posY) {
		this.posY = posY;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(final char symbol) {
		this.symbol = symbol;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(final int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(final int speedY) {
		this.speedY = speedY;
	}
	//endregion
}
