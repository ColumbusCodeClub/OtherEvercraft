package character;

public class Ability {

	private final int abilityScore;

	public Ability(int abilityScore) {
		this.abilityScore = abilityScore;
	}

	int getModifier() {
		return -5 + abilityScore / 2;
	}

}
