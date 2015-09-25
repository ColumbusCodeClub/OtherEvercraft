package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class AbilitiesTest {

	private static final int DEFAULT_ABILITY_SCORE = 10;
	private static final int ABILITY_SCORE = 5;
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
	public void shouldSetAbilityScore() {
		underTest.setAbilityScore(ABILITY_NAME, ABILITY_SCORE);
		assertThat(underTest.getAbilityScore(ABILITY_NAME), is(ABILITY_SCORE));
	}
	
	@Test
	public void shouldReturn10IfAbilityScoreIsUndefined() {
		assertThat(underTest.getAbilityScore(UNDEFINED_ABILITY_NAME), is(DEFAULT_ABILITY_SCORE));
	}
	
	@Test
	public void shouldNotAllowAbilityScoreUnder1() {
		underTest.setAbilityScore(ABILITY_NAME, LOW_ABILITY_SCORE);
		assertThat(underTest.getAbilityScore(ABILITY_NAME), is(MIN_ABILITY_SCORE));
	}
	
	@Test
	public void shouldNotAllowAbilityScoreOver20() {
		underTest.setAbilityScore(ABILITY_NAME, HIGH_ABILITY_SCORE);
		assertThat(underTest.getAbilityScore(ABILITY_NAME), is(MAX_ABILITY_SCORE));
	}
	
}
