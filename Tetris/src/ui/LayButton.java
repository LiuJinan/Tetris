package ui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.awt.geom.AreaOp.AddOp;

public class LayButton extends LayWindow {
	public static boolean start = false;
	private final Icon IMG_PLAY = new ImageIcon("image/string/play.png");
	private final Icon IMG_PAUSE = new ImageIcon("image/string/pause.png");
	
	public LayButton(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.drawWindow(g);
	}
}
