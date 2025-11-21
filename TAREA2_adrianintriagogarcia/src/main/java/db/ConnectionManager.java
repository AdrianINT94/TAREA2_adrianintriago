package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

		private static Connection connection;
		
		private static final String URL="jdbc:mysql://localhost:3306/circo_adrianintriago?useSSL=false&serverTimezone=UTC";
		private static final String USER="root";
		private static final String PASS="";
		
		private ConnectionManager() {}
		
		
		//metodo para obtener la conexion singleton
		public static Connection getConnection() {
		try {
			if(connection ==null || connection.isClosed()) {
			connection  = DriverManager.getConnection(URL,USER,PASS);
		}
		return connection;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
		}
}
