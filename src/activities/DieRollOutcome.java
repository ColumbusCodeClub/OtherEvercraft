package activities;


public class DieRollOutcome implements Outcome {

	private int outcomeValue;

	public DieRollOutcome(int outcomeValue) {
		this.outcomeValue = outcomeValue;
	}

	@Override
	public boolean isFailure() {
		return false;
	}

	@Override
	public int getOutcome() {
		return outcomeValue;
	}

}
