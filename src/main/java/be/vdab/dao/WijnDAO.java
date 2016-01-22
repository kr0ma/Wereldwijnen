package be.vdab.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.LockModeType;

import be.vdab.entities.Wijn;

public class WijnDAO extends AbstractDAO {
	public Wijn read(long id) {
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.loadgraph", getEntityManager()
				.createEntityGraph(Wijn.WIJN_MET_SOORT));
		return getEntityManager().find(Wijn.class, id, hints);
	}

	public List<Wijn> findByIDSWithLock(Set<Long> ids) {
		List<Wijn> wijnen = getEntityManager()
				.createNamedQuery("Wijn.findByIDSWithSoortAndCountry", Wijn.class)
				.setParameter("ids", ids).getResultList();
		for (Wijn wijn : wijnen) {
			getEntityManager().lock(wijn, LockModeType.PESSIMISTIC_READ);
		}
		return wijnen;
	}

	public List<Wijn> findByIDS(Set<Long> ids) {
		return getEntityManager()
				.createNamedQuery("Wijn.findByIDSWithSoortAndCountry", Wijn.class)
				.setParameter("ids", ids).getResultList();
	}
}
