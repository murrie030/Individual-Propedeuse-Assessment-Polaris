package nl.hu.ipass.dao;

import java.sql.*;
import java.util.*;

import nl.hu.ipass.domeinmodel.Artikel;

/*
 * Hier komen de SQL-statements van de tabel artikel
 */
public class ArtikelPostgresDaoImpl extends PostgresBaseDao implements ArtikelDao {
	
	// Methode voor het selecteren van meerdere artikelen
	@Override
	public List<Artikel> selectAlleArtikelen() {
		List<Artikel> alleArtikelen = new ArrayList<Artikel>();
		try (Connection conn = super.getConnection()) {
			String query = "SELECT * FROM artikel;";
			Statement stmt = conn.createStatement(); 		  		// Command-channel naar PostgreSQL-server
			ResultSet dbResultSet = stmt.executeQuery(query);		// Ophalen resultaten en versturen SQL-commando

			while (dbResultSet.next()) {
				int artikelnummer 			= dbResultSet.getInt("artikelnummer");
				String artikelnaam 			= dbResultSet.getString("artikelnaam");
				String merk 				= dbResultSet.getString("merk");
				double prijs 				= dbResultSet.getDouble("prijs");
				double maat 				= dbResultSet.getDouble("maat");
				String kleur 				= dbResultSet.getString("kleur");
				String beschrijving 		= dbResultSet.getString("beschrijving");
				int aantal 					= dbResultSet.getInt("aantal");
				String afbeeldingslink 		= dbResultSet.getString("afbeeldingslink");
				String afbeeldingslink2 	= dbResultSet.getString("afbeeldingslink2");
				String afbeeldingslink3 	= dbResultSet.getString("afbeeldingslink3");
				String afbeeldingslink4 	= dbResultSet.getString("afbeeldingslink4");

				// Eager-loading. Toevoegen van Artikel
				Artikel newArtikel = new Artikel(artikelnummer, artikelnaam, merk, prijs, maat, kleur, 
						beschrijving, aantal, afbeeldingslink, afbeeldingslink2, afbeeldingslink3, afbeeldingslink4);
				alleArtikelen.add(newArtikel);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return alleArtikelen;
	}
	
	
	// Methode voor het selecteren van slechts een enkele artikel
	@Override
	public Artikel selectArtikel(int artikelnummer) {
		Artikel newArtikel = null;
		try (Connection conn = super.getConnection()) {
			String query = "SELECT * FROM artikel WHERE artikelnummer = ?;";
			PreparedStatement pstmt = conn.prepareStatement(query); 		// Command-channel naar PostgreSQL-server
			pstmt.setInt(1, artikelnummer);
			ResultSet dbResultSet = pstmt.executeQuery();					// Ophalen resultaten en versturen SQL-commando

			while (dbResultSet.next()) {
				artikelnummer 				= dbResultSet.getInt("artikelnummer");
				String artikelnaam 			= dbResultSet.getString("artikelnaam");
				String merk 				= dbResultSet.getString("merk");
				double prijs 				= dbResultSet.getDouble("prijs");
				double maat 				= dbResultSet.getDouble("maat");
				String kleur 				= dbResultSet.getString("kleur");
				String beschrijving 		= dbResultSet.getString("beschrijving");
				int aantal 					= dbResultSet.getInt("aantal");
				String afbeeldingslink 		= dbResultSet.getString("afbeeldingslink");
				String afbeeldingslink2 	= dbResultSet.getString("afbeeldingslink2");
				String afbeeldingslink3 	= dbResultSet.getString("afbeeldingslink3");
				String afbeeldingslink4 	= dbResultSet.getString("afbeeldingslink4");
				
				// Eager-loading. Toevoegen van Artikel
				newArtikel = new Artikel(artikelnummer, artikelnaam, merk, prijs, maat, kleur, 
						beschrijving, aantal, afbeeldingslink, afbeeldingslink2, afbeeldingslink3, afbeeldingslink4);
			}
			System.out.println(newArtikel);
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return newArtikel;
	}

	
	// Methode voor het opslaan van een artikel
	@Override
	public boolean saveArtikel(Artikel artikel) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "INSERT INTO artikel(artikelnummer, artikelnaam, merk, prijs, maat,"
					+ " kleur, beschrijving, aantal) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);

			pstmt.setInt(1, artikel.getArtikelnummer());
			pstmt.setString(2, artikel.getArtikelnaam());
			pstmt.setString(3, artikel.getMerk());
			pstmt.setDouble(4, artikel.getPrijs());
			pstmt.setDouble(5, artikel.getMaat());
			pstmt.setString(6, artikel.getKleur());
			pstmt.setString(7, artikel.getBeschrijving());
			pstmt.setInt(8, artikel.getAantal());
			pstmt.setString(9, artikel.getAfbeeldingslink());
			pstmt.setString(10, artikel.getAfbeeldingslink2());
			pstmt.setString(11, artikel.getAfbeeldingslink3());
			pstmt.setString(12, artikel.getAfbeeldingslink4());
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	
	// Methode voor het updaten van een artikel
	@Override
	public boolean updateArtikel(Artikel artikel) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "UPDATE artikel SET artikelnummer = ?, artikelnaam = ?, merk = ?, prijs = ?, "
					+ "maat = ?, kleur = ?, beschrijving = ?, aantal = ?, "
					+ "afbeeldingslink = ?, afbeeldingslink2 = ?, afbeeldingslink3 = ?, afbeeldingslink4 = ?"
					+ " WHERE artikelnummer = ?;";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);

			pstmt.setInt(1, artikel.getArtikelnummer());
			pstmt.setString(2, artikel.getArtikelnaam());
			pstmt.setString(3, artikel.getMerk());
			pstmt.setDouble(4, artikel.getPrijs());
			pstmt.setDouble(5, artikel.getMaat());
			pstmt.setString(6, artikel.getKleur());
			pstmt.setString(7, artikel.getBeschrijving());
			pstmt.setInt(8, artikel.getAantal());
			pstmt.setString(9, artikel.getAfbeeldingslink());
			pstmt.setString(10, artikel.getAfbeeldingslink2());
			pstmt.setString(11, artikel.getAfbeeldingslink3());
			pstmt.setString(12, artikel.getAfbeeldingslink4());
			pstmt.setInt(13, artikel.getArtikelnummer());
			pstmt.executeUpdate();
			
			System.out.println("\n" + artikel.getArtikelnummer() + " is geüpdate.");
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	
	// Methode voor het verwijderen van een artikel
	@Override
	public boolean deleteArtikel(Artikel artikel) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "DELETE FROM artikel WHERE artikelnummer = ?;";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, artikel.getArtikelnummer());
			pstmt.executeUpdate();

			System.out.println("\n" + artikel.getArtikelnummer() + " is verwijderd.");
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
}
