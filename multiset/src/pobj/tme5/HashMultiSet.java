package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public  class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	HashMap<T, Integer> map;
	int size; 
	
	/**
	 * Constructeur : crée un multi-ensemble vide
	 */
	public HashMultiSet(){
		map = new  HashMap<T,Integer>();
		size=0;
	}
	
	/**
	 * Constructeur : initialise le nouveau multi-ensemble avec le contenu de la collection
	 * @param coll collection à convertir
	 */
	public HashMultiSet(Collection<T> coll){
		map = new HashMap<T,Integer>();
		for (T elem: coll) {
			add(elem);
		}
		size = 0;
	}

	@Override
	public boolean add(T e, int count) {
		if (count <=0)
			throw new IllegalArgumentException("Erreur fonction add() : Impossible d'ajouter un nombre négatif d'éléments");
		if (map.containsKey(e)) {
			map.put(e, map.get(e)+count);
		}
		else {
			map.put(e, count);
		}
		size += count;
		return true;
	}

	@Override
	public boolean add(T e) {
		if (map.containsKey(e)) {
			map.put(e, map.get(e)+1);
		}else {
			map.put(e,1);
		}
		size ++;
		return true;
	}
	
	@Override
	public boolean remove(Object e, int count) {
		if (count <=0)
			throw new IllegalArgumentException("Erreur fonction remove() : Impossible d'enlever un nombre négatif d'éléments");
		boolean b = false;
		T t = (T) e;
		if (map.containsKey(t)) {
			if (map.get(t) <= count) {
				int old = map.remove(t);
				size -= old;
			}
			else {
				map.put(t, map.get(t)-count);
				size -= count;
			}
		}
		return b;
	}
	
	@Override
	public boolean remove(Object e) {
		T t = (T) e;
		boolean b= false;
		if (map.containsKey(e)) {
			map.put(t, map.get(t)-1);
			size--;
			if (map.get(t) <= 0)
				map.remove(t);
			b= true;
		}
		return b;
	}

	@Override
	public int count(T o) {
		Integer res = map.get(o);
		if (res == null)
			res = 0;
		return res;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void clear() {
		map.clear();
		size = 0;
	}

	@Override
	public Iterator<T> iterator(){
		return new HashMultiSetIterator();
	}
	
	@Override
	public List<T> elements() {
		return new ArrayList<>(map.keySet()); // keySet retourne l'ensemble des clés
	}
	
	private boolean isConsistent() {
		boolean b = false;
		int nb=0;
		for(T s: this) {
			if(map.get(s)!= null){//HashMap<T,Integer>
				if (map.get(s) < 0)
					return false;
			}
			nb+=this.count( s);
		} 
		if (size == nb) {//size
			b=true;
		}
		return b;
	}

	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(T e: this){
			if(e!=null && !(s.toString().contains((String)e))){
				s.append(e+":"+map.get(e)+", "); 
			}
		}
		s.setCharAt(s.length()-2, ']');
		return s.toString();
	}
	
	//CLASSE INTERNE
	private class HashMultiSetIterator implements Iterator<T> {
		Iterator<Map.Entry<T, Integer>> iterMap;
		Map.Entry<T, Integer> entry;
		int index = 0;
		int occurence = 0; //numéro de l'occurence de la valeur
		
		public HashMultiSetIterator() {
			iterMap = map.entrySet().iterator();
			entry = iterMap.next();
			
		}
		
		@Override
		public boolean hasNext() {
			return index < size();
		}

		@Override
		public T next() {
			if (!hasNext()){
				throw new NoSuchElementException("it closed");
			}
			else {
				if (occurence < entry.getValue()){
					occurence++;
					index++;
					return  entry.getKey();
				}
				else {
					entry = iterMap.next(); // on passe à la valeur suivante
					occurence = 1;
					index ++;
					return entry.getKey() ;
				}
			}
		}
	}

	@Override
	public Comparator<T> comparator() {
		return new WordComparator(this);
	}
}
