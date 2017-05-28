package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.Main;

public class FrameGame extends JFrame{
	private final int width = 520;
	private final int height = 550;
	
	public FrameGame() {
		//���ñ���
		this.setTitle("Tetris");
		//����Ĭ�Ϲر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô����С
		this.setSize(width, height);
		//������ı䴰���С
		this.setResizable(false);
		//���þ�����ʾ
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize(); //��ȡ��Ļ��С
		this.setLocation((dim.width-width) / 2, (dim.height-height) / 2);
		this.setContentPane(new PanelGame());
		this.setVisible(true);
	}
}
