package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
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
		for(int i = 0; i<count; i++) {
			list.add(e);
		}
		return true;
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
		boolean b = false;
		for (int i = 0; i<count; i++) {
			b = list.remove(e);
			if (b == false)
				return false;
		}
		return b;
	}
	
	@Override
	public int count(T o) {
		int count = 0;
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
		return new ArrayList<T>(ens);		
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
}
