package types;

import java.util.HashSet;
import java.util.Set;


public class Fire extends Type {
	
	private static final long serialVersionUID = 1L;
	private Set<String> strongAgainst = new HashSet<String>();
	private Set<String> weakAgainst = new HashSet<String>();
	private static Fire fireFirstInstance = null;
	
	private Fire() {
		
		strongAgainst.add("hierba");
		weakAgainst.add("agua");
	}
	
	public static Fire getInstance() {
		if (fireFirstInstance == null) {
			fireFirstInstance = new Fire();
		}
		return fireFirstInstance;
	}

	public String getType() {
		return "fuego";
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
