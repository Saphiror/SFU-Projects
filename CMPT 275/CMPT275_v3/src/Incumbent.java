
public class Incumbent {
	
	//private String name;
	private String firstName;
	private String lastName;
	private String party;
	private int ID;
	
	public Incumbent(String newFirstName, String newLastName, String newParty, int newID) {
		firstName = newFirstName;
		lastName = newLastName;
		party = newParty;
		ID = newID;
	}
	public Incumbent() {
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
	
	public String toString() {
		String result = "ID of Incumbent: " + ID + "\nName of Incumbent: " + firstName + " " + lastName + "\nParty of Incumbent: " + party;
		return result;
	}
}
