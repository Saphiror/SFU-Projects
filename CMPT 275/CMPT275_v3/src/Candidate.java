
public class Candidate {
	
	private String firstName;
	private String lastName;
	private String party;
	private int ID;
	private int firstChoiceVotes;
	private Ballot[][] ballots = new Ballot[20][99999];
	//private int transferredVotes;
	
	public Candidate(String newFirstName, String newLastName, String newParty, int newID) {
		firstName = newFirstName;
		lastName = newLastName;
		party = newParty;
		ID = newID;
		//firstChoiceVotes = 0;
		//transferredVotes = 0;
	}
	public Candidate() {
		firstName = null;
		lastName = null;
		party = null;
		ID = 0;
	}
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}
	public void setLastName(String newLastName) {
		lastName = newLastName;
	}
	public void setParty(String newParty) {
		party = newParty;
	}
	public void setID(int newID){
		ID = newID;
	}
	//just set one ballot in to the array at location [i][j]
	public void setBallot(int i, int j, Ballot newBallot) {
		ballots[i][j] = newBallot;
	}
	
	public void setFirstChoiceVotes(int newVotes) {
		firstChoiceVotes = newVotes;
	}
	
	/*	public void setTransferredVotes(int newVotes) {
		transferredVotes = newVotes;
	}*/
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getParty() {
		return party;
	}
	public int getID(){
		return ID;
	}
	public Ballot getBallot(int i, int j) {
		return ballots[i][j];
	}
	public int getFirstChoiceVotes() {
		return firstChoiceVotes;
	}
	
	/*	public int getTransferredVotes() {
		return transferredVotes;
	}
	*/
	public String toString() {
		String result = "ID of Candidate: " + ID + "\nName of Candidate: " + firstName + " " + lastName + 
				"\nParty of Candidate: " + party;
		return result;
	}
}

