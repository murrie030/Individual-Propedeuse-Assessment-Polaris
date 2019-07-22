package nl.hu.ipass.domeinmodel;

import java.util.ArrayList;

// Dit is de klasse voor tabel categorie
public class Categorie {
	private String categoriecode;
	private String categorienaam;
	private String subcategorie;
	private ArrayList<Artikel> alleArtikelen; // extra attribuut met als type ArrayList met elementen van het type van
											  // de niet-verantwoordelijke klasse.

	// Constructor voor Categorie
	public Categorie(String categoriecode, String categorienaam, String subcategorie) {
		this.categoriecode = categoriecode;
		this.categorienaam = categorienaam;
		this.subcategorie = subcategorie;
		alleArtikelen = new ArrayList<Artikel>(); // creëer object van het type ArrayList met elementen van het type van
												  // de niet-verantwoordelijke klasse
	}
	
	// Extra getter om ArrayList op te kunnen vragen
	public ArrayList<Artikel> alleArtikelen() {
		return alleArtikelen;
	}
	
	// Extra methode om een element aan ArrayList toe te voegen
	public void voegArtikeltoe(Artikel art) {
		if (!this.alleArtikelen.contains(art)) {
			this.alleArtikelen.add(art);
			System.out.println("Artikel is toegevoegd!");
		}
	}
	
	// Getters en setters
	public String getCategoriecode() {
		return categoriecode;
	}
	
	public void setCategoriecode(String categoriecode) {
		this.categoriecode = categoriecode;
	}
	
	public String getCategorienaam() {
		return categorienaam;
	}
	
	public void setCategorienaam(String categorienaam) {
		this.categorienaam = categorienaam;
	}
	
	public String getSubcategorie() {
		return subcategorie;
	}
	
	public void setSubcategorie(String subcategorie) {
		this.subcategorie = subcategorie;
	}
	
	public String toString() {
		String s = "Categoriecode " + categoriecode + " behoort tot de categorie " + categorienaam;
		if (subcategorie == null) {
			s = s + " en heeft geen subcategorie. ";
		} else {
			s = s + " en behoort tot de subcategorie " + subcategorie;
		}
		return s;
	}
}
