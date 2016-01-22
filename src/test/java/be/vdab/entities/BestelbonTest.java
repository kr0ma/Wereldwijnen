package be.vdab.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.valueobjects.Bestelbonlijn;

public class BestelbonTest {
	
	private Wijn wijn;
	private Bestelbonlijn bestelbonlijn;
	private Bestelbon bestelbon;
	
	@Before
	public void before(){
		wijn = new Wijn(1999, new Soort(), BigDecimal.TEN);
		bestelbonlijn = new Bestelbonlijn(wijn, 5);
		bestelbon = new Bestelbon();	
	}
	
	@Test
	public void totaalVanBestelbonMet1BestelbonlijnIsGelijkAanTotaalVanBestelbonlijn(){
		bestelbon.addBestelbonlijn(bestelbonlijn);
		assertEquals(0, BigDecimal.valueOf(50).compareTo(bestelbon.getTotaal()));
	}
	
	@Test
	public void totaalVanBestelbonMet2BestelbonlijnenIsGelijkAanTotaalVanBeideBestelbonlijnen(){
		bestelbon.addBestelbonlijn(bestelbonlijn);
		bestelbonlijn = new Bestelbonlijn(wijn, 10);
		bestelbon.addBestelbonlijn(bestelbonlijn);
		assertEquals(0, BigDecimal.valueOf(150).compareTo(bestelbon.getTotaal()));
	}
	
}
