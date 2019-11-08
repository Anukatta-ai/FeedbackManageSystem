/**
* This is the Connection Factory class. It is used to call the connection to the database anywhere we need it
* instead of defining the entire connection again for each method call.
*
* @author  Anjali Shaw, Anudeep Reddy Katta, Amit Kumar Baghel, Vanpriya Shukla
* @since   2019-09-28 
*/
package com.feedback.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static ConnectionFactory connectionFactory = null;
	private static Properties properties = new Properties();
	
	private ConnectionFactory()  {
		try  {
			try  (InputStream inputStream = new FileInputStream(".//resources//config.properties ")) {
					properties.load(inputStream);
					//properties.list(System.out);
					String dbDriver = properties.getProperty("db.driver");
					Class.forName(dbDriver);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} catch (ClassNotFoundException ex) {
		ex.printStackTrace();
	}
	}
	
	public static ConnectionFactory getInstance() {
		if(connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
	public Connection getConnection() throws SQLException  {
		Connection conn = null;
		String dburl = properties.getProperty("db.url");
		String dbUser = properties.getProperty("db.user");
		String dbPassword = properties.getProperty("db.password");
		conn = DriverManager.getConnection(dburl,dbUser,dbPassword);
		return conn;

	}
	
	 public static void main(String[] args) {
		try {
			Connection connection = ConnectionFactory.getInstance().getConnection();
			//System.out.println(connection);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
		
	


