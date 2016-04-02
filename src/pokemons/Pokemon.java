package pokemons;

import java.io.Serializable;

import strategies.Strategy;
import types.Type;

public class Pokemon implements Fighter, Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int experience;
	private int level;
	private int evolution = 0;
	private int attack;
	private int defense;
	private int speed;
	private double life = 100;
	private double remainingLife;
	private Strategy strategy;
	private Family family;

	public Pokemon(String name, int level, int experience,
			int attack, int defense, int speed, Family family) {
		this.name = name;
		this.experience = experience;
		this.level = level;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.remainingLife = life;
		
		this.family=family;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public double attackTo(Pokemon enemy) {
		return strategy.attack(enemy, this);
	}

	public void increaseMyOfense() {
		strategy.offensive(this);
	}

	public void increaseMyDefense() {
		strategy.defend(this);
	}

	public void increaseMyLife() {
		strategy.heal(this);
	}

	public void receiveDamage(double damage) {
		double auxLife = this.getRemainingLife();
		if (auxLife < damage) {
			this.setRemainingLife(0);
		} else {
			this.setRemainingLife(auxLife - damage);
		}
	}

	public boolean status() {
		return this.getRemainingLife() == 0;
	}

	public void recover() {
		this.setRemainingLife(this.getLife());
	}

	public void changeStrategy(Strategy newStrategy) {
		this.strategy = newStrategy;
	}

	public Type getType() {
		return family.getType();
	}
	
	public int maxEvol(){
		return family.familySize();
	}
	
	public String evolution(){
		return family.getEvol(evolution);
	}
	
	public boolean isStrongAgainst(Pokemon enemy){
		return this.family.isStrongAgainst(enemy);
	}
	
	public boolean isWeakAgainst(Pokemon enemy){
		return this.family.isWeakAgainst(enemy);
	}

	/***** Hash and equals ******/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/***** Getters and Setters *****/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEvolution() {
		return evolution;
	}

	public void setEvolution(int evolution) {
		this.evolution = evolution;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getRemainingLife() { 
		return remainingLife;
	}

	public void setRemainingLife(double remainingLife) {
		this.remainingLife = remainingLife;
	}

}
