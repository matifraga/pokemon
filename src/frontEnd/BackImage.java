package frontEnd;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private String path;
	private static final String packaging="/images/";
	private static final String extension=".png";

	public BackImage(String path) {
		this.setSize(400, 280);
		this.path = packaging + path + extension;
	}

	@Override
	public void paintComponent(Graphics g) {
		Dimension size = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource(path));
		g.drawImage(imagenFondo.getImage(), 0, 0, size.width, size.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	public void changeBack(String path) {
		this.path = packaging + path + extension;
		return;
	}
}
