package randoms;

import java.util.Random;

import dataCollections.PokemonDataBase;

public class Randomizer extends Random {
	private static final long serialVersionUID = 1L;
	
	private static Randomizer randomizerFirstInstance = null;
	
	private Randomizer(){}

	public int defenseEfficiency(int defense) {
		return (int)(defense/2 * (this.nextDouble() / 2 + 0.5));
	}
	
	public int randomLevel(int level){
		int aux=level;
		aux+=this.nextInt(3)-1;
		if (aux<=0) {
			aux=1;
		}
		return aux;
	}
	
	public int randomExperience(){
		return this.nextInt(30)+10;
	}
	
	public int randomTurn(){
		return this.nextInt(15);
	} 
	
	public int randomFamily(){
		return this.nextInt(PokemonDataBase.getInstance().size());
	}
	
	public static Randomizer getInstance() {
		if(randomizerFirstInstance == null){
			randomizerFirstInstance = new Randomizer();
		}
		return randomizerFirstInstance;
	}
}
