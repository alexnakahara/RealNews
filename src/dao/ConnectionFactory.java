package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	static {
		try {
			System.out.println("Procurando o driver...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver encontrado com sucesso!");
		} catch (ClassNotFoundException ex) {
			System.err.println("O driver n�o foi encontrado.");
		}
	}
	
	public static Connection conectar() {
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "Alexander");
			properties.setProperty("password", "alex1006");
			properties.setProperty("useSSL", "false");
			return DriverManager
					.getConnection("jdbc:mysql://localhost:3306/portal_realnews?useTimezone=true&serverTimezone=UTC", properties);
		} catch (SQLException ex) {
			System.err.println("N�o foi possível conectar!");
			ex.printStackTrace();
			return null;
		}
	}
	
}
