package be.vdab.services;

import java.util.List;

import be.vdab.dao.LandDAO;
import be.vdab.entities.Land;

public class LandService {
	private final LandDAO landDAO = new LandDAO();
	
	public List<Land> findAll(){
		return landDAO.findAll();
	}
	
	public Land read(long id){
		return landDAO.read(id);
	}

}
