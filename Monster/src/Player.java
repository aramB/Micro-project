import com.googlecode.lanterna.terminal.Terminal;

public class Player {
	private int posX;
	private int posY;
	private int nextPosX;
	private int nextPosY;
	private char symbol;

	public Player(final int posX, final int posY, final char symbol) {
		this.posX = posX;
		this.posY = posY;
		this.symbol = symbol;
	}

	public void drawPlayer(Terminal terminal) {
		terminal.moveCursor(getPosX(), getPosY());
		terminal.applyForegroundColor(Terminal.Color.WHITE);
		terminal.putCharacter(getSymbol());
		terminal.applyForegroundColor(Main.randomGenerator(255), Main.randomGenerator(255), Main.randomGenerator(255));
	}

	public void movePlayer() {
		setPosX(getNextPosX());
		setPosY(getNextPosY());
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

	public int getNextPosX() {
		return nextPosX;
	}

	public void setNextPosX(final int nextPosX) {
		this.nextPosX = nextPosX;
	}

	public int getNextPosY() {
		return nextPosY;
	}

	public void setNextPosY(final int nextPosY) {
		this.nextPosY = nextPosY;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(final char symbol) {
		this.symbol = symbol;
	}

	//endregion
}
