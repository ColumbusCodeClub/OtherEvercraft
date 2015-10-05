package activities;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import character.Die;
import character.Modifier;
import activities.Outcome;
import activities.RollDie;


public class RollDieTest {

	@InjectMocks
	RollDie underTest;
	
	@Mock
	Die die;
	
	@Mock
	Modifier modifierOne;
	
	@Mock
	Modifier modifierTwo;
	
	@Before
	public void setup() {
		initMocks(this);
	}
	
	@Test
	public void shouldApplyAllSuppliedModifiersToDieRoll() {
		when(die.roll()).thenReturn(8);
		when(modifierOne.getValue()).thenReturn(1);
		when(modifierTwo.getValue()).thenReturn(1);
		assertThat(underTest.apply(modifierOne, modifierTwo), is(outcomeOf(10)));
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
