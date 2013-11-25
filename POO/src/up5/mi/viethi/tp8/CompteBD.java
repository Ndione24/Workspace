package up5.mi.viethi.tp8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompteBD {

	private String nomTitulaire;
	
	public CompteBD(String nomTitulaire) throws SQLException {
		this.nomTitulaire = nomTitulaire;
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
			} catch (SQLException exp) { exp.printStackTrace(); }
			// On ferme la connection et le statement dans tous les cas
			finally { BD.close(stmt); BD.close(connexion); };
		}
	}
	
	public double getDecouvertAutorise() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setDecouvertAutorise(double decouvertAutorise) throws SQLException {

	}

	public double getSolde() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isSoldeInsuffisant() throws SQLException{
		// TODO Auto-generated method stub
		return false;
		
	}

	public String getNomTitulaire() {
		return nomTitulaire;
	}

	/** rend le compte dont le titulaire est nommé 'nomTitulaire'
	Si create == true, crée le compte en base s'il n'existe pas */
	public static CompteBD getCompteByName(String nomTitulaire, boolean create) throws SQLException {
		// Création de l'instance correspondante
		if (isCompteExisteDansLaBase(nomTitulaire) || true)
			return new CompteBD(nomTitulaire);
		else 
			throw new RuntimeException("Compte inexistant " + nomTitulaire);
	}

	public static void creerLesTablesSiEllesNexistentPas() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = BD.getConnection();
		Statement stmt = connection.createStatement();
		String[] requetes = {
			"CREATE TABLE decouvert " +
			"(nom VARCHAR(25) " +
			"PRIMARY KEY,montant INTEGER not NULL)",
			
			"CREATE TABLE solde" +
		  	"(nom VARCHAR(25) " +
		  	"PRIMARY KEY,montant INTEGER not NULL)",
		  	
		  	"CREATE TABLE operation" +
			"(nom VARCHAR(25), " +
			"montant INTEGER not NULL)"		
		};
		
		try {
			for (String requete : requetes) stmt.executeUpdate(requete);
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			BD.close(stmt); BD.close(connection);
		}
	}
	
	/** enregistre une opération de 'montant' Euros sur ce compte
	* @param montant en euros de l'opération */
	private void addOperation(double montant) throws SQLException {
		Connection connection=null;
		PreparedStatement stmt=null;
		try {
			connection = BD.getConnection();
			
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			BD.close(stmt); BD.close(connection);
		}
		this.setSolde(this.getSolde() + montant);
		
	}
	
	private void setSolde(double d) {
		// TODO Auto-generated method stub
		
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
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			BD.close(stmt); BD.close(connection);
		}
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
