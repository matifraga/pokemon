package types;

import java.util.HashSet;
import java.util.Set;

public class Leaf extends Type {

	private static final long serialVersionUID = 1L;
	private Set<String> strongAgainst = new HashSet<String>();
	private Set<String> weakAgainst = new HashSet<String>();
	private static Leaf leafFirstInstance = null;

	private Leaf() {
		
		strongAgainst.add("agua");
		weakAgainst.add("fuego");
	}
	
	public static Leaf getInstance() {
		if (leafFirstInstance == null) {
			leafFirstInstance = new Leaf();
		}
		return leafFirstInstance;
	}
	
	public String getType() {
		return "hierba";
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
