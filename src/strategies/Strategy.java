package strategies;

import java.io.Serializable;

import pokemons.Pokemon;

public abstract class Strategy implements Serializable {
	private static final long serialVersionUID = 1L;

	
	public abstract double attack(Pokemon opponent, Pokemon myPokemon);

	public abstract void offensive(Pokemon myPokemon);

	public abstract void defend(Pokemon myPokemon);

	public abstract void heal(Pokemon myPokemon);

}
