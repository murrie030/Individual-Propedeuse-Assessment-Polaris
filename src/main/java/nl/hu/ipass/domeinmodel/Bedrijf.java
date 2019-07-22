package nl.hu.ipass.domeinmodel;

import java.util.ArrayList;

// Dit is de klasse voor tabel bedrijf
public class Bedrijf {
	private String bedrijfsnaam;
	private String adres;
	private String postcode;
	private String plaats;
	private ArrayList<Artikel> alleArtikelen; 	// extra attribuut met als type ArrayList met elementen van het type van
												// de niet-verantwoordelijke klasse.

	// Constructor voor Bedrijf
	public Bedrijf(String bedrijfsnaam, String adres, String postcode, String plaats) {
		this.bedrijfsnaam = bedrijfsnaam;
		this.adres = adres;
		this.postcode = postcode;
		this.plaats = plaats;
		alleArtikelen = new ArrayList<Artikel>(); 	// creëer object van het type ArrayList met elementen van het type van
													// de niet-verantwoordelijke klasse
	}

	// Extra getter om ArrayList op te kunnen vragen
	public ArrayList<Artikel> getArtikelen() {
		return alleArtikelen;
	}

	// Extra methode om een element aan ArrayList toe te voegen
	public void voegArtikelToe(Artikel art) {
		if (!this.alleArtikelen.contains(art)) {
			this.alleArtikelen.add(art);
			System.out.println("Artikel is toegevoegd!");
		}
	}

	// Getters en setters
	public String getBedrijfnaam() {
		return bedrijfsnaam;
	}

	public void setBedrijfsnaam(String bedrijfsnaam) {
		this.bedrijfsnaam = bedrijfsnaam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}
	
	public String toString() {
		String s = "Het bedrijf heet: " + bedrijfsnaam;
		s = s +	". Het adres is: " + adres + " postcode: " + postcode + " te " + plaats;
		return s;
	}
}
