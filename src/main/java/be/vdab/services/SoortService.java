package be.vdab.services;

import be.vdab.dao.SoortDAO;
import be.vdab.entities.Soort;

public class SoortService {
	private final SoortDAO soortDAO = new SoortDAO();
	
	public Soort read(long id){
		return soortDAO.read(id);
	}
}
