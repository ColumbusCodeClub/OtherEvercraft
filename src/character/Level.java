package character;

public class Level {

	private int xp;
	private int level;

	public Level() {
		xp = 0;
		level = 1;
	}
	
	public int getLevel() {
		return level;
	}

	public void increaseXp(int xPincrease) {
		xp += xPincrease;
		updateLevel();
	}

	private void updateLevel() {
		level = (xp / 1000) + 1;
	}

}
