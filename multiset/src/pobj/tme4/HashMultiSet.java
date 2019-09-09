package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
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
			return false;
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
}
