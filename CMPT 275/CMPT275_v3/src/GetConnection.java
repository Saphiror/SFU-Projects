import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GetConnection {
	
	private static Connection con;
	private static String space = "                                           ";
	
	public GetConnection() {

		String connectionUrl = "jdbc:odbc:briskDSN";

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException ce)
			{
				System.out.println("\n\nNo JDBC-ODBC bridge; exit now.\n\n");
				return;
			}

		try
		{
			con = DriverManager.getConnection(connectionUrl,"","");
			System.out.println("Connect sucessfully!\n");
			
		}catch (SQLException se)
			{
				System.out.println("\n\nNo proper DSN; exit now.\n\n");
				return;
			}
		}
	
	public Connection getCon() {
		return con;
	}
}
