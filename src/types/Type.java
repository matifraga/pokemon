package types;

import java.io.Serializable;

public abstract class Type implements Serializable{

	private static final long serialVersionUID = 1L;

	public abstract String getType();

	public abstract boolean isStrongAgainst(Type attackerType);

	public abstract boolean isWeakAgainst(Type attackerType);

}
