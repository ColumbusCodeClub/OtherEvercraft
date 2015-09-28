package battle;

import character.Die;
import character.EvercraftCharacter;

public class EvercraftBattle {

	private static Die die;
	private int dieRoll;
	private EvercraftCharacter aggressor;
	
	private static int HIT = 1;
	private static int CRITICAL_HIT = 2;
	
	public EvercraftBattle(Die die) {
		this.die = die;
	}

	public void battle(EvercraftCharacter aggressor, EvercraftCharacter victim) {
		dieRoll = die.roll();
		this.aggressor = aggressor;
		if (!shouldTakeHit(victim)) return;
		if (isCriticalRoll()){
			victim.takeHit(Math.max(CRITICAL_HIT + 2*addStrengthModifier(), 1));
		}else{
			victim.takeHit(Math.max(HIT + addStrengthModifier(), 1));
		}
	}
	
	private boolean shouldTakeHit(EvercraftCharacter victim) {
		return (dieRoll + addRollValueOfOneForEach2LevelsOfCharacter() + addStrengthModifier()) >= victim.getArmorClass();
	}
	
	private int addRollValueOfOneForEach2LevelsOfCharacter() {
		return aggressor.characterLevelValue() / 2;
	}
	
	private int addStrengthModifier() {
		return -5 + (int)Math.floor(aggressor.getAbilityScore("strength")/2);
	}
	
	private boolean isCriticalRoll() {
		return dieRoll == 20;
	}

}
