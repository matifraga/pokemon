package frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pokemons.Character;

import java.awt.BorderLayout;

public class GameDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private OptionPanel option;
	private GardenPanel garden;
	private HomePanel home;
	private PokedexPanel poke;
	private CreationPanel creation;
	private PreArenaPanel preArena;
	private ArenaPanel arena;
	private CreditsPanel credits;
	private JPanel backGround;

	public GameDisplay() {

		super("Pokemon");
		this.setBounds(0, 0, 626, 261);

		backGround = new JPanel();
		getContentPane().add(backGround, BorderLayout.CENTER);
		backGround.setLayout(null);
		this.setResizable(false);

		option = new OptionPanel(this);
		option.setBounds(0, 0, 620, 233);
		backGround.add(option);
		
		credits = new CreditsPanel(this);
		credits.setVisible(false);
		credits.setBounds(0, 0, 620, 233);
		backGround.add(credits);
	}

	public void inicialize() {
		garden = new GardenPanel(this);
		home = new HomePanel(this);
		poke = new PokedexPanel(this);
		creation = new CreationPanel(this);
		preArena = new PreArenaPanel(this);
		arena = new ArenaPanel(this);

		garden.setVisible(false);
		home.setVisible(false);
		poke.setVisible(false);
		creation.setVisible(false);
		preArena.setVisible(false);
		arena.setVisible(false);

		garden.setBounds(0, 0, 620, 233);
		home.setBounds(0, 0, 620, 233);
		creation.setBounds(0, 0, 620, 233);
		preArena.setBounds(0, 0, 620, 233);
		arena.setBounds(0, 0, 620, 233);

		backGround.add(garden);
		backGround.add(home);
		backGround.add(poke);
		backGround.add(creation);
		backGround.add(preArena);
		backGround.add(arena);
	}
	

	/**** Se ocultan todos los paneles ****/
	private void switchOff() {
		option.setVisible(false);
		garden.setVisible(false);
		home.setVisible(false);
		poke.setVisible(false);
		creation.setVisible(false);
		preArena.setVisible(false);
		arena.setVisible(false);
		credits.setVisible(false);
	}
	

	/**** Metodos para mostrar un panel especifico ****/
	public void switchOnOption() {
		this.switchOff();
		option.setVisible(true);
	}

	public void switchOnGarden() {
		this.switchOff();
		this.setBounds(0, 0, 626, 261);
		garden.setVisible(true);
	}

	public void switchOnHome() {
		this.switchOff();
		home.setVisible(true);
	}

	public void switchOnPoke() {
		this.switchOff();
		poke.setVisible(true);
		poke.setScreet();
	}

	public void switchOnCreation() {
		this.switchOff();
		creation.setVisible(true);
	}

	public void switchOnPreArena() {
		this.switchOff();
		preArena.setVisible(true);
	}

	public void switchOnArena(Character pokemon) {
		this.switchOff();
		arena.setVisible(true);
		this.setBounds(0, 0, 1026, 261);
		arena.setBounds(0, 0, 1026, 261);
		arena.setFighter(pokemon);
		arena.loadFight();
	}

	public void switchOnCredits() {
		option.setVisible(false);
		credits.setVisible(true);
	}
	
	public void endCredits(){
		credits.setVisible(false);
		option.setVisible(true);
	}

	public void refreshComboBox(Character pokemon) {
		poke.addPokemon(pokemon);
	}

	public void refreshArena() {
		preArena.clearBox();
		preArena.completeBox();
	}

}
