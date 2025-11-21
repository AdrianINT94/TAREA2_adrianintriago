package db;

import java.sql.Connection;

public class TestConexion {
 //test para ver si funciona 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			try {
				Connection con = ConnectionManager.getConnection();
				if(con != null) {
					System.out.println("Conexion establecida");
				}
				}catch(Exception e) {
					System.out.println("error conectando");
					e.printStackTrace();
				}
			
	}
}


