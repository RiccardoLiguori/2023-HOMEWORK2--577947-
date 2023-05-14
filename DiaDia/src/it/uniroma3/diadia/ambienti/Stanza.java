
package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Set <Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private Map <String,Stanza> stanzeAdiacenti;
	private int numeroStanzeAdiacenti;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.stanzeAdiacenti = new HashMap<String,Stanza>();
		this.attrezzi=new HashSet<Attrezzo>();
	}


	public int maxNumeroDirezioni() {
		return NUMERO_MASSIMO_DIREZIONI;
	}
	public int maxNumeroAttrezzi() {
		return NUMERO_MASSIMO_ATTREZZI;
	}

	public int getNumeroStanzeAdiacenti() {
		return numeroStanzeAdiacenti;
	}

	public void setNumeroStanzeAdiacenti(int numeroStanzeAdiacenti) {
		this.numeroStanzeAdiacenti = numeroStanzeAdiacenti;
	}

	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}
	public Collection<Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti.values();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public boolean impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		/* 	for(int i=0; i<this.direzioni.length; i++)
        	if (direzione.equals(this.direzioni[i])) {
        		this.stanzeAdiacenti[i] = stanza;
        		aggiornato = true;
		 */	if (stanzeAdiacenti.containsKey(direzione)) {
			 this.stanzeAdiacenti.put(direzione,stanza);
			 aggiornato = true;
		 }
		 if (!aggiornato) {
			 if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				 this.stanzeAdiacenti.put(direzione,stanza);
				 this.numeroStanzeAdiacenti++;
			 }
			 else
				 return false;
		 }
		 return true;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		/*for(int i=0; i<this.numeroStanzeAdiacenti; i++) {
        	if (this.direzioni[i].equals(direzione))
        		stanza = this.stanzeAdiacenti[i];
		}
		 */
		if(this.stanzeAdiacenti.containsKey(direzione))
			stanza=this.stanzeAdiacenti.get(direzione);

		return stanza;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	
	
	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo!=null && this.attrezzi.contains(attrezzo)==false)
			    this.numeroAttrezzi++;
				return this.attrezzi.add(attrezzo);
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\n* " + this.nome + " *");
		risultato.append("\nUscite: ");
		for (String direzione : this.stanzeAdiacenti.keySet())
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		if (!this.attrezzi.isEmpty()) {
			Iterator<Attrezzo> it=this.attrezzi.iterator();
			while(it.hasNext()) {
				Attrezzo a=it.next();
				risultato.append(a.toString()+" ");
			}
		}
		risultato.append("\n------------------------------------");
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> it=this.attrezzi.iterator();
		while(it.hasNext()) {
		Attrezzo a=it.next();
		if(a.getNome().equals(nomeAttrezzo))
			return true;
		}
		return false;
	}
	
	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		if(this.hasAttrezzo(nomeAttrezzo)) { 
			Iterator<Attrezzo> it=this.attrezzi.iterator();
			while(it.hasNext()) {
				Attrezzo a=it.next();
				if(a.getNome().equals(nomeAttrezzo)) {
					attrezzoCercato=a;
					return a;
				}
			}
		}

		return attrezzoCercato;
	}
	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return	this.attrezzi.remove(attrezzo);
		
	}
	
	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}


	public Map<String, Stanza> getDirezioni2stanze() {
		return this.stanzeAdiacenti;
	}



}