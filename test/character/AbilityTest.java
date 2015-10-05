package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;


public class AbilityTest {

	private static final int MIN_ABILITY_SCORE = 1;
	private static final int MAX_ABILITY_SCORE = 20;
	private Ability underTest;
	private int expectedModifier = -5;

	@Test
	public void shouldProperlyModifyAllAbilityScoreFromMinAbilityScoreToMaxAbilityScore() {
		for (int abilityScore = MIN_ABILITY_SCORE; abilityScore <= MAX_ABILITY_SCORE; abilityScore++) {
			setExpectedModifier(abilityScore);
			underTest = new Ability(abilityScore);
			assertThat(underTest.getModifier(), is(modifierWithValue(expectedModifier)));
		}
	}

	private void setExpectedModifier(int abilityScore) {
		if (abilityScore % 2 == 0) {
			expectedModifier  = expectedModifier + 1;
		}
	}
	
	private Matcher<Modifier> modifierWithValue(final int modifierValue) {
		return new TypeSafeMatcher<Modifier>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("outcome with value of ");
				description.appendValue(modifierValue);
			}
			
			@Override
			protected void describeMismatchSafely(final Modifier item, final Description description) {
				description.appendText(" got outcome with value of ");
				description.appendValue(item.getValue());
			}

			@Override
			protected boolean matchesSafely(Modifier item) {
				return item.getValue() == modifierValue;
			}
		};
	}

}
