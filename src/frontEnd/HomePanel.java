package frontEnd;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import dataCollections.Pokedex;
import javax.swing.JLabel;
import java.awt.Font;

public class HomePanel extends BackImage {

	private static final long serialVersionUID = 1L;

	public HomePanel(final GameDisplay display) {
		super("blanco");
		JButton pc = new JButton("");
		pc.setIcon(new ImageIcon(HomePanel.class.getResource("/images/pc.png")));
		JButton back = new JButton("Volver");

		this.setBounds(0, 0, 620, 283);
		this.setLayout(null);

		pc.setBounds(449, 11, 161, 134);
		back.setBounds(10, 201, 122, 23);
		add(pc);
		add(back);
		
		JLabel save = new JLabel("Guardar");
		save.setFont(new Font("Tahoma", Font.PLAIN, 20));
		save.setBounds(488, 156, 85, 23);
		add(save);
		

		/**** Vuelvo al gardenPanel ****/
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.switchOnGarden();
			}
		});
		

		/**** Evento al hacer click en PC ****/
		pc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pokedex.getInstance().saveGame();
			}
		});

	}
}
