package pobj.tme5;

import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T>{
	private MultiSet<T> decorated; 
	
	public MultiSetDecorator(MultiSet<T> d) {
		decorated =d;
	}
	@Override
	public boolean add(T e, int count) {
		decorated.add(e, count);
		assert isConsistent();
		return true;
	}
	@Override
	public boolean add(T e) {
		decorated.add(e);
		assert isConsistent();
		return true;
	}
	@Override
	public boolean remove(Object e) {
		decorated.remove( e);
		assert isConsistent();
		return true;
	}
	@Override
	public boolean remove(Object e, int count){
		decorated.remove(e, count);
		assert isConsistent();
		return true;
	}
	@Override
	public int count(T o) {
		return decorated.count(o);
	}
	@Override
	public void clear() {
		decorated.clear();
	}
	@Override
	public int size() {
		return decorated.size();
	}
	@Override
	public  List<T> elements(){
		return decorated.elements();
	}

	@Override
	public Comparator<T> comparator(){
		return decorated.comparator();
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return decorated.iterator();
	}
	
	public String toString(){
		return decorated.toString();
	}
	private boolean isConsistent() {
		boolean b = false;
		int nb=0;
		for(T s: this) {
			if(decorated.count(s) != 0){//HashMap<T,Integer>
				if (decorated.count(s) < 0)
					return false;
			}
			//nb+=this.count( s);
			nb+=decorated.count(s);
		} 
		if (decorated.size() == nb) {//size
			b=true;
		}
		return b;
	}
	
}
