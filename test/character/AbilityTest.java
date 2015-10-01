package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;


public class AbilityTest {

	private static final int MIN_ABILITY_SCORE = 1;
	private static final int MAX_ABILITY_SCORE = 20;
	private Ability underTest;
	private int expectedModifier = -5;

	@Test
	public void shouldModifierAbilityScoreToProduceModifier() {
		underTest = new Ability(1);
		assertThat(underTest.getModifier(), is(-5));
	}
	
	@Test
	public void shouldProperlyModifyAllAbilityScoreFromMinAbilityScoreToMaxAbilityScore() {
		for (int abilityScore = MIN_ABILITY_SCORE; abilityScore <= MAX_ABILITY_SCORE; abilityScore++) {
			setExpectedModifier(abilityScore);
			underTest = new Ability(abilityScore);
			assertThat(underTest.getModifier(), is(expectedModifier));
		}
	}

	private void setExpectedModifier(int abilityScore) {
		if (abilityScore % 2 == 0) {
			expectedModifier  = expectedModifier + 1;
		}
	}

}
