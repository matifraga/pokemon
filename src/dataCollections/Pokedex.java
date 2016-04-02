package dataCollections;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pokemons.Character;
import pokemons.Family;
import files.FileManager;

public class Pokedex implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Character, String> pokedex = new HashMap<Character, String>();
	private static String fileName = "datos_pokedex.dat";
	private static Pokedex pokedexFirstInstance = null;

	private Pokedex() {

	}

	public void saveGame() { 
								
		FileManager pokedexManager = new FileManager();
		try {
			pokedexManager.savePokedex(Pokedex.getInstance(), fileName);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	public void loadGame() {
		FileManager pokedexManager = new FileManager();
		try {
			Pokedex.pokedexFirstInstance = pokedexManager
					.recoverPokedex(fileName);
			Pokedex.getInstance();

		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	public static Pokedex getInstance() {
		if (pokedexFirstInstance == null) {
			pokedexFirstInstance = new Pokedex();
		}
		return pokedexFirstInstance;
	}

	public boolean addPokemon(Character pokemon, String img) {

		if (!pokedex.containsKey(pokemon)) {
			pokedex.put(pokemon, img);
			return true;
		}

		return false;
	}

	public void removePokemon(Character pokemon) {
		pokedex.remove(pokemon);
	}

	public Set<Character> pokedexSet() {
		return this.pokedex.keySet();
	}

	public boolean containsPokemon(Character pokemon) {
		return this.pokedex.containsKey(pokemon);
	}

	public Character foundPokemon(String name) {
		for (Character each : this.pokedex.keySet()) {
			if (each.toString().equals(name)) {
				return each;
			}
		}
		return null;
	}

	public String foundPath(String path) {
		for (Character each : this.pokedex.keySet()) {
			if (pokedex.get(each).equals(path)) {
				return pokedex.get(each);
			}
		}
		return null;
	}

	public Character createPokemon(String name, int attack,
			int defense, int speed, Family family) {
		return new Character(name, 1, 0, attack, defense, speed, family);
	}

	public String associatedImage(Character pokemon) {
		return this.pokedex.get(pokemon);
	}
	
	public void actualize(Character pokemon, String img){
		pokedex.put(pokemon, img);
	}

}