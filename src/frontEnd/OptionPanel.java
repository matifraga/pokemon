package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import dataCollections.Pokedex;

public class OptionPanel extends BackImage {
	private static final long serialVersionUID = 1L;

	public OptionPanel(final GameDisplay display) {

		super("BackStartMenu");

		
		/**** Se agregan los botones al JPanel ****/
		JButton play = new JButton("Jugar");
		JButton load = new JButton("Cargar Partida");
		JButton credits = new JButton("Creditos");
		JButton exit = new JButton("Salir");

		this.setLayout(null);
		this.setBounds(0, 0, 626, 261);
		

		/**** Codigo generado por el GroupLayout ****/
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(play)
						.addGap(75)
						.addComponent(load)
						.addPreferredGap(ComponentPlacement.RELATED, 178,
								Short.MAX_VALUE).addComponent(credits)
						.addGap(59).addComponent(exit).addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(223, Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(play)
														.addComponent(exit)
														.addComponent(credits)
														.addComponent(load))
										.addGap(37)));
		setLayout(groupLayout);

		
		/**** Evento click en Jugar ****/
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.inicialize();
				display.switchOnGarden();
			}
		});
		

		/**** Evento click en Salir ****/
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		
		/**** Evento click en Cargar ****/
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pokedex.getInstance().loadGame();
				display.inicialize();
				display.switchOnGarden();

			}
		});
		
		
		/**** Evento click en Creditos ****/
		credits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.switchOnCredits();
			}
		});

	}
}
