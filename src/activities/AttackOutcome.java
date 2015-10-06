package activities;

public class AttackOutcome implements Outcome {

	private int outcomeValue;
	private int dieRoll;

	public AttackOutcome(int outcomeValue) {
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

	public int getAssociatedDieRoll() {
		return dieRoll;
	}
	
	public void setAssociatedDieRoll(int dieRoll) {
		this.dieRoll = dieRoll;
	}
	
}
