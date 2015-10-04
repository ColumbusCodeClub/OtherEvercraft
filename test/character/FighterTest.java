package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class FighterTest {

	@Mock
	Level level;
	
	private Fighter underTest;

	@Before
	public void setup() {
		initMocks(this);
		underTest = new Fighter(level);
	}

	@Test
	public void fighterHitPointsGoUpBy10ForEveryLevelIncrease() {
		when(level.getLevel()).thenReturn(2);
		assertThat(underTest.getHitPoints(), is(15));
	}

}
