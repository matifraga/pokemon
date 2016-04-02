package frontEnd;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import dataCollections.PokemonDataBase;

public class GardenPanel extends BackImage {

	private static final long serialVersionUID = 1L;

	public GardenPanel(final GameDisplay display) {

		super("Back");
		PokemonDataBase.getInstance();
		
		/**** Se agregan los botones al JPanel ****/
		JButton exit = new JButton("Salir");
		exit.setBounds(13, 202, 79, 23);
		JButton home = new JButton("");
		home.setBounds(13, 12, 156, 151);
		JButton poke = new JButton("");
		poke.setIcon(new ImageIcon(GardenPanel.class
				.getResource("/images/Pokedex.png")));
		poke.setBounds(204, 12, 186, 151);
		JButton arena = new JButton("");
		arena.setBounds(416, 12, 200, 151);
		this.setBounds(0, 0, 626, 261);
		this.setLayout(null);
		

		/**** Asigno imagen a los botones ****/
		home.setIcon(new ImageIcon(GardenPanel.class
				.getResource("/images/Home.png")));
		arena.setIcon(new ImageIcon(GardenPanel.class
				.getResource("/images/Arena.png")));
		setLayout(null);
		add(exit);
		add(home);
		add(poke);
		add(arena);
		

		/**** Evento click en Salir ****/
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		

		/**** Evento click en Home ****/
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.switchOnHome();
			}
		});
		

		/**** Evento click en Pokedex ****/
		poke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.switchOnPoke();
			}
		});
		

		/**** Evento click en Arena ****/
		arena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.switchOnPreArena();
				display.refreshArena();
			}
		});

	}
}
