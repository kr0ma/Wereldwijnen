package be.vdab.dao;

import be.vdab.entities.Bestelbon;


public class BestelbonDAO extends AbstractDAO {
	public void create(Bestelbon bestelbon){
		getEntityManager().persist(bestelbon);
	}

}
