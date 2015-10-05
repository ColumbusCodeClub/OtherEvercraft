package battle;

import activities.Outcome;
import activities.RollDie;
import character.EvercraftCharacter;
import character.ModifiedCharacter;

public class EvercraftBattle {

	private static RollDie rollDie;
	public ModifiedCharacter aggressor;
	private ModifiedCharacter victim;
	
	private static int HIT = 1;
	private static int CRITICAL_HIT = 2;
	
	private Outcome initialOutcome;
	
	public EvercraftBattle(RollDie rollDie, Outcome initialOutcome) {
		this.rollDie = rollDie;
		this.initialOutcome = initialOutcome;
	}

	public void battle(ModifiedCharacter aggressor, ModifiedCharacter victim) {
		this.aggressor = aggressor;
		this.victim = victim;
		if (aggressor.preform(rollDie, initialOutcome).isFailure()) return;
		if (isCriticalRoll()){
			victim.takeHit(aggressor.getCriticalHitAttackPower());
		}else{
			victim.takeHit(aggressor.getAttackPower());
		}
	}
	
	private boolean isCriticalRoll() {
		return false;
	}
	
}
