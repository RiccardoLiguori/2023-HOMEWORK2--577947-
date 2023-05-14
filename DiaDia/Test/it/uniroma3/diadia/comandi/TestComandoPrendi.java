package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

  public class TestComandoPrendi {

	private Partita partita;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoPesante;
	private Attrezzo attrezzoNull;
	private Comando comando;
	private Stanza stanza;
	private Giocatore giocatore;
	private Borsa borsa;
	
	@Before
	public void setUp() {
		this.partita = new Partita();
		this.attrezzoPesante = new Attrezzo("incudine", 11);
		this.attrezzoNull = null;
		this.comando = new ComandoPrendi();
		this.stanza=new Stanza("N10");
		this.giocatore=new Giocatore();
		this.borsa=new Borsa();
		this.giocatore.setBorsa(borsa);
		this.partita.setGiocatore(giocatore);
		this.partita.setStanzaCorrente(stanza);
		
	}
	
	public boolean attrezzoPresente(Attrezzo a ) {
		Set<Attrezzo> attrezzi = (Set<Attrezzo>) this.partita.getStanzaCorrente().getAttrezzi();
		Iterator<Attrezzo> it = attrezzi.iterator();
		while(it.hasNext()) {
			Attrezzo b=it.next();
			System.out.println(b);
			if(a != null && b.getNome().equals(a.getNome()))
					return true;
		}
		return false;
	}
	
	@Test
	  public void testAttrezzoPreso() {
		Attrezzo attrezzo=new Attrezzo("palo",2);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
		this.comando.setParametro("palo");
		this.comando.esegui(partita);
		assertTrue(attrezzoPresente(attrezzo));
	}
	@Test
	 public void testAttrezzoNonPresente() {
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(attrezzoPresente(attrezzo));
	}
	
	@Test
	public void testAttrezzoPesante() {
		partita.getStanzaCorrente().addAttrezzo(attrezzoPesante);
		comando.setParametro("incudine");
		comando.esegui(partita);
		assertTrue(attrezzoPresente(attrezzoPesante));
	}
	
}