package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import activities.DieRollOutcome;
import activities.Outcome;
import activities.RollDie;


public class ModifiedCharacterTest {

	private static final Ability SUBTRACTIVE_ABILITY_SCORE = new Ability(1);
	private static final Ability ADDITIVE_ABILITY_SCORE = new Ability(15);
	private static final int MINIMUM_MODIFIED_VALUE = 1;
	private static final int DEFAULT_CHARACTER_HIT_POINTS = 5;
	private static final int DEFAULT_CHARACTER_ARMOR_CLASS = 10;
	

	@Mock
	private EvercraftCharacter character;

	private ModifiedCharacter underTest;
	
	@Mock
	private RollDie rollDie;
	
	@Mock
	private Outcome dieRollOutcome;
	
	@Mock
	private Ability value;
	private Outcome outcome;
	
	@Mock
	private Modifier modifier;
	
	@Before
	public void setup() {
		initMocks(this);
		underTest = new ModifiedCharacter(character);
		when(character.getHitPoints()).thenReturn(DEFAULT_CHARACTER_HIT_POINTS);
		when(character.getArmorClass()).thenReturn(DEFAULT_CHARACTER_ARMOR_CLASS);
		when(rollDie.getTotalAttackRoll((Outcome)Mockito.any())).thenReturn(10);
	}
	
	@Test
	public void shouldReturnRollModifierBasedOnLevelAndStrengthModifier() {
		whenGettingStrength().thenReturn(value);
		when(value.getModifier()).thenReturn(modifier);
		when(character.getLevelModifier()).thenReturn(modifier);
		when(rollDie.apply(modifier, modifier)).thenReturn(new DieRollOutcome(13));
		assertThat(underTest.preform(rollDie, outcome), is(outcomeOf(13)));
	}
	
	@Test
	public void shouldReturnAttackPowerBasedOnStrengthModifier() {
		whenGettingStrength().thenReturn(ADDITIVE_ABILITY_SCORE);
		assertThat(underTest.getAttackPower(), is(3));
	}

	
	@Test
	public void shouldNeverReturnAttackPowerOfLessThanOne() {
		whenGettingStrength().thenReturn(SUBTRACTIVE_ABILITY_SCORE);
		assertThat(underTest.getAttackPower(), is(MINIMUM_MODIFIED_VALUE));
	}
	
	@Test
	public void shouldReturnCriticalHitAttackPowerBasedOnDoubledStrengthModifier() {
		whenGettingStrength().thenReturn(ADDITIVE_ABILITY_SCORE);
		assertThat(underTest.getCriticalHitAttackPower(), is(6));
	}
	
	@Test
	public void shouldNeverReturnCriticalHitAttackPowerOfLessThanOne() {
		whenGettingStrength().thenReturn(SUBTRACTIVE_ABILITY_SCORE);
		assertThat(underTest.getCriticalHitAttackPower(), is(MINIMUM_MODIFIED_VALUE));
	}
	
	@Test
	public void shouldReturnDefenseBasedOnDexterityModifier() {
		whenGettingDexterity().thenReturn(ADDITIVE_ABILITY_SCORE);
		assertThat(underTest.getDefense(), is(12));
	}

	@Test
	public void shouldReturnCurrentHitPointsBasedOnLevelAndConstitutionModifier() {
		whenGettingConstitution().thenReturn(ADDITIVE_ABILITY_SCORE);
		when(character.characterLevelValue()).thenReturn(2);
		assertThat(underTest.getCurrentHitPoints(), is(14));
	}

	
	@Test
	public void shouldNeverAllowCharacterBaseHitpointsToBeBelowOne() {
		whenGettingConstitution().thenReturn(SUBTRACTIVE_ABILITY_SCORE);
		when(character.characterLevelValue()).thenReturn(1);
		assertThat(underTest.getCurrentHitPoints(), is(MINIMUM_MODIFIED_VALUE));
	}
	
	@Test
	public void shouldTakeDamageAwayFromCurrentBaseHitPoints() {
		whenGettingConstitution().thenReturn(ADDITIVE_ABILITY_SCORE);
		when(character.characterLevelValue()).thenReturn(2);
		underTest.takeHit(4);
		assertThat(underTest.getCurrentHitPoints(), is(10));
	}
	

	@Test
	public void eachEvenCharacterLevelShouldIncreaseRollLevelByOne() {
		doReturn(modifier).when(character).getLevelModifier();
		doReturn(value).when(character).getStrength();
		doReturn(modifier).when(value).getModifier();
		when(rollDie.apply(modifier, modifier)).thenReturn(new DieRollOutcome(11));
		assertThat(underTest.preform(rollDie, outcome), is(outcomeOf(11)));
	}
	
	@Test
	public void eachOddCharacterLevelShouldNotIncreaseRollLevelByOne() {
		doReturn(modifier).when(character).getLevelModifier();
		doReturn(value).when(character).getStrength();
		doReturn(modifier).when(value).getModifier();
		when(rollDie.apply(modifier, modifier)).thenReturn(new DieRollOutcome(11));
		assertThat(underTest.preform(rollDie, outcome), is(outcomeOf(11)));
	}
	
	@Test
	public void shouldReturnDieRollOutcomePlusStrengthModifierAndLevelModifier() {
		when(value.getModifier()).thenReturn(modifier);
		when(character.getStrength()).thenReturn(value);
		when(character.getLevelModifier()).thenReturn(modifier);
		when(rollDie.apply(modifier, modifier)).thenReturn(new DieRollOutcome(13));
		assertThat(underTest.preform(rollDie, outcome), is(outcomeOf(13)));
	}
	
	private OngoingStubbing<Ability> whenGettingDexterity() {
		return when(character.getDexterity());
	}
	
	private OngoingStubbing<Ability> whenGettingStrength() {
		return when(character.getStrength());
	}

	private OngoingStubbing<Ability> whenGettingConstitution() {
		return when(character.getConstitution());
	}

	private Matcher<Outcome> outcomeOf(final int outcomeValue) {
		return new TypeSafeMatcher<Outcome>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("outcome with value of ");
				description.appendValue(outcomeValue);
			}
			
			@Override
			protected void describeMismatchSafely(final Outcome item, final Description description) {
				description.appendText(" got outcome with value of ");
				description.appendValue(item.getOutcome());
			}

			@Override
			protected boolean matchesSafely(Outcome item) {
				return item.getOutcome() == outcomeValue;
			}
		};
	}
	
}
