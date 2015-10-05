package battle;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import activities.Outcome;
import activities.RollDie;
import character.EvercraftCharacter;
import character.Level;
import character.ModifiedCharacter;


public class EvercraftBattleTest {

	EvercraftBattle underTest;
	
	@Mock
	RollDie rollDie;
	
	@Mock
	private EvercraftCharacter victimChar;
	
	@Spy
	private ModifiedCharacter victim = new ModifiedCharacter(victimChar);
	
	@Mock
	private ModifiedCharacter aggressor;
	
	@Mock
	Outcome initialOutcome;
	
	@Mock
	Outcome successfullOutcome;
	
	@Mock
	Outcome failedOutcome;
	
	@Before
	public void setup() {
		initMocks(this);
		underTest = new EvercraftBattle(rollDie, initialOutcome);
		doReturn(10).when(victim).getDefense();
		when(failedOutcome.isFailure()).thenReturn(true);
		when(successfullOutcome.isFailure()).thenReturn(false);
	}
	
	@Test
	public void shouldHitVictimIfModifiedRollIsGreaterThanVictimDefense() {
		when(aggressor.preform(rollDie, initialOutcome)).thenReturn(successfullOutcome);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldHitVictimIfModifiedRollIsEqaulToVictimClassArmor() {
		when(aggressor.preform(rollDie, initialOutcome)).thenReturn(successfullOutcome);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldNotHitVictimIfModifiedRollIsLessThanVictimClassArmor(){
		when(aggressor.preform(rollDie, initialOutcome)).thenReturn(failedOutcome);
		underTest.battle(aggressor, victim);
		verify(victim, times(0)).takeHit(Mockito.anyInt());
	}
	
	@Test
	public void shouldTakeHitEqualToAttackPower() {
		when(aggressor.preform(rollDie, initialOutcome)).thenReturn(successfullOutcome);
		when(aggressor.getAttackPower()).thenReturn(2);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(2);
	}
//
//	@Test
//	public void shouldTakeHitEqualToCriticalHitAttackPowerWhenCriticalHitIsRolled() {
//		when(die.roll()).thenReturn(20);
//		when(aggressor.getRollModifier()).thenReturn(2);
//		when(aggressor.getCriticalHitAttackPower()).thenReturn(4);
//		underTest.battle(aggressor, victim);
//		verify(victim, times(1)).takeHit(4);
//	}
}
