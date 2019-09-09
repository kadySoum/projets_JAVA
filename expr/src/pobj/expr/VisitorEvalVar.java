package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval {
	private Map<String, Integer> environnement;
	
	public VisitorEvalVar(Map<String,Integer> env) {
		this.environnement = env;
	}
	
	@Override
	public Integer visit(Var v) {
		return environnement.get(v.getName());
	}
}
