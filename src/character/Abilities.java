package character;

import java.util.HashMap;
import java.util.Map;

public class Abilities {

	private static final int DEFAULT_ABILITY_SCORE = 10;
	private static final int MAX_ABILITY_SCORE = 20;
	private static final int MIN_ABILITY_SCORE = 1;
	private Map<String, Integer> abilityScores = new HashMap<String, Integer>();
	
	public void setAbilityScore(String abilityName, int abilityScore) {
		abilityScores.put(abilityName, Math.min(Math.max(abilityScore, MIN_ABILITY_SCORE), MAX_ABILITY_SCORE));
	}

	public Ability getAbilityScore(String abilityName) {
		int foo = abilityScores.containsKey(abilityName) ? abilityScores.get(abilityName) : DEFAULT_ABILITY_SCORE;
		return new Ability(foo);
	}
		
}