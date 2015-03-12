import java.util.Date;
//import java.lang.Exception;
import java.util.Scanner;

public class Riding {
	
	private String name;
	private int numOfVoters;
	//private int numOfVoterTurnout;
	private int numOfPollingStations;
	private int numOfSeats;
	private int numOfCandidates;
	//private int numOfIncumbents;
	private Date date;	
	private Candidate[] candidates;
	private PollingStation [] pollingstations;
	
	public Riding() {
		 
	}
	
	public void setCandidates(Candidate newcandidate[]) {
		candidates = newcandidate;
	}
	
	public void setPollingStation(PollingStation newpollingstation[]){
		pollingstations = newpollingstation;
	}
	
 	public void setCreatedDate(Date newDate) {
		date = newDate;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	/*public void setNumOfVoterTurnout(int newNumOfVoterTurnout) {
		numOfVoterTurnout = newNumOfVoterTurnout;
	}*/
	
	public void setNumOfVoters(int newNumOfVoters) {
		numOfVoters = newNumOfVoters;
	}
	
	public void setNumOfPollingStations(int newNumOfPollingStations) {
		numOfPollingStations = newNumOfPollingStations;
	}
	 
	public void setNumOfSeats(int newNumOfSeats) {
		numOfSeats = newNumOfSeats;
	}
	
	public void setNumOfCandidates(int newNumOfCandidates) {
		numOfCandidates = newNumOfCandidates;
	}

	/*public void setNumOfIncumbents(int newNumOfIncumbents) {
		numOfIncumbents = newNumOfIncumbents;
	}
	*/
	public void setCreatedDate(int createdYear, int createdMonth, int createdDate) {
		date = new Date(createdYear-1900, createdMonth-1, createdDate);
	}
	
	public Candidate[] getCandidates() {
		return candidates;
	}
	
	public PollingStation[] getPollingStations(){
		return pollingstations;
	}
	
	public Date getCreatedDate() {
		return date;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumOfVoters() {
		return numOfVoters;
	}
	
	/*public int getNumOfVoterTurnout() {
		return numOfVoterTurnout;
	}*/
	
	public int getNumOfPollingStations() {
		return numOfPollingStations;
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	public int getNumOfCandidates() {
		return numOfCandidates;
	}
	
	/*public int getNumOfIncumbents() {
		return numOfIncumbents;
	}*/
	
	public void initialRidingName() {
		System.out.print("Please enter a new riding name: ");
		//String name;
		Scanner sc1 = new Scanner(System.in);
		name = sc1.next();
		setName(name);
		
	}
	
	public void initialCreatedDate() {
		System.out.print("\nPlease enter the the date the riding was created(eg. 2012 3 22): ");
		Scanner sc2 = new Scanner(System.in);
		int year, month, day;
		year = sc2.nextInt();
		month = sc2.nextInt();
		day = sc2.nextInt();
		
		if ((year < 1900) || (month > 12) || (month < 1) || (day > 31) || (day < 1)) {
			System.out.println("Invalid date format!");
			initialCreatedDate();
		}
		else {
			setCreatedDate(year, month, day);
		}		
	}
	
	public void initialNumOfVoters() {
		System.out.print("\nPlease enter the number of voters in this riding: ");
		Scanner sc3 = new Scanner(System.in);
		int voters;
		voters = sc3.nextInt();
		setNumOfVoters(voters);
	}
	
	public void initialNumOfSeats() {
		System.out.print("\nPlease enter the number of Seats in this riding: ");
		Scanner sc4 = new Scanner(System.in);
		int seats;
		seats = sc4.nextInt();
		setNumOfSeats(seats);
	}
	
	public void initialNumOfCandidates() {
		System.out.print("\nPlease enter the number of candidates in this riding: ");
		Scanner sc5 = new Scanner(System.in);
		int candidates;
		candidates = sc5.nextInt();
		if (candidates > getNumOfSeats()) {
			setNumOfCandidates(candidates);
		}
		else {
			System.out.println("Number of candidates must be greater than the number of seat in this riding!");
			initialNumOfCandidates();
		}
	}
	
	public void initialNumOfPollingStations() {
		System.out.print("\nPlease enter the number of polling station in this riding: ");
		Scanner sc7 = new Scanner(System.in);
		int poll;
		poll = sc7.nextInt();
		setNumOfPollingStations(poll);
	}
	
	public void initialCandidates() {
		candidates = new Candidate[getNumOfCandidates()];
		int newID;
		String newFirstName;
		String newLastName;
		String newParty;
		
		System.out.print("\nCandidates # 1" + "\n");
		System.out.print("Please enter the first name of the candidates # 1: ");
		Scanner sc = new Scanner(System.in);
		newFirstName = sc.next();
		
		System.out.print("Please enter the last name of the candidates # 1: ");
		Scanner sc12 = new Scanner(System.in);
		newLastName = sc12.next();
		
		
		System.out.print("Please enter the party of the candidates # 1: ");
		Scanner sc1 = new Scanner(System.in);
		newParty = sc1.nextLine();
		

		newID = takeCandidatesIDInputHelp();
		
		candidates[0] = new Candidate(newFirstName, newLastName, newParty, newID);
		int count = 1;
		
		for (int i=1; i<getNumOfCandidates(); i++) {
			System.out.print("\nCandidates # " +(i+1) +"\n");
			
			System.out.print("Please enter the first name of this candidates: ");
			Scanner sc10 = new Scanner(System.in);
			newFirstName = sc10.next();
			
			System.out.print("Please enter the last name of this candidates: ");
			Scanner sc2 = new Scanner(System.in);
			newLastName = sc2.next();
			
			System.out.print("Please enter the party of this candidates: ");
			Scanner sc3 = new Scanner(System.in);
			newParty = sc3.nextLine();
			
			newID = takeCandidatesIDInputHelp();
			
			while (checkCandidatesID(candidates, newID, count) == false) {
				System.out.println("Candidate ID from the same riding cannot be repeated!");
				newID = takeCandidatesIDInputHelp();
			}
			candidates[i] = new Candidate(newFirstName, newLastName, newParty, newID);
			count++;
		}
		setCandidates(candidates);
		
	}
	
	private int takeCandidatesIDInputHelp() {
		int newID;
		System.out.print("Please enter the Candidates ID of this Poll: " );
		Scanner sc = new Scanner(System.in);
		newID = sc.nextInt();
		return newID;
	}
	
	private boolean checkCandidatesID(Candidate[] currentIDs, int newPollID, int currentCount) {
		for (int i=0; i<currentCount; i++) {
			if (newPollID == currentIDs[i].getID()) {
				return false;
			}
		}
		return true;
	}
	
	public void initialPollingStation() {
		pollingstations = new PollingStation[getNumOfPollingStations()];
		int newID;
		
		System.out.print("Poll # 1" + "\n");
		newID = takePollingStationIDInputHelp();
		pollingstations[0] = new PollingStation(newID);
		pollingstations[0].initialPollBox();
		int count = 1;
		
		for (int i=1; i<getNumOfPollingStations(); i++) {
			System.out.print("\nPoll # " +(i+1) +"\n");
			newID = takePollingStationIDInputHelp();
			
			while (checkPollingStationID(pollingstations, newID, count) == false) {
				System.out.println("Poll ID from the same riding cannot be repeated!");
				newID = takePollingStationIDInputHelp();
			}
			pollingstations[i] = new PollingStation(newID);
			pollingstations[i].initialPollBox();
			count++;
		}
		setPollingStation(pollingstations);
	}
	
	private int takePollingStationIDInputHelp() {
		int newID;
		System.out.print("Please enter the Poll ID of this Poll: " );
		Scanner sc = new Scanner(System.in);
		newID = sc.nextInt();
		return newID;
	}
	
	//check if any two Polling Stations' ID are the same
	// return true - ID are all different
	private boolean checkPollingStationID(PollingStation[] currentIDs, int newPollID, int currentCount) {
		for (int i=0; i<currentCount; i++) {
			if (newPollID == currentIDs[i].getPollID()) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String result = "\n-----------Riding Summary----------\nName of Riding: " + name + "\nNumber Of Voters: " + 
		numOfVoters + "\nNumber Of PollingStations: " + numOfPollingStations + "\nNumber Of Seats: " 
		+ numOfSeats + "\nNumber Of Candidates: " + numOfCandidates + "\nDate this riding was created: " + date;
		return result;
	}
}
