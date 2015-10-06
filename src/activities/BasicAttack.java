package activities;

import character.Modifier;

public class BasicAttack {

	private int dieRoll;

	public AttackOutcome apply(Modifier... modifier) {
		AttackOutcome attackOutcome = new AttackOutcome(0);
		attackOutcome.setAssociatedDieRoll(dieRoll);
		return attackOutcome;
	}

	public void setAssociatedDieRoll(int dieRoll) {
		this.dieRoll = dieRoll;
	}

}
