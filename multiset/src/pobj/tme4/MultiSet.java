package pobj.tme4;

import java.util.Collection;
import java.util.List;

public interface MultiSet<T> extends Iterable<T>, Collection<T> {
	/**
	 * Ajoute count occurences de e dans le multi-ensemble
	 * @param e
	 * @param count
	 * @return true si les éléments ont bien été ajoutés
	 */
	public boolean add(T e, int count);
	public boolean add(T e);
	public boolean remove(Object e);
	/**
	 * Supprime count occurences de e dans le multi-ensemble
	 * @param e
	 * @param count
	 * @return true si les éléments ont été retirés, false si aucune occurence de e n'était présente
	 */
	public boolean remove(Object e, int count);
	/**
	 * @param o élément à tester
	 * @return le nombre d'occurences de o dans le multi-ensemble
	 */
	public int count(T o);
	public void clear();	
	public int size();
	/**
	 * @return la liste des éléments du multi-ensemble
	 */
	public  List<T> elements();
}
