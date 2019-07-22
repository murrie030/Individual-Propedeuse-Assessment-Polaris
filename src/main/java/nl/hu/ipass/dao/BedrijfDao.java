package nl.hu.ipass.dao;

import java.util.ArrayList;

import nl.hu.ipass.domeinmodel.Bedrijf;

public interface BedrijfDao {	
	public Bedrijf findBedrijfByName(String bedrijfsnaam);
	
	public ArrayList<String> bedrijfsnamen();
	
	public boolean saveBedrijf(Bedrijf bedrijf);
	
	public boolean updateBedrijf(Bedrijf bedrijf);
	
	public boolean deleteBedrijf(String bedrijfsnaam);
}
