package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.HeaterSwitch;
import pojo.Temperature;

public class DataBaseService {
	
	private Connection conn = getConnection();
	
	private Connection getConnection() {
						
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercises","root","Ciupicipicss390");
			System.out.println("Connected...");			
		}catch(Exception e) {
			System.out.print(e);
		}
		return conn;
	}
	
	public void saveTemperature(Temperature temperature) {		
		String sql = "INSERT INTO temperature (log_date, value) VALUES (?,?)";
		java.sql.Timestamp tDate = new java.sql.Timestamp(temperature.getLogDate().getTime());
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, tDate);
			ps.setDouble(2, temperature.getValue());
			ps.execute();
			System.out.println("Temperature stored..");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public Temperature getLastTemperature() {		
		Temperature temperature = new Temperature();
		String sql = "SELECT * FROM temperature order by log_date desc limit 1;";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet result = ps.executeQuery();
				while (result.next()) {					
					temperature.setValue(result.getDouble("value"));
					temperature.setLogDate(result.getTimestamp("log_date"));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		return temperature;
	}
	
	public void saveSwitchStatus(HeaterSwitch heaterSwitch) {
		String sql = "INSERT INTO switch (log_date, value) VALUES (?, ?)";
		java.sql.Timestamp tDate = new java.sql.Timestamp(heaterSwitch.getLogDate().getTime());
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, tDate);
			ps.setString(2, heaterSwitch.getStatus());
			ps.execute();
			System.out.println("Switch status saved");
		} catch (SQLException e){
			e.printStackTrace();
		}		
	}
}