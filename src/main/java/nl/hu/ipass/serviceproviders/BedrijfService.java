package nl.hu.ipass.serviceproviders;

import java.util.ArrayList;

import nl.hu.ipass.dao.BedrijfDao;
import nl.hu.ipass.dao.BedrijfPostgresDaoImpl;
import nl.hu.ipass.domeinmodel.Bedrijf;

/*
 * De service-laag zit tussen je PostgresDaoImplentaties en je Resources
 */
public class BedrijfService {
	BedrijfDao bedrijfDao = new BedrijfPostgresDaoImpl();

	
	/*
	 * Hier return je het bedrijf die in de BedrijfPostgresDaoImpl is opgenomen.
	 * Result wordt eerst null, maar zodra Bedrijf b is aangemaakt zet je het resultaat van
	 * findBedrijfByname in het resultaat.
	 */
	public Bedrijf findBedrijfByName(String bedrijfsnaam) {
		Bedrijf result = null;

		Bedrijf b = bedrijfDao.findBedrijfByName(bedrijfsnaam);
		result = b;

		return result;
	}
	
	
	/*
	 * Hier return je de bedrijfsnamen.
	 */
	public ArrayList<String> bedrijfsnamen() {
		ArrayList<String> result = null;

		ArrayList<String> bn = bedrijfDao.bedrijfsnamen();
		result = bn;

		return result;
	}

	
	/*
	 * Deze methode dient voor het opslaan van een bedrijf
	 */
	public boolean saveBedrijf(String bedrijfsnaam, String adres, String postcode, String plaats) {
		Bedrijf b = new Bedrijf(bedrijfsnaam, adres, postcode, plaats);

		if (bedrijfDao.saveBedrijf(b)) {
			return true;
		}
		return false;
	}

	
	/*
	 * Deze methode dient voor het updaten van het bedrijf
	 */
	public boolean updateBedrijf(String bedrijfsnaam, String adres, String postcode, String plaats) {
		Bedrijf b = new Bedrijf(bedrijfsnaam, adres, postcode, plaats);

		if (bedrijfDao.updateBedrijf(b)) {
			return true;
		}
		return false;
	}

	
	/*
	 * Deze methode dient voor het verwijderen van het bedrijf. De boolean waarde is
	 * false wanneer het bedrijf niet is verwijderd. Deze veranderd in true zodra de
	 * if-statement met succes is uitgevoerd.
	 */
	public boolean deleteBedrijf(String bedrijfsnaam) {
		boolean verwijderd = false;

		verwijderd = bedrijfDao.deleteBedrijf(bedrijfsnaam);

		return verwijderd;
	}
}
