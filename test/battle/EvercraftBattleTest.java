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
	}
	
	@Test
	public void shouldHitVictimIfRollIsGreaterThanVictimClassArmor() {
		doReturn(1).when(aggressor).characterLevelValue();
		when(die.roll()).thenReturn(19);
		when(victim.getArmorClass()).thenReturn(10);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(1);
	}
	
	@Test
	public void shouldTakeDoubleDamageWhenCriticalHit() {
		doReturn(1).when(aggressor).characterLevelValue();
		when(die.roll()).thenReturn(20);
		when(victim.getArmorClass()).thenReturn(10);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(2);
	}
	
	@Test
	public void shouldNotHitVictimIfRollIsLessThanVictimClassArmor(){
		doReturn(1).when(aggressor).characterLevelValue();
		when(die.roll()).thenReturn(9);
		when(victim.getArmorClass()).thenReturn(10);
		underTest.battle(aggressor, victim);
		verify(victim, times(0)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void eachEvenCharacterLevelShouldIncreaseRollLevelByOne() {
		doReturn(2).when(aggressor).characterLevelValue();
		when(die.roll()).thenReturn(9);
		when(victim.getArmorClass()).thenReturn(10);

		underTest.battle(aggressor, victim);
		
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void eachOddCharacterLevelShouldNotIncreaseRollLevelByOne() {
		doReturn(3).when(aggressor).characterLevelValue();
		when(die.roll()).thenReturn(8);
		when(victim.getArmorClass()).thenReturn(10);
		
		underTest.battle(aggressor, victim);
		
		verify(victim, times(0)).takeHit(Mockito.anyInt());
	}
	
}
