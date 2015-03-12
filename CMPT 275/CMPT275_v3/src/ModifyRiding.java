import java.util.Scanner;

public class ModifyRiding {
	
	private Riding riding;
	
	public ModifyRiding(Riding riding) {

		int choice;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to CMPT 275!\n\n1 -- Name of Riding\n2 -- Number Of Voters\n3 -- Number Of PollingStations\n4 -- Number Of Seats\n5 -- Number Of Candidates" +
				"\n6 -- Date this riding was created\n0 -- Save Changes Exit\n");
		System.out.print("\nWhich attribute would you like to change: ");
		choice = scan.nextInt();
		
		while (choice != 0) {
			if (choice == 1) {
				riding.initialRidingName();
			}
			else if (choice == 2) {
				riding.initialNumOfVoters();
			}
			else if (choice == 3) {
				riding.initialNumOfPollingStations();
				riding.initialPollingStation();
			}
			else if (choice == 4) {
				riding.initialNumOfSeats();
			}
			else if (choice == 5) {
				riding.initialNumOfCandidates();
				riding.initialCandidates();
			}
//			else if (choice == 6) {
//				riding.initialNumOfIncumbents();
//				riding.initialIncumbents();
//			}
			else if (choice == 6) {
				riding.initialCreatedDate();
			}
			System.out.print("Which attribute would you like to change: ");
			choice = scan.nextInt();
		}
		//riding.initialRiding();	
		System.out.println("See you next time!");
	}
}