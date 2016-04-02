package dataCollections;

import java.util.ArrayList;

import pokemons.Family;
import types.*;


public class PokemonDataBase {

	private ArrayList<Family> pokeDataBase = new ArrayList<Family>();
	
	private static PokemonDataBase dataBaseFirstInstance = null;
	
	private PokemonDataBase(){
		
		Fire auxFire = Fire.getInstance();
		Leaf auxLeaf = Leaf.getInstance();
		Water auxWater = Water.getInstance();
		Thunder auxThunder = Thunder.getInstance();
		
		Family charmander=new Family(auxFire, "charmander","charmeleon","charizard");
		pokeDataBase.add(charmander);
		
		Family squirtle=new Family(auxWater, "squirtle","wartortle","blastoise");
		pokeDataBase.add(squirtle);
		
		Family bulbasaur=new Family(auxLeaf, "bulbasaur","ivysaur","venusaur");
		pokeDataBase.add(bulbasaur);
		
		Family caterpie=new Family(auxLeaf, "caterpie","metapod","butterfree");
		pokeDataBase.add(caterpie);
		
		Family growlithe=new Family(auxFire, "growlithe","arcanine");
		pokeDataBase.add(growlithe);
		
		Family magikarp=new Family(auxWater, "magikarp","gyarados");
		pokeDataBase.add(magikarp);
		
		Family horsea=new Family(auxWater, "horsea","seadra");
		pokeDataBase.add(horsea);
		
		Family vulpix=new Family(auxFire, "vulpix","ninetales");
		pokeDataBase.add(vulpix);
		
		Family starmie=new Family(auxWater, "starmie","staryu");
		pokeDataBase.add(starmie);
		
		Family pikachu=new Family(auxThunder, "pikachu","raichu");
		pokeDataBase.add(pikachu);
		
		
	}
	
	public static PokemonDataBase getInstance() {
		if(dataBaseFirstInstance == null){
			dataBaseFirstInstance = new PokemonDataBase();
		}
		return dataBaseFirstInstance;
	}
	
	public Family getFamily(int n){
		return pokeDataBase.get(n);
	}
	
	public Family getFamily(String name){
		for (Family each : pokeDataBase) {
			if(each.getName()==name){
				return each;
			}
		}
		return null;
	}
	
	public int size(){
		return pokeDataBase.size();
	}
}
