package be.vdab.dao;

import java.util.HashMap;
import java.util.Map;

import be.vdab.entities.Soort;

public class SoortDAO extends AbstractDAO{
	public Soort read(long id){
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Soort.metLandEnWijnen"));
		return getEntityManager().find(Soort.class, id,hints);
	}

}
