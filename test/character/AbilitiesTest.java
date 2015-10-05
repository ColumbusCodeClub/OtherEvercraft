package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;


public class AbilitiesTest {

	private static final int DEFAULT_ABILITY_SCORE = 10;
	private static final int ABILITY_SCORE = 15;
	private static final int LOW_ABILITY_SCORE = -1000;
	private static final int MIN_ABILITY_SCORE = 1;
	private static final int HIGH_ABILITY_SCORE = 1000;
	private static final int MAX_ABILITY_SCORE = 20;
	private static final String UNDEFINED_ABILITY_NAME = "undefinedAbilityName";
	private static final String ABILITY_NAME = "abilityName";
	
	Abilities underTest;
	
	@Before
	public void setup() {
		underTest = new Abilities();
	}
	
	@Test
	public void shouldSetAbility() {
		underTest.setAbilityScore(ABILITY_NAME, ABILITY_SCORE);
		assertThat(underTest.getAbilityScore(ABILITY_NAME), is(abilityWithModifierValue(2)));
	}
	
	@Test
	public void shouldReturnDefaultAbilityIfAccessingUndefinedAbility() {
		assertThat(underTest.getAbilityScore(UNDEFINED_ABILITY_NAME), is(abilityWithModifierValue(0)));
	}
	
	@Test
	public void shouldReturnMinAbilityForValuesUnder1() {
		underTest.setAbilityScore(ABILITY_NAME, LOW_ABILITY_SCORE);
		assertThat(underTest.getAbilityScore(ABILITY_NAME), is(abilityWithModifierValue(-5)));
	}
	
	@Test
	public void shouldReturnMaxAbilityForValuesOver20() {
		underTest.setAbilityScore(ABILITY_NAME, HIGH_ABILITY_SCORE);
		assertThat(underTest.getAbilityScore(ABILITY_NAME), is(abilityWithModifierValue(5)));
	}
	
	private Matcher<Ability> abilityWithModifierValue(final int modifier) {
		return new TypeSafeMatcher<Ability>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("Expected ability with modifier ");
				description.appendValue(modifier);
			}
			
			@Override
		     public void describeMismatchSafely(final Ability item, final Description description) {
				description.appendText(" was ability with modifier ");
				description.appendValue(item.getModifier().getValue());
			}

			@Override
			protected boolean matchesSafely(Ability item) {
				return item.getModifier().getValue() == modifier;
			}
		};
	}
	
}
