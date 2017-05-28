package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayBackground extends LayWindow {
	private final Image IMG_BG = new ImageIcon("image/background/bg01.png").getImage();
	private final Image IMG_TITLE = new ImageIcon("image/string/title.png").getImage();
	
	public LayBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		g.drawImage(IMG_BG, 0, 0, null);
		g.drawImage(IMG_TITLE, 50, 0, null);
	}

}
