package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class LayGame extends LayWindow {
	
//	private final Image IMG_R = new ImageIcon("image/game/rect.png").getImage();
	public LayGame(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	
	public void paint(Graphics g){
		this.drawWindow(g);
//		g.drawImage(IMG_R, x+7, y+7, null);
	}
}
