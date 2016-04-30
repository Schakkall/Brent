package enem;

public class Cost {
	private long position;
	private long accessCost;
	
	Cost(long pPosition, long pAccessCost) {
		this.setPosition(pPosition);
		this.setAccessCost(pAccessCost);
	}
	
	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public long getAccessCost() {
		return accessCost;
	}

	public void setAccessCost(long accessCost) {
		this.accessCost = accessCost;
	}
	
	public String toString() {
		return " Pos " + Integer.toString((int)this.getPosition()) + "  Cus " + Integer.toString((int)this.getAccessCost());
		
	}
}
