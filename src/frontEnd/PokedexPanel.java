package frontEnd;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import dataCollections.Pokedex;
import pokemons.Character;
import pokemons.Pokemon;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PokedexPanel extends BackImage {

	private static final long serialVersionUID = 1L;
	private final GameDisplay display;
	private JComboBox<String> optionalPokemon;
	private Pokedex myPokedex;
	private BackImage picture;
	private JLabel getName;
	private JLabel getType;
	private JLabel getExperience;
	private JLabel getLevel;
	private JLabel getAttack;
	private JLabel getDefense;
	private JLabel getSpeed;

	public PokedexPanel(final GameDisplay display) {

		super("blanco");
		this.display = display;
		myPokedex = Pokedex.getInstance();
		

		this.setBounds(0, 0, 626, 261);
		this.setLayout(null);

		picture = new BackImage("pokebola");
		picture.setBounds(13, 27, 146, 153);
		add(picture);

		
		/**** Se agregan los botones al JPanel ****/
		JButton back = new JButton("Volver");
		JButton create = new JButton("Crea tu Pokemon");
		back.setBounds(13, 196, 82, 23);
		create.setBounds(470, 196, 146, 23);
		add(back);
		add(create);

		
		/**** Se carga el JComboBox ****/
		optionalPokemon = new JComboBox<String>();
		completeBox();
		optionalPokemon.setBounds(196, 181, 192, 20);
		add(optionalPokemon);

		
		/**** JLabels descriptivos ****/
		JLabel name = new JLabel("Nombre");
		name.setBounds(196, 11, 46, 14);
		add(name);

		JLabel type = new JLabel("Tipo");
		type.setBounds(196, 36, 59, 14);
		add(type);

		JLabel experience = new JLabel("Experiencia");
		experience.setBounds(196, 61, 71, 14);
		add(experience);

		JLabel level = new JLabel("Nivel");
		level.setBounds(196, 86, 46, 14);
		add(level);

		JLabel attack = new JLabel("Ataque");
		attack.setBounds(196, 107, 59, 14);
		add(attack);

		JLabel defense = new JLabel("Defensa");
		defense.setBounds(196, 132, 59, 14);
		add(defense);

		JLabel speed = new JLabel("Velocidad");
		speed.setBounds(196, 156, 59, 14);
		add(speed);

		
		/**** Labels que reciben los datos ****/
		getName = new JLabel("");
		getName.setBounds(507, 11, 71, 14);
		add(getName);

		getType = new JLabel("");
		getType.setBounds(507, 36, 71, 14);
		add(getType);

		getExperience = new JLabel("");
		getExperience.setBounds(507, 61, 46, 14);
		add(getExperience);

		getLevel = new JLabel("");
		getLevel.setBounds(507, 86, 46, 14);
		add(getLevel);

		getAttack = new JLabel("");
		getAttack.setBounds(507, 107, 46, 14);
		add(getAttack);

		getDefense = new JLabel("");
		getDefense.setBounds(507, 132, 46, 14);
		add(getDefense);

		getSpeed = new JLabel("");
		getSpeed.setBounds(507, 156, 46, 14);
		add(getSpeed);

		
		/**** Evento click en el JComboBox para cambiar el item ****/
		optionalPokemon.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String seleccionado = (String) optionalPokemon.getSelectedItem();
				Character found = myPokedex.foundPokemon(seleccionado);
				refreshData(found);
				refreshScreen();
			}
		});

		
		/**** Evento click del boton de creacion ****/
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				display.switchOnCreation();

			}
		});

		
		/**** Vuelvo al gardenPanel ****/
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				display.switchOnGarden();
			}
		});

	}

	private void refreshScreen() {
		display.switchOnPoke();
	}

	public void addPokemon(Pokemon pokemon) {
		optionalPokemon.addItem(pokemon.toString());
	}

	public void completeBox() {
		for (Character each : myPokedex.pokedexSet()) {
			this.addPokemon(each);
		}
	}

	private void clearForm() {
		getName.setText("");
		getType.setText("");
		getLevel.setText("");
		getAttack.setText("");
		getDefense.setText("");
		getSpeed.setText("");
		getExperience.setText("");
		picture.changeBack("pokebola");
		if (optionalPokemon.getItemCount() != 0) {
			optionalPokemon.setSelectedIndex(0);
		}

	}
	
	private void refreshData(Character found){			
		String imagen = myPokedex.associatedImage(found);
		Integer level;
		Integer attack;
		Integer defense;
		Integer speed;
		Integer experience;
	
		level = found.getLevel();
		attack = found.getAttack();
		defense = found.getDefense();
		speed = found.getSpeed();
		experience = found.getExperience();
	
		getName.setText(found.getName());
		getType.setText(found.getType().getType());
		getLevel.setText(level.toString());
		getAttack.setText(attack.toString());
		getDefense.setText(defense.toString());
		getSpeed.setText(speed.toString());
		getExperience.setText(experience.toString());
		picture.changeBack(imagen);
	}
	
	public void setScreet(){
		if(optionalPokemon.getItemCount()!=0 && optionalPokemon.getSelectedIndex()==0){
			String seleccionado = (String) optionalPokemon.getItemAt(0);
			Character found = myPokedex.foundPokemon(seleccionado);
			refreshData(found);
		}
	}
}
