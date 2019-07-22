package nl.hu.ipass.dao;

import java.util.*;

import nl.hu.ipass.domeinmodel.Artikel;

public interface ArtikelDao {
	public List<Artikel> selectAlleArtikelen();

	public Artikel selectArtikel(int artikelnummer);

	public boolean saveArtikel(Artikel artikel);

	public boolean updateArtikel(Artikel artikel);

	public boolean deleteArtikel(Artikel artikel);
}
