package principal;

import java.util.Properties;

public class ConnectionManager {
	
	public static ConnectionManager  conection =null;
	
	public static ConnectionManager getConnection() {
		if(conection ==null) {
			try {
				Properties props = new Properties();
				InputStream input= ConnectionnManager.class.getClassLoader()
						.getResourceAsStream("database.properties");
					
				propload(input);
				
				String url= props.getProperty("database.url");
				String user= props.getProperty("database.user");
				String password= props.getProperty("database.password");
				
				connection = DriveManager.getConnection(url,user,password);
				
				}catch(Exception e) {
					e.printStackTrace();
			}
		}
		return connection;
	}

}
