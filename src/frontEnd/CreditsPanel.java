package frontEnd;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class CreditsPanel extends BackImage {

	private static final long serialVersionUID = 1L;

	public CreditsPanel(final GameDisplay display) {
		super("blanco");
		
		this.setLayout(null);
		this.setBounds(0, 0, 626, 261);
		
		JButton back = new JButton("Volver");
		back.setBounds(24, 199, 89, 23);
		add(back);
		
		JButton exit = new JButton("Salir");
		exit.setBounds(527, 199, 89, 23);
		add(exit);
		
		JLabel Creators = new JLabel("Creadores:      Fraga, Mat\u00EDas    -    Casta\u00F1o, Nicol\u00E1s");
		Creators.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Creators.setBounds(109, 0, 416, 39);
		add(Creators);
		
		
		/**** Evento click en Salir ****/
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		/**** Evento click del Volver ****/
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.endCredits();
			}
		});
	}
}
