package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	private Stanza stanzaCorrente;
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public void creaStanze() {

		// crea gli attrezzi 
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		// crea le stanze 
		Stanza atrio =new Stanza("atrio");
		Stanza aulaN10 =new Stanza("aulaN10");
		Stanza aulaN11 =new Stanza("aulaN11");
		Stanza biblioteca =new Stanza("biblioteca");
		Stanza laboratorio =new Stanza("laboratorio");
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		// pone gli attrezzi nelle stanze 
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
	}
	public LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale=stanzaIniziale;
	}
}