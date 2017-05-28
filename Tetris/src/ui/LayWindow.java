package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author ����
 * 
 * �����������ƴ���
 * 
 *
 */
public abstract class LayWindow {
	//���ڱ߿�����
	private static final  int SIZE = 7;	
	//��ȡ�߿�ͼ��
	private static final Image  WINDOW_IMG = new ImageIcon(
			"image/windows/Window.png").getImage();
	private static final int WINDOW_W = WINDOW_IMG.getWidth(null);
	private static final int WINDOW_H = WINDOW_IMG.getHeight(null);
	
	//�������Ͻ����꣬�����߿�����
	protected int x, y;
	//���ڿ�ȣ��߶�
	protected int w, h;
	
	public LayWindow(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**
	 * ���ƴ���
	 */
	protected void drawWindow(Graphics g){
		//���Ͻ�
		g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x+SIZE, y, x+w+SIZE, y+SIZE, SIZE, 0, WINDOW_W-SIZE, SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x+w+SIZE, y, x+w+2*SIZE, y+SIZE, WINDOW_W-SIZE, 0, WINDOW_W, SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h+SIZE, 0, SIZE, SIZE, WINDOW_H-SIZE, null);
		//��
		g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w+SIZE, y+h+SIZE, SIZE, SIZE, WINDOW_W-SIZE, WINDOW_H-SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x+w+SIZE, y+SIZE, x+w+2*SIZE, y+h+SIZE, WINDOW_W-SIZE, SIZE, WINDOW_W, WINDOW_H-SIZE, null);
		//����
		g.drawImage(WINDOW_IMG, x, y+h+SIZE, x+SIZE, y+h+2*SIZE, 0, WINDOW_H-SIZE, SIZE, WINDOW_H, null);
		//����
		g.drawImage(WINDOW_IMG, x+SIZE, y+h+SIZE, x+w+SIZE, y+h+2*SIZE, SIZE, WINDOW_H-SIZE, WINDOW_W-SIZE, WINDOW_H, null);
		//����
		g.drawImage(WINDOW_IMG, x+w+SIZE, y+h+SIZE, x+w+2*SIZE, y+h+2*SIZE, WINDOW_W-SIZE, WINDOW_H-SIZE, WINDOW_W, WINDOW_H, null);
	}
	
	public abstract void paint(Graphics g);
}
