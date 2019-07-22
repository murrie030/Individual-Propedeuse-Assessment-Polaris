package nl.hu.ipass.dao;

import java.sql.*;
import java.util.*;

import nl.hu.ipass.domeinmodel.Categorie;

/*
 * Hier komen de SQL-statemens van de tabel categorie
 */
public class CategoriePostgresDaoImpl extends PostgresBaseDao implements CategorieDao {
	
	// Methode voor het selecteren van meerdere categorieën
	public List<Categorie> selectAlleCategorieen() {
		List<Categorie> alleCategorieen = new ArrayList<Categorie>();
		try (Connection conn = super.getConnection()) {
			String query = "SELECT * FROM categorie;";
			Statement stmt = conn.createStatement(); 		  		// Command-channel naar PostgreSQL-server
			ResultSet dbResultSet = stmt.executeQuery(query);		// Ophalen resultaten en versturen SQL-commando
			
			while (dbResultSet.next()) { 
				String categoriecode = dbResultSet.getString("categoriecode");
				String categorienaam = dbResultSet.getString("categorienaam");
				String subcategorie  = dbResultSet.getString("subcategorie");
				
				// Eager-loading. Toevoegen van Categorie
				Categorie newCategorie = new Categorie(categoriecode, categorienaam, subcategorie);
				alleCategorieen.add(newCategorie);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return alleCategorieen;
	}
	
	
	// Methode voor het selecteren van slechts een enkele categorie
	@Override
	public Categorie selectCategorie(String categoriecode) {
		Categorie newCategorie = null;
		try (Connection conn = super.getConnection()) {
			String query = "SELECT * FROM categorie WHERE categoriecode = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query); 		// Command-channel naar PostgreSQL-server
			pstmt.setString(1, categoriecode);
			ResultSet dbResultSet = pstmt.executeQuery();					// Ophalen resultaten en versturen SQL-commando
			
			while (dbResultSet.next()) {
				categoriecode 			= dbResultSet.getString("categoriecode");
				String categorienaam 	= dbResultSet.getString("categorienaam");
				String subcategorie 	= dbResultSet.getString("subcategorie");
				
				// Eager-loading. Toevoegen van Categorie
				newCategorie = new Categorie(categoriecode, categorienaam, subcategorie);
			}
			
			System.out.println(newCategorie);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return newCategorie;
	}
	
	
	// Methode voor het opslaan van een categorie
	@Override
	public boolean saveCategorie(Categorie categorie) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "INSERT INTO categorie(categoriecode, categorienaam, subcategorie)"
							+ "VALUES(?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, categorie.getCategoriecode());
			pstmt.setString(2, categorie.getCategorienaam());
			pstmt.setString(3, categorie.getSubcategorie());
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	
	// Methode voor het updaten van een categorie
	@Override
	public boolean updateCategorie(Categorie categorie) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "UPDATE categorie SET categoriecode = ?, categorienaam = ?, subcategorie = ?;";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, categorie.getCategoriecode());
			pstmt.setString(2, categorie.getCategorienaam());
			pstmt.setString(3, categorie.getSubcategorie());
			pstmt.executeUpdate();

			return true; 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	
	// Methode voor het verwijderen van een categorie
	@Override
	public boolean deleteCategorie(Categorie categorie) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "DELETE FROM categorie WHERE categoriecode = ?;";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, categorie.getCategoriecode());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
}
