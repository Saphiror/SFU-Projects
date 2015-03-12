import java.util.Scanner;	

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Welcome to CMPT 275");
		
		//GetConnection con = new GetConnection();
		
//		Candidate c1 = new Candidate("lili", "x");
//		System.out.println(c1);
//		c1.setName("shijie");
//		System.out.println(c1);
//		System.out.println(c1.getName());
//		Candidate c2 = new Candidate();
//		System.out.println(c2);
		
/*		PollingStation p1 = new PollingStation(253);
		System.out.println(p1);
		p1.setPollID(235);
		System.out.println(p1);*/
		
/*		Riding r1 = new Riding();
		r1.setCreatedDate(2001, 8, 22);
		System.out.println(r1.getCreatedDate());*/
	
//--------------------------------------------------------		
//		System.out.println("Welcome to CMPT 275!\n\n1 -- Add Riding\n2 -- Modify Riding\n0 -- Exit\n");
//		System.out.print("Which function would you like to run: ");
//		int choice;
//		Scanner scan = new Scanner(System.in);
//		choice = scan.nextInt();
//		//AddRiding ar = new AddRiding();
//		
//		while (choice != 0) {
//			if (choice == 1) {
//				//AddRiding ar = new AddRiding();
//			}
//			else if (choice == 2) {
//				//Modify riding
//				ModifyRiding ar = new ModifyRiding();
//			}
//			System.out.println("\n1 -- Add Riding\n2 -- Modify Riding\n0 -- Exit\n");
//			System.out.print("Which function would you like to run: ");
//			choice = scan.nextInt();
//		}
//		System.out.println("See you next time!");
//--------------------------------------------------------
		//ModifyRiding ar = new ModifyRiding();	
		
		AddRiding ar =  new AddRiding();
		ar.initialRiding();
		Riding riding = ar.getRiding();
		
		InserRidingToDB insert = new InserRidingToDB(riding);
		//InserRidingToDB insert2 = new InserRidingToDB(riding);
		
		
		//ModifyRiding m = new ModifyRiding(r);
	}
}
