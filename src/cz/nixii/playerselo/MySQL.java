package cz.nixii.playerselo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

public class MySQL {

	static java.sql.Connection connection;
	
	FileConfiguration config = Main.inst.getConfig();
	
	String host, database, username, password,url;
	String sql;
	Integer port;
	
	public MySQL() {
		
		host = config.getString("MySQL.host");
		database = config.getString("MySQL.database");
		username = config.getString("MySQL.username");
		password = config.getString("MySQL.password");
		
		url = "jdbc:mysql://" + host + ":" + port.toString() + "/" + database;
		
		port = config.getInt("MySQL.port");
		
		connect();
		
		sql = "CREATE TABLE IF NOT EXIST PlayersELO(id int, player string, elo int);";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			if (!results.next()) {
			    System.out.println("Failed");
			} else {
			    System.out.println("Success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void connect(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
