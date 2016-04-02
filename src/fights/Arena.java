package fights;

import dataCollections.PokemonDataBase;
import pokemons.Character;
import pokemons.Pokemon;
import randoms.Randomizer;

public class Arena {
	private int iAttack;
	private int iDeffense;
	private int iSpeed;

	public Pokemon createOpponent(Character myPokemon) {

		int attack = (int) (myPokemon.getAttack()
				* Randomizer.getInstance().nextDouble() + 0.5);
		int defense = (int) (myPokemon.getDefense()
				* Randomizer.getInstance().nextDouble() + 0.5);
		int speed = (int) (myPokemon.getSpeed()
				* Randomizer.getInstance().nextDouble() + 0.5);
		int level = Randomizer.getInstance().randomLevel(myPokemon.getLevel());
																			
		int experience = Randomizer.getInstance().randomExperience();
		int family= Randomizer.getInstance().randomFamily();

		return new Pokemon("Enemigo",
				level, experience, attack, defense, speed,PokemonDataBase.getInstance().getFamily(family));
	}

	public int turn(Character myPokemon, Pokemon opponent) {
		if (myPokemon.getSpeed() >= opponent.getSpeed()) {
			return 0;
		} else {
			return 1;
		}
	}

	public boolean battleEnded(Character myPokemon, Pokemon opponent) {
		if (myPokemon.status() || opponent.status()) {
			return true;
		} else {
			return false;
		}
	}

	public void agressive(Character myPokemon, Pokemon opponent) {

		if (myPokemon.getRemainingLife() != 0) {
			attackTurn(myPokemon, opponent);
		}

	}

	public void lifeRecovered(Pokemon pokemon) {
		if (pokemon.getRemainingLife() != 0) {
			pokemon.increaseMyLife();
		}
	}

	public void boostAttack(Pokemon pokemon) {
		if (pokemon.getRemainingLife() != 0) {
			pokemon.increaseMyOfense();
		}
	}

	public void boostDefense(Pokemon pokemon) {
		if (pokemon.getRemainingLife() != 0) {
			pokemon.increaseMyDefense();
		}
	}

	public void enemyTurn(Character myPokemon, Pokemon opponent) {

		switch (Randomizer.getInstance().randomTurn()) {
		case 0:
			this.boostAttack(opponent);
			break;

		case 1:
			this.boostDefense(opponent);
			break;

		case 2:
			this.lifeRecovered(opponent);
			break;
			
		default:
			if (opponent.getRemainingLife() != 0) {
				attackTurn(opponent, myPokemon);
			}
		}

	}

	public void inicialStatus(Character myPokemon) {
		iAttack = myPokemon.getAttack();
		iDeffense = myPokemon.getDefense();
		iSpeed = myPokemon.getSpeed();
	}

	public void originalStatus(Character myPokemon) {
		myPokemon.setAttack(iAttack);
		myPokemon.setDefense(iDeffense);
		myPokemon.setSpeed(iSpeed);
	}
	
	private void attackTurn(Pokemon attacker, Pokemon defender){
		double damage;
		damage = attacker.attackTo(defender);
		defender.receiveDamage(damage);
	}
}
