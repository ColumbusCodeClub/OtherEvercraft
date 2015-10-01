package character;

public class ModifiedCharacter {

	private static final int CRITICAL_HIT_BASE_ATTACK_POWER = 2;
	private static final int MIN_MODIFIED_VALUE = 1;
	private static final int BASE_ATTACK_POWER = 1;
	private EvercraftCharacter character;
	private int damageTaken;

	public ModifiedCharacter(EvercraftCharacter character) {
		this.character = character;
		this.damageTaken = 0;
	}

	public int getRollModifier() {
		return character.characterLevelValue()/2 + character.getStrength().getModifier();
	}

	public int getAttackPower() {
		return Math.max(BASE_ATTACK_POWER + character.getStrength().getModifier(), MIN_MODIFIED_VALUE);
	}

	public int getCriticalHitAttackPower() {
		return Math.max(CRITICAL_HIT_BASE_ATTACK_POWER + 2*character.getStrength().getModifier(), MIN_MODIFIED_VALUE);
	}

	public int getDefense() {
		return character.getArmorClass() + character.getAbilityScore("dexterity").getModifier();
	}

	public void takeHit(int attackPower) {
		this.damageTaken += attackPower;
	}

	public int getCurrentHitPoints() {
		return baseCharacterHitPoints() - damageTaken;
	}
	
	private int baseCharacterHitPoints() {
		int baseCharacterHitPoints = character.getHitPoints() 
				+ (character.characterLevelValue()-1)*(5+character.getAbilityScore("constitution").getModifier()) 
				+ character.getAbilityScore("constitution").getModifier();
		return Math.max(baseCharacterHitPoints, MIN_MODIFIED_VALUE);
	}
	
}
