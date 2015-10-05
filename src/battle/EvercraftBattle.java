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
			victim.takeHit(CRITICAL_HIT);
		}else{
			victim.takeHit(HIT);
		}
	}
	
	private boolean shouldTakeHit(EvercraftCharacter victim) {
		return (dieRoll + addRollValueOfOneForEach2LevelsOfCharacter()) >= victim.getArmorClass();
	}
	
	private int addRollValueOfOneForEach2LevelsOfCharacter() {
		return aggressor.levelRollAdjustment();
	}
	
	private boolean isCriticalRoll() {
		return dieRoll == 20;
	}

}
