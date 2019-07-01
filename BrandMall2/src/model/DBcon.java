package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcon {
	static Connection con;
	private DBcon() throws Exception {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pass="123456";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con=DriverManager.getConnection(url, user, pass);
		System.out.println("접속");
	}
	public static Connection getConnection() throws Exception {
		if (con==null) {//Db에 접속이 안되어있으면
			new DBcon();
		}
		return con;
	}

}
