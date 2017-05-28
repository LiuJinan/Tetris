package rect;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * 该类用来描述小方块
 * 
 * @author 啊角
 *
 */
public class Rect {
	/**
	 * 小方块的横坐标
	 */
	private int x;
	/**
	 * 小方块的纵坐标
	 */
	private int y;
	/**
	 * 小方块的颜色横坐标，坐标取决于rect.png 图片
	 */
	private int colorX;
	/**
	 * 小方块的颜色纵坐标，坐标取决于rect.png 图片
	 */
	private int colorY;
	/**
	 * 表示当前方块的状态
	 */
	private int type;
	/**
	 * 方块图片
	 */
	private final Image IMG_R = new ImageIcon("image/game/rect.png").getImage();
	/**
	 * TYPE_DOWN：表示用来随机选择正在下落的方块颜色及当前方块为下落状态
	 */
	public static final int TYPE_DOWN = 0;
	/**
	 * TYPE_MELT:表示用来选择将要消行的方块颜色及当前方块为消行状态,该颜色统一
	 */
	public static final int	TYPE_MELT = 1;
	/**
	 * TYPE_DONE:表示选择已经完成下落的方块颜色及当前方块为已完成下落状态，该颜色统一
	 */
	public static final int TYPE_DONE = 2;
	/**
	 * TYPE_NULL:表示当前方格未被使用状态
	 */
	public static final int TYPE_NULL = -1;
	/**
	 * RECT_SIZE:小方格的大小
	 */
	public static final int RECT_SIZE = 25;
	
	
	public Rect(){
		
	}
	public Rect(int x, int y, int type){
		this.x = x;
		this.y = y;
		this.type  = type;
	}
	
	/**
	 * 功能：小方块选择颜色
	 * 		type = 0   正在下落的方块选择颜色，该颜色随机选择
	 * 		type = 1  将要消行的方块选择颜色，该颜色统一
	 * 		type = 2  已经下落的方块选择颜色，该颜色统一
	 * @param type
	 */
	public int selectColor(int type){
		int x = 0;
		//选择用来消行的方块颜色
		if(type==Rect.TYPE_MELT){
			x = 200;
			this.setColorX(x);
			this.setColorY(0);
		}
		//选择已下落方块颜色
		else if(type==Rect.TYPE_DONE){
			x = 175;
			this.setColorX(x);
			this.setColorY(0);
		}
		//随机选择正在下落的方块颜色
		else if(type==Rect.TYPE_DOWN){
			x = new Random().nextInt(7) + 1;
			x *= 25;
			this.setColorX(x);
			this.setColorY(0);
		}
		
		return x;
	}
	
	/**
	 * 绘制小方格
	 * @param g
	 */
	public void drawRect(Graphics g){
		g.drawImage(IMG_R, this.getX(), this.getY(), this.getX()+Rect.RECT_SIZE, this.getY()+Rect.RECT_SIZE, 
				this.getColorX(), this.getColorY(), this.getColorX()+Rect.RECT_SIZE, this.getColorY()+Rect.RECT_SIZE, null);
	}
	/**
	 * 根据type绘制小方格
	 * @param g
	 * @param type
	 */
	public void drawRect(Graphics g, int type){
		this.selectColor(type);
		g.drawImage(IMG_R, this.getX(), this.getY(), this.getX()+Rect.RECT_SIZE, this.getY()+Rect.RECT_SIZE, 
				this.getColorX(), this.getColorY(), this.getColorX()+Rect.RECT_SIZE, this.getColorY()+Rect.RECT_SIZE, null);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getColorX() {
		return colorX;
	}
	public void setColorX(int colorX) {
		this.colorX = colorX;
	}
	public int getColorY() {
		return colorY;
	}
	public void setColorY(int colorY) {
		this.colorY = colorY;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type = type;
	}
}
