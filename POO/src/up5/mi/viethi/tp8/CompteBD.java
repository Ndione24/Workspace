package up5.mi.viethi.tp8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompteBD {

	private String nomTitulaire;
	
	public CompteBD(String nomTitulaire) throws SQLException {
		
		this.nomTitulaire = nomTitulaire;
		// Si le compte n'existe pas on le créer
		if (!isCompteExisteDansLaBase(nomTitulaire)) {
			Connection connexion = null;
			Statement stmt = null;
			// L'initialisation consiste à mettre le solde et le découvert à zéro.
			String[] requetes  = {
				"INSERT INTO solde(nom,montant) VALUES('" + nomTitulaire + "', 0)",
				"INSERT INTO decouvert(nom,montant) VALUES('" + nomTitulaire + "', 0)"
			};
			try {
				connexion = BD.getConnection();
				for (String requete : requetes) stmt.executeUpdate(requete);
			} // On ferme la connection et le statement dans tous les cas
			finally { BD.close(stmt); BD.close(connexion); };
		}
	}
	
	public double getDecouvertAutorise() throws SQLException {
		double decouvert;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = BD.getConnection();
			statement = connection.prepareStatement(
				"SELECT montant FROM decouvert WHERE nom=?");
			statement.setString(1, this.nomTitulaire);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) decouvert= rs.getInt(1)/100.0;
			else throw new RuntimeException("Compte inexistant: " + this.nomTitulaire);
		} finally {BD.close(statement); BD.close(connection);}
		return decouvert;
	}
	
	public void setDecouvertAutorise(double decouvertAutorise) throws SQLException {
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = BD.getConnection();
			statement = connection.prepareStatement(
				"UPDATE decouvert SET montant=? WHERE nom=?");
			statement.setInt(1,(int)decouvertAutorise*100);
			statement.setString(2, this.nomTitulaire);
			statement.executeUpdate();
		} finally { BD.close(statement); BD.close(connection); }
	}
	
	public String getHistorique() throws SQLException{
		ArrayList histo = new ArrayList( );
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = BD.getConnection();
			statement = connection.prepareStatement(
				"SELECT montant FROM operation WHERE nom =?" );
			statement.setString(1, this.nomTitulaire);
			ResultSet rs = statement.executeQuery( );
			while (rs.next()) {
				double montant=rs.getInt(1)/100.0;
				histo.add(new Double(montant));
			}
		} finally { BD.close(statement); BD.close(connection); }
		return(histo.toString());
	}
	
	public String toString(){ return("Compte de "+this.nomTitulaire); }

	public double getSolde() throws SQLException {
		double solde;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = BD.getConnection();
			statement = connection.prepareStatement(
				"SELECT montant FROM solde WHERE nom=?");
			statement.setString(1, this.nomTitulaire);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) solde= rs.getInt(1)/100.0;
			else throw new RuntimeException("Compte inexistant: " + this.nomTitulaire);
		} finally {BD.close(statement); BD.close(connection);}
		return solde;
	}
	
	public boolean isSoldeInsuffisant() throws SQLException{
		return this.getSolde( ) < - this.getDecouvertAutorise();
	}

	public String getNomTitulaire() { return nomTitulaire; }

	/** rend le compte dont le titulaire est nommé 'nomTitulaire'
	Si create == true, crée le compte en base s'il n'existe pas */
	public static CompteBD getCompteByName(String nomTitulaire, boolean create) throws SQLException {
		// Création de l'instance correspondante
		if (isCompteExisteDansLaBase(nomTitulaire) || true) 
			return new CompteBD(nomTitulaire);
		else throw new RuntimeException("Compte inexistant " + nomTitulaire);
	}
	
	/** précise les informations de connexion à la base et crée si besoin les tables */
	public static void creerLesTablesSiEllesNexistentPas() throws SQLException {
		Connection connection = BD.getConnection();
		Statement stmt = connection.createStatement();
		try { 
			// création des 3 tables,si elles existaient déjà, une SQLException sera alors lancée
			// par jdbc. On attrape l'erreur dans le bloc catch.
			String[] requetes = {
				"CREATE TABLE decouvert " +
						"(nom VARCHAR(25) " +
						"PRIMARY KEY,montant INTEGER not NULL)",
				
				"CREATE TABLE solde" +
						"(nom VARCHAR(25) " +
						"PRIMARY KEY,montant INTEGER not NULL)",
						
				"CREATE TABLE operation" +
						"(nom VARCHAR(25)," +
						"montant INTEGER not NULL)"		
			};
			
			for (String requete : requetes) stmt.executeUpdate(requete);
		} catch (SQLException exp) { 
			exp.printStackTrace(); 
		} finally { BD.close(stmt); BD.close(connection); }
	}
	
	/** enregistre une opération de 'montant' Euros sur ce compte
	* @param montant en euros de l'opération */
	private void addOperation(double montant) throws SQLException {
		Connection connection=null;
		PreparedStatement stmt=null;
		try {
			connection = BD.getConnection();
			stmt = connection.prepareStatement(
				"INSERT INTO operation(nom,montant) VALUES (?,?)");
			stmt.setString(1, this.nomTitulaire);
			stmt.setInt(2,(int)montant*100);
			stmt.executeUpdate();
		} finally { BD.close(stmt); BD.close(connection); }
		this.setSolde(this.getSolde() + montant);
	}
	
	private void setSolde(double solde) throws SQLException {
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection = BD.getConnection();
			statement = connection.prepareStatement(
				"UPDATE solde SET montant=? WHERE nom=?");
			statement.setInt(1,(int)solde*100);
			statement.setString(2, this.nomTitulaire);
			statement.executeUpdate();
		} finally { BD.close(statement); BD.close(connection); }
	}

	// Teste si le compte à 'nomTitulaire' dans la base
	private static boolean isCompteExisteDansLaBase(String nomTitulaire) throws SQLException{
		boolean existe=false;
		Connection connection=null;
		PreparedStatement stmt=null;
		try {
			connection = BD.getConnection();
			stmt = connection.prepareStatement("SELECT montant FROM solde WHERE nom=?");
			stmt.setString(1, nomTitulaire);
			// le critère d'existence est : y a-t-il un solde pour ce compte ?
			ResultSet rs = stmt.executeQuery();
			existe = rs.next();
		} finally { BD.close(stmt); BD.close(connection); }
		return existe;
	}
	
	public static void main(String[] args) throws Exception {
		// creation des tables à la première exécution
		CompteBD.creerLesTablesSiEllesNexistentPas( );
		// on récupère les comptes de Dupond et Durand
		CompteBD c1=CompteBD.getCompteByName("Dupond",true);
		CompteBD c2=CompteBD.getCompteByName("Durand",true);
		// on effectue des opérations et on affiche des infos
		// (il faudrait un menu à la place ...)
		c1.addOperation(120);
		c2.addOperation(210);
		System.out.println(c1.getNomTitulaire( )+" "+c1.getSolde( )+
		" "+c1.getDecouvertAutorise( ));
		System.out.println(c2.getNomTitulaire( )+" "+c2.getSolde( )+
		" "+c2.getDecouvertAutorise( ));
	}
	
}
