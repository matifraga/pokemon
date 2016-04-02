package strategies;

import pokemons.Pokemon;
import randoms.Randomizer;

public class Offensive extends Strategy {

	private static final long serialVersionUID = 1L;
	private static final double attackBoost = 1.5;
	private static Offensive offensiveFirstInstance = null;
	
	private Offensive(){}

	public double attack(Pokemon opponent, Pokemon myPokemon) {
		double damage = myPokemon.getAttack()
				* attackBoost
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
	
	public static Offensive getInstance() {
		if (offensiveFirstInstance == null) {
			offensiveFirstInstance = new Offensive();
		}
		return offensiveFirstInstance;
	}

	public void defend(Pokemon myPokemon) {
		myPokemon.setDefense(myPokemon.getDefense() + 1);
	}

	public void heal(Pokemon myPokemon) {
		double aux = myPokemon.getRemainingLife() + 5;
		if (myPokemon.getRemainingLife() != 0) {
			if (myPokemon.getLife() <= aux) {
				aux = myPokemon.getLife();
			}
			myPokemon.setRemainingLife(aux);
		}

	}

	@Override
	public void offensive(Pokemon myPokemon) {
		myPokemon.setAttack(myPokemon.getAttack() + 5);

	}

}
