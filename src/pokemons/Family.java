package pokemons;

import java.io.Serializable;
import java.util.ArrayList;

import types.Type;;

public class Family implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> evolutions = new ArrayList<String>();
	private Type type;
	private String name;
	
	public Family(Type type,String ...strings){
		for (String each : strings) {
			evolutions.add(each);
		}
		this.type=type;
		this.name=evolutions.get(0);
	}
	
	public String getEvol(int evol){
		if (evol>this.familySize()) {
			evol=this.familySize();
		}
		return evolutions.get(evol);
	}
	
	public int familySize(){
		return evolutions.size()-1;
	}
	
	public Type getType(){
		return type;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isStrongAgainst(Pokemon enemy){
		return this.type.isStrongAgainst(enemy.getType());
	}
	
	public boolean isWeakAgainst(Pokemon enemy){
		return this.type.isWeakAgainst(enemy.getType());
	}
}
