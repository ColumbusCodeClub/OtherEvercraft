package character;


public class EvercraftCharacter {

	private static final int INITIAL_LEVEL_OFFSET = 1;
	private String name;
	private Alignment alignment;
	private Integer armorClass = 10;
	private Integer hitPoints = 5;
	private Integer strength = 10;
	private Level level;
	
	public EvercraftCharacter(Level level) {
		this.level = level;
	}

	public EvercraftCharacter() {
		this(new Level());
	}

	public int characterLevelValue() {
		return level.getLevel();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public String getName() {
		return name;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public Integer getArmorClass() {
		return armorClass ;
	}

	public Integer getHitPoints() {
		return hitPoints + (level.getLevel() - INITIAL_LEVEL_OFFSET);
	}

	public boolean isAttackHit(Die die, EvercraftCharacter opponent) {
		return die.roll() >= opponent.getArmorClass();
	}

	public void takeHit(int i) {
		hitPoints--;
	}

	public void setStrength(int i) {
		if (i < 1) i = 1;
		if (i > 20) i = 20;
		this.strength = i;
	}
	
	public Integer getStrength() {
		return strength;
	}

}
