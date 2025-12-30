package genericUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtils {

	Connection conn = null;
	ResultSet result = null;

	/**
	 * This method is used to get connection to database
	 * @author arthi
	 * @throws SQLException
	 */
	public void connectToDB() throws SQLException {
		// register the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);

		// get connection to database
		conn = DriverManager.getConnection(IPathConstants.dbPath, IPathConstants.dbUsername, IPathConstants.dbPassword);

	}

	/**
	 * This method is used to execute query and get the result
	 * @author arthi
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		// create statement
		Statement state = conn.createStatement();
		result = state.executeQuery(query);
		return result;
	}

	/**
	 * This method is used to update query and get the result
	 * @author arthi
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int updateQuery(String query) throws SQLException {
		// create statement
		Statement state = conn.createStatement();
		int res = state.executeUpdate(query);
		return res;
	}

	/**
	 * This method is used to close the connection to database
	 * @author arthi
	 * @throws SQLException
	 */
	public void disconnectDB() throws SQLException {
		// disconnect database connection
		conn.close();
	}

}
