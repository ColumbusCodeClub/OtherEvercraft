package character;

import java.util.HashMap;
import java.util.Map;


public class EvercraftCharacter {

	private static final int INITIAL_LEVEL_OFFSET = 1;
	private String name;
	private Alignment alignment;
	private Integer armorClass = 10;
	private Integer hitPoints = 5;
	private Abilities abilities = new Abilities();
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
		return hitPoints;
	}
	
	public void setAbilityScore(String abilityName, int abilityScore) {
		abilities.setAbilityScore(abilityName, abilityScore);
	}

	public Ability getAbilityScore(String abilityName) {
		return abilities.getAbilityScore(abilityName);
	}

	Ability getStrength() {
		return getAbilityScore("strength");
	}
	
}
