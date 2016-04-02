package frontEnd;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import dataCollections.Pokedex;
import dataCollections.PokemonDataBase;
import pokemons.Character;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreationPanel extends BackImage {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> imageLinks;
	private JTextField newName;
	private GameDisplay display;
	private JSpinner getAttack;
	private JSpinner getDefense;
	private JSpinner getSpeed;
	private Pokedex myPokedex;
	private BackImage image;
	private JLabel warning;
	private JLabel stats;

	public CreationPanel(final GameDisplay display) {
		super("blanco");

		this.display = display;
		myPokedex = Pokedex.getInstance();
		
		
		/**** Se agregan los botones al JPanel ****/
		JButton back = new JButton("Volver");
		back.setBounds(15, 186, 72, 23);
		JButton finish = new JButton("Finalizar");
		finish.setBounds(102, 187, 84, 23);
		image = new BackImage("blackpokeball");
		image.setBounds(13, 25, 146, 153);
		add(image);
		add(back);
		add(finish);
		
		/**** Agrego los JLabels descriptivos ****/
		JLabel name = new JLabel("Nombre");
		name.setBounds(242, 25, 53, 14);
		add(name);

		JLabel attack = new JLabel("Ataque");
		attack.setBounds(242, 58, 53, 14);
		add(attack);

		JLabel defense = new JLabel("Defensa");
		defense.setBounds(242, 83, 53, 14);
		add(defense);

		JLabel speed = new JLabel("Velocidad");
		speed.setBounds(242, 108, 64, 14);
		add(speed);

		JLabel look = new JLabel("Aspecto");
		look.setBounds(242, 164, 53, 14);
		add(look);

		
		/**** Agrego el JLabel Warning ****/
		warning = new JLabel("");
		warning.setBounds(299, 24, 171, 25);
		add(warning);
		

		/**** Agrego el panel de texto ****/
		newName = new JTextField();
		newName.setBounds(480, 29, 102, 20);
		newName.setColumns(10);
		add(newName);
		

		/**** Agrego los sppiners al JPanel ****/
		getAttack = new JSpinner();
		getAttack.setBounds(543, 55, 39, 20);
		getAttack.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		getDefense = new JSpinner();
		getDefense.setBounds(543, 81, 39, 20);
		getDefense.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		getSpeed = new JSpinner();
		getSpeed.setBounds(543, 107, 39, 20);
		getSpeed.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		add(getAttack);
		add(getDefense);
		add(getSpeed);
		

		/**** JComboBox de imagenes ****/
		imageLinks = new JComboBox<String>();
		this.loadImages();
		inicialize();
		imageLinks.setBounds(440, 164, 142, 20);
		add(imageLinks);
		

		/**** Evento click en el JComboBox para cambiar el item *****/
		imageLinks.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String seleccionado = (String) imageLinks.getSelectedItem();
				image.changeBack(seleccionado);
				refresh();
			}
		});
		setLayout(null);

		stats = new JLabel("Puntos restantes: 27");
		stats.setBounds(460, 9, 161, 20);
		add(stats);
		

		/**** Evento click del Volver ****/
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				display.switchOnPoke();
				inicialize();
			}
		});
		

		/**** Se actualizan los limites de los stats ****/

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				int auxAttack = (Integer) getAttack.getValue();
				int auxDefense = (Integer) getDefense.getValue();
				int auxSpeed = (Integer) getSpeed.getValue();
				Integer total = new Integer(30 - (Integer) getAttack.getValue()
						- (Integer) getDefense.getValue()
						- (Integer) getSpeed.getValue());
				stats.setText("Puntos restantes: " + total.toString());
				getAttack.setModel(new SpinnerNumberModel(auxAttack, 1, 30
						- auxDefense - auxSpeed, 1));
				getDefense.setModel(new SpinnerNumberModel(auxDefense, 1, 30
						- auxAttack - auxSpeed, 1));
				getSpeed.setModel(new SpinnerNumberModel(auxSpeed, 1, 30
						- auxAttack - auxDefense, 1));
			}
		});
		

		/**** Evento click en Finalizar Creacion ****/
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int auxAttack = (Integer) getAttack.getValue();
				int auxDefense = (Integer) getDefense.getValue();
				int auxSpeed = (Integer) getSpeed.getValue();
				String imageName = (String) imageLinks.getSelectedItem();

				if (newName.getText().equals("")) {
					warning.setText("Ingrese un nombre");
				} else {
					if (auxAttack+auxDefense+auxSpeed!=30) {
						warning.setText("Asigne los stats");
					}else{
						Character aux = myPokedex.createPokemon(newName.getText(),
								auxAttack, auxDefense, auxSpeed,PokemonDataBase.getInstance().getFamily(imageName));
						if (myPokedex.addPokemon(aux, imageName)) {
							display.refreshComboBox(aux);
							clearForm();
							display.switchOnPoke();
						} else {
							warning.setText("Nombre existente");
						}
					}
				}
			}
		});
	}

	private void clearForm() {
		getAttack.setValue(1);
		getDefense.setValue(1);
		getSpeed.setValue(1);
		if (imageLinks.getItemCount() != 0) {
			imageLinks.setSelectedIndex(0);
		}
		newName.setText("");
		warning.setText("");

	}

	public void refresh() {
		display.switchOnCreation();
	}

	public void addPokemon(Character pokemon) {
		imageLinks.addItem(pokemon.toString());
	}

	public void loadImages() {
		imageLinks.addItem("charmander");
		imageLinks.addItem("squirtle");
		imageLinks.addItem("bulbasaur");
		imageLinks.addItem("caterpie");
		imageLinks.addItem("growlithe");
		imageLinks.addItem("horsea");
		imageLinks.addItem("magikarp");
		imageLinks.addItem("pikachu");
		imageLinks.addItem("vulpix");
		imageLinks.addItem("starmie");
	}
	
	private void inicialize(){
		String seleccionado = (String) imageLinks.getItemAt(0);
		image.changeBack(seleccionado);
	}
}
