package be.vdab.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
@SuppressWarnings("unused")
public class SoortTest {
	
	private Soort soort,soort2;
	private Wijn wijn, wijn2;
	private Land land;
	
	@Before
	public void before(){
		land = new Land("lol");
		soort = new Soort("test", land);
		soort2 = new Soort("test2", land);
		wijn = new Wijn(1980, soort);
		wijn2 = new Wijn(1985, soort);			
	}	
	
	@Test
	public void aantalWijnenMoetNulZijnBijNieuwSoort(){
		Soort nieuweSoort = new Soort("nieuweSoort", land);
		assertEquals(0, nieuweSoort.getWijnen().size());
	}
	
	@Test
	public void aantalWijnenMoetEenZijnNaToevoegenWijnBijNieuwSoort(){
		Soort nieuweSoort = new Soort("nieuweSoort", land);
		nieuweSoort.addWijn(wijn);
		assertEquals(1, nieuweSoort.getWijnen().size());
	}
	
	@Test
	public void aantalWijnenMoetEenZijnNaTweemaalToevoegenZelfdeWijnBijNieuwSoort(){
		Soort nieuweSoort = new Soort("nieuweSoort", land);
		nieuweSoort.addWijn(wijn);
		nieuweSoort.addWijn(wijn);
		assertEquals(1, nieuweSoort.getWijnen().size());
	}
	
	@Test
	public void aantalWijnenMoetTweeZijnNaTweemaalToevoegenVerschillendeWijnBijNieuwSoort(){
		soort.addWijn(wijn);
		soort.addWijn(wijn2);
		assertEquals(2, soort.getWijnen().size());
	}
	
	@Test
	public void eenLandMoetOpgevraagdKunnenWordenViaSoortNaToekennenLandAanSoort(){
		Land land = new Land();
		soort.setLand(land);
		assertEquals(land, soort.getLand());
	}
	
	// als ik een land invul bij een soort, en ik vraag het soort van dat land moet dit overeenstemmen
	@Test
	public void eenLandMoetDeSoortBevattenNaToekennenLandAanDieSoort(){
		Land land = new Land();
		soort.setLand(land);
		assertTrue(land.getSoorten().contains(soort));
	}	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void naamVanEenSoortKanNietLeegZijnBijAanmaken(){
		Soort soort = new Soort("");
	}
	
	@Test
	public void naamVanEenSoortMoetGelijkZijnBijAanmakenMetNaamParameter(){
		assertEquals("test", soort.getNaam());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naamVanEenSoortKanNietLeegZijnBijToekennen(){
		soort.setNaam("");
	}
	
	@Test
	public void tweeSoortenMetHetzelfdeLandEnZelfdeNaamMoetenVolgensEqualsGelijkZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land1");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort", land1);
		assertTrue(soort1.equals(soort2));
	}
	
	@Test
	public void tweeSoortenMetHetzelfdeLandEnVerschillendeNaamMoetenVolgensEqualsVerschillendZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land1");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort2", land1);
		assertFalse(soort1.equals(soort2));
	}
	
	@Test
	public void tweeSoortenMetVerschillendLandEnVerschillendeNaamMoetenVolgensEqualsVerschillendZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land1");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort2", land2);
		assertFalse(soort1.equals(soort2));
	}
	
	@Test
	public void tweeSoortenMetVerschillendLandEnDezelfdeNaamMoetenVolgensEqualsVerschillendZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land2");
		Soort soort1 = new Soort("soort", land1);
		Soort soort2 = new Soort("soort", land2);
		assertFalse(soort1.equals(soort2));
	}
	
	

}
