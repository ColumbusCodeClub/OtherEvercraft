package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import character.Alignment;
import character.Die;
import character.EvercraftCharacter;

public class EvercraftCharacterTest {
	
	private static final String ABILITY_NAME = "abilityName";

	private static final int DEFAULT_ABILITY_SCORE = 10;

	private static final int ABILITY_SCORE = 5;
	
	private static final String abilityName = ABILITY_NAME;

	@Mock
	Alignment alignment;
	
	@Mock
	Die die;
	
	@Mock
	EvercraftCharacter opponent;
	
	@Mock
	Level level;

	@Mock
	private Abilities abilities;
	
	@InjectMocks
	EvercraftCharacter underTest;

	@Before
	public void setup() {
		initMocks(this);
		underTest = new EvercraftCharacter(level);
	}
	
	@Test
	public void shouldSetCharacterName() {
		underTest.setName("test");
		assertThat(underTest.getName(), is("test"));
	}
	
	@Test
	public void shouldSetCharacterAlignment() {
		underTest.setAlignment(alignment);
		assertThat(underTest.getAlignment(), is(alignment));
	}
	
	@Test
	public void shouldHaveClassArmorDefaultTo10() {
		assertThat(underTest.getArmorClass(), is(10));
	}
	
	@Test
	public void shouldHaveClassArmorHitPointsDefaultTo5()
	{
		when(level.getLevel()).thenReturn(1);
		assertThat(underTest.getHitPoints(), is(5));
	}
	
	@Test
	public void shouldDecreaseHitPointsByOneWhenHit() {
		when(level.getLevel()).thenReturn(1);
		underTest.takeHit(1);
		assertThat(underTest.getHitPoints(), is(4));
	}
	
	@Test
	public void shouldSetAbilityScore() {
		underTest.setAbilityScore(ABILITY_NAME, ABILITY_SCORE);
		verify(abilities, times(1)).setAbilityScore(ABILITY_NAME, ABILITY_SCORE);
	}
	
	@Test
	public void shouldGetAbilityScore() {
		when(abilities.getAbilityScore(ABILITY_NAME)).thenReturn(ABILITY_SCORE);
		assertThat(underTest.getAbilityScore(ABILITY_NAME), is(ABILITY_SCORE));
	}
	
	@Test
	public void anIncreaseInLevelShouldIncreaseHitPointsByOne() {
		when(level.getLevel()).thenReturn(2);
		
		assertThat(underTest.getHitPoints(), is(6));
	}
	
	@Test
	public void anIncreaseIn2LevelsShouldIncreaseHitPointsByTwo(){
		when(level.getLevel()).thenReturn(3);
		assertThat(underTest.getHitPoints(), is(7));
	}
	
	@Test
	public void levelShouldDefaultToOne() {
		underTest = new EvercraftCharacter();
		
		assertThat(underTest.characterLevelValue(), is(1));
	}
	
}
