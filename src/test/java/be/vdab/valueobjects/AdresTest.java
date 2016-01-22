package be.vdab.valueobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AdresTest {
	
	@Test
	public void eenLegeStringInputIsVerkeerd() {
		assertFalse(Adres.isInputValid(""));
	}
	
	@Test
	public void eenNullInputIsVerkeerd() {
		assertFalse(Adres.isInputValid(null));
	}
	
	@Test
	public void eenNietLegeInputStringIsOK(){
		assertTrue(Adres.isInputValid("lol"));
	}


}
