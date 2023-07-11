package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	public static Connection getConnection() throws SQLException {
		// jdbc:apache:commons:dbcp:커넥션풀이름
		// web.xml에 작성된 poolName
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
	}
}
