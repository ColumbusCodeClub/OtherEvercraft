package activities;

import character.Die;
import character.Modifier;
import battle.EvercraftBattle;

public class RollDie {	
	private Die die = new Die();

	public DieRollOutcome apply(Modifier... modifiers) {
		int roll = die.roll();
		for (Modifier modifier : modifiers) {
			roll += modifier.getValue();
		}
		return new DieRollOutcome(roll);
	}

}
