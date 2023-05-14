package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza vuota;
	private Attrezzo osso;
	private Stanza Nonvuota;
	private Attrezzo spada;

	@BeforeEach
	public void setUp(){
		this.Nonvuota=new Stanza("Nonvuota");
		this.osso=new Attrezzo("osso",1);
		this.spada=new Attrezzo("spada",5);
		this.vuota=new Stanza("vuota");
		this.Nonvuota.impostaStanzaAdiacente("sud",vuota);
		this.vuota.impostaStanzaAdiacente("nord",Nonvuota);
	}

	@Test
	final void testHasAttrezzo() {
		this.Nonvuota.addAttrezzo(osso);
		this.vuota.addAttrezzo(spada);
		assertTrue(this.Nonvuota.hasAttrezzo("osso"));
		assertFalse(this.vuota.hasAttrezzo("osso"));
		assertTrue(this.vuota.hasAttrezzo("spada"));
		assertFalse(this.Nonvuota.hasAttrezzo("spada"));
	}
	@Test
	final void testRemoveAttrezzo() {
		this.Nonvuota.addAttrezzo(osso);
		assertTrue(this.Nonvuota.removeAttrezzo(this.osso));
		assertFalse(this.vuota.removeAttrezzo(this.osso));


	}
	@Test
	final void testGetStanzaAdiacente() {
		assertEquals("vuota",this.Nonvuota.getStanzaAdiacente("sud").getNome());
		assertEquals("Nonvuota",this.vuota.getStanzaAdiacente("nord").getNome());

	}	
	@Test
	final void testaddAttrezzo() {
		assertTrue(this.Nonvuota.addAttrezzo(osso));
	    assertTrue(this.vuota.addAttrezzo(osso));
	    assertFalse(this.Nonvuota.addAttrezzo(osso));
	    assertTrue(this.vuota.addAttrezzo(spada));

	}



}