package be.vdab.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.vdab.entities.Land;
public class LandDAO extends AbstractDAO {
	
	public List<Land> findAll(){
		return getEntityManager().createNamedQuery("Land.findAll", Land.class).getResultList();
	}
	
	public Land read(long id){	
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Land.metSoorten"));
		return getEntityManager().find(Land.class, id, hints);
	}

}
