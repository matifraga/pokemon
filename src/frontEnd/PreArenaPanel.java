package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import dataCollections.Pokedex;
import pokemons.Character;

public class PreArenaPanel extends BackImage {

	private static final long serialVersionUID = 1L;
	private BackImage picture;
	private GameDisplay display;
	private JComboBox<String> optionalPokemon;
	private Pokedex myPokedex;
	private JLabel warning;

	public PreArenaPanel(final GameDisplay display) {

		super("blanco");
		this.display = display;
		myPokedex = Pokedex.getInstance();
		this.setBounds(0, 0, 626, 261);
		

		/**** Botones para ir a ArenaPanel o a GardenPanel ****/
		JButton fight = new JButton("A luchar!");
		JButton back = new JButton("Volver");

		fight.setBounds(517, 191, 99, 23);
		back.setBounds(13, 191, 89, 23);
		setLayout(null);
		add(fight);
		add(back);
		

		/**** Panel para la visualizacion de la imagen del Pokemon ****/
		picture = new BackImage("pokebola");
		picture.setBounds(13, 27, 146, 153);
		add(picture);
		

		/**** Se carga el JComboBox ****/
		optionalPokemon = new JComboBox<String>();
		completeBox();
		optionalPokemon.setBounds(192, 160, 192, 20);
		add(optionalPokemon);
		
		warning = new JLabel("");
		warning.setBounds(494, 166, 122, 14);
		add(warning);
		

		/**** Evento click en el JComboBox para cambiar el item ****/
		optionalPokemon.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String seleccionado = (String) optionalPokemon
						.getSelectedItem();
				Character found = myPokedex.foundPokemon(seleccionado);
				String imagen = myPokedex.associatedImage(found);
				picture.changeBack(imagen);
				refreshScreen();
			}
		});
		

		/**** Evento click en A LUCHAR! ****/
		fight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (optionalPokemon.getItemCount()==0) {
					warning.setText("No posee Pokemons");
					return;
				}
				String seleccionado = (String) optionalPokemon
						.getSelectedItem();
				Character found = myPokedex.foundPokemon(seleccionado);
				display.switchOnArena(found);
			}
		});

		
		/**** Vuelvo al gardenPanel ****/
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.switchOnGarden();
				warning.setText("");
				picture.changeBack("pokebola");
			}
		});

	}

	public void addPokemon(Character pokemon) {
		optionalPokemon.addItem(pokemon.toString());
	}

	private void refreshScreen() {
		display.switchOnPreArena();
	}

	public void completeBox() {
		for (Character each : myPokedex.pokedexSet()) {
			this.addPokemon(each);
		}
		inicialize();
	}

	public void clearBox() {
		optionalPokemon.removeAllItems();
	}
	
	private void inicialize(){
		if(optionalPokemon.getItemCount()!=0){
			String seleccionado = (String) optionalPokemon.getItemAt(0);
			Character found = myPokedex.foundPokemon(seleccionado);
			String imagen = myPokedex.associatedImage(found);
			picture.changeBack(imagen);
		}
	}
}
