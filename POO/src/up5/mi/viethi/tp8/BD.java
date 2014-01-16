package up5.mi.viethi.tp8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class BD {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** rend une connection à la base de données
	 * @throws IOException */
	public static Connection getConnection() throws SQLException {
		String nomBase="BDif00538";
		String hostname="opale.ens.math-info.univ-paris5.fr";
		String urlBd = "jdbc:postgresql://"+hostname+"/"+nomBase;
		String user="if00538", password="viethi92";
		return DriverManager.getConnection(urlBd,user,password);
	}

	/** pour fermer d'un coup le statement */
	public static void close(Statement statement) throws SQLException {
		if (statement != null)
			statement.close();
	}

	/** pour fermer d'un coup la connexion */
	public static void close(Connection connection) throws SQLException {
		if (connection != null)
			connection.close();
	}

}
