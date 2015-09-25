package character;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Abilities {

	private Map<String, Integer> abilityScores = new HashMap<String, Integer>();
	
	public void setAbilityScore(String abilityName, int abilityScore) {
		abilityScores.put(abilityName, Math.min(Math.max(abilityScore, 1), 20));
	}

	public int getAbilityScore(String abilityName) {
		return abilityScores.containsKey(abilityName) ? abilityScores.get(abilityName) : 10;
	}
		
}
