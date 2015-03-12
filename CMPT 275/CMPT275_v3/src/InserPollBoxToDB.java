
//May need to be change later

import java.sql.*;

public class InserPollBoxToDB {
	
	private static Connection con;
	private PreparedStatement stat1 = null;
	private String sql = "";
	private int pollBoxID;
	private int pollingStationID;
	private int numOfBallots;
	private PollBox[] pollBoxes;
	
	public InserPollBoxToDB(PollingStation newPollingStation) {
		
		try{
			GetConnection newCon = new GetConnection();
			con = newCon.getCon();
			
			pollBoxes = newPollingStation.getPollBoxes();
			
			for (int n=0; n<newPollingStation.getNumOfPollBoxes(); n++) {
				InserOnePollBoxToDB(pollBoxes[n]);
			}
			con.close();
			System.out.println("\nSuccessfully connected to CSIL SQL Server!\n\n");
		}
		catch (SQLException se)
		{
			System.out.println("\nSQL Exception occured, the state : "+ se.getSQLState()+"\nMessage:\n"+se.getMessage()+"\n");
			return;
		}
	}
	
	public void InserOnePollBoxToDB(PollBox newPollBox) {
		pollBoxID = newPollBox.getBoxID();
		numOfBallots = 0;
		
		try {
			sql = "insert into Poll_Box values (?,?,?)";
			stat1 = con.prepareStatement(sql);
			stat1.setInt(1, pollBoxID);
			stat1.setInt(2, numOfBallots);
			stat1.setInt(3, pollingStationID);
			stat1.executeUpdate();
		}
		catch (SQLException se)
		{
			System.out.println("\nSQL Exception occured, the state : "+ se.getSQLState()+"\nMessage:\n"+se.getMessage()+"\n");
			return;
		}
		
	}
	
}
