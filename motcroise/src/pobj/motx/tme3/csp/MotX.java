package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP {

	private List<IVariable> dico; 
	private GrillePotentiel gp;
	
	public MotX(GrillePotentiel gp) {
		dico = new ArrayList<>();
		this.gp = gp;
		List<Dictionnaire> mots=gp.getMotsPot();
		for(int i= 0; i<mots.size(); i++) {
			if (gp.hasCaseVide(i))
				dico.add(new DicoVariable(i, gp));
		}
	}
	@Override
	public List<IVariable> getVars() {
		return dico;
	}

	@Override
	public boolean isConsistent() {
		for (IVariable var : dico) {
			if (var.getDomain().size() == 0){
				return false;
			}
		}
		return true;
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
		if (vi instanceof DicoVariable) {
			DicoVariable newVi = (DicoVariable) vi; 
			GrillePotentiel gp = newVi.fixer(val);
			return new MotX(gp);
		}
		else {
			return null;
		}
	}

	public String toString() {
		return gp.toString();
	}
}
