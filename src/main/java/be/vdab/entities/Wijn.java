package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

@Entity
@Table(name = "wijnen")

@NamedEntityGraphs({ 
	@NamedEntityGraph(name = Wijn.WIJN_MET_SOORT, attributeNodes = @NamedAttributeNode("soort") ),
	
	@NamedEntityGraph(name = "Wijn.metSoortEnLand", 
	attributeNodes = @NamedAttributeNode(value = "soort", subgraph = "metLand") , 
	subgraphs = @NamedSubgraph(name = "metLand", attributeNodes = @NamedAttributeNode("land") ) )

})
public class Wijn implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String WIJN_MET_SOORT = "Wijn.metSoort";

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "soortid")
	private Soort soort;

	private int jaar;

	private int beoordeling;

	private BigDecimal prijs;

	private int inBestelling;

	protected Wijn() {
	}

	public Wijn(int jaar, Soort soort) {
		setJaar(jaar);
		setSoort(soort);
	}

	public Wijn(int jaar, Soort soort, BigDecimal prijs) {
		this(jaar, soort);
		setPrijs(prijs);
	}

	public long getId() {
		return id;
	}

	public Soort getSoort() {
		return soort;
	}

	public int getJaar() {
		return jaar;
	}

	public int getBeoordeling() {
		return beoordeling;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public int getInBestelling() {
		return inBestelling;
	}

	public void addAantalInBestelling(int aantal) {
		if (aantal <= 0) {
			throw new IllegalArgumentException();
		}
		this.inBestelling += aantal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jaar;
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
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
		Wijn other = (Wijn) obj;
		if (jaar != other.jaar)
			return false;
		if (soort == null) {
			if (other.soort != null)
				return false;
		} else if (!soort.equals(other.soort))
			return false;		
		return true;
	}

	@Override
	public String toString() {
		return soort.getLand().getNaam() + " " + soort.getNaam() + " " + jaar;
	}

	public void setSoort(Soort soort) {
		if (this.soort != null && this.soort.getWijnen().contains(this)) {
			this.soort.removeWijn(this);
		}
		this.soort = soort;
		if (soort != null && !soort.getWijnen().contains(this)) {
			soort.addWijn(this);
		}
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public void setBeoordeling(int beoordeling) {
		this.beoordeling = beoordeling;
	}

	public void setPrijs(BigDecimal prijs) {
		if (prijs.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException();
		}
		this.prijs = prijs;
	}

	public void setInBestelling(int inBestelling) {
		this.inBestelling = inBestelling;
	}

}
