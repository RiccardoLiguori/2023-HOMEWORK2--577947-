package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int pesoAttuale;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		//this.attrezzi = new Attrezzo[10]; // speriamo che bastino...
		this.attrezzi = new HashMap<>();
		this.numeroAttrezzi = 0;
		this.pesoAttuale = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if(this.attrezzi.containsKey(attrezzo.getNome())==false) {
			this.numeroAttrezzi++;
			this.pesoAttuale+=attrezzo.getPeso();
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}

	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo!=null && this.attrezzi.containsKey(nomeAttrezzo));
		a = this.attrezzi.get(nomeAttrezzo);
		return a;
	}

	public int getPeso() {
		return this.pesoAttuale;
	}
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(nomeAttrezzo!=null && this.attrezzi.containsKey(nomeAttrezzo)) {
			a=this.getAttrezzo(nomeAttrezzo);
			this.attrezzi.remove(nomeAttrezzo);

		}
		return a;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(this.getContenutoOrdinatoPerPeso().toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List <Attrezzo> lista=new LinkedList<Attrezzo>();
		lista.addAll(this.attrezzi.values());
		Collections.sort(lista,new ComparatorePerPeso());
		return lista;
		
	}
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> lista=new TreeSet<>();
		lista.addAll(this.attrezzi.values());
		return lista;
	}
	public Map<Integer,Set<Attrezzo>> getContenutoRaggrupatoPerPeso() {
		Set<Attrezzo> set=new HashSet<>();
		set.addAll(this.attrezzi.values());
		Map<Integer,Set<Attrezzo>> mappa=new HashMap<>();
		Set<Attrezzo> tmp;
		Iterator<Attrezzo> it = set.iterator();
		while(it.hasNext())  {
			Attrezzo a = it.next();
			tmp = mappa.get(a.getPeso());
			if(tmp == null)
				tmp = new HashSet<Attrezzo>();
			tmp.add(a);
			mappa.put(a.getPeso(), tmp);
			
		}
		return mappa;
	}
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> lista=new TreeSet<>(new ComparatorePerPeso());
		lista.addAll(this.attrezzi.values());
		return lista;
	}
}