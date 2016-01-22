package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table (name = "landen")
@NamedEntityGraph(name = "Land.metSoorten", attributeNodes = @NamedAttributeNode("soorten"))
public class Land implements Serializable{
	//members
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private String naam;
	
	@OneToMany(mappedBy = "land")
	@OrderBy("naam")
	private Set<Soort> soorten;
	
	protected Land(){
		soorten = new HashSet<Soort>();
	}
	
	public Land(String naam) {
		this();
		setNaam(naam);		
	}	

	public Land(String naam, Set<Soort> soorten) {
		this(naam);
		this.soorten = soorten;		
	}

	//getters
	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Set<Soort> getSoorten() {
		return Collections.unmodifiableSet(soorten);
	}
	
	public void addSoort(Soort soort){
		if (soort.getLand() != this){
			soort.setLand(this);
		}
		soorten.add(soort);
		
	}
	
	public void removeSoort(Soort soort){
		soorten.remove(soort);
		if (soort.getLand() == this){
			soort.setLand(null);
		}
	}
	
	// equals & hashCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Land other = (Land) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	public void setNaam(String naam) {
		if (naam.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}	
}
