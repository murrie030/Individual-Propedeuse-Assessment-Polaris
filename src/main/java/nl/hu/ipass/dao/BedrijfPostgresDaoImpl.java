package nl.hu.ipass.dao;

import java.sql.*;
import java.util.ArrayList;

import nl.hu.ipass.domeinmodel.Bedrijf;

/*
 * Hier komen de SQL-statemens van de tabel bedrijf
 */
public class BedrijfPostgresDaoImpl extends PostgresBaseDao implements BedrijfDao {
	
	// Methode voor het selecteren van het bedrijf
	@Override
	public Bedrijf findBedrijfByName(String bedrijfsnaam) {
		Bedrijf newBedrijf = null;
		try (Connection conn = super.getConnection()) {
			String strQuery = "SELECT * FROM bedrijf WHERE bedrijfsnaam = ?;";
			PreparedStatement pstmt = conn.prepareStatement(strQuery); 		// Command-channel naar PostgreSQL-server
			pstmt.setString(1, bedrijfsnaam);
			ResultSet dbResultSet = pstmt.executeQuery();					// Ophalen resultaten en versturen SQL-commando
			
			while (dbResultSet.next()) {
				bedrijfsnaam 	= dbResultSet.getString("bedrijfsnaam");
				String adres	= dbResultSet.getString("adres");
				String postcode = dbResultSet.getString("postcode");
				String plaats 	= dbResultSet.getString("plaats");
				
				// Eager-loading. Toevoegen van Bedrijf
				newBedrijf = new Bedrijf(bedrijfsnaam, adres, postcode, plaats);
			}
			
			System.out.println(newBedrijf);
		} catch (SQLException sqle ) {
			sqle.printStackTrace();
		}
		return newBedrijf;
	}
	
	// Methode voor het selecteren van het bedrijf
		@Override
		public ArrayList<String> bedrijfsnamen() {
			ArrayList<String> bedrijven = new ArrayList<String>();
			
			try (Connection conn = super.getConnection()) {
				String strQuery = "SELECT bedrijfsnaam FROM bedrijf;";
				PreparedStatement pstmt = conn.prepareStatement(strQuery); 		// Command-channel naar PostgreSQL-server
				ResultSet dbResultSet = pstmt.executeQuery();					// Ophalen resultaten en versturen SQL-commando
				
				while (dbResultSet.next()) {
					String bedrijfsnaam = dbResultSet.getString("bedrijfsnaam");
					
					// Toevoegen van bedrijven aan de ArrayList
					bedrijven.add(bedrijfsnaam);
				}
				
			} catch (SQLException sqle ) {
				sqle.printStackTrace();
			}
			return bedrijven;
		}
	
	
	// Methode voor het opslaan van het bedrijf
	@Override
	public boolean saveBedrijf(Bedrijf bedrijf) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "INSERT INTO bedrijf(bedrijfsnaam, adres, postcode, plaats) "
							+ "VALUES(?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, bedrijf.getBedrijfnaam());
			pstmt.setString(2, bedrijf.getAdres());
			pstmt.setString(3, bedrijf.getPostcode());
			pstmt.setString(4, bedrijf.getPlaats());
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	
	// Methode voor het updaten van het bedrijf
	@Override
	public boolean updateBedrijf(Bedrijf bedrijf) {
		try (Connection conn = super.getConnection()) {
			String strQuery = "UPDATE bedrijf SET adres = ?, postcode = ?, plaats = ? "
							+ "WHERE bedrijfsnaam = ?;";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			
			pstmt.setString(1, bedrijf.getAdres());
			pstmt.setString(2, bedrijf.getPostcode());
			pstmt.setString(3, bedrijf.getPlaats());
			pstmt.setString(4, bedrijf.getBedrijfnaam());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	
	// Methode voor het verwijderen van het bedrijf
	@Override
	public boolean deleteBedrijf(String bedrijfsnaam) {
		try (Connection conn = super.getConnection() ){
			String strQuery = "DELETE FROM bedrijf WHERE bedrijfsnaam = ?;";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, bedrijfsnaam);
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
}
