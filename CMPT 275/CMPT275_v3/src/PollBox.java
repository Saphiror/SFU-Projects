
// Need to be changed later for Poll_Box Table


public class PollBox {
	
	int boxID;
	private Ballot[][] ballots = new Ballot[20][99999];
	
	public PollBox(int newID) {
		boxID = newID;
	}
	
	public void setBoxID(int newID) {
		boxID = newID;
	}
	
	public void setBallot(int i, int j, Ballot newBallot) {
		ballots[i][j] = newBallot;
	}
	
	public int getBoxID() {
		return boxID;
	}
	
	public Ballot getBallot(int i, int j) {
		return ballots[i][j];
	}
	
	public String toString() {
		String result = "Poll Box ID; " + boxID;
		return result;
	}
}
