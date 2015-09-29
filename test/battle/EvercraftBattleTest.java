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
import character.ModifiedCharacter;


public class EvercraftBattleTest {

	@Mock
	Die die;
	
	@Mock
	private EvercraftCharacter victimChar;
	
	@Spy
	private ModifiedCharacter victim = new ModifiedCharacter(victimChar);
	
	@Mock
	private ModifiedCharacter aggressor;
	
	EvercraftBattle underTest;

	@Before
	public void setup() {
		initMocks(this);
		underTest = new EvercraftBattle(die);
		doReturn(10).when(victim).getDefense();
	}
	
	@Test
	public void shouldHitVictimIfModifiedRollIsGreaterThanVictimDefense() {
		when(die.roll()).thenReturn(9);
		when(aggressor.getRollModifier()).thenReturn(2);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldHitVictimIfModifiedRollIsEqaulToVictimClassArmor() {
		when(die.roll()).thenReturn(9);
		when(aggressor.getRollModifier()).thenReturn(1);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldNotHitVictimIfModifiedRollIsLessThanVictimClassArmor(){
		when(die.roll()).thenReturn(9);
		when(aggressor.getRollModifier()).thenReturn(0);
		underTest.battle(aggressor, victim);
		verify(victim, times(0)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldTakeHitEqualToAttackPower() {
		when(die.roll()).thenReturn(9);
		when(aggressor.getRollModifier()).thenReturn(2);
		when(aggressor.getAttackPower()).thenReturn(2);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(2);
	}

	@Test
	public void shouldTakeHitEqualToCriticalHitAttackPowerWhenCriticalHitIsRolled() {
		when(die.roll()).thenReturn(20);
		when(aggressor.getRollModifier()).thenReturn(2);
		when(aggressor.getCriticalHitAttackPower()).thenReturn(4);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(4);
	}
}
