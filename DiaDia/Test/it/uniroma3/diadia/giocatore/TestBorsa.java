package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestBorsa {
    private Borsa borsaPiena;
    private Attrezzo martello;
    private Borsa borsaVuota;   
    private Attrezzo Spada;
	private Attrezzo falce;
	private Attrezzo pala;
	private Attrezzo racchetta;
	private Attrezzo sega;
	private Attrezzo computer;
	private Attrezzo laptop;
    @BeforeEach
    public void setUp() {
	borsaPiena=new Borsa();
	borsaVuota=new Borsa();
	martello=new Attrezzo("martello",5);
	Spada=new Attrezzo("spada",3);
	borsaVuota.addAttrezzo(Spada);
	falce = new Attrezzo("falce", 2);
	pala = new Attrezzo("pala", 3);
	racchetta = new Attrezzo("racchetta", 3);
	sega = new Attrezzo("sega", 1);
	computer = new Attrezzo("computer", 2);
	laptop = new Attrezzo("laptop", 4);
}
    @Test	
	final void testHasAttrezzo() {
		assertFalse(borsaPiena.hasAttrezzo("Spada"));
	}
    @Test	
	final void testaddAttrezzo() {
    	assertTrue(borsaPiena.addAttrezzo(Spada));
		assertTrue(borsaPiena.addAttrezzo(martello));
		assertFalse(borsaPiena.addAttrezzo(martello));
		
	}
    @Test	
	final void removeatttrezzo() {
    	assertFalse(this.borsaVuota.addAttrezzo(Spada));
    	assertEquals(Spada,this.borsaVuota.getAttrezzo("spada"));
		assertEquals(Spada,borsaVuota.removeAttrezzo("spada"));
	}
    @Test	
   	final void testIsEmpty() {
   		assertFalse(borsaVuota.isEmpty());
   	}
    @Test 
    final void testRaggruppatoPerPeso() {
    	borsaPiena.addAttrezzo(martello);
        borsaPiena.addAttrezzo(falce);
        borsaPiena.addAttrezzo(computer);
        borsaPiena.addAttrezzo(sega);
        assertEquals("[sega (1kg), computer (2kg), falce (2kg), martello (5kg)]",borsaPiena.getContenutoOrdinatoPerPeso().toString());
      
    }
    @Test 
    final void testRaggruppatoPerNome() {
    	borsaPiena.addAttrezzo(martello);
        borsaPiena.addAttrezzo(falce);
        borsaPiena.addAttrezzo(computer);
        borsaPiena.addAttrezzo(sega);
        assertFalse(borsaPiena.addAttrezzo(Spada));
        assertEquals("[computer (2kg), falce (2kg), martello (5kg), sega (1kg)]",borsaPiena.getContenutoOrdinatoPerNome().toString());
      
    }
    @Test 
    final void testRaggruppatoPerNomeMap() {
    	borsaPiena.addAttrezzo(martello);
        borsaPiena.addAttrezzo(falce);
        borsaPiena.addAttrezzo(computer);
        borsaPiena.addAttrezzo(sega);
        assertEquals("{1=[sega (1kg)], 2=[computer (2kg), falce (2kg)], 5=[martello (5kg)]}",borsaPiena.getContenutoRaggrupatoPerPeso().toString());
      
    }
    @Test 
    final void testRaggruppatoPerPesoSortedSet() {
        borsaPiena.addAttrezzo(falce);
        borsaPiena.addAttrezzo(computer);
        Set<Attrezzo> expected = new TreeSet<>(new ComparatorePerPeso());
		expected.add(falce);
		expected.add(martello);
        assertEquals("[computer (2kg), falce (2kg)]",borsaPiena.getSortedSetOrdinatoPerPeso().toString());
        
    }
    @Test
	public void testGetPeso() {
		borsaVuota.addAttrezzo(falce);
		assertEquals(falce, borsaVuota.getAttrezzo("falce"));
  
}

    
    
    
    
}   