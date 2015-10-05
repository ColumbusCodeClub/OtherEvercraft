package character;

public class Ability {

	private final int abilityScore;

	public Ability(int abilityScore) {
		this.abilityScore = abilityScore;
	}

	public Modifier getModifier() {
		return new Modifier(-5 + abilityScore / 2);
	}

}
