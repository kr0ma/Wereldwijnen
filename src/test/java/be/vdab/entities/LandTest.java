package be.vdab.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
@SuppressWarnings("unused")
public class LandTest {
	
	private Land land, landMetSoorten, anderLand;
	private Soort soort, soort2, soortMetAnderLand1, soortMetAnderLand2;
	
	
	@Before
	public void before(){
		land = new Land("zonderSoorten");		
		soort = new Soort("test",land);
		soort2 = new Soort("test2", land);
		
		soortMetAnderLand1 = new Soort("anderLand1", anderLand );
		soortMetAnderLand2 = new Soort("anderLand2", anderLand );
		
		Set <Soort> soorten = new HashSet<>();
		soorten.add(soort);
		soorten.add(soort2);
		
		Set <Soort> soortenAnderLand = new HashSet<>();
		soortenAnderLand.add(soortMetAnderLand1);
		soortenAnderLand.add(soortMetAnderLand2);
		
		landMetSoorten = new Land("metSoorten", soorten);
		anderLand = new Land("anderLand", soortenAnderLand);
	}
	
	@Test
	public void aantalSoortenMoetNulZijnBijNieuwLand(){
		assertEquals(0, land.getSoorten().size());
	}
	
	@Test
	public void aantalSoortenMoetEenZijnNaToevoegenSoortBijNieuwLand(){
		land.addSoort(soort);
		assertEquals(1, land.getSoorten().size());
	}
	
	@Test
	public void aantalSoortenMoetEenZijnNaTweemaalToevoegenZelfdeSoortBijNieuwLand(){
		land.addSoort(soort);
		land.addSoort(soort);
		assertEquals(1, land.getSoorten().size());
	}
	
	@Test
	public void aantalSoortenMoetTweeZijnNaTweemaalToevoegenVerschillendeSoortBijNieuwLand(){
		land.addSoort(soort);
		land.addSoort(soort2);
		assertEquals(2, land.getSoorten().size());
	}	
	
	@Test (expected = NullPointerException.class )
	public void soortToevoegenMetNullWaardeIsVerkeerd(){
		land.addSoort(null);
	}
	
	@Test
	public void naSoortToevoegenMoetDatLandDezeSoortBevatten(){
		land.addSoort(soort);
		assertTrue(land.getSoorten().contains(soort));
	}
	
	@Test
	public void naToevoegenSoortBijLandMagVorigLandDezeSoortNietMeerBevatten(){
		landMetSoorten.addSoort(soortMetAnderLand1);
		assertFalse(anderLand.getSoorten().contains(soortMetAnderLand1));
	}
	
	@Test (expected = NullPointerException.class)
	public void soortVerwijderenMetNullWaardeIsVerkeerd(){
		landMetSoorten.removeSoort(null);
	}	
	
	@Test
	public void naSoortVerwijderenVanLandMagDitLandDeSoortNietMeerBevatten(){
		landMetSoorten.removeSoort(soort);
		assertFalse(landMetSoorten.getSoorten().contains(soort));
	}
	
	@Test
	public void naSoortVerwijderenVanLandMetTweeSoortenMagDitLandNogEenSoortBevatten(){
		landMetSoorten.removeSoort(soort);
		assertEquals(1, landMetSoorten.getSoorten().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naamVanEenLandKanNietLeegZijnBijAanmaken(){
		Land land = new Land("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naamVanEenLandKanNietLeegZijnBijToekennen(){
		land.setNaam("");
	}
	
	@Test
	public void tweeLandenMetDezelfdeNaamMoetenVolgensEqualsGelijkZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land1");
		assertTrue(land1.equals(land2));
	}
	
	@Test
	public void tweeLandenMetVerschillendeNaamMoetenVolgensEqualsVerschillendZijn(){
		Land land1 = new Land("land1");
		Land land2 = new Land("land2");
		assertFalse(land1.equals(land2));
	}
	


}
