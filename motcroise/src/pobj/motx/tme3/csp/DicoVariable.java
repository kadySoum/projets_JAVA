package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	
	private int index; 
	private GrillePotentiel gp;
	
	public DicoVariable(int index, GrillePotentiel gp) {
		this.index = index;
		this.gp = gp; 
	}
	public String toString() {
		return gp.getMotsPot().get(index).toString();
	}
	/**
	 * l’ensemble des valeurs qu'une variable peut prendre
	 */
	@Override
	public List<String> getDomain() {
		Dictionnaire d =gp.getMotsPot().get(index);
		List<String> newD = new ArrayList<String>();
		for(int i =0;i<d.size(); i++) {
			newD.add(d.get(i));
		}
		return newD;
	}
	
	public GrillePotentiel fixer(String val) {
		return gp.fixer(index, val);
	}

}
