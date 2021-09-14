package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnecDataBase {
	private static Connection connec;

	public static Connection getConnec() {
		return connec;
	}

	 public ConnecDataBase() {
		 super(); String url = "jdbc:sqlserver://localhost:1433;databaseName=CafeManageDB";
		 String user = "cafe_manager";
		 String password = "112233";
		 try {
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 connec = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
				 e.printStackTrace();
		}
	 }
	 
	public static void close() {
		try {
			connec.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
