package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "soorten")
@NamedEntityGraph(name = "Soort.metLandEnWijnen", attributeNodes = {
		@NamedAttributeNode(value = "land", subgraph = "metSoorten"),
		@NamedAttributeNode(value = "wijnen")},
		subgraphs = @NamedSubgraph(name = "metSoorten", attributeNodes = @NamedAttributeNode("soorten")))	
public class Soort implements Serializable {
	// members
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private String naam;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "landid")
	private Land land;
	
	@OneToMany(mappedBy = "soort")
	@OrderBy("jaar")
	private Set<Wijn> wijnen;	

	public Soort(){
		wijnen = new HashSet<Wijn>();
	}
	
	public Soort(String naam){
		this();
		setNaam(naam);
	}
	
	protected Soort(Land land){
		this();
		this.land = land;
	}
	
	public Soort(String naam, Land land) {
		this(naam);
		this.land = land;
	}
	
	// getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}	

	public Land getLand() {
		return land;
	}	
	
	public Set<Wijn> getWijnen() {
		return Collections.unmodifiableSet(wijnen);
	}
	
	//setters
	
	public void setNaam(String naam) {
		if (naam.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}
	
	public void setLand(Land land) {
		if (this.land != null && this.land.getSoorten().contains(this)){
			this.land.removeSoort(this);
		}
		this.land = land;
		if (land != null && !land.getSoorten().contains(this)){
			land.addSoort(this);
		}
	}
	
	public void addWijn(Wijn wijn){
		if (wijn.getSoort() != this){
			wijn.setSoort(this);
		}
		wijnen.add(wijn);		
	}
	
	public void removeWijn(Wijn wijn){
		wijnen.remove(wijn);
		if (wijn.getSoort() == this){
			wijn.setSoort(null);
		}
	}


	// equals & hashCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((land == null) ? 0 : land.hashCode());
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
		Soort other = (Soort) obj;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;					
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
}
