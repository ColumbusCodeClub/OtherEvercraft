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
		return character.characterLevelValue()/2 + getModifierForAbility("strength");
	}

	public int getAttackPower() {
		return Math.max(BASE_ATTACK_POWER + getModifierForAbility("strength"), MIN_MODIFIED_VALUE);
	}

	public int getCriticalHitAttackPower() {
		return Math.max(CRITICAL_HIT_BASE_ATTACK_POWER + 2*getModifierForAbility("strength"), MIN_MODIFIED_VALUE);
	}

	public int getDefense() {
		return character.getArmorClass() + getModifierForAbility("dexterity");
	}

	public void takeHit(int attackPower) {
		this.damageTaken += attackPower;
	}

	public int getCurrentHitPoints() {
		return baseCharacterHitPoints() - damageTaken;
	}
	
	private int getModifierForAbility(String abilityName) {
		return -5 + (int)Math.floor(character.getAbilityScore(abilityName) / 2);
	}
	
	private int baseCharacterHitPoints() {
		int baseCharacterHitPoints = character.getHitPoints() 
				+ (character.characterLevelValue()-1)*(5+getModifierForAbility("constitution")) 
				+ getModifierForAbility("constitution");
		return Math.max(baseCharacterHitPoints, MIN_MODIFIED_VALUE);
	}
	
}
