package battle;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import character.Die;
import character.EvercraftCharacter;
import character.Level;


public class EvercraftBattleTest {

	@Mock
	Die die;
	
	@Mock
	Level level;
	
	@Spy
	EvercraftCharacter victim = new EvercraftCharacter(level);
	
	@Spy
	private EvercraftCharacter aggressor = new EvercraftCharacter(level);
	
	EvercraftBattle underTest;

	@Before
	public void setup() {
		initMocks(this);
		underTest = new EvercraftBattle(die);
		doReturn(1).when(aggressor).characterLevelValue();
		when(victim.getArmorClass()).thenReturn(10);
	}
	
	@Test
	public void shouldHitVictimIfRollPlusStrengthModifierIsGreaterThanVictimClassArmor() {
		when(die.roll()).thenReturn(9);
		when(aggressor.getAbilityScore("strength")).thenReturn(15);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldTakeHitEqualToOnePlusStrengthModifier() {
		when(die.roll()).thenReturn(9);
		when(aggressor.getAbilityScore("strength")).thenReturn(15);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(1 + getStrengthModifier(15));
	}
	
	private int getStrengthModifier(int strengthAbilityScore) {
		return -5 + (int)Math.floor(strengthAbilityScore)/2;
	}
	
	@Test
	public void shouldDoubleDamageAndAddDoulbeStrengthModifierWhenCriticalHit() {
		when(die.roll()).thenReturn(20);
		when(aggressor.getAbilityScore("strength")).thenReturn(15);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(2 + 2*getStrengthModifier(15));
	}
	
	@Test
	public void shouldNotHitVictimIfRollPlusStrengthModifierIsLessThanVictimClassArmor(){
		when(die.roll()).thenReturn(9);
		when(aggressor.getAbilityScore("strength")).thenReturn(10);
		underTest.battle(aggressor, victim);
		verify(victim, times(0)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldHitVictimIfRollPlusStrengthModifierIsEqaulToVictimClassArmor() {
		when(die.roll()).thenReturn(9);
		when(aggressor.getAbilityScore("strength")).thenReturn(13);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldNotHitWithLessThanOneDamageOnNonCriticalRoll() {
		when(die.roll()).thenReturn(19);
		when(aggressor.getAbilityScore("strength")).thenReturn(1);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(1);
	}
	
	@Test
	public void shouldNotHitWithLessThanOneDamageOnCriticalRoll() {
		when(die.roll()).thenReturn(20);
		when(aggressor.getAbilityScore("strength")).thenReturn(1);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(1);
	}
	
	@Test
	public void eachEvenCharacterLevelShouldIncreaseRollLevelByOne() {
		doReturn(2).when(aggressor).characterLevelValue();
		when(die.roll()).thenReturn(9);

		underTest.battle(aggressor, victim);
		
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void eachOddCharacterLevelShouldNotIncreaseRollLevelByOne() {
		doReturn(3).when(aggressor).characterLevelValue();
		when(die.roll()).thenReturn(8);
		
		underTest.battle(aggressor, victim);
		
		verify(victim, times(0)).takeHit(Mockito.anyInt());
	}
	
}
