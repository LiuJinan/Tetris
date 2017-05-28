package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author 啊角
 * 
 * 该类用来绘制窗口
 * 
 *
 */
public abstract class LayWindow {
	//窗口边框像素
	private static final  int SIZE = 7;	
	//获取边框图像
	private static final Image  WINDOW_IMG = new ImageIcon(
			"image/windows/Window.png").getImage();
	private static final int WINDOW_W = WINDOW_IMG.getWidth(null);
	private static final int WINDOW_H = WINDOW_IMG.getHeight(null);
	
	//窗口左上角坐标，包含边框像素
	protected int x, y;
	//窗口宽度，高度
	protected int w, h;
	
	public LayWindow(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**
	 * 绘制窗口
	 */
	protected void drawWindow(Graphics g){
		//左上角
		g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		//中上
		g.drawImage(WINDOW_IMG, x+SIZE, y, x+w+SIZE, y+SIZE, SIZE, 0, WINDOW_W-SIZE, SIZE, null);
		//右上
		g.drawImage(WINDOW_IMG, x+w+SIZE, y, x+w+2*SIZE, y+SIZE, WINDOW_W-SIZE, 0, WINDOW_W, SIZE, null);
		//左中
		g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h+SIZE, 0, SIZE, SIZE, WINDOW_H-SIZE, null);
		//中
		g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w+SIZE, y+h+SIZE, SIZE, SIZE, WINDOW_W-SIZE, WINDOW_H-SIZE, null);
		//中右
		g.drawImage(WINDOW_IMG, x+w+SIZE, y+SIZE, x+w+2*SIZE, y+h+SIZE, WINDOW_W-SIZE, SIZE, WINDOW_W, WINDOW_H-SIZE, null);
		//左下
		g.drawImage(WINDOW_IMG, x, y+h+SIZE, x+SIZE, y+h+2*SIZE, 0, WINDOW_H-SIZE, SIZE, WINDOW_H, null);
		//中下
		g.drawImage(WINDOW_IMG, x+SIZE, y+h+SIZE, x+w+SIZE, y+h+2*SIZE, SIZE, WINDOW_H-SIZE, WINDOW_W-SIZE, WINDOW_H, null);
		//右下
		g.drawImage(WINDOW_IMG, x+w+SIZE, y+h+SIZE, x+w+2*SIZE, y+h+2*SIZE, WINDOW_W-SIZE, WINDOW_H-SIZE, WINDOW_W, WINDOW_H, null);
	}
	
	public abstract void paint(Graphics g);
}
