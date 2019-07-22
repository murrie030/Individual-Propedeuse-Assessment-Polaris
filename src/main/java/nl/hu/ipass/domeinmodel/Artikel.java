package nl.hu.ipass.domeinmodel;

//Dit is de klasse voor tabel artikel
public class Artikel {
	private int artikelnummer;
	private String artikelnaam;
	private String merk;
	private double prijs;
	private double maat;
	private String kleur;
	private String beschrijving;
	private int aantal;
	private String afbeeldingslink;
	private String afbeeldingslink2;
	private String afbeeldingslink3;
	private String afbeeldingslink4;

	// Constructor voor Artikel
	public Artikel(int artikelnummer, String artikelnaam, String merk, double prijs, double maat, String kleur,
			String beschrijving, int aantal, String afbeeldingslink, String afbeeldingslink2, String afbeeldingslink3, String afbeeldingslink4) {
		this.artikelnummer = artikelnummer;
		this.artikelnaam = artikelnaam;
		this.merk = merk;
		this.prijs = prijs;
		this.maat = maat;
		this.kleur = kleur;
		this.beschrijving = beschrijving;
		this.aantal = aantal;
		this.afbeeldingslink = afbeeldingslink;
		this.afbeeldingslink2 = afbeeldingslink2;
		this.afbeeldingslink3 = afbeeldingslink3;
		this.afbeeldingslink4 = afbeeldingslink4;
	}

	// Getters en setters
	public int getArtikelnummer() {
		return artikelnummer;
	}

	public void setArtikelnummer(int artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	public String getArtikelnaam() {
		return artikelnaam;
	}

	public void setArtikelnaam(String artikelnaam) {
		this.artikelnaam = artikelnaam;
	}

	public String getMerk() {
		return merk;
	}

	public void setMerk(String merk) {
		this.merk = merk;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public double getMaat() {
		return maat;
	}

	public void setMaat(double maat) {
		this.maat = maat;
	}

	public String getKleur() {
		return kleur;
	}

	public void setKleur(String kleur) {
		this.kleur = kleur;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	
	public String getAfbeeldingslink() {
		return afbeeldingslink;
	}
	
	public void setAfbeeldingslink(String afbeeldingslink) {
		this.afbeeldingslink = afbeeldingslink;
	}
	
	public String getAfbeeldingslink2() {
		return afbeeldingslink2;
	}
	
	public void setAfbeeldingslink2(String afbeeldingslink2) {
		this.afbeeldingslink2 = afbeeldingslink2;
	}
	
	public String getAfbeeldingslink3() {
		return afbeeldingslink3;
	}
	
	public void setAfbeeldingslink3(String afbeeldingslink3) {
		this.afbeeldingslink3 = afbeeldingslink3;
	}
	
	public String getAfbeeldingslink4() {
		return afbeeldingslink4;
	}
	
	public void setAfbeeldingslink4(String afbeeldingslink4) {
		this.afbeeldingslink4 = afbeeldingslink4;
	}
	
	public String toString() {
		String s = artikelnaam + "\nArtikelnummer" + artikelnummer + "\nKleur:" + kleur + "Merk: " + merk;
		s = s + "\nMaat: " + maat + "\nProductbeschrijving: " + beschrijving;
		return s;
	}
}
