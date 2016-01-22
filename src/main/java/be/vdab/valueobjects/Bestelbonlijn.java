package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Wijn;

@Embeddable
public class Bestelbonlijn implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Bestelbonlijn(Wijn wijn, int aantal) {
		this.wijn = wijn;
		setAantal(aantal);
		wijn.addAantalInBestelling(aantal);
	}

	public Bestelbonlijn() {
	}
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "wijnid")
	private Wijn wijn;
	private int aantal;

	public Wijn getWijn() {
		return wijn;
	}

	public int getAantal() {
		return aantal;
	}
	
	public BigDecimal getTeBetalen(){
		return wijn.getPrijs().multiply(BigDecimal.valueOf(aantal));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantal;
		result = prime * result + ((wijn == null) ? 0 : wijn.hashCode());
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
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (aantal != other.aantal)
			return false;
		if (wijn == null) {
			if (other.wijn != null)
				return false;
		} else if (!wijn.equals(other.wijn))
			return false;
		return true;
	}

	public void setWijn(Wijn wijn) {
		this.wijn = wijn;
	}

	public void setAantal(int aantal) {
		if (aantal<= 0){
			throw new IllegalArgumentException();
		}
		this.aantal = aantal;
	}
	
	
	
	
	
}
