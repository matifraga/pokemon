package types;

import java.util.HashSet;
import java.util.Set;

public class Thunder extends Type{
	
	private static final long serialVersionUID = 1L;
	private Set<String> strongAgainst = new HashSet<String>();
	private Set<String> weakAgainst = new HashSet<String>();
	private static Thunder thunderFirstInstance = null;
	
	private Thunder() {
		
		strongAgainst.add("agua");
	}
	
	public static Thunder getInstance() {
		if (thunderFirstInstance == null) {
			thunderFirstInstance = new Thunder();
		}
		return thunderFirstInstance;
	}

	public String getType(){
		return "trueno";
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
