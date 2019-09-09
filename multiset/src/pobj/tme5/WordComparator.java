package pobj.tme5;

import java.util.Comparator;

public class WordComparator<T> implements Comparator<T> {
	private MultiSet<T> ms;
	
	/**
	 * Compare des mots en fonction de leur nombre d'occurences dans le multi-ensemble ms
	 * @param ms multi-ensemble contenant des mots à comparer
	 */
	public WordComparator(MultiSet<T> ms) {
		this.ms = ms;
	}
	
	@Override
	public int compare(T o1, T o2) {
		if (ms.count(o1) < ms.count(o2))
			return -1;
		if (ms.count(o1) == ms.count(o2))
			return 0;
		else
			return 1;
	}		
}