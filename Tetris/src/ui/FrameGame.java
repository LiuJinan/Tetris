package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.Main;

public class FrameGame extends JFrame{
	private final int width = 520;
	private final int height = 550;
	
	public FrameGame() {
		//设置标题
		this.setTitle("Tetris");
		//设置默认关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体大小
		this.setSize(width, height);
		//不允许改变窗体大小
		this.setResizable(false);
		//设置居中显示
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize(); //获取屏幕大小
		this.setLocation((dim.width-width) / 2, (dim.height-height) / 2);
		this.setContentPane(new PanelGame());
		this.setVisible(true);
	}
}
