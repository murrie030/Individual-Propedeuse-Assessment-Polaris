package nl.hu.ipass.dao;

import java.util.*;

import nl.hu.ipass.domeinmodel.Categorie;


public interface CategorieDao {
	public List<Categorie> selectAlleCategorieen();
	
	public Categorie selectCategorie(String categoriecode);
	
	public boolean saveCategorie(Categorie categorie);
	
	public boolean updateCategorie(Categorie categorie);
	
	public boolean deleteCategorie(Categorie categorie);
}
