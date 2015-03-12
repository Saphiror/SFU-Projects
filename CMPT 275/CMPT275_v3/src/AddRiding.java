import java.util.Date;
import java.util.Scanner;

public class AddRiding {
	
	private Riding r1;
	private Candidate[] candidates;
	private PollingStation[] pollingStations;
	
	public AddRiding() {
	}
	
	public void initialRiding() {
		
		r1 = new Riding();
		System.out.println("in process");
		
		r1.initialRidingName();
//		r1.initialCreatedDate();
		r1.initialNumOfVoters();
		r1.initialNumOfSeats();
	    r1.initialNumOfCandidates();
	    r1.initialCandidates();
//		r1.initialNumOfIncumbents();
//		r1.initialIncumbents();
		r1.initialNumOfPollingStations(); 
		r1.initialPollingStation();
		diaplayRiding(r1);
	}
	
	public Riding getRiding() {
		return r1;
	}
	
/*	public Candidate[] getCandidates() {
		return candidates;
	}
	
	public void initialRidingName() {
		System.out.print("Please enter a new riding name: ");
		String name;
		Scanner sc1 = new Scanner(System.in);
		name = sc1.next();
		r1.setName(name);
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
			r1.setCreatedDate(year, month, day);
		}		
	}
	
	public void initialNumOfVoters() {
		System.out.print("\nPlease enter the number of voters in this riding: ");
		Scanner sc3 = new Scanner(System.in);
		int voters;
		voters = sc3.nextInt();
		r1.setNumOfVoters(voters);
	}
	
	public void initialNumOfSeats() {
		System.out.print("\nPlease enter the number of Seats in this riding: ");
		Scanner sc4 = new Scanner(System.in);
		int seats;
		seats = sc4.nextInt();
		r1.setNumOfSeats(seats);
	}
	
	public void initialNumOfCandidates() {
		System.out.print("\nPlease enter the number of candidates in this riding: ");
		Scanner sc5 = new Scanner(System.in);
		int candidates;
		candidates = sc5.nextInt();
		if (candidates > r1.getNumOfSeats()) {
			r1.setNumOfCandidates(candidates);
		}
		else {
			System.out.println("Number of candidates must be greater than the number of seat in this riding!");
			initialNumOfCandidates();
		}
	}
	
	public void initialNumOfIncumbents() {
		System.out.print("\nPlease enter the number of incumbents in this riding: ");
		Scanner sc6 = new Scanner(System.in);
		int incumbents;
		incumbents = sc6.nextInt();
		r1.setNumOfIncumbents(incumbents);
	}
	
	public void initialNumOfPollingStations() {
		System.out.print("\nPlease enter the number of polling station in this riding: ");
		Scanner sc7 = new Scanner(System.in);
		int poll;
		poll = sc7.nextInt();
		r1.setNumOfPollingStations(poll);
	}
	
	public void initialCandidates() {
		candidates = new Candidate[r1.getNumOfCandidates()];
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
		
		for (int i=1; i<r1.getNumOfCandidates(); i++) {
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
	
	
	public void initialIncumbents() {
		incumbents = new Incumbent[r1.getNumOfIncumbents()];
		int newID;
		String newFirstName;
		String newLastName;
		String newParty;
		
		System.out.print("\nIncumbent # 1" + "\n");
		System.out.print("Please enter the first name of the incumbent # 1: ");
		Scanner sc = new Scanner(System.in);
		newFirstName = sc.nextLine();
		
		System.out.print("Please enter the last name of the incumbent # 1: ");
		Scanner sc1 = new Scanner(System.in);
		newLastName = sc1.nextLine();
		
		System.out.print("Please enter the party of the incumbent # 1: ");
		Scanner sc12 = new Scanner(System.in);
		newParty = sc12.nextLine();
		
		//System.out.print("incumbent # 1" + "\n");
		newID = takeIncumbentsIDInputHelp();
		
		incumbents[0] = new Incumbent(newFirstName, newLastName, newParty, newID);
		int count = 1;
		
		for (int i=1; i<r1.getNumOfIncumbents(); i++) {
			System.out.print("\nIncumbent # " +(i+1) +"\n");
			System.out.print("Please enter the first name of this incumbent: ");
			Scanner sc14 = new Scanner(System.in);
			newFirstName = sc14.nextLine();
			
			System.out.print("Please enter the last name of this incumbent: ");
			Scanner sc15 = new Scanner(System.in);
			newLastName = sc15.nextLine();
			
			System.out.print("Please enter the party of this incumbent: ");
			Scanner sc3 = new Scanner(System.in);
			newParty = sc3.nextLine();
			
			newID = takeIncumbentsIDInputHelp();
			
			while (checkIncumbentsID(incumbents, newID, count) == false) {
				System.out.println("Incumbent ID from the same riding cannot be repeated!");
				newID = takeIncumbentsIDInputHelp();
			}
			incumbents[i] = new Incumbent(newFirstName, newLastName, newParty, newID);
			count++;
		}		
	}
	
	private int takeIncumbentsIDInputHelp() {
		int newID;
		System.out.print("Please enter the Incumbents ID of this Poll: " );
		Scanner sc = new Scanner(System.in);
		newID = sc.nextInt();
		return newID;
	}
	
	//check if any two incumbents' ID are the same
	// return true - ID are all different
	private boolean checkIncumbentsID(Incumbent[] currentIDs, int newPollID, int currentCount) {
		for (int i=0; i<currentCount; i++) {
			if (newPollID == currentIDs[i].getID()) {
				return false;
			}
		}
		return true;
	}
	
	
	public void initialPollingStation() {
		pollingStations = new PollingStation[r1.getNumOfPollingStations()];
		int newID;
		
		System.out.print("Poll # 1" + "\n");
		newID = takePollingStationIDInputHelp();
		pollingStations[0] = new PollingStation(newID);
		pollingStations[0].initialPollBox();
		int count = 1;
		
		for (int i=1; i<r1.getNumOfPollingStations(); i++) {
			System.out.print("Poll # " +(i+1) +"\n");
			newID = takePollingStationIDInputHelp();
			
			while (checkPollingStationID(pollingStations, newID, count) == false) {
				System.out.println("Poll ID from the same riding cannot be repeated!");
				newID = takePollingStationIDInputHelp();
			}
			pollingStations[i] = new PollingStation(newID);
			pollingStations[i].initialPollBox();
			count++;
		}
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
*/
	
	public void diaplayRiding(Riding r1) {
		candidates = r1.getCandidates();
		pollingStations = r1.getPollingStations();
		System.out.println(r1);
		
		System.out.print("\nCandidates Information:");
		for (int i=0; i<r1.getNumOfCandidates(); i++) {
			System.out.println("\nCandidate # " + (i+1) + "\n" + candidates[i]);
		}
		
		/*System.out.println("\nIncumbents Information:");
		for (int j=0; j<r1.getNumOfIncumbents(); j++) {
			System.out.print("\nIncumbent # " + (j+1) + "\n" + incumbents[j]);
		}
		*/
		
		System.out.println("\nPolling Station Information:");
		for (int n=0; n<r1.getNumOfPollingStations(); n++) {
			System.out.print("\nPoll # " + (n+1) + "\n" + pollingStations[n]);
		}
		System.out.println("\n---------End of Riding Summary---------\n");
	}
}