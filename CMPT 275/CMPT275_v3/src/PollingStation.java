import java.util.Scanner;

public class PollingStation {
	
	private int ID;
	private int numOfPollBoxes;
	private PollBox[] pollBoxes;
	
	public PollingStation(int pollID) {
		ID = pollID;
	}
	
	public PollingStation() {
		ID = 0;
	}
	
	public void setPollID(int pollID) {
		ID = pollID;
	}
	
	public int getPollID() {
		return ID;
	}
	
	public int getNumOfPollBoxes() {
		return numOfPollBoxes;
	}
	
	public PollBox[] getPollBoxes() {
		return pollBoxes;
	}
	
	public void initialPollBox() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the number of poll boxes in this polling station: ");
		numOfPollBoxes = sc.nextInt();
		pollBoxes = new PollBox[numOfPollBoxes];
		
		for (int n=0; n<numOfPollBoxes; n++) {
			pollBoxes[n] = new PollBox(n);
		}
	}
	
	public String toString() {
		String result = "Poll ID: " + ID + "\nNumber of Poll Boxes: " + numOfPollBoxes;
		return result;
	}
}
