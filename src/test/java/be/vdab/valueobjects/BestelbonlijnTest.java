package be.vdab.valueobjects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.entities.Soort;
import be.vdab.entities.Wijn;

public class BestelbonlijnTest {

	private Wijn wijn;
	private Bestelbonlijn bestelbonlijn;
	
	@Before
	public void before(){
		wijn = new Wijn(1999, new Soort(), BigDecimal.TEN);
		bestelbonlijn = new Bestelbonlijn(wijn, 5);
	}
	
	@Test
	public void teBetalenPrijsVoor5WijnenMetPrijs10Is50() {
		assertEquals(0, BigDecimal.valueOf(50).compareTo(bestelbonlijn.getTeBetalen()));
	}
	
	@Test
	public void aantalWijnenInBestellingIsGelijkAan5(){
		assertEquals(5, bestelbonlijn.getWijn().getInBestelling());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void aantalWijnenKanNietNulZijn(){
		bestelbonlijn = new Bestelbonlijn(wijn, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void aantalWijnenKanNietOnderNulZijnBijToewijzen(){
		bestelbonlijn.setAantal(-5);
	}
	

}
