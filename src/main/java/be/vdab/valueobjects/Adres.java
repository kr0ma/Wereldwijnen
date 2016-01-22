package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	private String straat;
	private String huisNr;
	private String postCode;
	private String gemeente;

	// je maakt voor straat, huisNr, postcode en gemeente getters, geen setters
	public Adres(String straat, String huisNr, String postcode, String gemeente) {
		this.straat = straat;
		this.huisNr = huisNr;
		this.postCode = postcode;
		this.gemeente = gemeente;
	}

	protected Adres() {
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getGemeente() {
		return gemeente;
	}
	
	public static boolean isInputValid(String input) { 
		return input != null && !input.isEmpty();
	}
	
	
}
