package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnnection() {
	    Connection con = null;
	    try {
	        String url = "jdbc:mysql://localhost/plant_db?useUnicode=true&characterEncoding=UTF-8";
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(url, "root", "");
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    return con;
	}
	
//	public static void main(String[] args) {
//		System.out.println(DatabaseUtil.getConnnection());
//	}
}
