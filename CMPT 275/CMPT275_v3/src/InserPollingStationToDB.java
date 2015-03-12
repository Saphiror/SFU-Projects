import java.sql.*;

public class InserPollingStationToDB {
	
	private static Connection con;
	private PreparedStatement stat1 = null;
	private String sql = "";
	private int ID;
	private int numOfPollBoxes;
	private String ridingName;
	private PollingStation[] pollingStations;
	
	public InserPollingStationToDB(Riding newRiding) {
		
		try {
			GetConnection newCon = new GetConnection();
			con = newCon.getCon();
			
			ridingName = newRiding.getName();
			pollingStations = newRiding.getPollingStations();
			
			for (int i=0; i<newRiding.getNumOfPollingStations(); i++) {
				InserOnePollingStationToDB(pollingStations[i]);
				//InserPollBoxToDB insert3 = new InserPollBoxToDB(pollingStations[i]);
				//con = newCon.getCon();
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
	
	public void InserOnePollingStationToDB(PollingStation newPollingStation) {
		ID = newPollingStation.getPollID();
		numOfPollBoxes = newPollingStation.getNumOfPollBoxes();
		
		try {
			sql = "insert into Polling_Station values (?,?,?)";
			stat1 = con.prepareStatement(sql);
			stat1.setInt(1, ID);
			stat1.setString(2, ridingName);
			stat1.setInt(3, numOfPollBoxes);
			stat1.executeUpdate();
		}
		catch (SQLException se)
		{
			System.out.println("\nSQL Exception occured, the state : "+ se.getSQLState()+"\nMessage:\n"+se.getMessage()+"\n");
			return;
		}
	}
}
