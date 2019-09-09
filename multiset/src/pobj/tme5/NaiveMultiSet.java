package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public  class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	List<T> list;
	
	public NaiveMultiSet() {
		list= new ArrayList<>();
	}
	@Override
	public boolean add(T e, int count) {
		boolean b= false;
		for(int i = 0; i<count; i++) {
			list.add( e);
			b = true;
		}
		return b;
	}
	@Override
	public boolean add(T e) {
		return list.add(e);
	}
	@Override
	public boolean remove(Object e) {
		return list.remove(e);
	}
	@Override
	public boolean remove(Object e, int count) {
		boolean b= false;
		for(int i = 0; i<count; i++) {
			list.remove( e);
			b = true;
		}
		return b;
	}
	@Override
	public int count(T o) {
		int count =0;
		if(list.contains(o)) {
			for(int i= 0; i<list.size(); i++) {
				if (list.get(i)==o) {
					count++;
				}
			}
		}
		return count;
	}
	@Override
	public void clear() {
		list.clear();
	}
	@Override
	public int size() {
		return list.size();
	}
	@Override
	public  List<T> elements(){
		Set<T> ens= new HashSet<T>(list);
		List<T> l = new ArrayList<T>(ens);
		// Collections.reverse(l);
		 return l;
		
	}

	public Comparator<T> comparator(){
		return new NaiveMultiSetComptarator();
	}
	
	public class NaiveMultiSetComptarator implements Comparator<T>{
		public int count(T o) {
			int count =0;
			if(list.contains(o)) {
				for(int i= 0; i<list.size(); i++) {
					if (list.get(i)==o) {
						count++;
					}
				}
			}
			return count;
		}
		@Override
		public int compare(T o1, T o2) {
			int val_o1  =count(o1);
			int val_o2  = this.count(o2);
			if(val_o1< val_o2) {
				return -1;
			}else {
				if(val_o1 ==val_o2) {
					return 0;
				}else {
					return 1;
				}
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
}
