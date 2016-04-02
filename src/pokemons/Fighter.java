package pokemons;


public interface Fighter {
	
	public double attackTo(Pokemon enemy);
	public void increaseMyOfense();
	public void increaseMyDefense();
	public void increaseMyLife();
	public void receiveDamage(double damage);
	public boolean status();
	public void recover();
	
}
