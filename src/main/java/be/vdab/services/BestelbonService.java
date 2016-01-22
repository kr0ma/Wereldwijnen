package be.vdab.services;

import java.util.List;
import java.util.Map;

import javax.persistence.RollbackException;

import be.vdab.dao.BestelbonDAO;
import be.vdab.dao.WijnDAO;
import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;
import be.vdab.valueobjects.Bestelbonlijn;

public class BestelbonService {
	private final BestelbonDAO bestelbonDAO = new BestelbonDAO();
	private final WijnDAO wijnDAO = new WijnDAO();
	
	public void create(Bestelbon bestelbon, Map<Long, Integer> mandje){
		bestelbonDAO.beginTransaction();		
		List<Wijn> wijnInMandje = wijnDAO.findByIDSWithLock(mandje.keySet());		
		for (Wijn wijn : wijnInMandje){
			bestelbon.addBestelbonlijn(new Bestelbonlijn(wijn, mandje.get(wijn.getId())));
		}
		bestelbonDAO.create(bestelbon);
		try {
			bestelbonDAO.commit();
		} catch (RollbackException ex){
			bestelbonDAO.rollback();
			throw new RollbackException();
		}
		
	}
}
