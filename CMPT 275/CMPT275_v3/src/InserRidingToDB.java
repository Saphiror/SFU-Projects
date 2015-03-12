import java.sql.*;

public class InserRidingToDB {
	
	private static Connection con;
	private PreparedStatement stat1 = null;
	private String sql = "";
	private String newName;
	private int numOfVoters;
	private int numOfPollingStations;
	private int numOfSeats;
	private int numOfCandidates;
	
	public InserRidingToDB(Riding newRiding) {
		
		GetConnection newCon = new GetConnection();
		con = newCon.getCon();
		
		newName = newRiding.getName();
		numOfSeats = newRiding.getNumOfSeats();
		numOfCandidates = newRiding.getNumOfCandidates();
		numOfPollingStations = newRiding.getNumOfPollingStations();
		numOfVoters = newRiding.getNumOfVoters();
			
		try {
			sql = "insert into Riding values (?,?,?,?,?)";
			stat1 = con.prepareStatement(sql);
			stat1.setString(1, newName);
			stat1.setInt(2, numOfSeats);
			stat1.setInt(3, numOfCandidates);
			stat1.setInt(4, numOfPollingStations);
			stat1.setInt(5, numOfVoters);

			stat1.executeUpdate();
			con.close();
			System.out.println("\nSuccessfully connected to CSIL SQL Server!\n\n");
			
			InserCandidateToDB insert1 = new InserCandidateToDB(newRiding);
			InserPollingStationToDB insert2 = new InserPollingStationToDB(newRiding);
		} 
		catch (SQLException se)
		{
			System.out.println("\nSQL Exception occured, the state : "+ se.getSQLState()+"\nMessage:\n"+se.getMessage()+"\n");
			return;
		}

	}
	
	

}
