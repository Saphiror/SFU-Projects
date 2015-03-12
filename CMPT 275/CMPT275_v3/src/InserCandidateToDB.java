import java.sql.*;

public class InserCandidateToDB {
	
	private static Connection con;
	private PreparedStatement stat1 = null;
	private String sql = "";
	private String firstName;
	private String lastName;
	private String party;
	private int ID;
	private int firstChoiceVotes;
	private String ridingName;
	private Candidate[] candidates;
	
	public InserCandidateToDB(Riding newRiding) {
		
		try {
			GetConnection newCon = new GetConnection();
			con = newCon.getCon();
			
			ridingName = newRiding.getName();
			candidates = newRiding.getCandidates();
			
			for (int i=0; i<newRiding.getNumOfCandidates(); i++) {
				InserOneCandidateToDB(candidates[i]);
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
	
	public void InserOneCandidateToDB(Candidate newCandidate) {
		ID = newCandidate.getID();
		firstName = newCandidate.getFirstName();
		lastName = newCandidate.getLastName();
		party = newCandidate.getParty();
		firstChoiceVotes = newCandidate.getFirstChoiceVotes();
		
		try {
			sql = "insert into Candidate values (?,?,?,?,?,?)";
			stat1 = con.prepareStatement(sql);
			stat1.setInt(1, ID);
			stat1.setString(2, firstName);
			stat1.setString(3, lastName);
			stat1.setString(4, party);
			stat1.setString(5, ridingName);
			stat1.setInt(6, firstChoiceVotes);
			stat1.executeUpdate();
		}
		catch (SQLException se)
		{
			System.out.println("\nSQL Exception occured, the state : "+ se.getSQLState()+"\nMessage:\n"+se.getMessage()+"\n");
			return;
		}
	}
}
