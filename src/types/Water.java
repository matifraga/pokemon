package types;

import java.util.HashSet;
import java.util.Set;

public class Water extends Type {

	private static final long serialVersionUID = 1L;
	private Set<String> strongAgainst = new HashSet<String>();
	private Set<String> weakAgainst = new HashSet<String>();
	private static Water waterFirstInstance = null;

	private Water() {
		
		strongAgainst.add("fuego");
		weakAgainst.add("hierba");
	}
	
	public static Water getInstance() {
		if (waterFirstInstance == null) {
			waterFirstInstance = new Water();
		}
		return waterFirstInstance;
	}

	public String getType(){
		return "agua";
	}

	@Override
	public boolean isStrongAgainst(Type attackerType) {
		for (String each : strongAgainst) {
			if (each.equals(attackerType.getType())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isWeakAgainst(Type attackerType) {
		for (String each : weakAgainst) {
			if (each.equals(attackerType.getType())) {
				return true;
			}
		}
		return false;
	}
}
