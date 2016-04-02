package pokemons;

import dataCollections.Pokedex;


public class Character extends Pokemon implements Player {
	
	private static final long serialVersionUID = 1L;
	private int evolutionFlag=2;

	public Character(String name, int level, int experience,
			int attack, int defense, int speed, Family family) {
		super(name, level, experience, attack, defense, speed, family);
	}

	public void levelUp() {
		if (this.getExperience() >= 100) {
			this.setLevel(this.getLevel() + 1);
			this.setExperience(this.getExperience() - 100);
			this.setAttack(this.getAttack() + 1);
			this.setDefense(this.getDefense() + 1);
			this.setSpeed(this.getSpeed() + 1);
		}
	}

	public void evolve() {
		if (this.getLevel()>this.evolutionFlag) {
			if (this.getEvolution() + 1 <= this.maxEvol()) {
				this.setEvolution(this.getEvolution() + 1);
				this.setAttack(this.getAttack() + 5);
				this.setDefense(this.getDefense() + 5);
				this.setSpeed(this.getSpeed() + 5);
				evolutionFlagChange(); 
				Pokedex.getInstance().actualize(this,this.evolution());
			}
		}

	}

	public void catchExperience(Pokemon pokemon) {
		this.setExperience(this.getExperience()+pokemon.getExperience());
		levelUp();
		evolve();
	}

	private void evolutionFlagChange(){
		this.evolutionFlag=5;
	}

}
