package strategies;

import pokemons.Pokemon;
import randoms.Randomizer;

public class Neutral extends Strategy {

	private static final long serialVersionUID = 1L;
	private static Neutral neutralFirstInstance = null;
	
	private Neutral(){}

	public double attack(Pokemon opponent, Pokemon myPokemon) {
		double damage = myPokemon.getAttack()
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
	
	public static Neutral getInstance() {
		if (neutralFirstInstance == null) {
			neutralFirstInstance = new Neutral();
		}
		return neutralFirstInstance;
	}

	public void defend(Pokemon myPokemon) {
		myPokemon.setDefense(myPokemon.getDefense() + 2);
	}

	public void heal(Pokemon myPokemon) {
		double aux = myPokemon.getRemainingLife() + 20;
		if (myPokemon.getRemainingLife() != 0) {
			if (myPokemon.getLife() <= aux) {
				aux = myPokemon.getLife();
			}
			myPokemon.setRemainingLife(aux);
		}
	}

	@Override
	public void offensive(Pokemon myPokemon) {
		myPokemon.setAttack(myPokemon.getAttack() + 2);

	}

}
