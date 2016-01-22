package be.vdab.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class WijnTest {

	private Wijn wijn, wijn2;
	private Soort soort;
	
	@Before
	public void before(){
		wijn = new Wijn(1987, new Soort());
		soort = new Soort("test");
	}			
	
	@Test
	public void tweeWijnenMetDezelfdeSoortEnZelfdeJaartalMoetenVolgensEqualsGelijkZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land2");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort", land1);
		
		wijn = new Wijn(1980, soort1);
		wijn2 = new Wijn(1980, soort1);
		assertTrue(wijn.equals(wijn2));
	}	
	
	@Test
	public void tweeWijnenMetDezelfdeSoortEnVerschillendJaartalMoetenVolgensEqualsVerschillendZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land2");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort", land1);
		
		wijn = new Wijn(1980, soort1);
		wijn2 = new Wijn(1985, soort1);
		assertFalse(wijn.equals(wijn2));
	}
	
	
	@Test
	public void tweeWijnenMetVerschillendeSoortEnHetzelfdeJaartalMoetenVolgensEqualsVerschillendZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land2");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort", land2);
		
		wijn = new Wijn(1980, soort1);
		wijn2 = new Wijn(1980, soort2);
		assertFalse(wijn.equals(wijn2));
	}
	
	@Test
	public void tweeSoortenMetVerschillendLandEnDezelfdeNaamMoetenVolgensEqualsVerschillendZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land2");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort", land2);
		assertFalse(soort1.equals(soort2));
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void prijsVanEenWijnMagNiet0Zijn() {
		wijn = new Wijn(1987, new Soort(), BigDecimal.ZERO);
	}
	
	@Test(expected = NullPointerException.class)
	public void prijsVanEenWijnKanNietNullZijn(){
		wijn = new Wijn(1987, new Soort(), null);
	}
	
	@Test
	public void aantalInBestellingVanEenNieuweWijnIsGelijkAan0(){
		assertEquals(0, new Wijn(1999, new Soort()).getInBestelling());
	}
	
	@Test
	public void aantalInBestellingNaEenmaalToevoegen10Is10(){
		wijn = new Wijn(1999, new Soort(), BigDecimal.TEN);
		wijn.addAantalInBestelling(10);
		assertEquals(10, wijn.getInBestelling());
	}
	
	@Test
	public void aantalInBestellingNaTweemaalToevoegen10Is20(){
		wijn = new Wijn(1999, new Soort(), BigDecimal.TEN);
		wijn.addAantalInBestelling(10);
		wijn.addAantalInBestelling(10);
		assertEquals(20, wijn.getInBestelling());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void aantalInBestellingVerhogenMet0IsVerkeerd(){
		wijn = new Wijn(1999, new Soort(), BigDecimal.TEN);
		wijn.addAantalInBestelling(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void aantalInBestellingVerhogenMetEenNegatiefGetalIsVerkeerd(){
		wijn = new Wijn(1999, new Soort(), BigDecimal.TEN);
		wijn.addAantalInBestelling(-5);
	}	
	
	@Test
	public void eenSoortMoetDeWijnBevattenNaToekennenSoortAanDieWijn(){		
		wijn.setSoort(soort);
		assertTrue(soort.getWijnen().contains(wijn));
	}
		
	

}
