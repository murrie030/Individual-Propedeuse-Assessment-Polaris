package nl.hu.ipass.serviceproviders;

import java.util.*;
import java.sql.*;

import nl.hu.ipass.dao.ArtikelDao;
import nl.hu.ipass.dao.ArtikelPostgresDaoImpl;
import nl.hu.ipass.domeinmodel.Artikel;


/*
 * De service-laag zit tussen je PostgresDaoImplentaties en je Resources
 */
public class ArtikelService {
	ArtikelDao artikelDao = new ArtikelPostgresDaoImpl();

	
	/*
	 * Hier return je alle artikelen die in de ArtikelPostgresDaoImpl zijn opgenomen
	 */
	public List<Artikel> getAlleArtikelen() {
		return artikelDao.selectAlleArtikelen();
	}

	
	public Artikel getArtikelByNumber(int artikelnummer) {
		Artikel result = null;

		for (Artikel a : artikelDao.selectAlleArtikelen()) {
			if (a.getArtikelnummer() == artikelnummer) {
				result = a;
				break;
			}
		}
		return result;
	}
	
	
	/*
	 * Deze methode dient voor het opslaan van een artikel
	 */
	public Artikel saveArtikel(int artikelnummer, String artikelnaam, String merk, double prijs, double maat, 
			String kleur, String beschrijving, int aantal, 
			String afbeeldingslink, String afbeeldingslink2, String afbeeldingslink3, String afbeeldingslink4) {
		
		Artikel a = new Artikel(artikelnummer, artikelnaam, merk, prijs, maat, kleur, beschrijving, aantal,
				afbeeldingslink, afbeeldingslink2, afbeeldingslink3, afbeeldingslink4);
		
		a.setArtikelnummer(artikelnummer);
		a.setArtikelnaam(artikelnaam);
		a.setMerk(merk);
		a.setPrijs(prijs);
		a.setMaat(maat);
		a.setKleur(kleur);
		a.setBeschrijving(beschrijving);
		a.setAantal(aantal);
		a.setAfbeeldingslink(afbeeldingslink);
		a.setAfbeeldingslink2(afbeeldingslink2);
		a.setAfbeeldingslink3(afbeeldingslink3);
		a.setAfbeeldingslink4(afbeeldingslink4);
		
		if (artikelDao.saveArtikel(a)) {
			return a;
		}
		return null;
	}
	
	
	/*
	 * Deze methode dient voor het updaten van een artikel
	 * Met a.set... zorg je ervoor dat je de gegevens wijzigt en in de if-statement
	 * update je het artikel.
	 */
	public Artikel updateArtikel(int artikelnummer, String artikelnaam, String merk, double prijs, double maat, 
			String kleur, String beschrijving, int aantal, 
			String afbeeldingslink, String afbeeldingslink2, String afbeeldingslink3, String afbeeldingslink4) throws SQLException {
		Artikel a = artikelDao.selectArtikel(artikelnummer);
		a.setArtikelnaam(artikelnaam);
		a.setMerk(merk);
		a.setPrijs(prijs);
		a.setMaat(maat);
		a.setKleur(kleur);
		a.setBeschrijving(beschrijving);
		a.setAantal(aantal);
		a.setAfbeeldingslink(afbeeldingslink);
		a.setAfbeeldingslink2(afbeeldingslink2);
		a.setAfbeeldingslink3(afbeeldingslink3);
		a.setAfbeeldingslink4(afbeeldingslink4);
		
		if (artikelDao.updateArtikel(a)) {
			return artikelDao.selectArtikel(artikelnummer);
		}
		return a;
	}
	
	
	/*
	 * Deze methode dient voor het verwijderen van een artikel.
	 * De boolean waarde is false wanneer een artikel niet is verwijderd.
	 * Deze veranderd in true zodra de if-statement met succes is uitgevoerd.
	 */
	public boolean deleteArtikel(int artikelnummer) {
		boolean verwijderd = false;
		
		Artikel a = artikelDao.selectArtikel(artikelnummer);
		if (a != null) {
			verwijderd = artikelDao.deleteArtikel(a);
		} else {
			throw new IllegalArgumentException("Artikelnummer bestaat niet");
		}
		return verwijderd;
	}
}