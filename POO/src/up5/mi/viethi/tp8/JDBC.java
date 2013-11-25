package up5.mi.viethi.tp8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	public static Connection getConnection() throws SQLException, IOException {
		String nomBase="BDif00538";
		String hostname="opale.ens.math-info.univ-paris5.fr";
		String urlBd = "jdbc:postgresql://"+hostname+"/"+nomBase;
		String user="if00538", password=getStringFromFile(new File("pass.txt"));
		return DriverManager.getConnection(urlBd,user,password );
	}
	
//	public static Connection getConnection() throws SQLException, IOException {
//		String nomBase = "melkir";
//		String hostname = "localhost";
//		String urlBd = "jdbc:mysql://" + hostname + "/" + nomBase;
//		String user = "root", password = getStringFromFile(new File("pass.txt"));
//		return DriverManager.getConnection(urlBd, user, password);
//	}

	public static String getStringFromFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		int value = 0;
		String res = "";
		while ((value = br.read()) != -1)
			res += (char) value;
		br.close();
		return res;
	}

	private static void createIfNotExistsTableQuestion(Connection connection)
			throws SQLException {
		Statement statement = connection.createStatement();
		String request = "CREATE TABLE question"
				+ " (intitule VARCHAR(80),reponse VARCHAR(80),"
				+ "auteur VARCHAR(80),niveau INTEGER not NULL)";
		try {
			statement.executeUpdate(request);
		} catch (SQLException ignored) {
			System.out.println(request);
			ignored.printStackTrace();
		} finally {
			if (statement != null)
				statement.close();
		}
	}

	public static void ajouterQuestion(Connection connection) throws SQLException {
		// tout d'abord, récupération d'un Statement à partir de la connexion
		Statement stmt = connection.createStatement();
		// puis exécution de la requête
		int count = stmt.executeUpdate(
				"INSERT INTO question(intitule,reponse,auteur,niveau)" +
				"VALUES (\'Les classes sont-elles toujours abstraites\',\'non\',\'pary\',1)");
		System.out.println("Add query : " + count);
	}

	public static void listerQuestion(Connection connection) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT intitule,reponse FROM question");
		System.out.println("Contenu de la table : ");
		while (rs.next()) {
			Object obj1 = rs.getObject(1);// l'intitulé
			Object obj2 = rs.getObject(2);// la réponse
			System.out.println("(" + obj1 + "," + obj2 + ")");
		}
	}

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = getConnection();
			System.out.println("Connexion réalisée !");
			//createIfNotExistsTableQuestion(connection);
			ajouterQuestion(connection);
			listerQuestion(connection);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Connexion non effectuée !");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out
					.println("Vérifier que la classe du driver est dans le classpath");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}