package character;

public class Fighter extends EvercraftCharacter {

	private static final int FIGHTER_HIT_POINT_LEVEL_INCREASE = 10;
	private static final int INITIAL_LEVEL_OFFSET = 1;
	private Level level;

	public Fighter(Level level) {
		this.level = level;
	}

	@Override
	public Integer getHitPoints() {
		return super.getHitPoints() + ((level.getLevel() - INITIAL_LEVEL_OFFSET) * FIGHTER_HIT_POINT_LEVEL_INCREASE);
	}
	
	@Override
	public int levelRollAdjustment() {
		return this.characterLevelValue();
	}
	
}
