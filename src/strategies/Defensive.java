package strategies;

import pokemons.Pokemon;
import randoms.Randomizer;

public class Defensive extends Strategy {

	private static final long serialVersionUID = 1L;
	private static final int weakAttack = 3;
	private static Defensive defensiveFirstInstance = null;
	
	private Defensive(){}

	public double attack(Pokemon opponent, Pokemon myPokemon) {
		double damage = myPokemon.getAttack()
				- weakAttack
				- Randomizer.getInstance().defenseEfficiency(
						opponent.getDefense());
		if (damage < 0) {
			damage = 0;
		}
		if (myPokemon.isStrongAgainst(opponent)) {
			damage=damage*2;
		}
		if (myPokemon.isWeakAgainst(opponent)) {
			damage=damage/2;
		}
		return damage;
	}
	
	public static Defensive getInstance() {
		if (defensiveFirstInstance == null) {
			defensiveFirstInstance = new Defensive();
		}
		return defensiveFirstInstance;
	}

	public void defend(Pokemon myPokemon) {
		myPokemon.setDefense(myPokemon.getDefense() + 5);
	}

	public void heal(Pokemon myPokemon) {
		double aux = myPokemon.getRemainingLife() + 10;

		if (myPokemon.getRemainingLife() != 0) {
			if (myPokemon.getLife() <= aux) {
				aux = myPokemon.getLife();
			}
			myPokemon.setRemainingLife(aux);
		}
	}

	@Override
	public void offensive(Pokemon myPokemon) {
		myPokemon.setAttack(myPokemon.getAttack() + 1);

	}
}
