package frontEnd;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import dataCollections.Pokedex;
import fights.Arena;
import pokemons.Character;
import pokemons.Pokemon;
import randoms.Randomizer;
import strategies.Defensive;
import strategies.Neutral;
import strategies.Offensive;

public class ArenaPanel extends BackImage {

	private static final long serialVersionUID = 1L;
	private BackImage picturePokemon = new BackImage("Pokebola");
	private BackImage opponent = new BackImage("Pokebola");
	private Arena arena;
	private Character fighter;
	private Pokemon enemy;

	private JLabel message;
	private JButton back;
	private JButton simpleAttack;
	private JButton defense;
	private JButton recover;
	private JButton beOffensive;

	private JLabel namePokemon;
	private JLabel levelPokemon;
	private JProgressBar lifeBar;
	private JProgressBar experienceBar;
	private JLabel lifePokemon;
	private JLabel experience;

	private JLabel nameOpponent;
	private JLabel levelOpponent;
	private JLabel lifeOpponent;
	private JProgressBar enemyBar;

	private JLabel strategy;
	private JRadioButton defensive;
	private JRadioButton offensive;
	private JRadioButton neutral;
	private ButtonGroup group;

	public ArenaPanel(final GameDisplay display) {
		super("blanco");
		this.arena = new Arena();
	
		setLayout(null);
		this.setVisible(false);
		
		/**** Se agregan los botones al JPanel ****/
		simpleAttack = new JButton("Ataque");
		defense = new JButton("Aumentar Defensa");
		recover = new JButton("Recuperarse");
		beOffensive = new JButton("Aumentar Ataque");

		simpleAttack.setBounds(642, 166, 114, 23);
		add(simpleAttack);
		defense.setBounds(766, 192, 150, 23);
		add(defense);
		recover.setBounds(642, 192, 114, 23);
		add(recover);
		beOffensive.setBounds(766, 166, 150, 23);
		add(beOffensive);

		/**** Vuelvo al gardenPanel ****/
		back = new JButton("Volver");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.switchOnGarden();
			}
		});
		back.setBounds(927, 166, 89, 23);
		add(back);
		back.setVisible(false);

		/**** Visualizacion del Pokemon elegido y todas sus propiedades ****/

		namePokemon = new JLabel("");
		namePokemon.setBounds(176, 130, 97, 14);
		add(namePokemon);

		levelPokemon = new JLabel("");
		levelPokemon.setBounds(316, 130, 46, 14);
		add(levelPokemon);

		lifeBar = new JProgressBar();
		lifeBar.setBounds(233, 155, 146, 14);
		add(lifeBar);

		experienceBar = new JProgressBar();
		experienceBar.setBounds(233, 175, 146, 14);
		add(experienceBar);

		lifePokemon = new JLabel("");
		lifePokemon.setBounds(176, 155, 46, 14);
		add(lifePokemon);

		experience = new JLabel("");
		experience.setBounds(176, 175, 46, 14);
		add(experience);

		/**** Visualizacion del oponente y todas sus propiedades ****/
		nameOpponent = new JLabel("");
		nameOpponent.setBounds(525, 0, 89, 14);
		add(nameOpponent);

		levelOpponent = new JLabel("");
		levelOpponent.setBounds(731, 0, 46, 14);
		add(levelOpponent);

		lifeOpponent = new JLabel("");
		lifeOpponent.setBounds(525, 25, 46, 14);
		add(lifeOpponent);

		enemyBar = new JProgressBar();
		enemyBar.setBounds(631, 25, 146, 14);
		add(enemyBar);

		message = new JLabel("");
		message.setBounds(10, 11, 201, 23);
		add(message);

		strategy = new JLabel("Estrategia");
		strategy.setBounds(525, 130, 60, 14);
		add(strategy);

		neutral = new JRadioButton("Normal");
		neutral.setBounds(525, 151, 109, 23);
		add(neutral);

		offensive = new JRadioButton("Ofensivo");
		offensive.setBounds(525, 177, 109, 23);
		add(offensive);

		defensive = new JRadioButton("Defensivo");
		defensive.setBounds(525, 203, 111, 23);
		add(defensive);

		group = new ButtonGroup();
		group.add(neutral);
		group.add(offensive);
		group.add(defensive);

		simpleAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStrategy();
				arena.agressive(fighter, enemy);
				arena.enemyTurn(fighter, enemy);
				refreshBattle();
				statusChanged();
			}
		});

		beOffensive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStrategy();
				arena.boostAttack(fighter);
				arena.enemyTurn(fighter, enemy);
				refreshBattle();
				statusChanged();
			}
		});

		defense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStrategy();
				arena.boostDefense(fighter);
				arena.enemyTurn(fighter, enemy);
				refreshBattle();
				statusChanged();
			}
		});

		recover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStrategy();
				arena.lifeRecovered(fighter);
				arena.enemyTurn(fighter, enemy);
				refreshBattle();
				statusChanged();
			}
		});

	}

	public void setFighter(Character myPokemon) {
		this.fighter = myPokemon;
	}

	public void loadFight() {
		arena.inicialStatus(fighter);
		enemy = arena.createOpponent(fighter);
		enemy.setEvolution(Randomizer.getInstance().nextInt(3));
		enemy.changeStrategy(Neutral.getInstance());
		fighter.changeStrategy(Neutral.getInstance());
		neutral.setSelected(true);
		initializeScreen();
		firstAttack();
		refreshBattle();
	}

	private void victory() {
		this.message.setText("Experiencia obtenida por : "
				+ enemy.getExperience());
		this.back.setVisible(true);
	}

	private void defeat() {
		this.message.setText(fighter.getName()
				+ " fue llevado para recuperarse");
		this.back.setVisible(true);
	}

	private void refreshBattle() {
		this.lifeBar.setValue((int) fighter.getRemainingLife());
		this.enemyBar.setValue((int) enemy.getRemainingLife());

		Integer aux = lifeBar.getValue();
		this.lifePokemon.setText("hp: "+aux.toString());
		aux = enemyBar.getValue();
		this.lifeOpponent.setText("hp: "+aux.toString());
	}

	private void initializeScreen() {

		this.namePokemon.setText(fighter.getName());

		Integer aux = fighter.getLevel();
		this.levelPokemon.setText("lvl: "+aux.toString());

		aux = lifeBar.getValue();
		this.lifePokemon.setText("hp: "+aux.toString());

		aux = fighter.getExperience();
		this.experience.setText("exp: "+aux.toString());

		nameOpponent.setText(enemy.getName());

		aux = enemy.getLevel();
		this.levelOpponent.setText("lvl: "+aux.toString());

		aux = enemyBar.getValue();
		this.lifeOpponent.setText("hp: "+aux.toString());

		this.lifeBar.setMaximum((int) fighter.getLife());
		this.enemyBar.setMaximum((int) enemy.getLife());
		this.experienceBar.setValue(fighter.getExperience());

		simpleAttack.setVisible(true);
		beOffensive.setVisible(true);
		defense.setVisible(true);
		recover.setVisible(true);
		back.setVisible(false);
		message.setText("");

		picturePokemon.changeBack(Pokedex.getInstance()
				.associatedImage(fighter));
		picturePokemon.setBounds(34, 130, 97, 96);
		opponent.changeBack(enemy.evolution());
		opponent.setBounds(800, 0, 99, 91);
		add(picturePokemon);
		add(opponent);
	}

	private void statusChanged() {
		if (arena.battleEnded(fighter, enemy)) {
			simpleAttack.setVisible(false);
			defense.setVisible(false);
			recover.setVisible(false);
			beOffensive.setVisible(false);
			back.setVisible(true);
			fighter.recover();
			arena.originalStatus(fighter);
			defeat();
		}
		if (enemy.getRemainingLife() == 0) { //esta logica deberia cambiar en el patron state
			fighter.catchExperience(enemy);
			victory();
		}
	}

	private void firstAttack() {
		if (arena.turn(fighter, enemy) == 1) {
			arena.enemyTurn(fighter, enemy);
		}
	}

	private void updateStrategy() {
		if (neutral.isSelected()) {
			fighter.changeStrategy(Neutral.getInstance());
		}
		if (defensive.isSelected()) {
			fighter.changeStrategy(Defensive.getInstance());
		}
		if (offensive.isSelected()) {
			fighter.changeStrategy(Offensive.getInstance());
		}
	}
}
