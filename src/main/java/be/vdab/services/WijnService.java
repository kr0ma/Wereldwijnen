package be.vdab.services;

import java.util.List;
import java.util.Set;

import be.vdab.dao.WijnDAO;
import be.vdab.entities.Wijn;

public class WijnService {
	private final WijnDAO wijnDAO = new WijnDAO();
	
	public Wijn read (long id){
		return wijnDAO.read(id);
	}
	
	public List<Wijn> findByIDS(Set<Long> ids){
		return wijnDAO.findByIDS(ids);
	}
	
	public List<Wijn> findByIDSWithLock(Set<Long> ids){
		return wijnDAO.findByIDSWithLock(ids);
	}
}
