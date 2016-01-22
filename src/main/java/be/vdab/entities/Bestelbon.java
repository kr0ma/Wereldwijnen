package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@Entity
@Table (name="bestelbonnen")
public class Bestelbon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date besteld;
	
	private String naam;
	
	@Embedded
	private Adres adres;
	
	private boolean bestelwijze;	
	
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid"))
	private Set<Bestelbonlijn> bestelbonlijnen;
	
	public Bestelbon(Date besteld, String naam, Adres adres,
			boolean bestelwijze) {
		super();
		this.besteld = besteld;
		this.naam = naam;
		this.adres = adres;
		this.bestelwijze = bestelwijze;
		this.bestelbonlijnen = new HashSet<Bestelbonlijn>();
	}
	
	public Bestelbon(){
		this.bestelbonlijnen = new HashSet<Bestelbonlijn>();
	}

	public long getId() {
		return id;
	}

	public Date getBesteld() {
		return besteld;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public boolean isBestelwijze() {
		return bestelwijze;
	}

	public Set<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}
	
	public void addBestelbonlijn(Bestelbonlijn bestelbonlijn) {
		bestelbonlijnen.add(bestelbonlijn);
	}	
	
	public static boolean isInputValid(String input) { 
		return input != null && !input.isEmpty();
	}	
	
	public static boolean isBestelwijzeValid(String input){
		return input!= null && (input.equals("0") || input.equals("1")); 
	}
	
	public BigDecimal getTotaal(){
		BigDecimal totaal = BigDecimal.ZERO;
		for (Bestelbonlijn lijn : bestelbonlijnen){
			totaal = totaal.add(lijn.getTeBetalen());
		}
		return totaal;
	}
	
}
