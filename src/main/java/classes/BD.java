package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	static Connection con = null;
	static String url = "jdbc:mysql://localhost:3306/miauguinho";
	static String user = "root";
	static String password = "12345678";
	
	public static Connection conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.print("Conexão efetuada!");
		} catch (SQLException e) {
			System.out.print("Conexão com erro: " + e.getMessage());
		} catch (ClassNotFoundException c) {
			System.out.print("Conexão com erro: " + c.getMessage());
		} finally {
			return con;
		}
	}
	
	public static void desconectar() {
		try {
			BD.con.close();
			BD.con = null;
		} catch (SQLException e) {
			System.out.print("Não desconectou. " + e.getMessage());
		}
	}
}
