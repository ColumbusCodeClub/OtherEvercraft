package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LevelingTest {
	private Level underTest;
	
	
	@Before
	public void setup() {
		underTest = new Level();
	}

	@Test
	public void levelDefaultsToOne() {
		assertThat(underTest.getLevel(), is(1));
	}
	
	@Test
	public void levelIncreasesBy1ForEach1000ofXPGained() {
		underTest.increaseXp(1000);
		underTest.increaseXp(500);
		underTest.increaseXp(500);
		
		assertThat(underTest.getLevel(), is(3));
	}
	
}
