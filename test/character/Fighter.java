package character;

public class Fighter extends EvercraftCharacter {

	private Level level;

	public Fighter(Level level) {
		this.level = level;
	}

	@Override
	public Integer getHitPoints() {
		return super.getHitPoints() + (level.getLevel());
	}
	
}
